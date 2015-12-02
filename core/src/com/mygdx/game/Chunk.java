package com.mygdx.game;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.debugging.Debug;
import com.mygdx.game.entities.Bloc;

public class Chunk {

    public static final int CHUNK_HEIGHT = 16;
    public static final int CHUNK_WIDTH = CHUNK_HEIGHT;

    public int heightIndex;
    public int widthIndex;

    private Bloc[][] blocs;

    public Chunk(int widthIndex, int heightIndex) {
        blocs = new Bloc[CHUNK_HEIGHT][CHUNK_WIDTH];
        this.widthIndex = widthIndex;
        this.heightIndex = heightIndex;
    }

    public Bloc[][] blocs() { return blocs; }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < blocs.length; i++) {
            for (int j = 0; j < blocs[i].length; j++) {
                if (blocs[i][j] != null) {
                    blocs[i][j].sprite.draw(batch);
                }
            }
        }

        Debug.i("1:" + blocs[2][4].sprite.getY() + "");
        Debug.i("2:" + blocs[6][13].sprite.getY()+ "");

    }

    public int getBoundPosition(Direction direction) {
        switch (direction) {
            case LEFT:
                return (int) blocs[0][0].sprite.getX();
            case RIGHT:
                return (int) blocs[0][blocs.length - 1].sprite.getX() + TextureManager.TEXTURE_SIZE;
            case DOWN:
                Debug.i("WHERE IS IT : " + blocs[blocs.length - 1][0].sprite.getY());
                return (int) blocs[blocs.length - 1][0].sprite.getY();
            case UP:
                return (int) blocs[0][0].sprite.getY() + TextureManager.TEXTURE_SIZE;

        }
        // never happens
        return 0;
    }

    public int[] getIndex() {
        return new int[]{widthIndex, heightIndex};
    }

    /**
     * Important method
     * It gives the blocs their positions
     */
    public void prepare() {
        for (int i = 0; i < blocs.length; i++) {
            for (int j = 0; j < blocs[i].length; j++) {
                if (blocs[i][j] != null) {
                    blocs[i][j].sprite.setPosition((CHUNK_WIDTH * TextureManager.TEXTURE_SIZE * widthIndex) + i * TextureManager.TEXTURE_SIZE , (CHUNK_HEIGHT * TextureManager.TEXTURE_SIZE * heightIndex) + j * TextureManager.TEXTURE_SIZE);
                }
            }
        }
    }
}
