package com.phraselist.components.services.impl;

import com.phraselist.components.dao.user.PhraseDAO;
import com.phraselist.components.data.hbnt.entities.Item;
import com.phraselist.components.services.PhraseService;
import com.phraselist.exceptions.login.UserException;
import com.phraselist.exceptions.phrase.PhraseListException;
import com.phraselist.model.beans.db.ItemBean;
import com.phraselist.storage.Word;
import com.phraselist.validation.ValidationManager;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 04.08.2016
 * Created by Rodion.
 */
public class PhraseServiceImpl implements PhraseService {
    private static final Logger LOG = Logger.getLogger(PhraseServiceImpl.class);

    private static final String NOT_VALID_ERROR = "Words are not valid.";
    private static final String TRANSLATED_LANGUAGE = "russian";

    @Inject
    private ValidationManager validationManager;

    @Inject
    private PhraseDAO phraseDAO;

    public ItemBean addWord(Word word, String userLogin, String language) throws PhraseListException {
        if (!validationManager.validate(word, Word.class)) {
            LOG.error(NOT_VALID_ERROR);
            throw new PhraseListException(NOT_VALID_ERROR);
        }
        ItemBean item = getItemBean(word, userLogin);
        try {
            Item temp = phraseDAO.addItem(item, language, TRANSLATED_LANGUAGE);
            item = new ItemBean.Builder().inItemBean(item).id(temp.getId()).build();
        } catch (UserException ex) {
            LOG.error(ex);
            throw new PhraseListException(ex);
        }
        return item;
    }

    public List<ItemBean> getListOfWords(String language, String login) {
        return convertToItemBean(phraseDAO.getUsersItems(language, TRANSLATED_LANGUAGE, login));
    }

    public void deleteItem(long id) {
        phraseDAO.deleteItem(id);
    }

    public void deleteItems(List<String> markedItems) {
        for (String item : markedItems) {
            phraseDAO.deleteItem(Long.valueOf(item));
        }
    }

    public List<ItemBean> searching(String language, String login, String keyWord) {
        if (keyWord.matches("[a-zA-Zа-яА-Я]{1,30}")) {
            List<ItemBean> wordContainer = getListOfWords(language, login);
            LOG.info("Key Word ==> " + keyWord);
            return searchString(keyWord, wordContainer);
        }
        return null;
    }

    private List<ItemBean> searchString(String keyWord, List<ItemBean> wordContainer) {
        List<ItemBean> wordContainerResult = new ArrayList<ItemBean>();
        for (ItemBean item : wordContainer) {
            if (item.getForeign().toLowerCase().contains(keyWord) ||
                    item.getTranslation().toLowerCase().contains(keyWord)) {
                wordContainerResult.add(item);
            }
        }
        return wordContainerResult;
    }

    private ItemBean getItemBean(Word word, String userLogin) {
        return new ItemBean.Builder().foreign(word.getForeign()).translation(word.getTranslation())
                .login(userLogin).comment("none").dateOfCreation(new Date()).dateOfEdition(new Date()).build();
    }

    private List<ItemBean> convertToItemBean(List<Item> lst) {
        List<ItemBean> result = new ArrayList<ItemBean>();
        for (Item item : lst) {
            ItemBean temp = new ItemBean.Builder().id(item.getId()).foreign(item.getOriginalWord().getWord())
                    .translation(item.getTranslation().getWord()).login(item.getUser().getLogin()).comment(item.getComment())
                    .dateOfCreation(item.getDateOfCreation()).dateOfEdition(item.getDateOfEdition()).build();
            result.add(temp);
        }
        return result;
    }
}
