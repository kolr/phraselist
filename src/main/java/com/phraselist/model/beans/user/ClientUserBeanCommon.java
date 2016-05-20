package com.phraselist.model.beans.user;

/**
 * 20.05.2016
 * Created by Rodion.
 */
public class ClientUserBeanCommon {
    private String login;
    private String email;
    private String name;
    private String lastname;

    public ClientUserBeanCommon() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
