package com.phraselist.components.data.hbnt.entities;

import javax.persistence.*;

/**
 * 26.07.2016
 * Created by Rodion.
 */
@Entity
@Table(name = "original_languages")
public class OriginalLanguage {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "language")
    private String language;

    public OriginalLanguage() {
    }

    public OriginalLanguage(String language) {
        this.language = language;
    }

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
