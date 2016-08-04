package com.phraselist.components.controllers;

import com.phraselist.components.data.hbnt.entities.Item;
import com.phraselist.components.dao.user.PhraseDAO;
import com.phraselist.exceptions.login.UserException;
import com.phraselist.model.beans.db.ItemBean;
import com.phraselist.model.beans.user.ClientUserBeanCommon;
import com.phraselist.storage.Storage;
import com.phraselist.storage.Word;
import com.phraselist.validation.ValidationManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    private PhraseDAO phraseDAO;

    @Inject
    private ValidationManager validationManager;

    @Inject
    public PhraseController(Storage storage) {
        this.storage = storage;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ItemBean> addWord(HttpServletRequest request,
                                            @RequestBody Word word, @PathVariable String language) {
        LOG.info(word);
        if (!validationManager.validate(word, Word.class)) {
            LOG.error("words are not valid");
            return new ResponseEntity<ItemBean>(HttpStatus.NOT_ACCEPTABLE);
        }
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
            Item temp = phraseDAO.addItem(item, language, "russian");
            item.setId(temp.getId());
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
            return convertToItemBean(phraseDAO.getUsersItems(language, "russian", user.getLogin()));
        } else {
            LOG.info("Guest is using this vocabulary.");
        }
        return this.storage.getAll();
    }

    @RequestMapping(value = "/{wordID}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteWord(@PathVariable long wordID) {
        phraseDAO.deleteItem(wordID);
        return new ResponseEntity<String>(String.valueOf(wordID), HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public ResponseEntity<String> deleteWords(@RequestBody List<String> markedItems) {
        for (String item : markedItems) {
            phraseDAO.deleteItem(Long.valueOf(item));
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    private List<ItemBean> convertToItemBean(List<Item> lst) {
        List<ItemBean> result = new ArrayList<ItemBean>();
        for (Item item : lst) {
            ItemBean temp = new ItemBean();
            temp.setId(item.getId());
            temp.setDateOfCreation(item.getDateOfCreation());
            temp.setComment(item.getComment());
            temp.setLogin(item.getUser().getLogin());
            temp.setForeign(item.getOriginalWord().getWord());
            temp.setTranslation(item.getTranslation().getWord());
            temp.setDateOfEdition(item.getDateOfEdition());
            result.add(temp);
        }
        return result;
    }

}
