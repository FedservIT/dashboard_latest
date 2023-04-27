package com.fedserv.pulse_dashboard.model;

import java.util.List;

public class QuizData {
    List<Quiz> data;
    private String isActive;

    private String published_date;
   private String quiz_id;

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(String quiz_id) {
        this.quiz_id = quiz_id;
    }

    public List<Quiz> getData() {
        return data;
    }

    public void setData(List<Quiz> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "QuizData{" +
                "data=" + data +
                ", isActive='" + isActive + '\'' +
                ", published_date='" + published_date + '\'' +
                ", quiz_id='" + quiz_id + '\'' +
                '}';
    }
}
