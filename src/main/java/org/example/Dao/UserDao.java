package org.example.Dao;

import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public User login(String name, String password){
        return jdbcTemplate.query("SELECT * FROM Users Where name=? and password=?",
                new Object[]{name, password},
                new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
    }

    public void registration(String name, String password){
        jdbcTemplate.update("INSERT INTO Users( name, password)  VALUES (?,?)", name, password);
    }
}
