package com.phraselist.components.data.hbnt.entities;

import javax.persistence.*;

/**
 * 26.07.2016
 * Created by Rodion.
 */

@Entity
@Table(name = "translated_languages")
public class TranslatedLanguage {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "tlanguage")
    private String tlanguage;

    public TranslatedLanguage() {
    }

    public TranslatedLanguage(String tlanguage) {
        this.tlanguage = tlanguage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTlanguage() {
        return tlanguage;
    }

    public void setTlanguage(String tlanguage) {
        this.tlanguage = tlanguage;
    }
}
