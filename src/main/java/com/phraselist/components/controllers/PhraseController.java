package com.phraselist.components.controllers;

import com.phraselist.components.data.dao.ItemDAO;
import com.phraselist.components.services.user.PhraseService;
import com.phraselist.exceptions.login.UserException;
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
import java.util.Date;
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
    private ItemDAO itemDAO;

    @Inject
    private PhraseService phraseService;

    @Inject
    public PhraseController(Storage storage) {
        this.storage = storage;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ItemBean> addWord(HttpServletRequest request,
                                            @RequestBody Word word, @PathVariable String language) {
        ItemBean item = new ItemBean();
        item.setForeign(word.getForeign());
        item.setTranslation(word.getTranslation());
        ClientUserBeanCommon user = (ClientUserBeanCommon) request.getSession().getAttribute("user");
        if (user == null) {
            LOG.error("Guest has no rights to add words.");
            return new ResponseEntity<ItemBean>(HttpStatus.NOT_ACCEPTABLE);
        }
        item.setLogin(user.getLogin());
        item.setComment("none");
        item.setDateOfCreation(new Date());
        item.setDateOfEdition(new Date());
        try {
            itemDAO.addItem(item, language, "russian");
        } catch (UserException ex) {
            LOG.error(ex);
        }

        return new ResponseEntity<ItemBean>(item, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<ItemBean> getListWord(HttpServletRequest request, @PathVariable String language) {
        ClientUserBeanCommon user = null;
        if (request.getSession().getAttribute("user") != null) {
            user = (ClientUserBeanCommon) request.getSession().getAttribute("user");
            LOG.info(String.format("Current user is %s %s.", user.getName(), user.getLastname()));
            return this.itemDAO.getUsersItems(language, "russian", user.getLogin());
        } else {
            LOG.info("Guest is using this vocabulary.");
        }
        return this.storage.getAll();
    }

    @RequestMapping(value = "/{wordID}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteWord(@PathVariable long wordID) {
        this.itemDAO.deleteWord(wordID);
        return new ResponseEntity<String>(String.valueOf(wordID), HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public ResponseEntity<String> deleteWords(@RequestBody List<String> markedItems) {
        for (String item : markedItems) {
            this.itemDAO.deleteWord(Long.valueOf(item));
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }

}
