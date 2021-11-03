package com.command;

import java.util.ArrayList;
import java.util.List;


public final class Macro {

    private List<Action> commands;

    public Macro() {
        this.commands = new ArrayList<>();
    }

    public void record(final Action action) {
        this.commands.add(action);
    }

    public void run() {
        this.commands.forEach(Action::perform);
    }
    public void clear(){
        this.commands = new ArrayList<>();
    }

}