package com.skilldetective;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InterviewResultRepository {

    private final JdbcTemplate jdbcTemplate;

    public InterviewResultRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveResult(int userId, int roleId, double score) {

        String sql = """
                INSERT INTO interview_results (user_id, role_id, score)
                VALUES (?, ?, ?)
                """;

        return jdbcTemplate.update(
                sql,
                userId,
                roleId,
                score
        );
    }
}
