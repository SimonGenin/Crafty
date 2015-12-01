package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bloc extends Entity {

    private String name;
    public Sprite sprite;
    private int hardness;

    public Bloc(String name, Sprite sprite, int hardness) {
        this.name = name;
        this.sprite = sprite;
        this.hardness = hardness;
    }

}
