package com.phraselist.components.data.dao;

import com.phraselist.components.data.mapper.UserMapper;
import com.phraselist.entity.user.User;
import com.phraselist.exceptions.login.UserException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * 18.04.2016
 * Created by Rodion.
 */
public class UserDAO {
    private static final String USER_NOT_EXISTS = "User with login \"%s\" does not exists.";

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Inject
    public UserDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertUser(User user) {
        String query = "INSERT INTO USERS (pass, email, login, name, lastname, role_id) " +
                "VALUES (:pass, :email, :login, :name, :lastname, :role_id)";
        Map namedParameters = new HashMap();
        namedParameters.put("pass", user.getPass());
        namedParameters.put("email", user.getEmail());
        namedParameters.put("login", user.getLogin());
        namedParameters.put("name", user.getName());
        namedParameters.put("lastname", user.getLastName());
        namedParameters.put("role_id", user.getRoleId());
        jdbcTemplate.update(query, namedParameters);
    }

    public User getUser(String login) throws UserException {
        String query = "SELECT * FROM USERS WHERE login=:login";
        Map namedParameters = new HashMap();
        namedParameters.put("login", login);
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(query, namedParameters, new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new UserException(String.format(USER_NOT_EXISTS, login));
        }
        return user;
    }

    public void insertLabel(String label) {
        String query = "INSERT INTO LABELS (LABEL) VALUES (:label)";
        Map namedParameters = new HashMap();
        namedParameters.put("label", label);
        jdbcTemplate.update(query, namedParameters);
    }

}
