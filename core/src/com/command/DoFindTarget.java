package com.command;

import com.models.Character;
import com.models.Unit;

public class DoFindTarget implements Action{

    private Finder finder;
    Character unit;

    public DoFindTarget(Finder finder, Character unit){
        this.unit = unit;
        this.finder = finder;


    }
    @Override
    public void perform() {
        finder.find(unit);
    }
}
