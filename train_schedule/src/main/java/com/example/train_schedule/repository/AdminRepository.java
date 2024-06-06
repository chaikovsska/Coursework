package com.example.train_schedule.repository;

import com.example.train_schedule.entities.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

@Stateless
public class AdminRepository {
    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    @PersistenceContext
    private EntityManager em;

    public User findUserByUsername(String username) {
        try {
            return em.createQuery("SELECT c FROM users c WHERE c.userName = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User register(User user) {
        logger.info("Creating user " + user.getUserName());
        em.persist(user);
        return user;
    }
}