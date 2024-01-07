package com.example.ptbtb;

public class datarating {
    private String username,user_id;
    private String rating;
    private String feedback;
    private String feedbackText;

    // Konstruktor default diperlukan untuk Firebase
    public datarating() {
    }

    public datarating(String user_id, String username, String rating, String feedback, String feedbackText) {
        this.user_id = user_id;
        this.username = username;
        this.rating = rating;
        this.feedback = feedback;
        this.feedbackText = feedbackText;

    }

    public String getUsername() {
        return username;
    }

    public String getRating() {
        return rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}

