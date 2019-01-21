package com.aurorahernandez.web.palms.service;

import com.aurorahernandez.web.palms.entity.Owner;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aurora Hernandez
 */
@Stateless
public class OwnerFacade extends AbstractFacade<Owner> {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OwnerFacade() {
        super(Owner.class);
    }
    
}
