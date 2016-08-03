package com.phraselist.components.data.hbnt.entities;

import javax.persistence.*;

/**
 * 17.04.2016
 * Created by Rodion.
 */

@Entity
@Table(name = "original_words")
public class OriginalWord {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "word")
    private String word;

    public OriginalWord() {
    }

    public OriginalWord(String word) {
        this.word = word;
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

}
