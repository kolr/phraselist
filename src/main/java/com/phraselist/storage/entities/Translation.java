package com.phraselist.storage.entities;

/**
 * ${APP}
 * Created by Rodion on 25.03.2016.
 */
public class Translation {
    private Long id;

    private String word;

    private String language;

    public Translation() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
