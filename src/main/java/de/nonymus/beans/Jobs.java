package de.nonymus.beans;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.RandomStringUtils;

import de.nonymus.testing.entities.KeyValue;

@Stateless
public class Jobs {

    public static final int ENTITY_COUNT = 10000;

    @EJB
    Tasks task;

    @PersistenceContext
    EntityManager em;

    @Resource
    SessionContext ctx;

    public int createEntitiesBulk() {
        int done = 0;
        for (int i = 0; i < ENTITY_COUNT; i++) {
            task.createEntity();
            done++;
        }
        return done;
    }

    public int createEntitiesSingle() {
        int done = 0;
        for (int i = 0; i < ENTITY_COUNT; i++) {
            task.createEntityST();
            done++;
        }
        return done;
    }

    public int createEntitiesDirect() {
        int done = 0;
        for (int i = 0; i < ENTITY_COUNT; i++) {
            KeyValue entry = new KeyValue();
            entry.setKey(RandomStringUtils.randomAscii(30));
            entry.setValue(RandomStringUtils.randomAscii(200));
            em.persist(entry);
            done++;
        }
        return done;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public int createEntitiesBulkUselessTransaction() {
        return createEntitiesBulk();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public int createEntitiesSingleUselessTransaction() {
        return createEntitiesSingle();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public int createEntitiesDirectUselessTransaction() {
        return createEntitiesDirect();
    }
}
