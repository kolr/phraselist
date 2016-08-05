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
import java.util.Date;

/**
 * 04.08.2016
 * Created by Rodion.
 */
public class PhraseServiceImpl implements PhraseService {
    private static final Logger LOG = Logger.getLogger(PhraseServiceImpl.class);

    private static final String NOT_VALID_ERROR = "Words are not valid.";

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
            Item temp = phraseDAO.addItem(item, language, "russian");
            item = new ItemBean.Builder().inItemBean(item).id(temp.getId()).build();
        } catch (UserException ex) {
            LOG.error(ex);
            throw new PhraseListException(ex);
        }
        return item;
    }

    private ItemBean getItemBean(Word word, String userLogin) {
        return new ItemBean.Builder().foreign(word.getForeign()).translation(word.getTranslation())
                .login(userLogin).comment("none").dateOfCreation(new Date()).dateOfEdition(new Date()).build();
    }
}
