package com.phraselist.components.data.mapper;

import com.phraselist.model.beans.db.LanguageBean;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 13.06.2016
 * Created by Rodion.
 */
public class TLanguageMapper implements RowMapper<LanguageBean> {

    public LanguageBean mapRow(ResultSet resultSet, int i) throws SQLException {
        LanguageBean languageBean = new LanguageBean();
        languageBean.setId(resultSet.getLong("id"));
        languageBean.setLanguage(resultSet.getString("tlanguage"));
        return languageBean;
    }
}
