package com.models;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;

public class CardListener extends ClickListener{
    private final Character character;


    public CardListener(Character character) {

        this.character = character;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {

        this.character.setTargets(new ArrayList<TiledMapTileLayer.Cell>());



    }
}
