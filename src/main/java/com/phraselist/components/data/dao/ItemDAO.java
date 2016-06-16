package com.phraselist.components.data.dao;

import com.phraselist.components.data.mapper.*;
import com.phraselist.entity.user.User;
import com.phraselist.exceptions.login.UserException;
import com.phraselist.model.beans.db.ItemBean;
import com.phraselist.model.beans.db.LanguageBean;
import com.phraselist.model.beans.db.WordBean;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 12.06.2016
 * Created by Rodion.
 */
public class ItemDAO {
    private static final Logger LOG = Logger.getLogger(ItemDAO.class);

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Inject
    private UserDAO userDAO;

    @Inject
    public ItemDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ItemBean> getItems(String originalLanguage, String translationLanguage) {
        ItemMapper itemMapper = new ItemMapper();
        String query = "SELECT i.id, u.login, ow.word, t.tword, i.comment, i.date_of_creation, i.date_of_edition" +
                " FROM items i, users u, original_words ow, translations t, original_languages ol, translated_languages tl " +
                "WHERE i.user_id=u.id AND i.original_id=ow.id AND i.translation_id=t.id AND i.original_language=ol.id " +
                "AND i.translation_language=tl.id " +
                "AND ol.language=:originalLanguage AND tl.tlanguage=:translatedLanguage AND u.login='kolr'";
        Map namedParameters = new HashMap();
        namedParameters.put("originalLanguage", originalLanguage);
        namedParameters.put("translatedLanguage", translationLanguage);
        List<ItemBean> items = jdbcTemplate.query(query, namedParameters, itemMapper);
        return items;
    }

    public void addTranslatedLanguage(String language) {
        String query = "INSERT into translated_languages VALUES(default, :tlanguage)";
        addLanguage(language, query);
    }

    public void addOriginalLanguage(String language) {
        String query = "INSERT into original_languages VALUES(default, :language)";
        addLanguage(language, query);
    }

    private void addLanguage(String language, String query) {
        Map namedParameters = new HashMap();
        namedParameters.put("language", language);
        jdbcTemplate.update(query, namedParameters);
    }

    public LanguageBean getOriginalLanguage(String language) {
        System.out.println("Language: " + language);
        String query = "SELECT * from original_languages WHERE language=:language";
        Map namedParameters = new HashMap();
        namedParameters.put("language", language);
        LanguageBean languageBean = jdbcTemplate.queryForObject(query, namedParameters, new LanguageMapper());
        return languageBean;
    }

    public LanguageBean getTranslatedLanguage(String language) {
        String query = "SELECT * from translated_languages WHERE tlanguage=:language";
        Map namedParameters = new HashMap();
        namedParameters.put("language", language);
        LanguageBean languageBean = jdbcTemplate.queryForObject(query, namedParameters, new TLanguageMapper());
        return languageBean;
    }

    public void addTranslatedWord(String word) {
        String query = "INSERT into translations VALUES(default, :tword)";
        Map namedParameters = new HashMap();
        namedParameters.put("tword", word);
        jdbcTemplate.update(query, namedParameters);
    }

    public void addOriginalWord(String word) {
        String query = "INSERT into original_words VALUES(default, :word)";
        Map namedParameters = new HashMap();
        namedParameters.put("word", word);
        jdbcTemplate.update(query, namedParameters);
    }

    public WordBean getOriginalWord(String word) {
        String query = "SELECT * from original_words WHERE word=:word";
        Map namedParameters = new HashMap();
        namedParameters.put("word", word);
        WordBean wordBean = null;
        try {
            wordBean = jdbcTemplate.queryForObject(query, namedParameters, new WordMapper());
        } catch (EmptyResultDataAccessException ex) {
            LOG.info(ex);
        }
        return wordBean;
    }

    public WordBean getTranslatedWord(String word) {
        String query = "SELECT * from translations WHERE tword=:word";
        Map namedParameters = new HashMap();
        namedParameters.put("word", word);
        WordBean wordBean = null;
        try {
            wordBean = jdbcTemplate.queryForObject(query, namedParameters, new TWordMapper());
        } catch (EmptyResultDataAccessException ex) {
            LOG.info(ex);
        }
        return wordBean;
    }

    public void addItem(ItemBean item, String originalLanguage, String translatedLanguage) throws UserException {
        LanguageBean oLanguage = this.getOriginalLanguage(originalLanguage);
        if (oLanguage == null) {
            this.addOriginalLanguage(originalLanguage);
        }
        LanguageBean tLanguage = this.getTranslatedLanguage(translatedLanguage);
        if (tLanguage == null) {
            this.addTranslatedLanguage(translatedLanguage);
        }
        WordBean oWord = this.getOriginalWord(item.getOriginalWord());
        if (oWord == null) {
            this.addOriginalWord(item.getOriginalWord());
            oWord = this.getOriginalWord(item.getOriginalWord());
        }
        WordBean tWord = this.getTranslatedWord(item.getTranslatedWord());
        if (tWord == null) {
            this.addTranslatedWord(item.getTranslatedWord());
            tWord = this.getTranslatedWord(item.getTranslatedWord());
        }
        User user = userDAO.getUser(item.getLogin());

        String query = "INSERT into ITEMS VALUES(default, :user, :oWord, :tWord, :comment, :dateCr, :dateEd, :oLang, :tLang)";
        Map namedParameters = new HashMap();
        namedParameters.put("user", user.getId());
        namedParameters.put("oWord", oWord.getId());
        namedParameters.put("tWord", tWord.getId());
        namedParameters.put("comment", item.getComment());
        namedParameters.put("dateCr", item.getDateOfCreation());
        namedParameters.put("dateEd", item.getDateOfEdition());
        namedParameters.put("oLang", oLanguage.getId());
        namedParameters.put("tLang", tLanguage.getId());
        jdbcTemplate.update(query, namedParameters);
    }

}
