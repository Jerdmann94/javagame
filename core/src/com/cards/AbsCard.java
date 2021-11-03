package com.cards;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.models.CardButton;
import com.models.Character;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public abstract class AbsCard implements PropertyChangeListener {
    protected List <TiledMapTileLayer.Cell> cells;
    protected List <TextButton> obuttons;
    public final String name;
    public final int cost;
    public final int targets;
    protected final Character player;
    private Actor actor;
    private CardButton cardButton;



    public AbsCard(String name, int cost, int targets, Character cha) {
        this.name = name;
        this.cost = cost;
        this.targets = targets;
        this.player = cha;



    }

    public abstract void playCard();
    public abstract void discardCard();

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
