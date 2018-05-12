package com.telecom.services.impl;

import com.telecom.dao.api.UserDao;
import com.telecom.dao.model.User;
import com.telecom.exception.DaoException;
import com.telecom.exception.InputException;
import com.telecom.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public Iterable<User> findAll() {
        return userDao.findAll();

    }

    public User findById(int idUser) {
        return userDao.findById(idUser).get();
    }

    public Iterable<User> findAllClients() {
        return userDao.findByRole(User.Role.CLIENT);

    }

    public void save(User user) {
        userDao.save(user);
    }
    
}
