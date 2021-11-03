package com.command;

import com.models.Unit;

public class DoMove implements Action{

    private Mover mover;
    Unit unit;
    int x;
    int y;

    public DoMove(Mover mover,Unit unit, int x,int y){
        this.unit = unit;
        this.mover = mover;
        this.x = x;
        this.y = y;
    }


    @Override
    public void perform() {
        mover.move(unit,x,y);
    }
}
