package com.phraselist.exceptions.login;

/**
 * 20.05.2016
 * Created by Rodion.
 */
public class UserException extends Exception {
    public UserException() {
        super();
    }

    public UserException(String message, Throwable exception) {
        super(message, exception);
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(Throwable exception) {
        super(exception);
    }
}
