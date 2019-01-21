package com.aurorahernandez.web.palms.service;

import com.aurorahernandez.web.palms.entity.Address;
import com.aurorahernandez.web.palms.entity.Address_;
import com.aurorahernandez.web.palms.entity.Booking;
import com.aurorahernandez.web.palms.entity.Booking_;
import com.aurorahernandez.web.palms.entity.BookingStatus;
import com.aurorahernandez.web.palms.entity.Property;
import com.aurorahernandez.web.palms.entity.PropertyRating;
import com.aurorahernandez.web.palms.entity.Property_;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import javax.persistence.criteria.Subquery;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Aurora Hernandez
 */
@Stateless
public class PropertyFacade extends AbstractFacade<Property> {

    public enum AvailableSorting {
        price_min,
        price_max,
        distance;
    }

    public static class RangeLimit <X extends Comparable<? super X>> {
        enum Ordering {
            ASC,
            DESC;
        }

        public RangeLimit() {
        }

        public RangeLimit(X min, X max) {
            this.min = min;
            this.max = max;
        }

        //Adds expressions necessary for limit to a list of predicates
        public void applyExpression(CriteriaBuilder cb, Expression<X> expr, List<Predicate> addTo) {
            if(min != null) {
                addTo.add(cb.greaterThanOrEqualTo(expr, min));
            }
            if(max != null) {
                addTo.add(cb.lessThanOrEqualTo(expr, max));
            }
        }

        //Adds sorting on limit if specified
        public <T> void addSort(CriteriaBuilder cb, Expression<X> expr, Ordering ordering, List<Order> orders) {
            if(ordering != null) {
                Order ord = null;
                switch(ordering) {
                    case ASC:
                        ord = cb.asc(expr);
                        break;
                    case DESC:
                        ord = cb.desc(expr);
                        break;
                    default:
                        throw new RuntimeException("Nonexistant order");
                }
                orders.add(ord);
            }
        }

        //true if both are set
        public boolean both() {
            return min != null && max != null;
        }

        public X getMin() {
            return min;
        }

        public void setMin(X min) {
            this.min = min;
        }

        public X getMax() {
            return max;
        }

        public void setMax(X max) {
            this.max = max;
        }

        private X min;
        private X max;
    }

    public static class IntRangeLimit extends RangeLimit<Integer> {
        public IntRangeLimit() { super(); }
        public IntRangeLimit(Integer min, Integer max) {
            super(min, max);
        }
    }

    public static class BigDecimalRangeLimit extends RangeLimit<BigDecimal> {
        public BigDecimalRangeLimit() { super(); }
        public BigDecimalRangeLimit(BigDecimal min, BigDecimal max) {
            super(min, max);
        }
    }

    public static class DateRangeLimit extends RangeLimit<LocalDate> {
        public DateRangeLimit() { super(); }
        public DateRangeLimit(LocalDate min, LocalDate max) {
            super(min, max);
        }
    }

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    public List<Property> findAvailable(    String searchString,
                                            @NotNull(message = "date limit required") DateRangeLimit dateLimit,
                                            @NotNull(message = "size limit required") IntRangeLimit sizelimit,
                                            @NotNull(message = "room limit required") IntRangeLimit roomLimit,
                                            @NotNull(message = "price limit required") BigDecimalRangeLimit priceLimit,
                                            @NotNull(message = "sorting order required") AvailableSorting availableSorting) {
        //Build a dynamic query with java JPA
        //for dynamic sorting availbale property
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Property> q = cb.createQuery(Property.class);
        Root<Property> r = q.from(Property.class);
        q.select(r);
        q.distinct(true);

        List<Predicate> predicates = new ArrayList<>();

        Expression<Integer> expr = r.get(Property_.size);
        q.where(cb.lessThan(expr, Integer.valueOf(5)));

        if(dateLimit.both()) {
            //If checkin and chekc out is specified, then remove
            //all properties that not available in that time window
            //using a subquery
            Subquery<Booking> notBookedSubQuery = q.subquery(Booking.class);
            Root<Booking> subQueryRoot = notBookedSubQuery.from(Booking.class);
            notBookedSubQuery.select(subQueryRoot);
            List<Predicate> sqPredicates = new ArrayList<>(2);
            if(dateLimit.both()) {
                sqPredicates.add(cb.lessThanOrEqualTo(subQueryRoot.get(Booking_.cin), dateLimit.getMax()));
                sqPredicates.add(cb.greaterThanOrEqualTo(subQueryRoot.get(Booking_.cout), dateLimit.getMin()));
            }
            sqPredicates.add(subQueryRoot.get(Booking_.bookingStatus).in(BookingStatus.Completed, BookingStatus.Pending));
            SetJoin<Booking, Property> subJoinProperty = subQueryRoot.join(Booking_.propertySet);
            sqPredicates.add(cb.equal(r.get(Property_.pid), subJoinProperty.get(Property_.pid)));
            notBookedSubQuery.where(sqPredicates.toArray(new Predicate[0]));
            predicates.add(cb.not(cb.exists(notBookedSubQuery)));
        }

        //Apply range limits from search, if any
        sizelimit.applyExpression(cb, r.get(Property_.size), predicates);
        roomLimit.applyExpression(cb, r.get(Property_.rooms), predicates);
        priceLimit.applyExpression(cb, r.get(Property_.price), predicates);

        if(searchString != null && !searchString.isBlank()) {
            for(String search : searchString.split(" ")) {
                String str = "%" + search.toLowerCase() + "%";
                List<Predicate> likes = new ArrayList<>();
                SetJoin<Property, Address> joinAddr = r.join(Property_.addressSet);
                likes.add(cb.like(cb.lower(r.get(Property_.description)), str));
                likes.add(cb.like(cb.lower(joinAddr.get(Address_.street)), str));
                likes.add(cb.like(cb.lower(joinAddr.get(Address_.city)), str));
                likes.add(cb.like(cb.lower(joinAddr.get(Address_.postalCode)), str));
                likes.add(cb.like(cb.lower(joinAddr.get(Address_.country)), str));
                predicates.add(cb.or(likes.toArray(new Predicate[0])));
            }
        }

        List<Order> orderings = new ArrayList<>();
        //Apply price sorting, if any
        switch(availableSorting) {
            case price_max:
                priceLimit.addSort(cb, r.get(Property_.price), RangeLimit.Ordering.DESC, orderings);
                break;
            case price_min:
                priceLimit.addSort(cb, r.get(Property_.price), RangeLimit.Ordering.ASC, orderings);
                break;
        }

        q.orderBy(orderings);

        //apply all predicates from above, if any
        q.where(cb.and(predicates.toArray(new Predicate[0])));
        return this.em.createQuery(q).getResultList();
    }

    void createBookingSp(Integer guestId, LocalDate checkin, LocalDate checkout, List<Integer> propertyIds) {
    getEntityManager().createStoredProcedureQuery("makeBooking")
        .setParameter(0, guestId)
        .setParameter(1, java.sql.Date.valueOf(checkin))
        .setParameter(2, java.sql.Date.valueOf(checkout))
        .setParameter(3, propertyIds)
        .execute();
    }

    //Returns a list of rows mapping to booking.* and property.*
    // outer joined on selecting property by id, ordered by pid
    List<Object[]> getAllBookingWithProperties() {
        return getEntityManager().createNativeQuery("select * from booking_property_info").getResultList();
    }

    public PropertyFacade() {
        super(Property.class);
    }

}
