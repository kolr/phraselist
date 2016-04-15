package com.phraselist.controllers;

import com.phraselist.storage.Storage;
import com.phraselist.storage.entities.Word;
import org.junit.Test;
import org.mockito.InjectMocks;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Rodion on 15.04.2016.
 */
public class PhraseControllerTest {

    @InjectMocks
    PhraseController controller;

    @Mock
    Storage storage;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void test() throws Exception {
        Word word = new Word();
        word.setForeign("test");
        word.setTranslation("тест");
        List<Word> list = new ArrayList<Word>();
        list.add(word);
        Mockito.when(storage.getAll()).thenReturn(list);
        mockMvc.perform(get("/english/phrases")).andDo(print());
    }
}
