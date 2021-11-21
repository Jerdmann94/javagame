package com.cards;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.models.Character;
import com.models.CardButton;
import com.models.TiledMapActor;

import java.beans.PropertyChangeEvent;


import static com.javagame.GameClass.player;

public class BasicMoveCard extends AbsCard {
    public BasicMoveCard(String name, int cost, int targets ) {
        super(name, cost, targets);
    }

    @Override
    public void playCard() {
        for (int i = 0; i < targets; i++) {
            move(player.getTargets().get(player.getTargets().size()-1));
        }
        discardCard();
    }

    @Override
    public void discardCard() {

       // player.getSelectedCard().getActor().remove();
        player.getSelectedCard().getCardButton().remove();
        player.getHand().remove(this);
        player.setSelectedCard(null);
    }

    @Override
    public boolean tileCheck(TiledMapActor actor) {

        int x = (int)actor.getCell().getTile().getProperties().get("x");
        int y = (int)actor.getCell().getTile().getProperties().get("y");


        if (x - 64 == player.getX() && y == player.getY() ){
            return true;
        }
        else if (x + 64 == player.getX() && y == player.getY() ){
            return true;
        }
        else if (x == player.getX() && y - 64 == player.getY() ){
            return true;
        }
        else if (x == player.getX() && y + 64 == player.getY() ){
            return true;
        }
        else
            return false;
    }

    public void move(TiledMapTileLayer.Cell cell) {
        player.setX((Integer) cell.getTile().getProperties().get("x"));
        player.setY((Integer) cell.getTile().getProperties().get("y"));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
       this.obuttons.add((CardButton) evt.getNewValue());
       System.out.print(evt.getNewValue() + "this is value");
        System.out.print(evt.getOldValue() + "this is old value");
    }
}
