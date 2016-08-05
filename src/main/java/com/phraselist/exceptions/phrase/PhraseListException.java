package com.phraselist.exceptions.phrase;

/**
 * 05.08.2016
 * Created by Rodion.
 */
public class PhraseListException extends Exception {
    public PhraseListException() {
        super();
    }

    public PhraseListException(String message, Throwable exception) {
        super(message, exception);
    }

    public PhraseListException(String message) {
        super(message);
    }

    public PhraseListException(Throwable exception) {
        super(exception);
    }
}
