package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.constants.ColorConstants;
import com.mygdx.game.entities.Bloc;

public class Crafty extends ApplicationAdapter {

	SpriteBatch batch;

	TextureManager textureManager;
	Bloc c;

	@Override
	public void create () {
		batch = new SpriteBatch();

		textureManager  = TextureManager.getInstance();
		Sprite s = textureManager.getSprite("dirt");
		c = new Bloc("dirt", s, "B001");

		ChunkManager manager = new ChunkManager();
		Chunk chunk = new Chunk();
		chunk.genTestChunk();

		manager.saveChunk(chunk);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(ColorConstants.SKY_LIGHT_BLUE.r, ColorConstants.SKY_LIGHT_BLUE.g, ColorConstants.SKY_LIGHT_BLUE.b, ColorConstants.SKY_LIGHT_BLUE.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
			c.sprite.draw(batch);
		batch.end();
	}
}
