package com.aurorahernandez.web.palms.service;

import com.aurorahernandez.web.palms.entity.LivesAt;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aurora Hernandez
 */
@Stateless
public class LivesatFacade extends AbstractFacade<LivesAt> {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LivesatFacade() {
        super(LivesAt.class);
    }
    
}
