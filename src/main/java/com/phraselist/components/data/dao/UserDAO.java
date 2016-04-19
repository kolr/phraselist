package com.phraselist.components.data.dao;

import com.phraselist.entity.label.Label;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * 18.04.2016
 * Created by Rodion.
 */
public class UserDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Inject
    public UserDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertLabel(String label) {
        String query = "INSERT INTO LABELS (LABEL) VALUES (:label)";
        Map namedParameters = new HashMap();
        namedParameters.put("label", label);
        jdbcTemplate.update(query, namedParameters);
    }

}
