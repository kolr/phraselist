package com.phraselist.components.services.impl;

import com.phraselist.components.dao.user.UserDAO;
import com.phraselist.components.services.UserService;
import com.phraselist.model.beans.user.ClientUserBean;

import javax.inject.Inject;

/**
 * 05.08.2016
 * Created by Rodion.
 */
public class UserServiceImpl implements UserService {

    @Inject
    private UserDAO userDAO;

    public void createUser(ClientUserBean user) {
        userDAO.createUser(user);
    }
}
