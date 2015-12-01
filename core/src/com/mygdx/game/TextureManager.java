package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class TextureManager {

    private static TextureManager instance;
    private TextureAtlas atlas;

    private TextureManager() {
        atlas = new TextureAtlas(Gdx.files.internal("textures/textures.pack"));
    }

    public static TextureManager getInstance() {
        if (instance == null)
            instance = new TextureManager();
        return instance;
    }

    /**
     * Return a sprite made of the texture find with the
     * paramater
     */
    public Sprite getSprite(String textureName) {
        return atlas.createSprite(textureName);
    }

}
