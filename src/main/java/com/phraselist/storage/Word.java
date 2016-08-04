package com.phraselist.storage;

import com.phraselist.validation.annotation.Validate;
import com.phraselist.validation.entities.Verifiable;

import java.util.Random;

/**
 * 19.04.2016
 * Created by Rodion.
 */
public class Word implements Verifiable {
    private long id;

    @Validate(regexp = "[a-zA-Zа-яА-Я]{2,30}")
    private String foreign;

    @Validate(regexp = "[a-zA-Zа-яА-Я]{2,30}")
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

    static long generateId() {
        Random rnd = new Random();
        return rnd.nextInt(1000000);
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", foreign='" + foreign + '\'' +
                ", translation='" + translation + '\'' +
                '}';
    }
}
