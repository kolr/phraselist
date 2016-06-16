package com.phraselist.components.data.mapper;

import com.phraselist.model.beans.db.ItemBean;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 12.06.2016
 * Created by Rodion.
 */
public class ItemMapper implements RowMapper<ItemBean> {

    public ItemBean mapRow(ResultSet resultSet, int i) throws SQLException {
        ItemBean itemBean = new ItemBean();
        itemBean.setId(resultSet.getLong("id"));
        itemBean.setLogin(resultSet.getString("login"));
        itemBean.setForeign(resultSet.getString("word"));
        itemBean.setTranslation(resultSet.getString("tword"));
        itemBean.setComment(resultSet.getString("comment"));
        itemBean.setDateOfCreation(resultSet.getDate("date_of_creation"));
        itemBean.setDateOfEdition(resultSet.getDate("date_of_edition"));
        return itemBean;
    }
}
