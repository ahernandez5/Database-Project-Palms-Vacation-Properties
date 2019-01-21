package com.aurorahernandez.web.palms.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Aurora Hernandez
 */
@Embeddable
public class OperatesAtPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "oid", nullable = false)
    private int oid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aid", nullable = false)
    private int aid;

    public OperatesAtPK() {
    }

    public OperatesAtPK(int oid, int aid) {
        this.oid = oid;
        this.aid = aid;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) oid;
        hash += (int) aid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OperatesAtPK)) {
            return false;
        }
        OperatesAtPK other = (OperatesAtPK) object;
        if (this.oid != other.oid) {
            return false;
        }
        if (this.aid != other.aid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aurorahernandez.web.palms.OperatesatPK[ oid=" + oid + ", aid=" + aid + " ]";
    }
    
}
