package com.command;

import com.models.Unit;

public class Mover {

    public Mover(){

    }
    public void move(Unit unit, int x, int y) {
        unit.setX(x);
        unit.setY(y);
    }
}
