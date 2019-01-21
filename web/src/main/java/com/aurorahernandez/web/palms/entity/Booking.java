package com.aurorahernandez.web.palms.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Aurora Hernandez
 */
@Entity
@Table(name = "booking", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"confirm_nr"})})
@NamedQueries({
    @NamedQuery(name = "Booking.findAll", query = "SELECT b FROM Booking b")})
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bid", nullable = false)
    private Integer bid;
    @Column(name = "daily_price")
    private BigDecimal dailyPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "booking_date", nullable = false)
    private LocalDateTime bookingDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cin", nullable = false)
    private LocalDate cin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cout", nullable = false)
    private LocalDate cout;
    @Basic(optional = false)
    @NotNull
    @Column(name = "booking_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
    @Size(max = 64)
    @Column(name = "confirm_nr", length = 64)
    private String confirmNr;
    @JoinTable(name = "selects", joinColumns = {
        @JoinColumn(name = "bid", referencedColumnName = "bid", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "pid", referencedColumnName = "pid", nullable = false)})
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Property> propertySet = new LinkedHashSet<>();
    @JoinColumn(name = "gid", referencedColumnName = "gid")
    @ManyToOne(cascade = CascadeType.ALL)
    private Guest gid;
    @OneToMany(mappedBy = "bid", cascade = CascadeType.ALL)
    private Set<PropertyRating> propertyRatingSet = new LinkedHashSet<>();

    public Booking() {
    }

    public Booking(Integer bid) {
        this.bid = bid;
    }

    public Booking(Integer bid, LocalDateTime bookingDate, LocalDate cin, LocalDate cout, BookingStatus bookingStatus) {
        this.bid = bid;
        this.bookingDate = bookingDate;
        this.cin = cin;
        this.cout = cout;
        this.bookingStatus = bookingStatus;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public BigDecimal getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(BigDecimal dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalDate getCin() {
        return cin;
    }

    public void setCin(LocalDate cin) {
        this.cin = cin;
    }

    public LocalDate getCout() {
        return cout;
    }

    public void setCout(LocalDate cout) {
        this.cout = cout;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getConfirmNr() {
        return confirmNr;
    }

    public void setConfirmNr(String confirmNr) {
        this.confirmNr = confirmNr;
    }

    public Set<Property> getPropertySet() {
        return propertySet;
    }

    public void setPropertySet(Set<Property> propertySet) {
        this.propertySet = propertySet;
    }

    public Guest getGid() {
        return gid;
    }

    public void setGid(Guest gid) {
        this.gid = gid;
    }

    public Set<PropertyRating> getPropertyRatingSet() {
        return propertyRatingSet;
    }

    public void setPropertyRatingSet(Set<PropertyRating> propertyRatingSet) {
        this.propertyRatingSet = propertyRatingSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bid != null ? bid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Booking)) {
            return false;
        }
        Booking other = (Booking) object;
        if ((this.bid == null && other.bid != null) || (this.bid != null && !this.bid.equals(other.bid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aurorahernandez.web.palms.Booking[ bid=" + bid + " ]";
    }
    
}
