package com.phraselist.storage.entities;

/**
 * ${APP}
 * Created by Rodion on 25.03.2016.
 */
public class Word {
    private String foreign;
    private String translation;

    public Word() {

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
}
