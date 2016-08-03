package com.phraselist.components.services.user;

import com.phraselist.components.data.hbnt.entities.*;
import com.phraselist.exceptions.login.UserException;
import com.phraselist.model.beans.db.ItemBean;

import java.util.List;

/**
 * 26.07.2016
 * Created by Rodion.
 */
public interface PhraseService {
    OriginalWord getOriginalWord(String word);

    OriginalWord addOriginalWord(String word);

    Translation getTranslation(String word);

    Translation addTranslation(String word);

    OriginalLanguage getOriginalLanguage(String language);

    OriginalLanguage addOriginalLanguage(String language);

    TranslatedLanguage getTranslatedLanguage(String language);

    TranslatedLanguage addTranslatedLanguage(String language);

    List<Item> getUsersItems(String oLanguage, String tLanguage, String login);

    void addItem(ItemBean item, String originalLanguage, String translatedLanguage) throws UserException;

    void deleteItem(long id);
}
