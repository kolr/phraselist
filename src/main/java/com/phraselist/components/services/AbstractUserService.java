package com.phraselist.components.services;

import com.phraselist.components.data.hbnt.entities.User;
import com.phraselist.model.beans.user.ClientUserBeanCommon;

/**
 * Created by Rodion
 * on 29.01.2018.
 */
public abstract class AbstractUserService {
    protected ClientUserBeanCommon getClientUserBeanCommon(User user) {
        ClientUserBeanCommon userBean = new ClientUserBeanCommon();
        userBean.setLogin(user.getLogin());
        userBean.setEmail(user.getEmail());
        userBean.setName(user.getName());
        userBean.setLastname(user.getLastname());
        return userBean;
    }
}
