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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 12.06.2016
 * Created by Rodion.
 */
public class ItemDAO {
//    private static final Logger LOG = Logger.getLogger(ItemDAO.class);

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
                "AND ol.language=:originalLanguage AND tl.tlanguage=:translatedLanguage";
        Map namedParameters = new HashMap();
        namedParameters.put("originalLanguage", originalLanguage);
        namedParameters.put("translatedLanguage", translationLanguage);
        return jdbcTemplate.query(query, namedParameters, itemMapper);
    }

    public List<ItemBean> getUsersItems(String originalLanguage, String translationLanguage, String login) {
        ItemMapper itemMapper = new ItemMapper();
        String query = "SELECT i.id, u.login, ow.word, t.tword, i.comment, i.date_of_creation, i.date_of_edition" +
                " FROM items i, users u, original_words ow, translations t, original_languages ol, translated_languages tl " +
                "WHERE i.user_id=u.id AND i.original_id=ow.id AND i.translation_id=t.id AND i.original_language=ol.id " +
                "AND i.translation_language=tl.id " +
                "AND ol.language=:originalLanguage AND tl.tlanguage=:translatedLanguage AND u.login=:login";
        Map namedParameters = new HashMap();
        namedParameters.put("originalLanguage", originalLanguage);
        namedParameters.put("translatedLanguage", translationLanguage);
        namedParameters.put("login", login);
        return jdbcTemplate.query(query, namedParameters, itemMapper);
    }

    public void deleteWord(long id) {
        String query = "DELETE FROM items WHERE id=:id";
        Map namedParameters = new HashMap();
        namedParameters.put("id", id);
        jdbcTemplate.update(query, namedParameters);
    }

    private LanguageBean putTranslatedLanguage(String language) {
        String getQuery = "SELECT * from translated_languages WHERE tlanguage=:tlanguage";
        String addQuery = "INSERT into translated_languages VALUES(default, :tlanguage)";
        LanguageBean languageBean;
        Map namedParameters = new HashMap();
        namedParameters.put("tlanguage", language);
        try {
            languageBean = jdbcTemplate.queryForObject(getQuery, namedParameters, new TLanguageMapper());
        } catch (EmptyResultDataAccessException ex) {
            jdbcTemplate.update(addQuery, namedParameters);
            languageBean = jdbcTemplate.queryForObject(getQuery, namedParameters, new TLanguageMapper());
        }
        return languageBean;
    }

    private LanguageBean putOriginalLanguage(String language) {
        String getQuery = "SELECT * from original_languages WHERE language=:language";
        String addQuery = "INSERT into original_languages VALUES(default, :language)";
        LanguageBean languageBean;
        Map namedParameters = new HashMap();
        namedParameters.put("language", language);
        try {
            languageBean = jdbcTemplate.queryForObject(getQuery, namedParameters, new LanguageMapper());
        } catch (EmptyResultDataAccessException ex) {
            jdbcTemplate.update(addQuery, namedParameters);
            languageBean = jdbcTemplate.queryForObject(getQuery, namedParameters, new LanguageMapper());
        }
        return languageBean;
    }

    private WordBean putTranslatedWord(String word) {
        String getQuery = "SELECT * from translations WHERE tword=:tword";
        String addQuery = "INSERT into translations VALUES(default, :tword)";
        WordBean wordBean;
        Map namedParameters = new HashMap();
        namedParameters.put("tword", word);
        try {
            wordBean = jdbcTemplate.queryForObject(getQuery, namedParameters, new TWordMapper());
        } catch (EmptyResultDataAccessException ex) {
            jdbcTemplate.update(addQuery, namedParameters);
            wordBean = jdbcTemplate.queryForObject(getQuery, namedParameters, new TWordMapper());
        }
        return wordBean;
    }

    private WordBean putOriginalWord(String word) {
        String getQuery = "SELECT * from original_words WHERE word=:word";
        String addQuery = "INSERT into original_words VALUES(default, :word)";
        WordBean wordBean;
        Map namedParameters = new HashMap();
        namedParameters.put("word", word);
        try {
            wordBean = jdbcTemplate.queryForObject(getQuery, namedParameters, new WordMapper());
        } catch (EmptyResultDataAccessException ex) {
            jdbcTemplate.update(addQuery, namedParameters);
            wordBean = jdbcTemplate.queryForObject(getQuery, namedParameters, new WordMapper());
        }
        return wordBean;
    }

    public void addItem(ItemBean item, String originalLanguage, String translatedLanguage) throws UserException {
        LanguageBean oLanguage = putOriginalLanguage(originalLanguage);
        LanguageBean tLanguage = putTranslatedLanguage(translatedLanguage);
        WordBean oWord = putOriginalWord(item.getForeign());
        WordBean tWord = putTranslatedWord(item.getTranslation());
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
