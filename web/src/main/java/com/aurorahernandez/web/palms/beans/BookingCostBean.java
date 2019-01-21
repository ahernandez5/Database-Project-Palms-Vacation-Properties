package com.aurorahernandez.web.palms.beans;

import com.aurorahernandez.web.palms.entity.Booking;
import com.aurorahernandez.web.palms.entity.Property;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Aurora Hernandez
 */
@Named
@RequestScoped
public class BookingCostBean {
    
    public double priceForStay(Property p) {
        return getStayDuration() * p.getPrice().doubleValue();
    }
    
    public double priceForStay(Booking b) {
        if(b.getCin() != null && b.getCin() != null && b.getDailyPrice() != null) {
            return ChronoUnit.DAYS.between(b.getCin(), b.getCout()) * b.getDailyPrice().doubleValue();
        } else {
            return 0;
        }
    }

    public long getStayDuration() {
        if(duration == -1) {
            LocalDate from = null;
            LocalDate to = null;
            if(this.indexBean.getDateLimit().both()) {
                from = this.indexBean.getDateLimit().getMin();
                to = this.indexBean.getDateLimit().getMax();
            } else if(this.bookingConfirmationBean.getBooking() != null) {
                from = this.bookingConfirmationBean.getBooking().getCin();
                to = this.bookingConfirmationBean.getBooking().getCout();
            } else {
                duration = 0;
            }            
            if(from != null && to != null) {
                duration =  ChronoUnit.DAYS.between(from, to);               
            }
        }
        return duration;
    }

    private long duration = -1; //uninitialized

    @Inject
    private BookingConfirmationBean bookingConfirmationBean;    
    @Inject
    private IndexBean indexBean;    
}
