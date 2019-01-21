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
public class LivesAtPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "gid", nullable = false)
    private int gid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aid", nullable = false)
    private int aid;

    public LivesAtPK() {
    }

    public LivesAtPK(int gid, int aid) {
        this.gid = gid;
        this.aid = aid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
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
        hash += (int) gid;
        hash += (int) aid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LivesAtPK)) {
            return false;
        }
        LivesAtPK other = (LivesAtPK) object;
        if (this.gid != other.gid) {
            return false;
        }
        if (this.aid != other.aid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aurorahernandez.web.palms.LivesatPK[ gid=" + gid + ", aid=" + aid + " ]";
    }
    
}
