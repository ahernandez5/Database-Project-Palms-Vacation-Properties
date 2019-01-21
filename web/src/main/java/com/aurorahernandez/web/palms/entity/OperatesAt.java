package com.aurorahernandez.web.palms.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Aurora Hernandez
 */
@Entity
@Table(name = "operatesat")
@NamedQueries({
    @NamedQuery(name = "Operatesat.findAll", query = "SELECT o FROM OperatesAt o")})
public class OperatesAt implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OperatesAtPK operatesatPK = new OperatesAtPK();
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDateTime start;
    @Column(name = "end_date")
    private LocalDateTime end;
    @JoinColumn(name = "aid", referencedColumnName = "aid", nullable = false)
    @MapsId("aid")
    @ManyToOne
    private Address address;    
    @JoinColumn(name = "oid", referencedColumnName = "oid", nullable = false)
    @ManyToOne
    @MapsId("oid")
    private Owner owner;

    public OperatesAt() {
    }

    public OperatesAt(OperatesAtPK operatesatPK) {
        this.operatesatPK = operatesatPK;
    }

    public OperatesAt(OperatesAtPK operatesatPK, LocalDateTime startDate) {
        this.operatesatPK = operatesatPK;
        this.start = startDate;
    }

    public OperatesAt(int oid, int aid) {
        this.operatesatPK = new OperatesAtPK(oid, aid);
    }

    public OperatesAtPK getOperatesatPK() {
        return operatesatPK;
    }

    public void setOperatesatPK(OperatesAtPK operatesatPK) {
        this.operatesatPK = operatesatPK;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (operatesatPK != null ? operatesatPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OperatesAt)) {
            return false;
        }
        OperatesAt other = (OperatesAt) object;
        if ((this.operatesatPK == null && other.operatesatPK != null) || (this.operatesatPK != null && !this.operatesatPK.equals(other.operatesatPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aurorahernandez.web.palms.Operatesat[ operatesatPK=" + operatesatPK + " ]";
    }
    
}
