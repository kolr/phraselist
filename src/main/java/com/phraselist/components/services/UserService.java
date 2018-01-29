package com.phraselist.components.services;

import com.phraselist.model.beans.user.ClientUserBean;
import com.phraselist.model.beans.user.ClientUserBeanCommon;

/**
 * 05.08.2016
 * Created by Rodion.
 */
public interface UserService {
    ClientUserBeanCommon createUser(ClientUserBean user);
}
