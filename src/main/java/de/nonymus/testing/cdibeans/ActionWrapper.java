package de.nonymus.testing.cdibeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import de.nonymus.beans.BackgroundJobs;

@SessionScoped
@Named
public class ActionWrapper implements Serializable {

    /**
     * default serial
     */
    private static final long serialVersionUID = 1L;

    @EJB
    private BackgroundJobs bgJobs;

    public void createBulk() {
        bgJobs.createEntitiesBulk();
    }
    
    public void createSingles() {
        bgJobs.createEntitiesSingle();
    }
    
    public void createDirect() {
        bgJobs.createEntitiesDirect();
    }
    
    public void createBulkUselessTransaction() {
        bgJobs.createEntitiesBulkUselessTransaction();
    }
    
    public void createSinglesUselessTransaction() {
        bgJobs.createEntitiesSingleUselessTransaction();
    }
    
    public void createDirectUselessTransaction() {
        bgJobs.createEntitiesDirectUselessTransaction();
    }
    
}
