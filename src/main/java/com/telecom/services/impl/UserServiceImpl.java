package com.telecom.services.impl;

import com.telecom.dao.api.UserDao;
import com.telecom.dao.model.User;
import com.telecom.exception.DaoException;
import com.telecom.exception.InputException;
import com.telecom.services.api.UserService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

public class UserServiceImpl /*implements UserService*/ {
    
    /*private UserDao userDao;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    
    public UserServiceImpl(UserDao jpaud) {
        userDao = jpaud;
    }
    
    public User getUser(int idUser) {
        return userDao.find(idUser);
    }
    
    public User getByEmail(String email) {
        return userDao.findByEmail(email);
    }
    
    public List<User> getAllUsers() {
        return userDao.findAll();
         
    }
    
    public List<User> getAllClients() {
        return userDao.findAllClients();
    }
    
    public boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }
    
    public void updateUser(User user) throws DaoException, InputException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            
            userDao.updateUser(user);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        }
    }

    public void createUser(User user)
            throws DaoException, InputException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            userDao.createUser(user);
            
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        }
    }*/
    
    
    
    
}
