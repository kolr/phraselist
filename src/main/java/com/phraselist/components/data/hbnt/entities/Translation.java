package com.phraselist.components.data.hbnt.entities;

import javax.persistence.*;

/**
 * 17.04.2016
 * Created by Rodion.
 */

@Entity
@Table(name = "translations")
public class Translation {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "tword")
    private String tword;

    public Translation() {
        super();
    }

    public Translation(String word) {
        this.tword = word;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return tword;
    }

    public void setWord(String word) {
        this.tword = word;
    }

}
