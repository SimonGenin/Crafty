package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.StringBuilder;
import com.mygdx.game.debugging.Debug;
import com.mygdx.game.entities.Bloc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChunkManager {

    private static final String TAG = ChunkManager.class.getSimpleName();

    /**
     * This list is loaded at game launch
     * Must be saved at game end. (or any save option.)
     */
    // int (h_id, w_id)
    ArrayList<int[]> existingChunkIDs;

    File idsFile;
    FileHandle idsFileHandle;

    FileHandle chunksSaveFileHandle;
    FileHandle chunksLoadFileHandle;

    public ChunkManager() {
        idsFileHandle = Gdx.files.internal("files/save/chunks.ids");
        Debug.i(idsFileHandle.path(), TAG);
        existingChunkIDs = getExistingChunkIDs();
    }

    public Chunk loadChunk(int h_id, int w_id) {

        // the chunk id
        int[] id = {w_id, h_id};

        Chunk chunk = new Chunk(w_id, h_id);

        // If it doesn't exist yet, let's delegate
        // the work to a generator
        if (!existingChunkIDs.contains(id)) {
            chunk = generateNewChunk(id);
            chunk.prepare();
            return chunk;
        }

        // find the file. It must exists if the id is in the list
        chunksLoadFileHandle = Gdx.files.internal("files/save/chunks/" + id[0] + "," + id[1] + ".chunk");

        // Let's check anyway if the file exists
        if (!chunksLoadFileHandle.exists()) {
            Debug.i("Chunk (" + id[0] + "," +id[1] + ") doesn't exist ! Generating...", TAG);
            chunk = generateNewChunk(id);
            chunk.prepare();
            return chunk;
        }

        // Read the entire file
        String chunkString = chunksLoadFileHandle.readString();

        // Get the lines
        String[] blocLines = chunkString.split("[\\r\\n]+");

        String blocsId[][] = new String[Chunk.CHUNK_WIDTH][Chunk.CHUNK_HEIGHT];
        for (int i = 0; i < blocLines.length; i++) {
            blocsId[i] = blocLines[i].split(" ");
        }


        for (int i = 0; i < blocsId.length; i++) {
            for (int j = 0; j < blocsId[i].length; j++) {
                if (blocsId[i][j]!= "NONE")
                    chunk.blocs()[i][j] = Bloc.createBlocByID(blocsId[i][j]);
            }
        }

        chunk.prepare();

        return chunk;
    }

    private Chunk generateNewChunk(int[] id) {
        Sprite dirt = TextureManager.getInstance().getSprite("dirt");
        Chunk newChunk = new Chunk(id[0], id[1]);
        for (int i = 0; i < Chunk.CHUNK_HEIGHT; i++) {
            for (int j = 0; j < Chunk.CHUNK_WIDTH; j++) {
                newChunk.blocs()[i][j] = new Bloc("dirt", dirt, "B001");
            }
        }

        existingChunkIDs.add(id);

        return newChunk;
    }

    public void saveChunks(List<Chunk> chunkList) {
        for (Chunk chunk : chunkList) {
            saveChunk(chunk);
        }
    }

    /**
     * Save a chunk to a file with a name such as h_id,w_id.chunk
     * Update the list of ids.
     */
    public void saveChunk(Chunk chunk) {

        int h_id = chunk.heightIndex;
        int w_id = chunk.widthIndex;

        // Get the file
        chunksSaveFileHandle = Gdx.files.local("files/save/chunks/" + w_id + "," + h_id + ".chunk");

        // Iterate over every bloc and write its ID
        Bloc[][] blocs = chunk.blocs();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < blocs.length; i++) {
            for (int j = 0; j < blocs[i].length; j++) {
                if (blocs[i][j] == null)
                    sb.append("NONE" + " ");
                else
                    sb.append(blocs[i][j].getID() + " ");
            }
            sb.append("\n");
        }

        // Write all the content to the file
        chunksSaveFileHandle.writeString(sb.toString(), false);

        // Add the id to the list of ids if not already there
        int[] id = {w_id, h_id};
        if (!existingChunkIDs.contains(id))
            existingChunkIDs.add(id);
    }

    /**
     * Return a list of all the existing chunk ids
     * This must be used at the launch of the game
     */
    private ArrayList<int[]> getExistingChunkIDs() {

        ArrayList<int[]> list = new ArrayList<>();

        // If no file with ids, return empty list
        if (!idsFileHandle.exists()) {
            Debug.i("No ids file", TAG);
            return list;
        }

        // Read the whole file
        String s = idsFileHandle.readString();

        // Split at every line break
        String[] elements = s.split("[\\r\\n]+");

        // Init for the loop
        String[] valuesString = new String[2];
        int[] values = null;

        // Loop through the elements, split them, and add them
        // to the list
        for (int i = 0; i < elements.length; i++) {
            values = new int[2];
            valuesString = elements[i].split(",");
            values[0] = Integer.parseInt(valuesString[0]);
            values[1] = Integer.parseInt(valuesString[1]);
            list.add(values);
        }

        return list;

    }
}
