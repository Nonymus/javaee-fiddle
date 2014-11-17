package de.nonymus.testing.foobar.beans;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import javax.ejb.EJB;
import javax.ejb.Singleton;

import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class AsyncObserver {

	@EJB
	private WorkerBean workerBean;

	private Map<String, Future<String>> jobs = new HashMap<String, Future<String>>();

	public void addJob(String id, Future<String> handle) {
		jobs.put(id, handle);
		log.info("Added job id " + id);
	}
}
