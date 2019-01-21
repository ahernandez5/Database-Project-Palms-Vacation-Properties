/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aurorahernandez.web.palms.beans;

import com.aurorahernandez.web.palms.entity.Booking;
import com.aurorahernandez.web.palms.service.BookingFacade;
import com.aurorahernandez.web.palms.service.PropertyFacade;
import java.io.Serializable;
import java.util.List;
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
public class BookingsBean implements Serializable {
    
    public void prepare() {
        try {
            this.bookings = this.bookingFacade.findAll();
        } catch (Exception e) {
            Messages.addError(null, "Failed looking up bookings", e.getMessage());
            e.printStackTrace();
        }
    }                   

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }            
    
    private List<Booking> bookings;     
    
    @Inject
    private IndexBean indexBean;
    
    @Inject
    transient private PropertyFacade propertyFacade;
    
    @Inject
    transient private BookingFacade bookingFacade;

}
