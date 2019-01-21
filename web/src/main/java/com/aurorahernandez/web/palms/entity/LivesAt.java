package com.aurorahernandez.web.palms.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Aurora Hernandez
 */
@Entity
@Table(name = "livesat")
@NamedQueries({
    @NamedQuery(name = "Livesat.findAll", query = "SELECT l FROM LivesAt l")})
public class LivesAt implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LivesAtPK livesatPK = new LivesAtPK();
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @JoinColumn(name = "aid", referencedColumnName = "aid", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("aid")
    private Address address;
    @JoinColumn(name = "gid", referencedColumnName = "gid", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("gid")
    private Guest guest;

    public LivesAt() {
    }

    public LivesAt(LivesAtPK livesatPK) {
        this.livesatPK = livesatPK;
    }

    public LivesAt(LivesAtPK livesatPK, LocalDateTime startDate) {
        this.livesatPK = livesatPK;
        this.startDate = startDate;
    }

    public LivesAt(int gid, int aid) {
        this.livesatPK = new LivesAtPK(gid, aid);
    }

    public LivesAtPK getLivesatPK() {
        return livesatPK;
    }

    public void setLivesatPK(LivesAtPK livesatPK) {
        this.livesatPK = livesatPK;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (livesatPK != null ? livesatPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LivesAt)) {
            return false;
        }
        LivesAt other = (LivesAt) object;
        if ((this.livesatPK == null && other.livesatPK != null) || (this.livesatPK != null && !this.livesatPK.equals(other.livesatPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aurorahernandez.web.palms.Livesat[ livesatPK=" + livesatPK + " ]";
    }
    
}
