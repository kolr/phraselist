package com.phraselist.components.services.impl;

import com.phraselist.components.dao.user.UserDAO;
import com.phraselist.components.data.hbnt.entities.User;
import com.phraselist.components.services.AbstractUserService;
import com.phraselist.components.services.UserService;
import com.phraselist.model.beans.user.ClientUserBean;
import com.phraselist.model.beans.user.ClientUserBeanCommon;

import javax.inject.Inject;

/**
 * 05.08.2016
 * Created by Rodion.
 */
public class UserServiceImpl extends AbstractUserService implements UserService {

    @Inject
    private UserDAO userDAO;

    public ClientUserBeanCommon createUser(ClientUserBean user) {
        User createdUser = userDAO.createUser(user);
        return getClientUserBeanCommon(createdUser);
    }
}
