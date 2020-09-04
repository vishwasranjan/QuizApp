package com.example.quizapp;

public class QuizModel {
    int mQuestions;
    Boolean mAnswers;
    QuizModel(int Question,Boolean Answer)
    {
        mQuestions=Question;
        mAnswers=Answer;
    }
    public void setQuestion(int Question)
    {
        mQuestions=Question;
    }
    public int getQuestion()
    {
        return mQuestions;
    }
    public void setAnswer(Boolean Answer)
    {
        mAnswers=Answer;
    }
    public Boolean getmAnswers()
    {
        return mAnswers;
    }
}
