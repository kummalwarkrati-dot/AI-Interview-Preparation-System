
package com.skilldetective;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/interview")
@CrossOrigin
public class InterviewController {

    private final QuestionRepository questionRepository;
    private final AnswerEvaluator answerEvaluator;
    private final InterviewResultRepository interviewResultRepository;

    public InterviewController(
            QuestionRepository questionRepository,
            AnswerEvaluator answerEvaluator,
            InterviewResultRepository interviewResultRepository) {

        this.questionRepository = questionRepository;
        this.answerEvaluator = answerEvaluator;
        this.interviewResultRepository = interviewResultRepository;
    }

    @PostMapping("/submit")
    public String submitInterview(@RequestBody InterviewResult result) {

        List<AnswerData> answers = result.getAnswers();

        if (answers == null || answers.isEmpty()) {
            return "No answers submitted.";
        }

        int totalScore = 0;
        int answeredQuestions = 0;

        List<Question> questions =
                questionRepository.getQuestionsByRole(result.getRoleId());

        for (AnswerData answerData : answers) {

            for (Question question : questions) {

                if (question.getQuestionId() == answerData.getQuestionId()) {

                    int score = answerEvaluator.evaluateAnswer(
                            answerData.getAnswer(),
                            question.getKeywords()
                    );

                    System.out.println("Question: " + question.getQuestion());
                    System.out.println("Keywords: " + question.getKeywords());
                    System.out.println("User Answer: " + answerData.getAnswer());
                    System.out.println("Score: " + score);

                    totalScore += score;
                    answeredQuestions++;

                    break;
                }
            }
        }

        if (answeredQuestions == 0) {
            return "Unable to evaluate answers.";
        }

        int finalScore = totalScore / answeredQuestions;

        interviewResultRepository.saveResult(
                result.getUserId(),
                result.getRoleId(),
                finalScore
        );

        return "Interview Score: " + finalScore + "%";
    }
}
