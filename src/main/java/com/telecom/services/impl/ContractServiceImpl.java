package com.telecom.services.impl;

import com.telecom.dao.api.ContractDao;
import com.telecom.dao.api.UserDao;
import com.telecom.dao.model.Contract;
import com.telecom.dao.model.Option;
import com.telecom.dao.model.User;
import com.telecom.exception.DaoException;
import com.telecom.exception.InputException;
import com.telecom.services.api.ContractService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractDao contractDao;

    @Autowired
    private UserDao userDao;

    public void setContractDao(ContractDao contractDao) {
        this.contractDao = contractDao;
    }

    public List<Contract> findByPhoneNumber(String phoneNumber) {
        return contractDao.findByPhoneNumber(phoneNumber);

    }

    public void save(Contract contract) {
        contractDao.save(contract);
    }

    public void delete(Contract contract) {
        contractDao.delete(contract);

        /*User user = contract.getUser();
        user.getContracts().remove(contract);
        contractDao.delete(contract);*/

        /*User user = contract.getUser();
        user.getContracts().remove(contract);
        userDao.save(user);*/
    }

    public Contract findById(int idContract) {
        return contractDao.findById(idContract).get();
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
        Contract contractWoOptions = findById(contract.getId());
        contractWoOptions.getContractOptions().clear();
        save(contractWoOptions);
    }

}
