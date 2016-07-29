package com.phraselist.components.services.user;

import com.phraselist.components.data.hbnt.entities.*;

import java.util.List;

/**
 * 26.07.2016
 * Created by Rodion.
 */
public interface PhraseService {
    OriginalWord getOriginalWord(String word);

    Translation getTranslation(String word);

    OriginalLanguage getOriginalLanguage(String language);

    TranslatedLanguage getTranslatedLanguage(String language);

    List<Item> getUsersItems(String oLanguage, String tLanguage, String login);
}
