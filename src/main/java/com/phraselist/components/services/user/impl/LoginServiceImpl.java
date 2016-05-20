package com.phraselist.components.services.user.impl;

import com.phraselist.components.services.user.LoginService;
import com.phraselist.components.services.user.UserService;
import com.phraselist.entity.user.User;
import com.phraselist.exceptions.login.LoginException;
import com.phraselist.model.beans.user.ClientUserBeanCommon;

import javax.inject.Inject;

/**
 * 20.05.2016
 * Created by Rodion.
 */
public class LoginServiceImpl implements LoginService {
    private static final String NOT_VALID_ERROR_MESSAGE = "Login \"%s%n\" or password \"%s%n\" does not valid.";
    private static final String PASSWORD_NOT_MATCH_MESSAGE = "Password \"%s%n\" entered by \"%s%n\" does not match.";

    @Inject
    private UserService userService;

    public ClientUserBeanCommon login(String login, String pass) throws LoginException {
        User user;
        if (validation(login, pass)) {
            user = userService.getUserByLogin(login);
            if (passwordVerification(pass, user.getPass())) {
                return getClientUserBeanCommon(user);
            } else {
                throw new LoginException(String.format(PASSWORD_NOT_MATCH_MESSAGE, pass, login));
            }
        } else {
            throw new LoginException(String.format(NOT_VALID_ERROR_MESSAGE, login, pass));
        }
    }

    private boolean validation(String login, String password) {
        return false;
    }

    private boolean passwordVerification(String pass, String storedPass) {
        return false;
    }

    private ClientUserBeanCommon getClientUserBeanCommon(User user) {
        ClientUserBeanCommon userBean = new ClientUserBeanCommon();
        userBean.setLogin(user.getLogin());
        userBean.setEmail(user.getEmail());
        userBean.setName(user.getName());
        userBean.setLastname(user.getLastName());
        return userBean;
    }
}
