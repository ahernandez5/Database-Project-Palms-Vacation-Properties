/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aurorahernandez.web.palms.beans;

import com.aurorahernandez.web.palms.entity.Address;
import com.aurorahernandez.web.palms.entity.Booking;
import com.aurorahernandez.web.palms.entity.BookingStatus;
import com.aurorahernandez.web.palms.entity.Guest;
import com.aurorahernandez.web.palms.entity.LivesAt;
import com.aurorahernandez.web.palms.entity.Property;
import com.aurorahernandez.web.palms.service.BookingFacade;
import com.aurorahernandez.web.palms.service.PropertyFacade;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Messages;

/**
 *
 * @author Aurora Hernandez
 */
@Named
@ViewScoped
public class BookingActivityBean implements Serializable {
    
    public void prepare() {
        try {
            this.property = this.propertyFacade.find(pid);
            this.booking = new Booking();
            this.guest = new Guest();         
            this.address = new Address();
            
            LivesAt la = new LivesAt();
            la.setStartDate(LocalDateTime.now());
            la.setAddress(this.address);
            la.setGuest(this.guest);
            
            this.guest.getLivesatSet().add(la);
            this.guest.getBookingSet().add(this.booking);
            this.address.getLivesatSet().add(la);  
            
            this.booking.setGid(this.guest);
            this.booking.getPropertySet().add(this.property);
            
            
            this.booking.setDailyPrice(this.property.getPrice());
            this.booking.setBookingStatus(BookingStatus.Completed);            
            this.booking.setBookingDate(LocalDateTime.now());
            this.booking.setConfirmNr(UUID.randomUUID().toString().replace("-", "").substring(0, 10));
        } catch (Exception e) {
            Messages.addError(null, "Failed looking up property for booking", e.getMessage());
            e.printStackTrace();
        }
    }            
        
    public String confirmBooking() {
        try {            
            //get latest dates selected
            this.booking.setCin(indexBean.getDateLimit().getMin());
            this.booking.setCout(indexBean.getDateLimit().getMax());
            
            Booking res = this.bookingFacade.edit(this.booking);
            return "confirmation?bid=" + res.getBid() + "&amp;faces-redirect=true";
        } catch (Exception e) {
            Messages.addError(null, "Failed to confirm booking", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }    

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }    

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }    
    
    private Integer pid;
    private Property property;
    
    private Booking booking;    
    
    private Guest guest;
    private Address address;    
    
    
    @Inject
    private IndexBean indexBean;
    
    @Inject
    transient private PropertyFacade propertyFacade;
    
    @Inject
    transient private BookingFacade bookingFacade;

}
