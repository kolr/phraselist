package com.phraselist.components.data.hbnt.entities;

/**
 * 17.04.2016
 * Created by Rodion.
 */
public class Label {
    private long id;
    private String label;

    public Label() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Label label1 = (Label) o;

        if (id != label1.id) return false;
        return label != null ? label.equals(label1.label) : label1.label == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (label != null ? label.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }
}
