package com.phraselist.storage;

import com.phraselist.entity.word.PhraseListItem;
import com.phraselist.storage.entities.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * ${APP}
 * Created by Rodion on 25.03.2016.
 */
public class Storage {
    List<PhraseListItem> table;

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

    public void delete(long word) {
        for (Word item : this.table) {
            if (item.getId() == word) {
                this.table.remove(item);
                break;
            }
        }
    }

    private void init() {
        Word word = new Word();
        word.setForeign("Hello");
        word.setTranslation("Привет");
        word.setId(Word.generateId());
        this.table.add(word);
    }
}
