package com.phraselist.components.controllers;

import com.phraselist.components.services.user.LoginService;
import com.phraselist.components.services.user.UserService;
import com.phraselist.model.beans.user.ClientUserBean;
import com.phraselist.model.beans.user.ClientUserBeanCommon;
import org.apache.log4j.Logger;
import org.apache.log4j.or.ObjectRenderer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        ClientUserBeanCommon userBean;
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

    @RequestMapping(value = "{login}/out", method = RequestMethod.POST)
    public ResponseEntity<Object> signOut(@PathVariable String login, HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            session.invalidate();
            LOG.info(String.format("Session of user %s has been closed.", login));
            return new ResponseEntity<Object>(HttpStatus.OK);
        }
        LOG.error(String.format("Session of user %s had been closed long ago.", login));
        return new ResponseEntity<Object>(HttpStatus.METHOD_NOT_ALLOWED);
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ClientUserBeanCommon> initializeUser(HttpServletRequest request) {
        ClientUserBeanCommon user = (ClientUserBeanCommon) request.getSession().getAttribute("user");
        if (user != null) {
            return new ResponseEntity<ClientUserBeanCommon>(user, HttpStatus.OK);
        }
        return new ResponseEntity<ClientUserBeanCommon>(HttpStatus.NOT_FOUND);
    }
}
