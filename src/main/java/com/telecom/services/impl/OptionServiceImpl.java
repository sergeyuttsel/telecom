package com.telecom.services.impl;

import com.telecom.dao.api.OptionDao;
import com.telecom.dao.model.Option;
import com.telecom.exception.DaoException;
import com.telecom.exception.InputException;
import com.telecom.services.api.OptionService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

public class OptionServiceImpl /*implements OptionService*/ {

    /*private OptionDao optionDao;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public OptionServiceImpl(OptionDao jpaod) {
        optionDao = jpaod;
    }

    public List<Option> getAllOptions() {
        return optionDao.findAll();
    }

    public Option getOption(int idOption) {
        return optionDao.find(idOption);
    }

    public void createOption(Option option) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            optionDao.create(option);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        }
    }

    public void updateOption(Option option, List<Option> newRequiredOptions, List<Option> newIncompatibleOptions)
            throws DaoException, InputException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            for (Option iOption : newRequiredOptions) {
                boolean compatibility = newIncompatibleOptions.contains(iOption);
                if (compatibility == true) {
                    throw new InputException();
                }
            }
            optionDao.updateOption(option, newRequiredOptions, newIncompatibleOptions);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        }
    }

    public void createOption(Option option, List<Option> newRequiredOptions, List<Option> newIncompatibleOptions)
            throws DaoException, InputException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            if (optionDao.uniqueNameOption(option.getName()) == false)
                throw new InputException();
            for (Option iOption : newRequiredOptions) {
                boolean compatibility = newIncompatibleOptions.contains(iOption);
                if (compatibility == true) {
                    throw new InputException();
                }
            }
            optionDao.createOption(option, newRequiredOptions, newIncompatibleOptions);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        }
    }*/
}
