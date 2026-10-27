package com.skilldetective;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JobRoleRepository {

    private final JdbcTemplate jdbcTemplate;

    public JobRoleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<JobRole> getAllRoles() {

        String sql = """
                SELECT role_id, role_name
                FROM job_roles
                ORDER BY role_id
                """;

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {
                    JobRole role = new JobRole();

                    role.setRoleId(rs.getInt("role_id"));
                    role.setRoleName(rs.getString("role_name"));

                    return role;
                }
        );
    }
}
