package com.phraselist.validation;

/**
 * 11.07.2016
 * Created by Rodion.
 */
public class VerifiableField {

    private FieldTypes type;
    private String value;
    private String regexp;

    public VerifiableField(FieldTypes type, String value) {
        this.type = type;
        this.value = value;
        this.regexp = type.getRegExp();
    }

    public VerifiableField(String regexp, String value) {
        this.value = value;
        this.regexp = regexp;
    }

    public FieldTypes getType() {
        return type;
    }

    public void setType(FieldTypes type) {
        this.type = type;
    }

    public String getRegexp() {
        return regexp;
    }

    public void setRegexp(String regexp) {
        this.regexp = regexp;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
