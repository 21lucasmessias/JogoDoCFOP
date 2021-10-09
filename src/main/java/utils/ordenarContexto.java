package utils;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import pergunta.Pergunta;

import java.util.ArrayList;

/**
 *
 * @author cesar
 */
public class ordenarContexto {

    private ordemPerguntasStrategy strategy;

    public void setOrdemStrategy(ordemPerguntasStrategy strategy) {
        this.strategy = strategy;
    }

    public void criarLista(ArrayList<Pergunta> perguntas) {
        strategy.ordenarPerguntas(perguntas);
    }
}
