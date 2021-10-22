package utils;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import questions.Question;

import java.util.ArrayList;
import java.util.Collections;



public class questionsRandom implements questionsSortStrategy {

    @Override
    public void sortQuestions(ArrayList<Question> questions) {
        Collections.shuffle(questions);
    }

}
