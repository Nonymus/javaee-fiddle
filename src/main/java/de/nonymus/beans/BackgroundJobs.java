package de.nonymus.beans;

import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.RandomStringUtils;

import de.nonymus.testing.entities.KeyValue;

/**
 * Session Bean implementation class BackgroundJobs
 */
@Stateless
@Slf4j
public class BackgroundJobs {

    public static final int ENTITY_COUNT = 10000;

    @PersistenceContext
    private EntityManager em;

    @Resource
    SessionContext ctx;

    @Asynchronous
    public Future<Integer> createEntities() {
        int done = 0;
        long start = System.currentTimeMillis();
        log.info("Creating {} entities. Start: {} ", ENTITY_COUNT, start);
        for (int i = 0; i < ENTITY_COUNT; i++) {
            if (i % 100 == 0) {
                if (ctx.wasCancelCalled()) {
                    log.info("Job was cancelled");
                    break;
                } else {
                    log.info("Creating entity {}", i);
                }
            }
            KeyValue entry = new KeyValue();
            entry.setKey(RandomStringUtils.randomAscii(30));
            entry.setValue(RandomStringUtils.randomAscii(200));
            em.persist(entry);
            done++;
        }
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Creation took: {} ms", diff);
        return new AsyncResult<Integer>(done);
    }
}
