package com.aurorahernandez.web.palms.entity;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Aurora Hernandez
 */
@Entity
@Table(name = "guest")
@NamedQueries({
    @NamedQuery(name = "Guest.findAll", query = "SELECT g FROM Guest g")})
public class Guest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gid", nullable = false)
    private Integer gid;
    @Size(max = 32)
    @Column(name = "firstname", length = 32)
    private String firstname;
    @Size(max = 32)
    @Column(name = "middlename", length = 32)
    private String middlename;
    @Size(max = 32)
    @Column(name = "lastname", length = 32)
    private String lastname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 64)
    @Column(name = "email", length = 64)
    private String email;
    @OneToMany(mappedBy = "gid", cascade = CascadeType.ALL)
    private Set<Booking> bookingSet = new LinkedHashSet<>();
    @OneToMany(mappedBy = "gid", cascade = CascadeType.ALL)
    private Set<Phone> phoneSet = new LinkedHashSet<>();
    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<LivesAt> livesatSet = new LinkedHashSet<>();
    @OneToMany(mappedBy = "gid", cascade = CascadeType.ALL)
    private Set<PropertyRating> propertyRatingSet = new LinkedHashSet<>();

    public Guest() {
    }

    public Guest(Integer gid) {
        this.gid = gid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Booking> getBookingSet() {
        return bookingSet;
    }

    public void setBookingSet(Set<Booking> bookingSet) {
        this.bookingSet = bookingSet;
    }

    public Set<Phone> getPhoneSet() {
        return phoneSet;
    }

    public void setPhoneSet(Set<Phone> phoneSet) {
        this.phoneSet = phoneSet;
    }

    public Set<LivesAt> getLivesatSet() {
        return livesatSet;
    }

    public void setLivesatSet(Set<LivesAt> livesatSet) {
        this.livesatSet = livesatSet;
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
        hash += (gid != null ? gid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Guest)) {
            return false;
        }
        Guest other = (Guest) object;
        if ((this.gid == null && other.gid != null) || (this.gid != null && !this.gid.equals(other.gid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aurorahernandez.web.palms.Guest[ gid=" + gid + " ]";
    }
    
}
