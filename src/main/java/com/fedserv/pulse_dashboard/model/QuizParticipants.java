package com.fedserv.pulse_dashboard.model;

public class QuizParticipants {
    String answeredTime;
    String empID;
    String score;

    public String getAnsweredTime() {
        return answeredTime;
    }

    public void setAnsweredTime(String answeredTime) {
        this.answeredTime = answeredTime;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
