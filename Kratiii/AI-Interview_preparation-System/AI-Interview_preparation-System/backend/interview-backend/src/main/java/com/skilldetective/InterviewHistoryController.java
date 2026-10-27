package com.skilldetective;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/interview")
@CrossOrigin
public class InterviewHistoryController {

    private final JdbcTemplate jdbcTemplate;

    public InterviewHistoryController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/history/{userId}")
    public List<Map<String, Object>> getInterviewHistory(
            @PathVariable int userId) {

        String sql = """
                SELECT ir.result_id,
                       jr.role_name,
                       ir.score,
                       ir.interview_date
                FROM interview_results ir
                JOIN job_roles jr
                    ON ir.role_id = jr.role_id
                WHERE ir.user_id = ?
                ORDER BY ir.interview_date DESC
                """;

        return jdbcTemplate.queryForList(sql, userId);
    }
}
