package com.phraselist.components.services.user;

import com.phraselist.entity.user.User;

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
     */
    User login(String login, String pass);
}
