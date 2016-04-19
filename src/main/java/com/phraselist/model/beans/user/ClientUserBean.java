package com.phraselist.model.beans.user;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;

/**
 * 19.04.2016
 * Created by Rodion.
 */
public class ClientUserBean {
    @Size(min = 3, max = 15)
    private String login;

    @Email
    private String email;

    @Size(min = 2, max = 30)
    private String name;

    @Size(min = 2, max = 30)
    private String lastname;

    @Size(min = 8)
    private String password;

    public ClientUserBean() {
        super();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
