package com.skilldetective;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin
public class JobRoleController {

    private final JobRoleRepository jobRoleRepository;

    public JobRoleController(JobRoleRepository jobRoleRepository) {
        this.jobRoleRepository = jobRoleRepository;
    }

    @GetMapping
    public List<JobRole> getAllRoles() {
        return jobRoleRepository.getAllRoles();
    }
}
