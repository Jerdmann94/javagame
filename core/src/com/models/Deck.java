package com.models;

import com.cards.AbsCard;
import com.cards.BasicMoveCard;

import java.util.*;

public class Deck {


    ArrayList<AbsCard> urDeck;
    ArrayList<AbsCard> combatDeck;
    ArrayList<AbsCard> discardPile;
    Character player;

    public Deck(Character player){
        this.player = player;
        urDeck = basicDeck();

    }

    private ArrayList<AbsCard> basicDeck() {
        ArrayList<AbsCard> cardList = new ArrayList<AbsCard>();
        cardList.add(new BasicMoveCard("move",2,1));
        cardList.add(new BasicMoveCard("move",2,1));
        cardList.add(new BasicMoveCard("move",2,1));
        cardList.add(new BasicMoveCard("move",2,1));
        cardList.add(new BasicMoveCard("move",2,1));
        cardList.add(new BasicMoveCard("move",2,1));
        cardList.add(new BasicMoveCard("move",2,1));

        return cardList;
    }


    public AbsCard draw() {
        return combatDeck.remove(0);
    }
    public void startPlay(){
        Collections.shuffle(this.urDeck);
        this.combatDeck = this.urDeck;
    }
}

