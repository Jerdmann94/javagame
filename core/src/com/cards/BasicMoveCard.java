package com.cards;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.models.Character;
import com.models.CardButton;

import java.beans.PropertyChangeEvent;

public class BasicMoveCard extends AbsCard {
    public BasicMoveCard(String name, int cost, int targets, Character player) {
        super(name, cost, targets, player);
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
