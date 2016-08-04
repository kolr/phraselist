package com.phraselist.validatior;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 20.05.2016
 * Created by Rodion.
 */
public class Validator {
    public static boolean loginValidation(String login) {
        String loginTemplate = "[a-zA-Z0-9]*";
        Pattern pattern = Pattern.compile(loginTemplate);
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }

    public static boolean passValidation(String pass) {
        if (pass.length() >= 8) {
            String loginTemplate = "[^!\"№;%:?*()_]*";
            Pattern pattern = Pattern.compile(loginTemplate);
            Matcher matcher = pattern.matcher(pass);
            return matcher.matches();
        }
        return false;
    }
}
