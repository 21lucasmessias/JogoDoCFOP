package utils;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import pergunta.Pergunta;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author cesar
 */
public class perguntasAleatorio implements ordemPerguntasStrategy {

    @Override
    public void ordenarPerguntas(ArrayList<Pergunta> perguntas) {
        Collections.shuffle(perguntas);
    }

}
