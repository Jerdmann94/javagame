package com.models;


import com.badlogic.gdx.scenes.scene2d.InputEvent;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import static com.javagame.GameClass.player;

import static com.javagame.GameClass.createConfirm;


public class TiledMapClickListener extends ClickListener {

    private final Character character;

    private TiledMapActor actor;

    public TiledMapClickListener(TiledMapActor actor, Character character) {
        this.actor = actor;
        this.character = character;

    }

    @Override
    public void clicked(InputEvent event, float x, float y) {

        System.out.println();
        player.getTargets().add(actor.getCell());
        //THIS IS HIGHLINTING CELLS
        character.highlightCells();
        createConfirm();




    }
}