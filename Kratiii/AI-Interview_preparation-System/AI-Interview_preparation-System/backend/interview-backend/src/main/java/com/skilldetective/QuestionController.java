package com.skilldetective;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin
public class QuestionController {

    private final QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping("/role/{roleId}")
    public List<Question> getQuestionsByRole(@PathVariable int roleId) {
        return questionRepository.getQuestionsByRole(roleId);
    }
}
