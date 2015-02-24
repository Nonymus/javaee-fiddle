package de.nonymus.testing.cdibeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import de.nonymus.beans.KeyValueStore;

@SessionScoped
@Named
public class ActionWrapper implements Serializable {

    /**
     * default serial
     */
    private static final long serialVersionUID = 1L;

    @EJB
    private KeyValueStore kvs;

    public void entityTest() {
        kvs.createTestET();
    }

    public void listEntity() {
        kvs.loadEntity();
    }
}
