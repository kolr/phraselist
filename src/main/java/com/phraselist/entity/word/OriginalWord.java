package com.phraselist.entity.word;

/**
 * 17.04.2016
 * Created by Rodion.
 */
public class OriginalWord {
    long id;
    String word;
    long language_id;

    public OriginalWord() {
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

    public long getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(long language_id) {
        this.language_id = language_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OriginalWord that = (OriginalWord) o;

        if (id != that.id) return false;
        if (language_id != that.language_id) return false;
        return word != null ? word.equals(that.word) : that.word == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (word != null ? word.hashCode() : 0);
        result = 31 * result + (int) (language_id ^ (language_id >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "OriginalWord{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", language_id=" + language_id +
                '}';
    }
}
