package com.skilldetective;

import java.util.List;

public class InterviewResult {

   private int userId;
private int roleId;
private List<AnswerData> answers;

    public InterviewResult() {
    }
public int getUserId() {
    return userId;
}

public void setUserId(int userId) {
    this.userId = userId;
}
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public List<AnswerData> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerData> answers) {
        this.answers = answers;
    }
}
