package com.skilldetective;

public class Question {

    private int questionId;
    private int roleId;
    private String question;
    private String topic;
    private String difficulty;
    private String keywords;

    public Question() {
    }

    public Question(int questionId, int roleId, String question,
                    String topic, String difficulty, String keywords) {

        this.questionId = questionId;
        this.roleId = roleId;
        this.question = question;
        this.topic = topic;
        this.difficulty = difficulty;
        this.keywords = keywords;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}