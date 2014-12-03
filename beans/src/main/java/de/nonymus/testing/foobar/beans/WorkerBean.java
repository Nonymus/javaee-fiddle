package de.nonymus.testing.foobar.beans;

import java.math.BigInteger;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import lombok.extern.slf4j.Slf4j;
import de.nonymus.testing.foobar.util.DemoTasks;

@Stateless
@Slf4j
public class WorkerBean {
	private static final long ROUNDS = 40000;
	
	@Resource
	private SessionContext ctx;

	@Asynchronous
	public Future<String> intensiveJob() {
		if (!ctx.wasCancelCalled()) {
			BigInteger result = DemoTasks.fac(ROUNDS);
			log.info("Done: " + result.toString().length());
			return new AsyncResult<String>(result.toString());
		} else {
			log.info("Not running canceld job");
			return new AsyncResult<String>("Canceld");
		}
	}
}
