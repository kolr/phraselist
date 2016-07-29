package com.phraselist.components.services.user.hbnt.impl;

import com.phraselist.components.data.hbnt.entities.*;
import com.phraselist.components.services.user.UserService;
import com.phraselist.exceptions.login.UserException;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

/**
 * 26.07.2016
 * Created by Rodion.
 */
public class PhraseServiceImplTest {
    private static final Logger LOG = Logger.getLogger(PhraseServiceImplTest.class);

    private static final String ORIGINAL_WORD = "Window";
    private static final long ORIGINAL_WORD_ID = 30;

    private static final String TRANSLATED_WORD = "Окно";
    private static final long TRANSLATED_WORD_ID = 21;

    private static final String ORIGINAL_LANGUAGE = "english";
    private static final long ORIGINAL_LANGUAGE_ID = 1;

    private static final String TRANSLATED_LANGUAGE = "russian";
    private static final long TRANSLATED_LANGUAGE_ID = 1;

    private static final String USER_CORRECT_LOGIN = "kolr";
    private static final int NUMBER_OF_ITEMS = 3;

    @InjectMocks
    private PhraseServiceImpl phraseService;

    @Mock
    private UserService userService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        try {
            when(userService.getUserByLogin(USER_CORRECT_LOGIN)).thenReturn(getUser());
        } catch (UserException e) {
            LOG.error(e);
        }
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

    @Test
    public void getItemsByLogin() {
        List<Item> actual = phraseService.getUsersItems(ORIGINAL_LANGUAGE, TRANSLATED_LANGUAGE, USER_CORRECT_LOGIN);

        assertEquals(NUMBER_OF_ITEMS, actual.size());
    }


    private static User getUser() {
        User user = new User();
        user.setId(3);
        user.setEmail("kolrlrs@gmail.com");
        user.setLogin("kolr");
        user.setName("Rodion");
        user.setLastname("Kolomoiets");
        user.setPass("Busawos0");
        Role role = new Role();
        role.setId(1);
        role.setRole("Administrator");
        user.setRole(role);
        return user;
    }

}