package com.phraselist.components.data.dao;

import com.phraselist.components.data.mapper.*;
import com.phraselist.entity.user.User;
import com.phraselist.exceptions.login.UserException;
import com.phraselist.model.beans.db.ItemBean;
import com.phraselist.model.beans.db.LanguageBean;
import com.phraselist.model.beans.db.WordBean;
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
    private NamedParameterJdbcTemplate jdbcTemplate;

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

    public void addTranslatedLanguage(LanguageBean languageBean) {
        String query = "INSERT into translated_languages VALUES(default, :tlanguage)";
        addLanguage(languageBean, query);
    }

    public void addOriginalLanguage(LanguageBean languageBean) {
        String query = "INSERT into original_languages VALUES(default, :language)";
        addLanguage(languageBean, query);
    }

    private void addLanguage(LanguageBean languageBean, String query) {
        Map namedParameters = new HashMap();
        namedParameters.put("language", languageBean.getLanguage());
        jdbcTemplate.update(query, namedParameters);
    }

    public LanguageBean getOriginalLanguage(String language) {
        String query = "SELECT from original_languages WHERE language=:language";
        Map namedParameters = new HashMap();
        namedParameters.put("language", language);
        LanguageBean languageBean = jdbcTemplate.queryForObject(query, namedParameters, new LanguageMapper());
        return languageBean;
    }

    public LanguageBean getTranslatedLanguage(String language) {
        String query = "SELECT from translated_languages WHERE language=:language";
        Map namedParameters = new HashMap();
        namedParameters.put("language", language);
        LanguageBean languageBean = jdbcTemplate.queryForObject(query, namedParameters, new TLanguageMapper());
        return languageBean;
    }

    public void addTranslatedWord(WordBean wordBean) {
        String query = "INSERT into translated_languages VALUES(default, :tlanguage)";
        addWord(wordBean, query);
    }

    public void addOriginalWord(WordBean wordBean) {
        String query = "INSERT into original_languages VALUES(default, :language)";
        addWord(wordBean, query);
    }

    private void addWord(WordBean wordBean, String query) {
        Map namedParameters = new HashMap();
        namedParameters.put("language", wordBean.getWord());
        jdbcTemplate.update(query, namedParameters);
    }

    public WordBean getOriginalWord(String word) {
        String query = "SELECT from original_words WHERE word=:word";
        Map namedParameters = new HashMap();
        namedParameters.put("word", word);
        WordBean wordBean = jdbcTemplate.queryForObject(query, namedParameters, new WordMapper());
        return wordBean;
    }

    public WordBean getTranslatedWord(String word) {
        String query = "SELECT from translations WHERE tword=:word";
        Map namedParameters = new HashMap();
        namedParameters.put("word", word);
        WordBean wordBean = jdbcTemplate.queryForObject(query, namedParameters, new TWordMapper());
        return wordBean;
    }

}
