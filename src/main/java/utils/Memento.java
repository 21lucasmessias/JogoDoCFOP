package utils;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.time.Instant;

/**
 *
 * @author cesar
 */
public class Memento {

    private Instant actionTime;

    public Memento(Instant action) {
        this.actionTime = action;
    }

    public Instant getActionTime() {
        return actionTime;
    }
}
