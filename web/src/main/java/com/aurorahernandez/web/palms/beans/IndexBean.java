package com.aurorahernandez.web.palms.beans;

import com.aurorahernandez.web.palms.entity.Property;
import com.aurorahernandez.web.palms.service.BookingFacade;
import com.aurorahernandez.web.palms.service.PropertyFacade;
import com.aurorahernandez.web.palms.service.PropertyFacade.AvailableSorting;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Messages;

/**
 *
 * @author Aurora Hernandez
 */
@Named
@javax.faces.view.ViewScoped
public class IndexBean implements Serializable {

    public IndexBean() {
        this.dateLimit = new PropertyFacade.DateRangeLimit();
        LocalDate from = LocalDate.now().plusWeeks(1).with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        this.dateLimit.setMin(from);
        this.dateLimit.setMax(from.plusDays(2));        
        this.sizeLimit = new PropertyFacade.IntRangeLimit();
        this.roomLimit = new PropertyFacade.IntRangeLimit();
        this.priceLimit = new PropertyFacade.BigDecimalRangeLimit();
        this.sorting = AvailableSorting.price_min;
    }

    public List<Property> getResults() {
        return results;
    }

    public void prepare() {                
        try {
            if(this.sorting == null) {
                this.sorting = AvailableSorting.price_min;
            }
            this.results = this.propertyFacade.findAvailable(this.search, this.dateLimit, this.sizeLimit, this.roomLimit, this.priceLimit, this.sorting);
        } catch(Throwable e) {
            Messages.addError(null, "Failed loading search results", e.getMessage());
        }
    }    
    
    public Date getDateFromPlusOne() {
        if(this.dateLimit.getMin() != null) {
            return java.sql.Date.valueOf(this.dateLimit.getMin().plusDays(1));
        } else {
            return null;
        }
    }
    
    public Date getDateToMinusOne() {
        if(this.dateLimit.getMax() != null) {
            return java.sql.Date.valueOf(this.dateLimit.getMax().minusDays(1));
        } else {
            return null;
        }
    }
    
    public void setDateFrom(Date date) {
        if(date != null) {
            this.dateLimit.setMin(LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault()));
        } else {
            this.dateLimit.setMin(null);
        }
    }
    
    public Date getDateFrom() {
        if(this.dateLimit.getMin() != null) {
            return java.sql.Date.valueOf(this.dateLimit.getMin());
        } else {
            return null;
        }
    }
    
    
    public void setDateTo(Date date) {
        if(date != null) {
            this.dateLimit.setMax(LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault()));
        } else {
            this.dateLimit.setMax(null);
        }
    }
    
    public Date getDateTo() {
        if(this.dateLimit.getMax() != null) {
            return java.sql.Date.valueOf(this.dateLimit.getMax());
        } else {
            return null;
        }
    }    

    public PropertyFacade.IntRangeLimit getSizeLimit() {
        return sizeLimit;
    }

    public void setSizeLimit(PropertyFacade.IntRangeLimit sizeLimit) {
        this.sizeLimit = sizeLimit;
    }

    public PropertyFacade.IntRangeLimit getRoomLimit() {
        return roomLimit;
    }

    public void setRoomLimit(PropertyFacade.IntRangeLimit roomLimit) {
        this.roomLimit = roomLimit;
    }

    public PropertyFacade.BigDecimalRangeLimit getPriceLimit() {
        return priceLimit;
    }
    
    public PropertyFacade.DateRangeLimit getDateLimit() {
        return dateLimit;
    }       

    public void setPriceLimit(PropertyFacade.BigDecimalRangeLimit priceLimit) {
        this.priceLimit = priceLimit;
    }

    public AvailableSorting getSorting() {
        return sorting;
    }

    public void setSorting(AvailableSorting sorting) {
        this.sorting = sorting;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }        
    
    
    
    private List<Property> results;
        
    private String search;
    PropertyFacade.DateRangeLimit dateLimit;
    PropertyFacade.IntRangeLimit sizeLimit;
    PropertyFacade.IntRangeLimit roomLimit;
    PropertyFacade.BigDecimalRangeLimit priceLimit;
    
    AvailableSorting sorting;
    
    @Inject
    transient private PropertyFacade propertyFacade;
    @Inject
    transient private BookingFacade bookingFacade;                        

}
