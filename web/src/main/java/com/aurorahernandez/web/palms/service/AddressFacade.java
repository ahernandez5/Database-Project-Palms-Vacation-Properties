package com.aurorahernandez.web.palms.service;

import com.aurorahernandez.web.palms.entity.Address;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aurora Hernandez
 */
@Stateless
public class AddressFacade extends AbstractFacade<Address> {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AddressFacade() {
        super(Address.class);
    }
    
}
