package com.mygdx.game;

import com.mygdx.game.entities.Bloc;

public class Blocs {

    private static TextureManager textureManager = TextureManager.getInstance();

    /**
     * Blocs
     */
    private static Bloc DIRT = new Bloc("dirt", textureManager.getSprite("dirt"), BlocIDs.DIRT);
    private static Bloc STONE = new Bloc("stone", textureManager.getSprite("stone"), BlocIDs.STONE);

}
