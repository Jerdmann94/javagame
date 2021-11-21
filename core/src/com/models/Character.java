package com.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.cards.AbsCard;


import java.util.ArrayList;

public class Character extends Unit{

    private ArrayList<TiledMapTileLayer.Cell> possibleTargets;
    private Deck deck;
    private ArrayList<AbsCard> hand;
    private AbsCard selectedCard;


    private ArrayList<TiledMapTileLayer.Cell> targets;


    public Character(int x, int y, Sprite sprite, int health){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.health = health;
        this.targets = new ArrayList<>();
        this.possibleTargets = new ArrayList<>();
        this.deck = new Deck(this);
        this.hand = drawStartingHand();

    }


    private ArrayList<AbsCard> drawStartingHand() {
        this.deck.startPlay();
        ArrayList<AbsCard> cardList = new ArrayList<AbsCard>();
        for (int i = 0; i < 6; i++) {
            cardList.add(deck.draw());
        }

        return cardList;
    }

    @Override
    protected void move(int x, int y) {
        //should be done with cards
    }

    public ArrayList<TiledMapTileLayer.Cell> getTargets() {

        return targets;
    }

    public void setTargets(ArrayList<TiledMapTileLayer.Cell> targets) {
        this.targets = targets;
        System.out.println("SETTING TARGETS");
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public ArrayList<AbsCard> getHand() {
        return hand;
    }

    public void setHand(ArrayList<AbsCard> hand) {
        this.hand = hand;
    }

    public AbsCard getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(AbsCard selectedCard) {
        this.selectedCard = selectedCard;

    }

    public ArrayList<TiledMapTileLayer.Cell> getPossibleTargets() {
        return possibleTargets;
    }

    public void setPossibleTargets(ArrayList<TiledMapTileLayer.Cell> possibleTargets) {
        this.possibleTargets = possibleTargets;
    }

    public void highlightCells(){
        TextureRegion temp = (TextureRegion) getTargets().get(0).getTile().getProperties().get("selected");
        getTargets().forEach(cell -> {
            cell.getTile().setTextureRegion((TextureRegion) cell.getTile().getProperties().get("default"));
        });
        for (int i = 1; i <= getSelectedCard().targets; i++) {
            getTargets().get(getTargets().size()-i).getTile().setTextureRegion(temp);
        }

    }


    public void resetCells(){
        System.out.println(getTargets().size());
        TextureRegion temp = (TextureRegion) getTargets().get(0).getTile().getProperties().get("selected");
        getTargets().forEach(cell -> {
            cell.getTile().setTextureRegion((TextureRegion) cell.getTile().getProperties().get("default"));
            System.out.println("reseting cell");
        });
        getPossibleTargets().forEach(cell -> {
            cell.getTile().setTextureRegion((TextureRegion) cell.getTile().getProperties().get("default"));
            System.out.println("reseting cell");
        });

        setTargets(new ArrayList<>());

    }
}
