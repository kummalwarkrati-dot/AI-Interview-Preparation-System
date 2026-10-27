package com.skilldetective;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin
public class DashboardController {

    private final JdbcTemplate jdbcTemplate;

    public DashboardController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/stats/{userId}")
    public Map<String, Object> getDashboardStats(
            @PathVariable int userId) {

        String sql = """
                SELECT
                    COUNT(*) AS total_interviews,
                    COALESCE(MAX(score), 0) AS best_score,
                    COALESCE(ROUND(AVG(score), 2), 0) AS average_score
                FROM interview_results
                WHERE user_id = ?
                """;

        return jdbcTemplate.queryForMap(sql, userId);
    }
}
