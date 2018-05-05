package com.telecom.dao.api;

import com.telecom.dao.model.Option;
import com.telecom.dao.model.Plan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PlanDao extends CrudRepository<Plan, Integer> {

}