package com.phraselist.components.dao.user.impl;

import com.phraselist.components.dao.user.LoginService;
import com.phraselist.components.dao.user.UserDAO;
import com.phraselist.components.data.hbnt.entities.User;
import com.phraselist.components.services.AbstractUserService;
import com.phraselist.exceptions.login.LoginException;
import com.phraselist.model.beans.user.ClientUserBeanCommon;
import com.phraselist.validatior.Validator;

import javax.inject.Inject;

/**
 * 20.05.2016
 * Created by Rodion.
 */
public class LoginServiceImpl extends AbstractUserService implements LoginService {
    private static final String NOT_VALID_ERROR_MESSAGE = "Login \"%s\" or password \"%s\" does not valid.";
    private static final String PASSWORD_NOT_MATCH_MESSAGE = "Password \"%s\" entered by \"%s\" does not match.";

    @Inject
    private UserDAO userDAO;

    public ClientUserBeanCommon login(String login, String pass) throws Exception {
        User user;
        if (validation(login, pass)) {
            user = userDAO.getUserByLogin(login);
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
        return Validator.loginValidation(login) && Validator.passValidation(password);
    }

    private boolean passwordVerification(String pass, String storedPass) {
        return pass.equals(storedPass);
    }
}
