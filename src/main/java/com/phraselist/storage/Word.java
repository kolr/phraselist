package com.phraselist.storage;

import java.util.Random;

/**
 * 19.04.2016
 * Created by Rodion.
 */
public class Word {
    private long id;
    private String foreign;
    private String translation;

    public Word() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public static long generateId() {
        Random rnd = new Random();
        return rnd.nextInt(1000000);
    }
}
