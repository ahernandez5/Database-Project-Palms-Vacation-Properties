package com.aurorahernandez.web.palms.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Aurora Hernandez
 */
@Entity
@Table(name = "property")
@NamedQueries({
    @NamedQuery(name = "Property.findAll", query = "SELECT p FROM Property p")})
public class Property implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pid", nullable = false)
    private Integer pid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5120)
    @Column(name = "description", nullable = false, length = 5120)
    private String description;
    @Column(name = "rooms")
    private Integer rooms;
    @Column(name = "size")
    private Integer size;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price", precision = 15, scale = 2)
    private BigDecimal price;
    @ManyToMany(mappedBy = "propertySet", cascade = CascadeType.ALL)
    private Set<Booking> bookingSet;
    @ManyToMany(mappedBy = "propertySet", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Address> addressSet;
    @OneToMany(mappedBy = "pid", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PropertyRating> propertyRatingSet;
    @ElementCollection
    @CollectionTable(name = "amenity", joinColumns = @JoinColumn(name = "pid"))
    @Column(name = "name")
    private Set<String> amenities;

    @ElementCollection
    @CollectionTable(name = "attraction", joinColumns = @JoinColumn(name = "pid"))
    @Column(name = "name")
    private Set<String> attractions;

    @ElementCollection
    @CollectionTable(name = "view", joinColumns = @JoinColumn(name = "pid"))
    @Column(name = "name")
    private Set<String> views;

    public Property() {
    }

    public Property(Integer pid) {
        this.pid = pid;
    }

    public Property(Integer pid, String description) {
        this.pid = pid;
        this.description = description;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Booking> getBookingSet() {
        return bookingSet;
    }

    public void setBookingSet(Set<Booking> bookingSet) {
        this.bookingSet = bookingSet;
    }

    public Set<Address> getAddressSet() {
        return addressSet;
    }

    public void setAddressSet(Set<Address> addressSet) {
        this.addressSet = addressSet;
    }

    public Set<PropertyRating> getPropertyRatingSet() {
        return propertyRatingSet;
    }

    public void setPropertyRatingSet(Set<PropertyRating> propertyRatingSet) {
        this.propertyRatingSet = propertyRatingSet;
    }

    public Set<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(Set<String> amenities) {
        this.amenities = amenities;
    }

    public Set<String> getAttractions() {
        return attractions;
    }

    public void setAttractions(Set<String> attractions) {
        this.attractions = attractions;
    }

    public Set<String> getViews() {
        return views;
    }

    public void setViews(Set<String> views) {
        this.views = views;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pid != null ? pid.hashCode() : 0);
        return hash;
    }
    
    //Calculate average rating
    public Double getAverageRating() {
        return this.propertyRatingSet.stream().collect(Collectors.averagingDouble(PropertyRating::getRating));
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Property)) {
            return false;
        }
        Property other = (Property) object;
        if ((this.pid == null && other.pid != null) || (this.pid != null && !this.pid.equals(other.pid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aurorahernandez.web.palms.Property[ pid=" + pid + " ]";
    }

}
