package com.skilldetective;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveUser(User user) {

        String sql = """
                INSERT INTO users (name, email, password)
                VALUES (?, ?, ?)
                """;

        return jdbcTemplate.update(
                sql,
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public User loginUser(String email, String password) {

        String sql = """
                SELECT user_id, name, email, password
                FROM users
                WHERE email = ? AND password = ?
                """;

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> {
                        User user = new User();

                        user.setUserId(rs.getInt("user_id"));
                        user.setName(rs.getString("name"));
                        user.setEmail(rs.getString("email"));
                        user.setPassword(rs.getString("password"));

                        return user;
                    },
                    email,
                    password
            );

        } catch (Exception e) {
            return null;
        }
    }
}