package com.mygdx.game;


import com.mygdx.game.entities.Bloc;

public class Chunck {

    public static final int CHUNK_HEIGHT = 32;
    public static final int CHUNK_WIDTH = 32;

    private int heightIndex;
    private int widthIndex;

    private Bloc[][] blocs;

    public Chunck() {
        blocs = new Bloc[CHUNK_HEIGHT][CHUNK_WIDTH];
    }



}
