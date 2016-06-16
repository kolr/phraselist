package com.phraselist.model.beans.db;

/**
 * 13.06.2016
 * Created by Rodion.
 * For tables called 'original_languages' and 'translated_languages'.
 */
public class LanguageBean {
    private long id;
    private String language;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
