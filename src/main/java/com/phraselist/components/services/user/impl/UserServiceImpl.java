package com.phraselist.components.services.user.impl;

import com.phraselist.components.data.dao.UserDAO;
import com.phraselist.components.services.user.UserService;
import com.phraselist.entity.user.User;
import com.phraselist.exceptions.login.UserException;
import com.phraselist.model.beans.user.ClientUserBean;

import javax.inject.Inject;

/**
 * 19.04.2016
 * Created by Rodion.
 */
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Inject
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void createUser(ClientUserBean user) {
        User convertedUser = convertUser(user);
        userDAO.insertUser(convertedUser);
    }

    public void updateUser(User user) {

    }

    public void deleteUser(long id) {

    }

    public User getUserByLogin(String login) throws UserException{
        return userDAO.getUser(login);
    }

    public User getUserByEmail(String email) {
        return null;
    }


    private User convertUser(ClientUserBean user) {
        User convertedUser = new User();
        convertedUser.setEmail(user.getEmail());
        convertedUser.setLogin(user.getLogin());
        convertedUser.setName(user.getName());
        convertedUser.setLastName(user.getLastname());
        convertedUser.setPass(user.getPassword());
        convertedUser.setRoleId(2);
        return convertedUser;
    }
}
