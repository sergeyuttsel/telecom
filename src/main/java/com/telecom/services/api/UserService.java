package com.telecom.services.api;

import com.telecom.dao.model.User;
import com.telecom.exception.DaoException;
import com.telecom.exception.InputException;

import java.util.List;

public interface UserService {
	User getUser(int idUser);
    List<User> getAllUsers();
    List<User> getAllClients();
    public User getByEmail(String email);
    boolean checkPassword(User user, String password);
    void updateUser(User user) throws InputException, DaoException;
    void createUser(User user) throws InputException, DaoException;

}
