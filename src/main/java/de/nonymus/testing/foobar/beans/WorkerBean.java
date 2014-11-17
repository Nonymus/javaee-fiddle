package de.nonymus.testing.foobar.beans;

import java.math.BigInteger;
import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

import lombok.extern.slf4j.Slf4j;
import de.nonymus.testing.foobar.util.DemoTasks;

@Stateless
@Slf4j
public class WorkerBean {
	private static final long ROUNDS = 40000;

	@Asynchronous
	public Future<String> intensiveJob() {
		BigInteger result = DemoTasks.fac(ROUNDS);
		log.info("Done: " + result.toString().length());
		return new AsyncResult<String>(result.toString());
	}
}
