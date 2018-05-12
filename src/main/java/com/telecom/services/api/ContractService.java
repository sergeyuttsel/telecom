package com.telecom.services.api;

import com.telecom.dao.model.Contract;
import com.telecom.dao.model.Option;
import com.telecom.exception.DaoException;
import com.telecom.exception.InputException;

import java.util.List;

public interface ContractService {
    List<Contract> findByPhoneNumber(String phoneNumber);
    void save(Contract contract);
    Contract findById(int idContract);
    void delete(Contract contract);
    List<Option> getAvailableOptions(Contract contract);
    void clearContractOptions(Contract contract);
    /*List<Contract> getAllContracts();
    Contract getContract(int idContract);
    void clearContractOptions(Contract contract);
    void updateContract(Contract contract) throws InputException, DaoException;
    public List<Option> getAvailableOptions(Contract contract);
    Contract getByPhoneNumber(String phoneNumber);
    void createContract(Contract contract) throws DaoException, InputException;
    void removeContract(Contract contract) throws DaoException, InputException;*/
}
