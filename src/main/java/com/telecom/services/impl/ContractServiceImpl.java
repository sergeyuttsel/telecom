package com.telecom.services.impl;

import com.telecom.dao.api.ContractDao;
import com.telecom.dao.model.Contract;
import com.telecom.dao.model.Option;
import com.telecom.dao.model.User;
import com.telecom.exception.DaoException;
import com.telecom.exception.InputException;
import com.telecom.services.api.ContractService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

public class ContractServiceImpl /*implements ContractService*/ {

	/*private ContractDao contractDao;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

	public ContractServiceImpl(ContractDao jpacd) {
		contractDao = jpacd;
	}

	public List<Contract> getAllContracts() {
		return contractDao.findAll();
	}

	public Contract getContract(int idContract) {
		return contractDao.find(idContract);
	}
	
	public Contract getByPhoneNumber(String phoneNumber) {
        return contractDao.findByPhoneNumber(phoneNumber);
    }

	public List<Option> getAvailableOptions(Contract contract) {
		List<Option> availableContractOptions = contract.getPlan().getAvailableOptions();
		
		for (Option i : contract.getContractOptions()) {
			if (availableContractOptions.contains(i) == false) {
				availableContractOptions.add(i);
			}
		}
		
		return availableContractOptions;

	}
	
	public void clearContractOptions(Contract contract) {
		contractDao.clearContractOptions(contract);
	}
	
	public void updateContract(Contract contract) throws DaoException, InputException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {

            entityManager.getTransaction().begin();
            
            contractDao.update(contract);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        }
    }
	
	public void createContract(Contract contract)
            throws DaoException, InputException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            contractDao.createContract(contract);
            
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        }
    }
	
	public void removeContract(Contract contract)
            throws DaoException, InputException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            User user = contract.getUser();
            user.getContracts().remove(contract);
            contractDao.delete(contract);
            
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DaoException();
        }
    }*/
	
}
