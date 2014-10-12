package de.nonymus.testing.foobar.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import lombok.Getter;
import lombok.Setter;
import de.nonymus.testing.foobar.model.Device;
import de.nonymus.testing.foobar.model.Variable;
import de.nonymus.testing.foobar.model.VariableValue;

@SessionScoped
@Named
public class Devices implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Device device;
    
    @Getter
    @Setter
    private Device selectedDevice;
    
    @Getter
    @Setter
    private List<Device> allDevices;

    @Getter
    @Setter
    private Variable varible;

    @Getter
    @Setter
    private String varText;

    @PersistenceContext
    EntityManager em;

    @PostConstruct
    private void init() {
        this.device = new Device();
        TypedQuery<Device> q = em.createNamedQuery("device.findAll", Device.class);
        this.allDevices = q.getResultList();
    }

    public void newDevice() {
        this.device = new Device();
    }

    @Transactional
    public void save() {
        em.persist(device);
    }

    @Transactional
    public void createVar() {
        Variable var = new Variable();
        var.setName("myVar");
        em.persist(var);
    }

    @Transactional
    public void setVarValue() {
        TypedQuery<Variable> q = em.createNamedQuery("variable.findByName", Variable.class);
        q.setParameter("name", "myVar");
        Variable var = q.getSingleResult();

        VariableValue val = new VariableValue();
        val.setVariable(var);
        this.device = em.find(Device.class, device.getId());
        em.persist(val);
        val.setDevice(device);
        val.setValue(varText);
        
    }
    
    @Transactional
    public void deleteDevice() {
        em.refresh(selectedDevice);
        em.remove(selectedDevice);
    }
}
