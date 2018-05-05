package com.telecom.services.impl;

import com.telecom.dao.api.PlanDao;
import com.telecom.dao.model.Option;
import com.telecom.dao.model.Plan;
import com.telecom.exception.DaoException;
import com.telecom.exception.InputException;
import com.telecom.services.api.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Component
public class PlanServiceImpl implements PlanService {

    public void setPlanDao(PlanDao planDao) {
        this.planDao = planDao;
    }

    @Autowired
    private PlanDao planDao;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public PlanServiceImpl() {

    }

    public Iterable<Plan> findAll() {
        return planDao.findAll();

    }

    public Plan findById(int idPlan) {
        return planDao.findById(idPlan).get();
    }

    /*public List<Plan> getNotArchival() {
        return planDao.findNotArchival();

    }
    
    public Plan getPlan(int idPlan) {
        return planDao.find(idPlan);
    }

    public void updatePlan(Plan plan, List<Option> newAvailableOptions) throws DaoException, InputException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            /
            if (isOptionsCompatible(newAvailableOptions) == false) throw new InputException();
            plan.setAvailableOptions(newAvailableOptions);
            planDao.save(plan);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        }
    }

    public boolean isOptionsCompatible(List<Option> listOptions) {
    	for (Option iOption : listOptions) {
            List<Option> iOptionIncompatibleList = iOption.getIncompatibleOptions();
            for (Option iTargetOption : listOptions) {
                if (iOptionIncompatibleList.contains(iTargetOption)) {
                    return false;
                }
            }
        }
    	return true;
    }
    
    public void createPlan(Plan plan, List<Option> newAvailableOptions)
            throws DaoException, InputException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            if (planDao.uniqueNamePlan(plan.getName()) == false)
                throw new InputException();

            if (isOptionsCompatible(newAvailableOptions) == false) throw new InputException();
            plan.setAvailableOptions(newAvailableOptions);
            planDao.create(plan);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        }
    }*/
}
