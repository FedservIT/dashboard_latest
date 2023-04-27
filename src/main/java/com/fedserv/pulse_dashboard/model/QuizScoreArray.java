package com.fedserv.pulse_dashboard.model;

import java.util.List;

public class QuizScoreArray {
    public List<QuizScore> getQuizScore() {
        return quizScore;
    }
    public void setQuizScore(List<QuizScore> quizScore) {
        this.quizScore = quizScore;
    }
    List<QuizScore> quizScore;
}
