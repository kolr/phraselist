package com.phraselist.storage;

import com.phraselist.model.beans.db.ItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * ${APP}
 * Created by Rodion on 25.03.2016.
 */
public class Storage {
    List<ItemBean> table;

    public Storage() {
        this.table = new ArrayList<ItemBean>();
        init();
    }

    public void add(ItemBean word) {
        this.table.add(word);
    }

    public List<ItemBean> getAll() {
        return this.table;
    }

    public void delete(long word) {
        for (ItemBean item : this.table) {
            if (item.getId() == word) {
                this.table.remove(item);
                break;
            }
        }
    }

    private void init() {
        ItemBean word = new ItemBean.Builder().id(Word.generateId()).foreign("Hello").translation("Привет").build();
        this.table.add(word);
    }
}
