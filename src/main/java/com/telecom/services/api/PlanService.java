package com.telecom.services.api;

import com.telecom.dao.model.Option;
import com.telecom.dao.model.Plan;
import com.telecom.exception.DaoException;
import com.telecom.exception.InputException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PlanService {
    Iterable<Plan> findAll();
    Plan findById(int id);
    void update(Plan plan);
    void create(Plan plan);
    boolean isOptionsCompatible(List<Option> listOptions);
    List<Plan> getNotArchival();
    /*Plan getPlan(int idPlan);
    void updatePlan(Plan plan, List<Option> newAvailableOptions) throws InputException, DaoException;
    void createPlan(Plan plan, List<Option> newAvailableOptions) throws InputException, DaoException;
    List<Plan> getNotArchival();
    public boolean isOptionsCompatible(List<Option> listOptions);*/
}
