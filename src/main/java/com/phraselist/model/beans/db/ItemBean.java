package com.phraselist.model.beans.db;

import com.phraselist.components.data.hbnt.entities.Item;

import java.util.Date;

/**
 * 12.06.2016
 * Created by Rodion.
 */
public class ItemBean {
    private final long id;
    private final String login;
    private final String foreign;
    private final String translation;
    private final String comment;
    private final Date dateOfCreation;
    private final Date dateOfEdition;

    public ItemBean(Builder builder) {
        id = builder.id;
        login = builder.login;
        foreign = builder.foreign;
        translation = builder.translation;
        comment = builder.comment;
        dateOfCreation = builder.dateOfCreation;
        dateOfEdition = builder.dateOfEdition;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getForeign() {
        return foreign;
    }

    public String getTranslation() {
        return translation;
    }

    public String getComment() {
        return comment;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public Date getDateOfEdition() {
        return dateOfEdition;
    }

    public static class Builder {
        private long id;
        private String login;
        private String foreign;
        private String translation;
        private String comment;
        private Date dateOfCreation;
        private Date dateOfEdition;

        public Builder() {

        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public Builder foreign(String foreign) {
            this.foreign = foreign;
            return this;
        }

        public Builder translation(String translation) {
            this.translation = translation;
            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder dateOfCreation(Date date) {
            this.dateOfCreation = date;
            return this;
        }

        public Builder dateOfEdition(Date date) {
            this.dateOfEdition = date;
            return this;
        }

        public Builder inItemBean(ItemBean item) {
            this.login = item.login;
            this.foreign = item.foreign;
            this.translation = item.translation;
            this.comment = item.comment;
            this.dateOfCreation = item.dateOfCreation;
            this.dateOfEdition = item.dateOfEdition;
            return this;
        }

        public ItemBean build() {
            return new ItemBean(this);
        }
    }
}
