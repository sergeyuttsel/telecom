package com.telecom.dao.api;

import com.telecom.dao.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<User, Integer> {

}