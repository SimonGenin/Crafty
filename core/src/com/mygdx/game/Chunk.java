package com.mygdx.game;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.entities.Bloc;

public class Chunk {

    public static final int CHUNK_HEIGHT = 32;
    public static final int CHUNK_WIDTH = 32;

    public int heightIndex;
    public int widthIndex;

    private Bloc[][] blocs;

    public Chunk() {
        blocs = new Bloc[CHUNK_HEIGHT][CHUNK_WIDTH];
    }

    public void genTestChunk() {
        Sprite dirt = TextureManager.getInstance().getSprite("dirt");
        for (int i = 0; i < blocs.length; i++) {
            for (int j = 0; j < blocs.length; j++) {
                blocs[i][j] = new Bloc("dirt", dirt, "B001");
            }
        }
    }

    public Bloc[][] blocs() { return blocs; }
}
