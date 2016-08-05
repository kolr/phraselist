package com.phraselist.components.controllers;

import com.phraselist.components.services.PhraseService;
import com.phraselist.exceptions.phrase.PhraseListException;
import com.phraselist.model.beans.db.ItemBean;
import com.phraselist.model.beans.user.ClientUserBeanCommon;
import com.phraselist.storage.Storage;
import com.phraselist.storage.Word;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
    private PhraseService phraseService;

    @Inject
    public PhraseController(Storage storage) {
        this.storage = storage;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ItemBean> addWord(HttpServletRequest request,
                                            @RequestBody Word word, @PathVariable String language) {
        ClientUserBeanCommon user = (ClientUserBeanCommon) request.getSession().getAttribute("user");
        ItemBean item;
        try {
            item = phraseService.addWord(word, user.getLogin(), language);
        } catch (PhraseListException e) {
            LOG.error(e);
            return new ResponseEntity<ItemBean>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<ItemBean>(item, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<ItemBean> getListWord(HttpServletRequest request, @PathVariable String language) {
        ClientUserBeanCommon user = (ClientUserBeanCommon) request.getSession().getAttribute("user");
        if (user != null) {
            LOG.info(String.format("Current user is %s %s.", user.getName(), user.getLastname()));
            return phraseService.getListOfWords(language, user.getLogin());
        } else {
            LOG.info("Guest is using this vocabulary.");
        }
        return this.storage.getAll();
    }

    @RequestMapping(value = "/{wordID}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteWord(@PathVariable long wordID) {
        phraseService.deleteItem(wordID);
        return new ResponseEntity<String>(String.valueOf(wordID), HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public ResponseEntity<String> deleteWords(@RequestBody List<String> markedItems) {
        phraseService.deleteItems(markedItems);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

}
