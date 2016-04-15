package com.phraselist.storage;

import com.phraselist.storage.entities.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * ${APP}
 * Created by Rodion on 25.03.2016.
 */
public class Storage {
    List<Word> table;

    public Storage() {
        this.table = new ArrayList<Word>();
        init();
    }

    public void add(Word word) {
        this.table.add(word);
    }

    public List<Word> getAll() {
        return this.table;
    }

    public void delete(String word) {
        for (Word item : this.table) {
            if (item.getForeign().equals(word)) {
                this.table.remove(item);
                break;
            }
        }
    }

    private void init() {
        Word word = new Word();
        word.setForeign("Hello");
        word.setTranslation("Привет");
        this.table.add(word);
    }
}
