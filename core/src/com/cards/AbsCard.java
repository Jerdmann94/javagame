package com.cards;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.kotcrab.vis.ui.widget.VisImageButton;
import com.models.CardButton;
import com.models.Character;
import com.models.TiledMapActor;

import java.beans.PropertyChangeListener;
import java.util.List;

public abstract class AbsCard implements PropertyChangeListener {
    public String text = "card text";
    protected List <TiledMapTileLayer.Cell> cells;
    protected List <VisImageButton> obuttons;
    public final String name;
    public final String cost;
    public final int targets;

    private Actor actor;
    private CardButton cardButton;



    public AbsCard(String name, int cost, int targets) {
        this.name = name;
        this.cost = Integer.toString(cost);
        this.targets = targets;




    }

    public abstract void playCard();
    public abstract void discardCard();
    public abstract boolean tileCheck(TiledMapActor actor);

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public List<TiledMapTileLayer.Cell> getCells() {
        return cells;
    }

    public void setCells(List<TiledMapTileLayer.Cell> cells) {
        this.cells = cells;
    }

    public void setButton(CardButton cardButton) {
        this.cardButton = cardButton;
    }
    public CardButton getCardButton() {
        return cardButton;
    }

}
