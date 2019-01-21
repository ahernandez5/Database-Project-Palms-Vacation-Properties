package com.aurorahernandez.web.palms.service;

import com.aurorahernandez.web.palms.entity.Phone;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aurora Hernandez
 */
@Stateless
public class PhoneFacade extends AbstractFacade<Phone> {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PhoneFacade() {
        super(Phone.class);
    }
    
}
