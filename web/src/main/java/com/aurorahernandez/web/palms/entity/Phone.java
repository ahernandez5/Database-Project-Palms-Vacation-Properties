package com.aurorahernandez.web.palms.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Aurora Hernandez
 */
@Entity
@Table(name = "phone")
@NamedQueries({
    @NamedQuery(name = "Phone.findAll", query = "SELECT p FROM Phone p")})
public class Phone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "phid", nullable = false)
    private Integer phid;
    @Size(max = 64)
    @Column(name = "phone_number", length = 64)
    private String phoneNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "phone_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PhoneType phoneType;
    @JoinColumn(name = "gid", referencedColumnName = "gid")
    @ManyToOne
    private Guest gid;
    @JoinColumn(name = "oid", referencedColumnName = "oid")
    @ManyToOne
    private Owner oid;

    public Phone() {
    }

    public Phone(Integer phid) {
        this.phid = phid;
    }

    public Phone(Integer phid, PhoneType phoneType) {
        this.phid = phid;
        this.phoneType = phoneType;
    }

    public Integer getPhid() {
        return phid;
    }

    public void setPhid(Integer phid) {
        this.phid = phid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }

    public Guest getGid() {
        return gid;
    }

    public void setGid(Guest gid) {
        this.gid = gid;
    }

    public Owner getOid() {
        return oid;
    }

    public void setOid(Owner oid) {
        this.oid = oid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (phid != null ? phid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Phone)) {
            return false;
        }
        Phone other = (Phone) object;
        if ((this.phid == null && other.phid != null) || (this.phid != null && !this.phid.equals(other.phid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aurorahernandez.web.palms.Phone[ phid=" + phid + " ]";
    }
    
}
