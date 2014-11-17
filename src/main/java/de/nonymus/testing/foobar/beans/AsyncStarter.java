package de.nonymus.testing.foobar.beans;

import java.io.Serializable;
import java.util.concurrent.Future;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@SessionScoped
@Slf4j
@Named
public class AsyncStarter implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int JOBS = 16;

	@Getter
	@Setter
	private boolean running = false;

	@EJB
	private AsyncObserver observer;
	
	@EJB
	private WorkerBean worker;

	public void startJobs() {
		for (int i = 0; i < JOBS; i++) {
			Future<String> job = worker.intensiveJob();
			observer.addJob("Job " + i, job);
		}
		this.setRunning(true);
		log.info("Started " + JOBS);
	}
	
	public void stopJobs() {
		observer.cancelAll();
	}
}
