package com.phraselist.components.data.mapper;

import com.phraselist.entity.user.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 19.05.2016
 * Created by Rodion.
 */
public class UserMapper implements RowMapper<User> {

    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        return null;
    }
}
