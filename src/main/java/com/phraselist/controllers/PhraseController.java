package com.phraselist.controllers;

import com.phraselist.storage.Storage;
import com.phraselist.storage.entities.Word;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * ${APP}
 * Created by Rodion on 26.03.2016.
 */

@Controller
@RequestMapping("{language}/phrases")
public class PhraseController {
    private static final Logger LOG = Logger.getLogger(PhraseController.class);

    private Storage storage;

    @Inject
    public PhraseController(Storage storage) {
        this.storage = storage;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> addWord(@RequestBody Word word, @PathVariable String language) {
        LOG.info(language);
        word.setId(Word.generateId());
        this.storage.add(word);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Word> getListWord() {
        return this.storage.getAll();
    }

    @RequestMapping(value = "/{wordID}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteWord(@PathVariable long wordID) {
        LOG.info(wordID);
        this.storage.delete(wordID);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
