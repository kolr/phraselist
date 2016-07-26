package com.phraselist.components.services.user;

import com.phraselist.components.data.hbnt.entities.OriginalLanguage;
import com.phraselist.components.data.hbnt.entities.OriginalWord;
import com.phraselist.components.data.hbnt.entities.TranslatedLanguage;
import com.phraselist.components.data.hbnt.entities.Translation;

/**
 * 26.07.2016
 * Created by Rodion.
 */
public interface PhraseService {
    OriginalWord getOriginalWord(String word);

    Translation getTranslation(String word);

    OriginalLanguage getOriginalLanguage(String language);

    TranslatedLanguage getTranslatedLanguage(String language);
}
