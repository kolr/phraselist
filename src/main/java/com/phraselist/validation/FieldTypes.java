package com.phraselist.validation;

/**
 * 11.07.2016
 * Created by Rodion.
 */
public enum FieldTypes {
    EMAIL {
        public String getRegExp() {
            return "[A-Za-z0-9._%-]+@[A-Za-z0-9._%-]+\\.[A-Za-z]{2,4}";
        }
    }, NAME {
        public String getRegExp() {
            return "[a-zA-Zа-яА-Я]{3,30}";
        }
    }, PASSWORD {
        public String getRegExp() {
            return "[^!\"№;%:?*()_]{8,30}";
        }
    }, NONE {
        public String getRegExp() {
            return "";
        }
    };

    abstract public String getRegExp();


}
