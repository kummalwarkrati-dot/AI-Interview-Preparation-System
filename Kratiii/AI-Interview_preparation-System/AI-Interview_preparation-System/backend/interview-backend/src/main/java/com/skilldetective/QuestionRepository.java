package com.skilldetective;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionRepository {

    private final JdbcTemplate jdbcTemplate;

    public QuestionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Question> getQuestionsByRole(int roleId) {

        String sql = """
                SELECT question_id, role_id, question, topic, difficulty, keywords
                FROM questions
                WHERE role_id = ?
                ORDER BY question_id
                """;

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {

                    Question q = new Question();

                    q.setQuestionId(rs.getInt("question_id"));
                    q.setRoleId(rs.getInt("role_id"));
                    q.setQuestion(rs.getString("question"));
                    q.setTopic(rs.getString("topic"));
                    q.setDifficulty(rs.getString("difficulty"));
                    q.setKeywords(rs.getString("keywords"));

                    return q;
                },
                roleId
        );
    }
}