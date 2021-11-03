package com.models;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.cards.AbsCard;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CardButton extends TextButton {


    AbsCard card;




    public CardButton(String text, Skin skin, AbsCard card) {
        super(text, skin);
        this.card = card;

    }

    public CardButton(String text, Skin skin, String styleName, AbsCard card) {
        super(text, skin, styleName);
        this.card = card;

    }

    public CardButton(String text, TextButtonStyle style, AbsCard card) {
        super(text, style);
        this.card = card;
    }

    public AbsCard getCard() {
        return card;
    }
}
