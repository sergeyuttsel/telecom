package com.telecom.services.api;

import com.telecom.dao.model.Option;
import com.telecom.exception.DaoException;
import com.telecom.exception.InputException;

import java.util.List;

public interface OptionService {
    Option findById(int id);
    Iterable<Option> findAll();
    void update(Option option);
    void create(Option option);
    /*List<Option> getAllOptions();
    public Option getOption(int idOption);
    void createOption(Option option) throws DaoException;
    void updateOption(Option option, List<Option> newRequiredOptions, List<Option> newIncompatibleOptions) throws InputException, DaoException;
    void createOption(Option option, List<Option> newRequiredOptions, List<Option> newIncompatibleOptions) throws InputException, DaoException;*/
    
}
