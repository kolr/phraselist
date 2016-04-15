package com.phraselist.controllers;

import com.phraselist.storage.Storage;
import com.phraselist.storage.entities.Word;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * ${APP}
 * Created by Rodion on 26.03.2016.
 */

@Controller
@RequestMapping("{language}/phrases")
public class PhraseController {
    private static final Logger LOG = Logger.getLogger(PhraseController.class);

    private static final String FOREIGN_VALUE = "foreign";
    private static final String TRANSLATION_VALUE = "translation";

    Storage storage;

    @Inject
    public PhraseController(Storage storage) {
        this.storage = storage;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addWord(@RequestBody Word word) {
        LOG.info(word.toString());
        this.storage.add(word);
    }


    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Word> getListWord() {
        LOG.info("Number of words: " + this.storage.getAll().size());
        return this.storage.getAll();
    }


    private Word getWord(HttpServletRequest request) {
        String foreign = request.getParameter(FOREIGN_VALUE);
        String translation = request.getParameter(TRANSLATION_VALUE);
        Word word = new Word();
        word.setForeign(foreign);
        word.setTranslation(translation);
        return word;
    }
}
