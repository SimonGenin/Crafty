package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mygdx.game.debugging.Debug;

import java.io.File;
import java.util.ArrayList;

public class ChunkLoader {

    private static final String TAG = ChunkLoader.class.getSimpleName();

    // int (h_id, w_id)
    ArrayList<int[]> existingChunkIDs;

    File idsFile;
    FileHandle idsFileHandle;

    public ChunkLoader() {
        idsFileHandle = Gdx.files.internal("files/save/chunks.ids");
        Debug.i(idsFileHandle.path(), TAG);
        existingChunkIDs = getExistingChunkIDs();
    }

    public static void loadChunk(int h_id, int w_id) {

    }

    /**
     * Return a list of all the existing chunk ids
     */
    private ArrayList<int[]> getExistingChunkIDs() {

        ArrayList<int[]> list = new ArrayList<>();

        // If no file with ids, return empty list
        if (!idsFileHandle.exists()) {
            Debug.i("No ids file", TAG);
            return list;
        }

        // Read all the file
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
