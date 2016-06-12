package com.phraselist.components.controllers;

import com.phraselist.components.data.dao.ItemDAO;
import com.phraselist.components.data.dao.UserDAO;
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
    private UserDAO userDAO;

    @Inject
    private ItemDAO itemDAO;

    @Inject
    public PhraseController(Storage storage) {
        this.storage = storage;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Word> addWord(@RequestBody Word word, @PathVariable String language) {
        LOG.info(language);
        word.setId(Word.generateId());
        this.storage.add(word);
        return new ResponseEntity<Word>(word, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Word> getListWord(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            ClientUserBeanCommon user = (ClientUserBeanCommon) request.getSession().getAttribute("user");
            LOG.info(String.format("Current user is %s %s.", user.getName(), user.getLastname()));
        } else {
            LOG.info("Guest is using this vocabulary.");
        }
        return this.storage.getAll();
    }

    @RequestMapping(value = "/{wordID}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteWord(@PathVariable long wordID) {
        this.storage.delete(wordID);
        return new ResponseEntity<String>(String.valueOf(wordID), HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public ResponseEntity<String> deleteWords(@RequestBody List<String> markedItems) {
        for (String item : markedItems) {
            this.storage.delete(Long.valueOf(item));
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value = "/label/{name}", method = RequestMethod.POST)
    public ResponseEntity<String> addLabel(@PathVariable String name) {
        userDAO.insertLabel(name);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value = "/getall/{ol}/{tl}", method = RequestMethod.GET)
    @ResponseBody
    public List<ItemBean> getAll(HttpServletRequest request, @PathVariable String ol, @PathVariable String tl) {
        return this.itemDAO.getItems(ol, tl);
    }

}
