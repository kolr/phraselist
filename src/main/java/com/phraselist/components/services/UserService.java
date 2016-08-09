package com.phraselist.components.services;

import com.phraselist.exceptions.login.UserException;
import com.phraselist.model.beans.user.ClientUserBean;

/**
 * 05.08.2016
 * Created by Rodion.
 */
public interface UserService {
    void createUser(ClientUserBean user) throws UserException;
}
