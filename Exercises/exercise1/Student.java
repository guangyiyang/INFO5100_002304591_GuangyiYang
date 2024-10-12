package com.student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student {
    private String name;
    private List<Integer> quizScores;

    public Student(String name) {
        this.name = name;
        this.quizScores = new ArrayList<>(Collections.nCopies(15, 0));
    }

    public String getName() {
        return name;
    }

    public void setQuizScores(List<Integer> scores) {
        if (scores.size() == 15) {
            this.quizScores = scores;
        } else {
            throw new IllegalArgumentException("Need to input 15 quiz scores");
        }
    }

    public double calculateAverageQuizScore(){
        return quizScores.stream().mapToInt(Integer::intValue).average().orElse(0);
    }


    public List<Integer> getQuizScores() {
        return quizScores;

    }
}
