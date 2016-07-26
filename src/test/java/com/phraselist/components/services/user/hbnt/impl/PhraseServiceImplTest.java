package com.phraselist.components.services.user.hbnt.impl;

import com.phraselist.components.data.hbnt.entities.OriginalLanguage;
import com.phraselist.components.data.hbnt.entities.OriginalWord;
import com.phraselist.components.data.hbnt.entities.TranslatedLanguage;
import com.phraselist.components.data.hbnt.entities.Translation;
import com.phraselist.components.services.user.PhraseService;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 26.07.2016
 * Created by Rodion.
 */
public class PhraseServiceImplTest {
    private static final String ORIGINAL_WORD = "Window";
    private static final long ORIGINAL_WORD_ID = 30;

    private static final String TRANSLATED_WORD = "Окно";
    private static final long TRANSLATED_WORD_ID = 21;

    private static final String ORIGINAL_LANGUAGE = "english";
    private static final long ORIGINAL_LANGUAGE_ID = 1;

    private static final String TRANSLATED_LANGUAGE = "russian";
    private static final long TRANSLATED_LANGUAGE_ID = 1;

    private static PhraseService phraseService;

    @BeforeClass
    public static void setUp() {
        phraseService = new PhraseServiceImpl();
    }

    @Test
    public void getOriginalWord() throws Exception {
        OriginalWord actual = phraseService.getOriginalWord(ORIGINAL_WORD);

        assertEquals(ORIGINAL_WORD_ID, actual.getId());
    }

    @Test
    public void getTranslation() throws Exception {
        Translation actual = phraseService.getTranslation(TRANSLATED_WORD);

        assertEquals(TRANSLATED_WORD_ID, actual.getId());
    }

    @Test
    public void getOriginalLanguage() throws Exception {
        OriginalLanguage actual = phraseService.getOriginalLanguage(ORIGINAL_LANGUAGE);

        assertEquals(ORIGINAL_LANGUAGE_ID, actual.getId());
    }

    @Test
    public void getTranslatedLanguage() throws Exception {
        TranslatedLanguage actual = phraseService.getTranslatedLanguage(TRANSLATED_LANGUAGE);

        assertEquals(TRANSLATED_LANGUAGE_ID, actual.getId());
    }

}