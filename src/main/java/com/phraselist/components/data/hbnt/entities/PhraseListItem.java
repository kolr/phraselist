package com.phraselist.components.data.hbnt.entities;

import java.util.Date;

/**
 * 17.04.2016
 * Created by Rodion.
 */
public class PhraseListItem {
    private long id;
    private long userId;
    private long originalWordId;
    private long translationId;
    private long labelId;
    private String comment;
    private Date dateOfCreation;
    private Date dateOfEdition;

    public PhraseListItem() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getOriginalWordId() {
        return originalWordId;
    }

    public void setOriginalWordId(long originalWordId) {
        this.originalWordId = originalWordId;
    }

    public long getTranslationId() {
        return translationId;
    }

    public void setTranslationId(long translationId) {
        this.translationId = translationId;
    }

    public long getLabelId() {
        return labelId;
    }

    public void setLabelId(long labelId) {
        this.labelId = labelId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhraseListItem that = (PhraseListItem) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (originalWordId != that.originalWordId) return false;
        if (translationId != that.translationId) return false;
        if (labelId != that.labelId) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (dateOfCreation != null ? !dateOfCreation.equals(that.dateOfCreation) : that.dateOfCreation != null)
            return false;
        return dateOfEdition != null ? dateOfEdition.equals(that.dateOfEdition) : that.dateOfEdition == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (originalWordId ^ (originalWordId >>> 32));
        result = 31 * result + (int) (translationId ^ (translationId >>> 32));
        result = 31 * result + (int) (labelId ^ (labelId >>> 32));
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (dateOfCreation != null ? dateOfCreation.hashCode() : 0);
        result = 31 * result + (dateOfEdition != null ? dateOfEdition.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PhraseListItem{" +
                "id=" + id +
                ", userId=" + userId +
                ", originalWordId=" + originalWordId +
                ", translationId=" + translationId +
                ", labelId=" + labelId +
                ", comment='" + comment + '\'' +
                ", dateOfCreation=" + dateOfCreation +
                ", dateOfEdition=" + dateOfEdition +
                '}';
    }
}
