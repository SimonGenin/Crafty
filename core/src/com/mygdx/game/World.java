package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Arrays;

/**
 * The world handles the chunks.
 * Here is a representation of them :
 * 00 10 20
 * 01 11 21
 * 02 12 22
 * The player is in the 11
 */

public class World {

    public Chunk[][] loadedChunks;
    private ChunkManager chunkManager;

    // on many covers of chunck is loaded around the player ?
    // 1 mean already 9 chunks
    // 2 would be 22 chunks
    private static final int CHUNK_COVER_NUMBER = 1;

    public World() {
        chunkManager = new ChunkManager();
        loadedChunks = new Chunk[getChunkNumberForCover()][getChunkNumberForCover()];
        loadChunks((getCurrentChunk() == null) ? new int[]{0, 0} : getCurrentChunk().getIndex());
    }

    /**
     * Simply a helper to know the size of the chunk wrap
     * See info on the CHUNK_COVER_NUMBER doc above
     */
    private int getChunkNumberForCover() {
        return (CHUNK_COVER_NUMBER * 2) + 1;
    }

    /**
     * Load the relevant chuncks around a postion
     * @param position chunk index
     */
    private void loadChunks(int[] position) {
        int w = position[0];
        int h = position[1];

        for (int n = 0, i = h + CHUNK_COVER_NUMBER; i >= h - CHUNK_COVER_NUMBER; i--, n++) {
            for (int m = 0, j = w - CHUNK_COVER_NUMBER; j <= w + CHUNK_COVER_NUMBER; j++, m++ ) {
                loadedChunks[n][m] = chunkManager.loadChunk(i, j);
            }
        }
    }

    public void render(SpriteBatch batch, int[] position) {

        int w = position[0];
        int h = position[1];

        for (int n = 0, i = h + CHUNK_COVER_NUMBER; i >= h - CHUNK_COVER_NUMBER; i--, n++) {
            for (int m = 0, j = w - CHUNK_COVER_NUMBER; j <= w + CHUNK_COVER_NUMBER; j++, m++ ) {
                loadedChunks[n][m].render(batch);
            }
        }
    }

    public Chunk getCurrentChunk() {
        return loadedChunks[CHUNK_COVER_NUMBER][CHUNK_COVER_NUMBER];
    }

    public void updateLoadedChunks(Direction direction) {

        // Working !!!
        if (direction == Direction.UP) {
            // chunks to be saved
            Chunk[] bottomChunks = loadedChunks[getChunkNumberForCover() - 1];
            chunkManager.saveChunks(Arrays.asList(bottomChunks));

            // new Chunks
            Chunk[] topChunks = new Chunk[getChunkNumberForCover()];
            for (int i = 0, n = -CHUNK_COVER_NUMBER; i < getChunkNumberForCover() ; i++, n++) {
                topChunks[i] = chunkManager.loadChunk(getCurrentChunk().heightIndex + 1, getCurrentChunk().widthIndex + n);
            }

            // Move the place of the chunks
            for (int i = getChunkNumberForCover() - 1; i > 0; i--) {
                loadedChunks[i] = loadedChunks[i - 1];
            }

            // Add the new ones
            loadedChunks[0] = topChunks;
        }

        if (direction == Direction.DOWN) {

            // chunks to be saved
            Chunk[] topChunks = loadedChunks[0];
            chunkManager.saveChunks(Arrays.asList(topChunks));

            // new Chunks
            Chunk[] bottomChunks = new Chunk[getChunkNumberForCover()];
            for (int i = 0, n = -CHUNK_COVER_NUMBER; i < getChunkNumberForCover() ; i++, n++) {
                bottomChunks[i] = chunkManager.loadChunk(getCurrentChunk().heightIndex - 1, getCurrentChunk().widthIndex + n);
            }

            // Move the place of the chunks
            for (int i = 0; i < getChunkNumberForCover() - 1; i++) {
                loadedChunks[i] = loadedChunks[i + 1];
            }

            // Add the new ones
            loadedChunks[getChunkNumberForCover() - 1] = bottomChunks;
        }


    }

    public void update() {

    }
}
