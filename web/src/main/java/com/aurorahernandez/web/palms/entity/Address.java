package com.aurorahernandez.web.palms.entity;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "address")
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a")})
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "aid", nullable = false)
    private Integer aid;
    @Size(max = 64)
    @Column(name = "street", length = 64)
    private String street;
    @Size(max = 64)
    @Column(name = "city", length = 64)
    private String city;
    @Size(max = 16)
    @Column(name = "postal_code", length = 16)
    private String postalCode;
    @Size(max = 64)
    @Column(name = "country", length = 64)
    private String country;
    @JoinTable(name = "includes", joinColumns = {
        @JoinColumn(name = "aid", referencedColumnName = "aid", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "pid", referencedColumnName = "pid", nullable = false)})
    @ManyToMany
    private Set<Property> propertySet = new LinkedHashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
    private Set<LivesAt> livesatSet = new LinkedHashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
    private Set<OperatesAt> operatesatSet = new LinkedHashSet<>();

    public Address() {
    }

    public Address(Integer aid) {
        this.aid = aid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Property> getPropertySet() {
        return propertySet;
    }

    public void setPropertySet(Set<Property> propertySet) {
        this.propertySet = propertySet;
    }

    public Set<LivesAt> getLivesatSet() {
        return livesatSet;
    }

    public void setLivesatSet(Set<LivesAt> livesatSet) {
        this.livesatSet = livesatSet;
    }

    public Set<OperatesAt> getOperatesatSet() {
        return operatesatSet;
    }

    public void setOperatesatSet(Set<OperatesAt> operatesatSet) {
        this.operatesatSet = operatesatSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aid != null ? aid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.aid == null && other.aid != null) || (this.aid != null && !this.aid.equals(other.aid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aurorahernandez.web.palms.Address[ aid=" + aid + " ]";
    }
    
}
