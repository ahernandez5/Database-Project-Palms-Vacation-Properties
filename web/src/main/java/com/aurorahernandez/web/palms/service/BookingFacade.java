package com.aurorahernandez.web.palms.service;

import com.aurorahernandez.web.palms.entity.Booking;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aurora Hernandez
 */
@Stateless
public class BookingFacade extends AbstractFacade<Booking> {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }    

    public BookingFacade() {
        super(Booking.class);
    }
    
}
