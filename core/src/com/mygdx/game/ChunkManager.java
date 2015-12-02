package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mygdx.game.debugging.Debug;
import com.mygdx.game.entities.Bloc;

import java.io.File;
import java.util.ArrayList;

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

    FileHandle chunksFileHandle;

    public ChunkManager() {
        idsFileHandle = Gdx.files.internal("files/save/chunks.ids");
        Debug.i(idsFileHandle.path(), TAG);
        existingChunkIDs = getExistingChunkIDs();
    }

    public void loadChunk(int h_id, int w_id) {

    }

    /**
     * Save a chunk to a file with a name such as h_id,w_id.chunk
     * Update the list of ids.
     */
    public void saveChunk(Chunk chunk) {

        int h_id = chunk.heightIndex;
        int w_id = chunk.widthIndex;

        // Get the file
        chunksFileHandle = Gdx.files.internal("files/save/chunks/" + w_id + "," + h_id);

        // Iterate over every bloc and write its ID
        Bloc[][] blocs = chunk.blocs();
        for (int i = 0; i < blocs.length; i++) {
            for (int j = 0; j < blocs[i].length; j++) {
                chunksFileHandle.writeString(blocs[i][j].getID() + " ", true);
            }
            chunksFileHandle.writeString("\n", true);
        }


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