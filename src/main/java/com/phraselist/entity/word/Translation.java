package com.phraselist.entity.word;

/**
 * 17.04.2016
 * Created by Rodion.
 */
public class Translation {
    private long id;
    private String word;
    private long languageId;

    public Translation() {
        super();
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

    public long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(long languageId) {
        this.languageId = languageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Translation that = (Translation) o;

        if (id != that.id) return false;
        if (languageId != that.languageId) return false;
        return word != null ? word.equals(that.word) : that.word == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (word != null ? word.hashCode() : 0);
        result = 31 * result + (int) (languageId ^ (languageId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Translation{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", languageId=" + languageId +
                '}';
    }
}
