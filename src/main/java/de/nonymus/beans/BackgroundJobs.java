package de.nonymus.beans;

import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import lombok.extern.slf4j.Slf4j;

/**
 * Session Bean implementation class BackgroundJobs
 */
@Stateless
@Slf4j
public class BackgroundJobs {

    @EJB
    Jobs jobs;

    @Asynchronous
    public Future<Integer> createEntitiesBulk() {
        log.info("Start bulk single transaction creation");
        long start = System.currentTimeMillis();
        int count = jobs.createEntitiesBulk();
        long end = System.currentTimeMillis();
        long diff = end - start;

        log.info("Bulk creation took {} ms", diff);
        return new AsyncResult<Integer>(count);
    }

    @Asynchronous
    public Future<Integer> createEntitiesSingle() {
        log.info("Start bulk one transaction each creation");
        long start = System.currentTimeMillis();
        int count = jobs.createEntitiesSingle();
        long end = System.currentTimeMillis();
        long diff = end - start;

        log.info("Single creation took {} ms", diff);
        return new AsyncResult<Integer>(count);
    }
    
    @Asynchronous
    public Future<Integer> createEntitiesDirect() {
        log.info("Start bulk single bean call creation");
        long start = System.currentTimeMillis();
        int count = jobs.createEntitiesDirect();
        long end = System.currentTimeMillis();
        long diff = end - start;

        log.info("Direct creation took {} ms", diff);
        return new AsyncResult<Integer>(count);
    }
    
    @Asynchronous
    public Future<Integer> createEntitiesBulkUselessTransaction() {
        log.info("Start bulk single bean call creation w/ additonal transaction");
        long start = System.currentTimeMillis();
        int count = jobs.createEntitiesBulkUselessTransaction();
        long end = System.currentTimeMillis();
        long diff = end - start;

        log.info("Bulk creation w/ additonal transaction took {} ms", diff);
        return new AsyncResult<Integer>(count);
    }
    
    @Asynchronous
    public Future<Integer> createEntitiesSingleUselessTransaction() {
        log.info("Start bulk single bean call creation w/ additonal transaction");
        long start = System.currentTimeMillis();
        int count = jobs.createEntitiesSingleUselessTransaction();
        long end = System.currentTimeMillis();
        long diff = end - start;

        log.info("Single creation w/ additonal transaction took {} ms", diff);
        return new AsyncResult<Integer>(count);
    }
    
    @Asynchronous
    public Future<Integer> createEntitiesDirectUselessTransaction() {
        log.info("Start bulk single bean call creation w/ additonal transaction");
        long start = System.currentTimeMillis();
        int count = jobs.createEntitiesDirectUselessTransaction();
        long end = System.currentTimeMillis();
        long diff = end - start;

        log.info("Direct creation w/ additonal transaction took {} ms", diff);
        return new AsyncResult<Integer>(count);
    }
}
