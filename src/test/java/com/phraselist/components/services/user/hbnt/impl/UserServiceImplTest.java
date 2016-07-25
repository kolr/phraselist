package com.phraselist.components.services.user.hbnt.impl;

import com.phraselist.components.services.user.UserService;
import com.phraselist.model.beans.user.ClientUserBean;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 25.07.2016
 * Created by Rodion.
 */
public class UserServiceImplTest {
    private static final String USER_LOGIN = "example";
    private static final String USER_NAME = "John";
    private static final String USER_LASTNAME = "Doe";
    private static final String USER_EMAIL = "example@example.com";
    private static final String USER_PASS = "password12";


    private static UserServiceImpl userService;

    @BeforeClass
    public static void setUp() {
        userService = new UserServiceImpl();
    }

    @Test
    public void createUser() throws Exception {
        ClientUserBean test = new ClientUserBean(USER_LOGIN, USER_EMAIL, USER_NAME, USER_LASTNAME, USER_PASS);
        userService.createUser(test);
    }

}