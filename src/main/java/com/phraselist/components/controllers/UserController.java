package com.phraselist.components.controllers;

import com.phraselist.components.services.user.LoginService;
import com.phraselist.components.services.user.UserService;
import com.phraselist.model.beans.user.ClientUserBean;
import com.phraselist.model.beans.user.ClientUserBeanCommon;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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

    @Inject
    private LoginService loginService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@Valid @RequestBody ClientUserBean user) {
        userService.createUser(user);
        LOG.info("User " + user.getName() + " " + user.getEmail() + " was created successfully.");
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(value = "{login}", method = RequestMethod.POST)
    public ResponseEntity<ClientUserBeanCommon> signIn(@PathVariable String login, @RequestBody String pass, HttpServletRequest req) {
        if (req.getSession().getAttribute("user") != null) {
            LOG.info("The session variable had been returned.");
            return new ResponseEntity<ClientUserBeanCommon>((ClientUserBeanCommon) req.getSession().getAttribute("user"), HttpStatus.OK);
        }
        LOG.info("Input variables - " + login + ".");
        ClientUserBeanCommon userBean = null;
        try {
            userBean = loginService.login(login, pass);
        } catch (Exception e) {
            LOG.error(e);
            return new ResponseEntity<ClientUserBeanCommon>(HttpStatus.NOT_FOUND);
        }
        req.getSession().setAttribute("user", userBean);
        LOG.info("New user had been set up in session.");
        return new ResponseEntity<ClientUserBeanCommon>(userBean, HttpStatus.OK);
    }
}
