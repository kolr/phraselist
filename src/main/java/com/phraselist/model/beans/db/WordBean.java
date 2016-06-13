package com.phraselist.model.beans.db;

/**
 * 13.06.2016
 * Created by Rodion.
 * For tables 'original_words' and 'translations'
 */
public class WordBean {
    private long id;
    private String word;

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
}
