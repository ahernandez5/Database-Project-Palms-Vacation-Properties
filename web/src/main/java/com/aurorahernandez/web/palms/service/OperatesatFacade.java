package com.aurorahernandez.web.palms.service;

import com.aurorahernandez.web.palms.entity.OperatesAt;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aurora Hernandez
 */
@Stateless
public class OperatesatFacade extends AbstractFacade<OperatesAt> {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OperatesatFacade() {
        super(OperatesAt.class);
    }
    
}
