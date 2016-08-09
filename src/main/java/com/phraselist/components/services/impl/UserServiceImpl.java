package com.phraselist.components.services.impl;

import com.phraselist.components.dao.user.UserDAO;
import com.phraselist.components.data.hbnt.entities.User;
import com.phraselist.components.services.UserService;
import com.phraselist.exceptions.login.UserException;
import com.phraselist.model.beans.user.ClientUserBean;
import org.apache.log4j.Logger;

import javax.inject.Inject;

/**
 * 05.08.2016
 * Created by Rodion.
 */
public class UserServiceImpl implements UserService {
    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    private static final String SAME_LOGIN_ERROR = "The login '%s' is already using by another user.";
    private static final String SAME_EMAIL_ERROR = "The email '%s' is already using by another user.";
    private static final String USER_CREATED = "New user account for %s %s has been created.";

    @Inject
    private UserDAO userDAO;

    public void createUser(ClientUserBean user) throws UserException {
        User existingUser = userDAO.getUserByLogin(user.getLogin());

        if (existingUser != null) {
            throw new UserException(String.format(SAME_LOGIN_ERROR, user.getLogin()));
        }

        existingUser = userDAO.getUserByEmail(user.getEmail());

        if (existingUser != null) {
            throw new UserException(String.format(SAME_EMAIL_ERROR, user.getEmail()));
        }

        userDAO.createUser(user);
        LOG.info(String.format(USER_CREATED, user.getName(), user.getLastname()));
    }
}
