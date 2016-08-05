package com.phraselist.components.services;

import com.phraselist.exceptions.phrase.PhraseListException;
import com.phraselist.model.beans.db.ItemBean;
import com.phraselist.storage.Word;

/**
 * 04.08.2016
 * Created by Rodion.
 */
public interface PhraseService {
    ItemBean addWord(Word word, String userLogin, String language) throws PhraseListException;
}
