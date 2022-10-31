package com.mygdx.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game1.MyGdxGame;

import Draw.Draw;
import Entities.ClickableGrid;


public class GameScreen implements Screen {
	
	//A main game screen, uses a camera to project all tiles while the game is running
	
	final MyGdxGame game;
	FitViewport fitViewport;
	OrthographicCamera camera;
	ClickableGrid cg1;
	
	//Scene2d
	Stage stage;


	public GameScreen(final MyGdxGame game) {
		
		this.game = game;
		
		camera = new OrthographicCamera();
		camera.translate(400,400);
		
		fitViewport = new FitViewport(600, 650, camera);		
		stage = new Stage(fitViewport);
		cg1 = new ClickableGrid(8, 8, game.batch);
		fitViewport.setScreenSize(600, 700);
		fitViewport.apply();
	}

	
	//The render method is the most important function of the application
	//It is called continuously and draws the screen for every frame (60 frames per second)
	//It is standard to take delta time as a parameter but my game does not implement it
	@Override
	public void render(float delta) {
		
		//The screen is cleared
		ScreenUtils.clear(0, 1, 0, 1);
		
		
		//The camera and viewport are set up
		fitViewport.apply();
		game.batch.setProjectionMatrix(fitViewport.getCamera().combined);
		game.batch.begin();
	
		//The grid is drawn to the screen while the clicker polls for events
		Draw.drawClickableGrid(cg1, game.batch);
		cg1.Clicker();

		game.batch.end();
	}
	
	
	@Override
	public void resize(int width, int height) {
		fitViewport.update(width, height);
	}
	
	// The game screen has to implement all of these abstract methods
	@Override
	public void show() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {}

}
	