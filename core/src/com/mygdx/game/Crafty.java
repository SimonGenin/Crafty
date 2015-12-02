package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.constants.ColorConstants;

public class Crafty extends ApplicationAdapter {

	SpriteBatch batch;
	OrthographicCamera camera;
	FPSLogger fpsLogger;

	World world;
	Player player;

	@Override
	public void create () {
		batch = new SpriteBatch();

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(0, 0, 0); // TODO
		camera.update();

		fpsLogger = new FPSLogger();

		world = new World();
		player = new Player(world, camera);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(ColorConstants.SKY_LIGHT_BLUE.r, ColorConstants.SKY_LIGHT_BLUE.g, ColorConstants.SKY_LIGHT_BLUE.b, ColorConstants.SKY_LIGHT_BLUE.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		camera.update();

		// fpsLogger.log();
		handleInput();
		world.update();

		batch.begin();
			world.render(batch, (world.getCurrentChunk() == null) ? new int[]{0, 0} : world.getCurrentChunk().getIndex());
		batch.end();
	}

	private void handleInput() {

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			player.move(Direction.LEFT);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			player.move(Direction.RIGHT);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			player.move(Direction.DOWN);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			player.move(Direction.UP);
		}

	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = Gdx.graphics.getWidth();
		camera.viewportHeight = Gdx.graphics.getHeight();
		camera.update();
	}
}
