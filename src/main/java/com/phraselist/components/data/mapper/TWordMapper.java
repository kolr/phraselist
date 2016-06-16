package com.phraselist.components.data.mapper;

import com.phraselist.model.beans.db.WordBean;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 13.06.2016
 * Created by Rodion.
 */
public class TWordMapper implements RowMapper<WordBean> {

    public WordBean mapRow(ResultSet resultSet, int i) throws SQLException {
        WordBean wordBean = new WordBean();
        wordBean.setId(resultSet.getLong("id"));
        wordBean.setWord(resultSet.getString("tword"));
        return wordBean;
    }
}
