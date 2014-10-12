package de.nonymus.testing.foobar.beans;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;

import lombok.extern.slf4j.Slf4j;

import org.omnifaces.cdi.Startup;

import de.nonymus.testing.foobar.model.User;

@ApplicationScoped
@Startup
@Slf4j
public class DefaultUser {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;

    @PostConstruct
    @Transactional
    public void init() {
        TypedQuery<Long> q = em.createQuery("SELECT COUNT(u) FROM User AS u", Long.class);
        long userCount = q.getSingleResult();
        if (userCount == 0) {
            log.info("No users in database. Creating standard user.");
            try {
                createDefaultUser();
            } catch (NoSuchAlgorithmException e) {
                log.error("Platform does not support secure hashing algorithm: ", e);
            } catch (UnsupportedEncodingException e) {
                log.error("Platform does not support application character encoding: ", e);
            }
        } else {
            log.info("Database contains users.");
        }
    }

    @Transactional
    private void createDefaultUser() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        try {
            // No container transaction available at startup. Need to use our
            // own
            utx.begin();
            User user = new User();
            user.setLogin("admin");
            user.setPassword("admin");
            em.persist(user);
            utx.commit();
            log.info("Created default user 'admin' with password 'admin'. CHANGE IT!");
        } catch (NotSupportedException | SystemException | SecurityException | IllegalStateException
                | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
            log.error("Could not create default user: ", e);
        }

    }

}
