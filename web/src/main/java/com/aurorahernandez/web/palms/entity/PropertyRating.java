package com.aurorahernandez.web.palms.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Aurora Hernandez
 */
@Entity
@Table(name = "property_rating")
@NamedQueries({
    @NamedQuery(name = "PropertyRating.findAll", query = "SELECT p FROM PropertyRating p")})
public class PropertyRating implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prid", nullable = false)
    private Integer prid;
    @Column(name = "rating")
    @Min(value = 0, message = "Lowest rating is 0")
    @Max(value = 5, message = "Highest rating is 5")
    private Integer rating;
    @Size(max = 1024)
    @Column(name = "review", length = 1024)
    private String review;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rating_date", nullable = false)
    private LocalDateTime ratingDate;
    @JoinColumn(name = "bid", referencedColumnName = "bid")
    @ManyToOne
    private Booking bid;
    @JoinColumn(name = "gid", referencedColumnName = "gid")
    @ManyToOne
    private Guest gid;
    @JoinColumn(name = "pid", referencedColumnName = "pid")
    @ManyToOne
    private Property pid;

    public PropertyRating() {
    }

    public PropertyRating(Integer prid) {
        this.prid = prid;
    }

    public PropertyRating(Integer prid, LocalDateTime ratingDate) {
        this.prid = prid;
        this.ratingDate = ratingDate;
    }

    public Integer getPrid() {
        return prid;
    }

    public void setPrid(Integer prid) {
        this.prid = prid;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public LocalDateTime getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(LocalDateTime ratingDate) {
        this.ratingDate = ratingDate;
    }

    public Booking getBid() {
        return bid;
    }

    public void setBid(Booking bid) {
        this.bid = bid;
    }

    public Guest getGid() {
        return gid;
    }

    public void setGid(Guest gid) {
        this.gid = gid;
    }

    public Property getPid() {
        return pid;
    }

    public void setPid(Property pid) {
        this.pid = pid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prid != null ? prid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PropertyRating)) {
            return false;
        }
        PropertyRating other = (PropertyRating) object;
        if ((this.prid == null && other.prid != null) || (this.prid != null && !this.prid.equals(other.prid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aurorahernandez.web.palms.PropertyRating[ prid=" + prid + " ]";
    }
    
}
