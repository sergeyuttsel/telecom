package com.telecom.util;

import com.telecom.dao.api.ContractDao;
import com.telecom.dao.api.OptionDao;
import com.telecom.dao.api.PlanDao;
import com.telecom.dao.api.UserDao;
import com.telecom.services.api.ContractService;
import com.telecom.services.api.OptionService;
import com.telecom.services.api.PlanService;
import com.telecom.services.api.UserService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextListener;

//@WebListener
public class ApplicationContext implements ServletContextListener {
    public static ApplicationContext INSTANCE;

    private static EntityManagerFactory EMFactory;
    
    private static EntityManager entityManager;

    private static OptionDao optionDao;

    private static PlanDao planDao;
    
    private static ContractDao contractDao;
    
    private static UserDao userDao;

    private static OptionService optionService;

    private static PlanService planService;
    
    private static ContractService contractService;
    
    private static UserService userService;
/*
    //@Override
    public void contextInitialized(ServletContextEvent event) {
        EMFactory = Persistence.createEntityManagerFactory("telecomjpa");
        entityManager = EMFactory.createEntityManager();
    }

    //@Override
    public void contextDestroyed(ServletContextEvent event) {
        entityManager.close();
        EMFactory.close();
    }
    
    public static ApplicationContext getApplicationContext() {
        if (INSTANCE!=null) {
            return INSTANCE;
        } else {
            INSTANCE = new ApplicationContext();
            return INSTANCE;
        }
    }

    public ApplicationContext() {
        EMFactory = Persistence.createEntityManagerFactory("telecomjpa");
        entityManager = EMFactory.createEntityManager();
    }

    private static EntityManager getEntityManager() {
        return entityManager;
    }

    public static OptionDao getOptionDao() {
        if (optionDao != null) {
            return optionDao;
        } else {
            optionDao = new JpaOptionDao(getEntityManager());
            return optionDao;
        }
    }

    public static OptionService getOptionService() {
        if (optionService != null) {
            return optionService;
        } else {
            optionService = new OptionServiceImpl(getOptionDao(), getEntityManager());
            return optionService;
        }
    }

    public static PlanDao getPlanDao() {
        if (planDao != null) {
            return planDao;
        } else {
            planDao = new JpaPlanDao(getEntityManager());
            return planDao;
        }
    }

    public static PlanService getPlanService() {
        if (planService != null) {
            return planService;
        } else {
            planService = new PlanServiceImpl(getPlanDao(), getEntityManager());
            return planService;
        }
    }
    
    public static ContractDao getContractDao() {
        if (contractDao != null) {
            return contractDao;
        } else {
            contractDao = new JpaContractDao(getEntityManager());
            return contractDao;
        }
    }

    public static ContractService getContractService() {
        if (contractService != null) {
            return contractService;
        } else {
            contractService = new ContractServiceImpl(getContractDao(), getEntityManager());
            return contractService;
        }
    }
    
    public static UserDao getUserDao() {
        if (userDao != null) {
            return userDao;
        } else {
            userDao = new JpaUserDao(getEntityManager());
            return userDao;
        }
    }

    public static UserService getUserService() {
        if (userService != null) {
            return userService;
        } else {
            userService = new UserServiceImpl(getUserDao(), getEntityManager());
            return userService;
        }
    }
    */
}
