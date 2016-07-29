package com.phraselist.components.data.hbnt.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * 26.07.2016
 * Created by Rodion.
 */

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "original_id", nullable = false)
    private OriginalWord originalWord;

    @ManyToOne
    @JoinColumn(name = "translation_id", nullable = false)
    private Translation translation;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date_of_creation")
    private Date dateOfCreation;

    @Column(name = "date_of_edition")
    private Date dateOfEdition;

    @ManyToOne
    @JoinColumn(name = "original_language", nullable = false)
    private OriginalLanguage originalLanguage;

    @ManyToOne
    @JoinColumn(name = "translation_language", nullable = false)
    private TranslatedLanguage translatedLanguage;

    public Item() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OriginalWord getOriginalWord() {
        return originalWord;
    }

    public void setOriginalWord(OriginalWord originalWord) {
        this.originalWord = originalWord;
    }

    public Translation getTranslation() {
        return translation;
    }

    public void setTranslation(Translation translation) {
        this.translation = translation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Date getDateOfEdition() {
        return dateOfEdition;
    }

    public void setDateOfEdition(Date dateOfEdition) {
        this.dateOfEdition = dateOfEdition;
    }

    public OriginalLanguage getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(OriginalLanguage originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public TranslatedLanguage getTranslatedLanguage() {
        return translatedLanguage;
    }

    public void setTranslatedLanguage(TranslatedLanguage translatedLanguage) {
        this.translatedLanguage = translatedLanguage;
    }
}