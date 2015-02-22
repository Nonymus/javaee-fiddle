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
        bgJobs.createEntitiesSBulk();
    }
    
    public void createSingles() {
        bgJobs.createEntitiesSingle();
    }
    
    public void createDirect() {
        bgJobs.createEntitiesDirect();
    }
    
}
