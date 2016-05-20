package com.phraselist.components.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 20.05.2016
 * Created by Rodion.
 */
@Controller
@RequestMapping(value = "{userLogin}")
public class UserProfileController {

    @RequestMapping(value = "/profile",method = RequestMethod.POST)
    public ResponseEntity<Object> changeProfileSettings() {
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
