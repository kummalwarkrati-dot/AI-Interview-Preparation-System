package com.skilldetective;

import org.springframework.stereotype.Service;
import java.util.Locale;

@Service
public class AnswerEvaluator {

    public int evaluateAnswer(String answer, String keywords) {

        if (answer == null || answer.trim().isEmpty()) {
            return 0;
        }

        if (keywords == null || keywords.trim().isEmpty()) {
            return 0;
        }

        String userAnswer = normalize(answer);
        String[] keywordList = keywords.split(",");

        int totalKeywords = 0;
        int matchedKeywords = 0;

        for (String keyword : keywordList) {

            keyword = normalize(keyword);

            if (keyword.isEmpty()) {
                continue;
            }

            totalKeywords++;

            // Match complete words or phrases, not partial words.
            String searchableAnswer = " " + userAnswer + " ";
            String searchableKeyword = " " + keyword + " ";

            if (searchableAnswer.contains(searchableKeyword)) {
                matchedKeywords++;
            }
        }

        if (totalKeywords == 0) {
            return 0;
        }

        double percentage =
                ((double) matchedKeywords / totalKeywords) * 100;

        return (int) Math.round(percentage);
    }

    private String normalize(String text) {

        return text.toLowerCase(Locale.ROOT)
                   .replaceAll("[^a-z0-9+#.]+", " ")
                   .trim()
                   .replaceAll("\\s+", " ");
    }
}