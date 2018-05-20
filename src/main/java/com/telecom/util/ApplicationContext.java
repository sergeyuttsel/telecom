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

}
