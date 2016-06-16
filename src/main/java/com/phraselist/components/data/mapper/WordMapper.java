package com.phraselist.components.data.mapper;

import com.phraselist.model.beans.db.LanguageBean;
import com.phraselist.model.beans.db.WordBean;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 13.06.2016
 * Created by Rodion.
 */
public class WordMapper implements RowMapper<WordBean> {

    public WordBean mapRow(ResultSet resultSet, int i) throws SQLException {
        WordBean wordBean = new WordBean();
        wordBean.setId(resultSet.getLong("id"));
        wordBean.setWord(resultSet.getString("word"));
        return wordBean;
    }
}
