package com.models;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Unit {


    protected Sprite sprite;
    protected int x;
    protected int y;



    protected int health;

    protected void move(){
        System.out.println("I should be moving");
    }
    protected void death(){
        System.out.println("I should be dead ");
    }

    protected abstract void move(int x, int y);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        this.sprite.setPosition(x,getY());
    }
    public void setY(int y) {
        this.y = y;
        this.sprite.setPosition(getX(),y);
    }
    public int getY() {
        return y;
    }
    public Sprite getSprite() {
        return sprite;
    }

    public int getHealth() {
        return health;
    }
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
