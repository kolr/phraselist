package com.phraselist.exceptions.login;

/**
 * 20.05.2016
 * Created by Rodion.
 */
public class LoginException extends Exception {
    public LoginException() {
        super();
    }

    public LoginException(String message, Throwable exception) {
        super(message, exception);
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(Throwable exception) {
        super(exception);
    }

}
