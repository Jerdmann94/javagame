package com.command;


import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.models.Character;
import com.models.Unit;

import java.util.ArrayList;
import java.util.List;

public class Finder {
    public void find(Character unit) {
        unit.setTargets(new ArrayList<TiledMapTileLayer.Cell>());


    }
}
