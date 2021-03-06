package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.TextureManager;

public class Bloc extends Entity {

    private String name;
    public Sprite sprite;
    private String id;

    public Bloc(String name, Sprite sprite, String id) {
        this.name = name;
        this.sprite = sprite;
        this.id = id;
    }

    public String getID() {
        return id;
    }

    public static Bloc createBlocByID(String id) {
        return new Bloc("dirt", TextureManager.getInstance().getSprite("dirt"), "B001");
    }


    public void update() {

    }
}
