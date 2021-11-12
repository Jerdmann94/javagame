package com.models;

import com.badlogic.gdx.scenes.scene2d.ui.Label;


import com.cards.AbsCard;

import com.kotcrab.vis.ui.widget.VisImageButton;
import com.kotcrab.vis.ui.widget.VisLabel;


public class CardButton extends VisImageButton {


    AbsCard card;
    Label cost;
    Label name;
    Label cardText;





    public CardButton(String text, String styleName, AbsCard card) {

        super("default");

        this.card = card;
        this.cost = new VisLabel(card.cost);
        this.name = new VisLabel(card.name);
        this.cardText = new VisLabel(card.text);

        this.add(cost).top().left().padTop(4);

        this.add(name).expand().top().padTop(4);

        row();
        this.add(cardText).colspan(3).pad(3);

    }



    public AbsCard getCard() {
        return card;
    }
}
