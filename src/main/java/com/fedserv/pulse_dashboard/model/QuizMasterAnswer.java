package com.fedserv.pulse_dashboard.model;

import java.util.List;

public class QuizMasterAnswer {
    List<QuizParticipants> attemptedUsers;

    public List<QuizParticipants> getAttemptedUsers() {
        return attemptedUsers;
    }

    public void setAttemptedUsers(List<QuizParticipants> attemptedUsers) {
        this.attemptedUsers = attemptedUsers;
    }
}
