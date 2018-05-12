package com.telecom.dao.api;

import com.telecom.dao.model.Contract;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;


public interface ContractDao extends CrudRepository<Contract, Integer> {
    List<Contract> findByPhoneNumber(String phoneNumber);
}