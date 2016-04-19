package com.phraselist.components.controllers;

import com.phraselist.components.data.dao.UserDAO;
import com.phraselist.entity.user.User;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    private UserDAO userDAO;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {

        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
