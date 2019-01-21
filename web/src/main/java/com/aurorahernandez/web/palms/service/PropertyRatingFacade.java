package com.aurorahernandez.web.palms.service;

import com.aurorahernandez.web.palms.entity.PropertyRating;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aurora Hernandez
 */
@Stateless
public class PropertyRatingFacade extends AbstractFacade<PropertyRating> {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PropertyRatingFacade() {
        super(PropertyRating.class);
    }
    
}
