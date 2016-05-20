package com.phraselist.components.controllers;

import com.phraselist.components.services.user.UserService;
import com.phraselist.entity.user.User;
import com.phraselist.model.beans.user.ClientUserBean;
import com.phraselist.model.beans.user.ClientUserBeanCommon;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * 19.04.2016
 * Created by Rodion.
 */

@Controller
@RequestMapping(value = "user")
public class UserController {
    private static final Logger LOG = Logger.getLogger(UserController.class);

    @Inject
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@Valid @RequestBody ClientUserBean user) {
        userService.createUser(user);
        LOG.info("User " + user.getName() + " " + user.getEmail() + " was created successfully.");
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(value = "{login}", method = RequestMethod.GET)
    public ResponseEntity<ClientUserBeanCommon> getUser(@PathVariable String login) {
        LOG.info("Input variables - " + login + ".");
        User user = userService.getUserByLogin(login);
        ClientUserBeanCommon userBean = new ClientUserBeanCommon();
        userBean.setLogin(user.getLogin());
        userBean.setEmail(user.getEmail());
        userBean.setName(user.getName());
        userBean.setLastname(user.getLastName());
        return new ResponseEntity<ClientUserBeanCommon>(userBean, HttpStatus.OK);
    }
}
