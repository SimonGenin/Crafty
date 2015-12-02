package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;

public class Player {

    private Camera camera;
    private World world;

    public int xPosition;
    public int yPosition;
    private int speed = 15;

    int wIndex;
    int hIndex;

    public Player(World world, Camera camera) {
        this.camera = camera;
        this.world = world;

        xPosition = 0;
        yPosition = 0;

        wIndex = world.getCurrentChunk().widthIndex;
        hIndex = world.getCurrentChunk().heightIndex;
    }

    public void move(Direction direction) {
        int boundPos  = 0;
        switch (direction) {
            case UP:
                camera.translate(0, speed, 0);
                yPosition += speed;
                boundPos = world.getCurrentChunk().getBoundPosition(Direction.UP);
                if (yPosition + Gdx.graphics.getHeight() / 2 >= boundPos)
                    world.updateLoadedChunks(Direction.UP);
                break;
            case DOWN:
                camera.translate(0, -speed, 0);
                yPosition -= speed;
                boundPos = world.getCurrentChunk().getBoundPosition(Direction.DOWN);
                if (Math.abs(yPosition) + Gdx.graphics.getHeight() / 2 >= Math.abs(boundPos))
                    world.updateLoadedChunks(Direction.DOWN);
                break;
            case RIGHT:
                camera.translate(speed, 0, 0);
                xPosition += speed;
                boundPos = world.getCurrentChunk().getBoundPosition(Direction.RIGHT);
                if (xPosition + Gdx.graphics.getWidth() / 2 > boundPos)
                    world.updateLoadedChunks(Direction.RIGHT);
                break;
            case LEFT:
                camera.translate(-speed, 0, 0);
                xPosition -= speed;
                break;
        }

    }

}
