package de.nonymus.beans;

import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import lombok.extern.slf4j.Slf4j;
import de.nonymus.testing.entities.KeyValue;
import de.nonymus.testing.entities.Parent;

@Slf4j
@Stateless
public class KeyValueStore {

    @PersistenceContext
    private EntityManager em;

    public void createTestET() {
        Parent parent = new Parent();
        parent.setHandle("Foo");
        em.persist(parent);
        log.info("Persisted parent");

        // First entry
        KeyValue entryOne = new KeyValue(parent);
        entryOne.setKey("eins");
        entryOne.setValue("foo");
        em.persist(entryOne);
        log.info("Persisted first child");

        parent.getParams().put("eins", entryOne);

        // Second entry
        KeyValue entryTwo = new KeyValue(parent);
        entryTwo.setKey("zwo");
        entryTwo.setValue("bar");
        em.persist(entryTwo);
        log.info("Persisted second child");

        parent.getParams().put("zwo", entryTwo);
        log.info("All done");
    }
    
    public void anotherTest() {
        
    }

    public void loadEntity() {
        // Ensure nothings in cache for proper POC
        em.flush();
        TypedQuery<Parent> q = em.createQuery("select p from Parent p where p.handle=:handle", Parent.class)
                .setParameter("handle", "Foo");
        Parent p = q.getSingleResult();

        Map<String, KeyValue> params = p.getParams();

        for (String key : params.keySet()) {
            log.info("Key: {}\tValue: {}", key, params.get(key).getValue());
        }
    }
}
