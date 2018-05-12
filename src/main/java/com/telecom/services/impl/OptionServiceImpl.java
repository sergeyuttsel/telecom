package com.telecom.services.impl;

import com.telecom.dao.api.OptionDao;
import com.telecom.dao.model.Option;
import com.telecom.exception.DaoException;
import com.telecom.exception.InputException;
import com.telecom.services.api.OptionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

public class OptionServiceImpl implements OptionService {

    @Autowired
    private OptionDao optionDao;

    public void setOptionDao(OptionDao optionDao) {
        this.optionDao = optionDao;
    }

    public Iterable<Option> findAll() {
        return optionDao.findAll();

    }

    public Option findById(int idOption) {
        return optionDao.findById(idOption).get();
    }

    public void create(Option option) {
        if (uniqueNameOption(option.getName()) == false)
            throw new Error("Option name not unique");
        for (Option iOption : option.getRequiredOptions()) {
            boolean compatibility = option.getIncompatibleOptions().contains(iOption);
            if (compatibility == true) {
                throw new Error("Option in required and incompatible lists are not coordinated");
            }
        }
            optionDao.save(option);
            for (Option iOption : option.getIncompatibleOptions()) {
                if (iOption.equals(option)) {
                    continue;
                }
                iOption.getIncompatibleOptions().add(option);
                optionDao.save(iOption);
            }

    }

        public void update(Option option){
        /*if (uniqueNameOption(option.getName()) == false)
            throw new Error("Option name not unique");*/
            for (Option iOption : option.getRequiredOptions()) {
                boolean compatibility = option.getIncompatibleOptions().contains(iOption);
                if (compatibility == true) {
                    throw new Error("Option in required and incompatible lists are not coordinated");
                }
            }

            for (Option iOption : findAll()) {
                if (iOption.equals(option)) {
                    continue;
                }
                List<Option> incompatibleOptions = iOption.getIncompatibleOptions();
                incompatibleOptions.remove(option);
                optionDao.save(iOption);
            }
            for (Option iOption : option.getIncompatibleOptions()) {
                if (iOption.equals(option)) {
                    continue;
                }
                iOption.getIncompatibleOptions().add(option);
                optionDao.save(iOption);
            }
            optionDao.save(option);
        }

        public boolean uniqueNameOption (String name){
            List<Option> list = optionDao.findByName(name);
            boolean result = false;
            if (list.size() == 0)
                result = true;
            else
                result = false;
            return result;
        }


}