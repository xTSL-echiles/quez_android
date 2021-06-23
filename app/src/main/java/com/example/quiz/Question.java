package com.example.quiz;

public class Question {
    private int question;
    private boolean answer;

    public Question(int question, boolean b) {
        this.question = question;
        this.answer = b;
    }

    public int getQuestion() {
        return question;
    }

    public boolean isAnswer() {
        return answer;
    }
}
