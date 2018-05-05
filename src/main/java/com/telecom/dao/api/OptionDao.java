package com.telecom.dao.api;

import com.telecom.dao.model.Option;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OptionDao extends CrudRepository<Option, Integer> {

}
