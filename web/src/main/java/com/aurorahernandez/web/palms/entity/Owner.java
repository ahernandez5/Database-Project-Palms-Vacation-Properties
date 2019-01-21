package com.aurorahernandez.web.palms.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Aurora Hernandez
 */
@Entity
@Table(name = "owner")
@NamedQueries({
    @NamedQuery(name = "Owner.findAll", query = "SELECT o FROM Owner o")})
public class Owner implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "oid", nullable = false)
    private Integer oid;
    @Size(max = 32)
    @Column(name = "firstname", length = 32)
    private String firstname;
    @Size(max = 32)
    @Column(name = "middlename", length = 32)
    private String middlename;
    @Size(max = 32)
    @Column(name = "lastname", length = 32)
    private String lastname;
    @Email
    @Size(max = 64)
    @Column(name = "email", length = 64)
    private String email;
    @OneToMany(mappedBy = "oid")
    private Set<Phone> phoneSet;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<OperatesAt> operatesatSet;

    public Owner() {
    }

    public Owner(Integer oid) {
        this.oid = oid;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
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

    public Set<Phone> getPhoneSet() {
        return phoneSet;
    }

    public void setPhoneSet(Set<Phone> phoneSet) {
        this.phoneSet = phoneSet;
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
        hash += (oid != null ? oid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Owner)) {
            return false;
        }
        Owner other = (Owner) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aurorahernandez.web.palms.Owner[ oid=" + oid + " ]";
    }
    
}
