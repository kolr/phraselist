package com.phraselist.model.beans.db;

import java.util.Date;

/**
 * 12.06.2016
 * Created by Rodion.
 */
public class ItemBean {
    private long id;
    private String login;
    private String foreign;
    private String translation;
    private String comment;
    private Date dateOfCreation;
    private Date dateOfEdition;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getForeign() {
        return foreign;
    }

    public void setForeign(String foreign) {
        this.foreign = foreign;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Date getDateOfEdition() {
        return dateOfEdition;
    }

    public void setDateOfEdition(Date dateOfEdition) {
        this.dateOfEdition = dateOfEdition;
    }
}
