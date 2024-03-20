package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainClass extends ApplicationAdapter{
	ShapeRenderer painter;

	int gridSize=40;
	//make sure It's dividable with 800

	Block[][] grid = new Block[800/gridSize][800/gridSize];

	@Override
	public void create () {
		painter = new ShapeRenderer();

		for (int i = 0; i < 800 / gridSize; i++) {
			for (int j = 0; j < 800 / gridSize; j++) {
				grid[i][j]= new Block(com.mygdx.game.Texture.DIRT);
			}
		}

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();

		painter.setAutoShapeType(true);
		painter.begin();
		painter.set(ShapeRenderer.ShapeType.Filled);

		for (int i = 0; i < 800 / gridSize; i++) {

			for (int j = 0; j < 800 / gridSize; j++) {
				painter.setColor(grid[i][j].getTexture());
				painter.rect(i*gridSize,j*gridSize,gridSize,gridSize);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

		}
		for (int i = 0; i < 800 / gridSize; i++) {
			painter.setColor(Color.BLACK);
			painter.set(ShapeRenderer.ShapeType.Line);
			painter.line(i*40,0,i*40,800);
		}
		for (int i = 0; i < 800 / gridSize; i++) {
			painter.setColor(Color.BLACK);
			painter.set(ShapeRenderer.ShapeType.Line);
			painter.line(0,i*40,800,i*40);
		}

		painter.end();


	}
	
	@Override
	public void dispose () {


	}
	public void sleep(int millis) throws InterruptedException {
		Thread.sleep(millis);
	}



}
