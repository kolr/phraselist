package com.phraselist.model.beans;

import java.util.Arrays;
import java.util.Date;

/**
 * 17.04.2016
 * Created by Rodion.
 */
public class PhraseListItemBean {
    private long id;
    private String originalWord;
    private String translation;
    private String[] labels;
    private String comment;
    private Date dateOfCreation;
    private Date editedDate;

    public PhraseListItemBean() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginalWord() {
        return originalWord;
    }

    public void setOriginalWord(String originalWord) {
        this.originalWord = originalWord;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
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

    public Date getEditedDate() {
        return editedDate;
    }

    public void setEditedDate(Date editedDate) {
        this.editedDate = editedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhraseListItemBean that = (PhraseListItemBean) o;

        if (id != that.id) return false;
        if (originalWord != null ? !originalWord.equals(that.originalWord) : that.originalWord != null) return false;
        if (translation != null ? !translation.equals(that.translation) : that.translation != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(labels, that.labels)) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (dateOfCreation != null ? !dateOfCreation.equals(that.dateOfCreation) : that.dateOfCreation != null)
            return false;
        return editedDate != null ? editedDate.equals(that.editedDate) : that.editedDate == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (originalWord != null ? originalWord.hashCode() : 0);
        result = 31 * result + (translation != null ? translation.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(labels);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (dateOfCreation != null ? dateOfCreation.hashCode() : 0);
        result = 31 * result + (editedDate != null ? editedDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PhraseListItemBean{" +
                "id=" + id +
                ", originalWord='" + originalWord + '\'' +
                ", translation='" + translation + '\'' +
                ", labels=" + Arrays.toString(labels) +
                ", comment='" + comment + '\'' +
                ", dateOfCreation=" + dateOfCreation +
                ", editedDate=" + editedDate +
                '}';
    }
}
