package utils;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import questions.Question;

import java.util.ArrayList;


public class sortContext {

    private questionsSortStrategy strategy;

    public void setOrdemStrategy(questionsSortStrategy strategy) {
        this.strategy = strategy;
    }

    public void createList(ArrayList<Question> questions) {
        strategy.sortQuestions(questions);
    }
}
