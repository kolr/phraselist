package com.phraselist.components.data.dao;

import com.phraselist.components.data.mapper.ItemMapper;
import com.phraselist.components.data.mapper.UserMapper;
import com.phraselist.entity.user.User;
import com.phraselist.exceptions.login.UserException;
import com.phraselist.model.beans.db.ItemBean;
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
        List<ItemBean>items = jdbcTemplate.query(query, namedParameters, itemMapper);
        return items;
    }

}
