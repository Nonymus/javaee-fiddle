package de.nonymus.beans;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.RandomStringUtils;

import de.nonymus.testing.entities.KeyValue;

/**
 * Session Bean implementation class Tasks
 */
@Stateless
public class Tasks {
    
    @PersistenceContext
    private EntityManager em;
    
    public void createEntity() {
        KeyValue entry = new KeyValue();
        entry.setKey(RandomStringUtils.randomAscii(30));
        entry.setValue(RandomStringUtils.randomAscii(200));
        em.persist(entry);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void createEntityST() {
        createEntity();
    }

}
