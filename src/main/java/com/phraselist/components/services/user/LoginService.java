package com.phraselist.components.services.user;

import com.phraselist.exceptions.login.LoginException;
import com.phraselist.model.beans.user.ClientUserBeanCommon;

/**
 * 20.05.2016
 * Created by Rodion.
 */
public interface LoginService {

    /**
     * Whole process of authentication.
     *
     * @param login - user's login.
     * @param pass  - user's password.
     * @return user data.
     * @throws LoginException - if error while login occurred.
     */
    ClientUserBeanCommon login(String login, String pass) throws Exception;
}
