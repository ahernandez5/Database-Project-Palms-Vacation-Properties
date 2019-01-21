package com.aurorahernandez.web.palms.service;

import com.aurorahernandez.web.palms.entity.Guest;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aurora Hernandez
 */
@Stateless
public class GuestFacade extends AbstractFacade<Guest> {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GuestFacade() {
        super(Guest.class);
    }
    
}
