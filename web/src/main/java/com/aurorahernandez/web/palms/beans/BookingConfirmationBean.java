package com.aurorahernandez.web.palms.beans;

import com.aurorahernandez.web.palms.entity.Address;
import com.aurorahernandez.web.palms.entity.Booking;
import com.aurorahernandez.web.palms.entity.Guest;
import com.aurorahernandez.web.palms.entity.Property;
import com.aurorahernandez.web.palms.service.BookingFacade;
import java.io.Serializable;
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
public class BookingConfirmationBean implements Serializable {
    
    public void prepare() {
        try {
            this.booking = this.bookingFacade.find(this.bid);
            this.guest = this.booking.getGid();
            if(!this.booking.getPropertySet().isEmpty()) {
                this.property = this.booking.getPropertySet().iterator().next();
                if(!this.property.getAddressSet().isEmpty()) {
                    this.address= this.property.getAddressSet().iterator().next();
                }
            }            
        } catch (Exception e) {
            Messages.addError(null, "Failed looking up booking for confirmation", e.getMessage());
            e.printStackTrace();
        }
    }            
    
    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
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
    
    private Integer bid;
    private Booking booking;
    private Guest guest;
    private Property property;
    private Address address;
    
    @Inject
    private IndexBean indexBean;
    
    @Inject
    transient private BookingFacade bookingFacade;

}
