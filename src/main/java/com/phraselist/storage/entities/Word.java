package com.phraselist.storage.entities;

import java.util.Random;

/**
 * ${APP}
 * Created by Rodion on 25.03.2016.
 */
public class Word {
    private long id;
    private String foreign;
    private String translation;

    public Word() {

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

    @Override
    public String toString() {
        return "Word{" +
                "foreign='" + foreign + '\'' +
                ", translation='" + translation + '\'' +
                '}';
    }

    public static long generateId() {
        Random rnd = new Random();
        return rnd.nextInt(1000000);
    }

}
