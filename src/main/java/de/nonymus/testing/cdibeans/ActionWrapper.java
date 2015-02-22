package de.nonymus.testing.cdibeans;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import lombok.extern.slf4j.Slf4j;
import de.nonymus.beans.BackgroundJobs;

@SessionScoped
@Named
@Slf4j
public class ActionWrapper implements Serializable {

    /**
     * default serial
     */
    private static final long serialVersionUID = 1L;

    private Future<Integer> result = null;

    @EJB
    private BackgroundJobs jobs;

    public void beginCreate() {
        this.result = jobs.createEntities();
    }

    public void cancel() {
        this.result.cancel(true);
    }

    public int getCount() {
        if (result == null) {
            return -2;
        }
        try {
            if (result.isDone()) {
                return result.get().intValue();
            } else {
                return 0;
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error("Failed", e);
        }
        return -1;
    }
}
