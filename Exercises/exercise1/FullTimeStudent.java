package com.student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListResourceBundle;

public class FullTimeStudent extends Student{
    private List<Integer> examScores;
    public FullTimeStudent(String name){
        super(name);
        this.examScores = new ArrayList<>(Collections.nCopies(2,0));
    }

    public void setExamScores(List<Integer> scores) {
        if  (scores.size() == 2) {
            this.examScores = scores;
        } else {
            throw new IllegalArgumentException("Need to input 2 exam scores");
        }

    }

    public List<Integer> getExamScores() {
        return examScores;
    }
}