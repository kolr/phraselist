package com.phraselist.gmail.props.entities;

/**
 * 06.08.2016
 * Created by Rodion.
 */
public class Credentials {
    private final String email;
    private final String pass;
    private final String transport;
    private final String trust;

    public Credentials(String email, String pass, String transport, String trust) {
        this.email = email;
        this.pass = pass;
        this.transport = transport;
        this.trust = trust;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getTransport() {
        return transport;
    }

    public String getTrust() {
        return trust;
    }
}
