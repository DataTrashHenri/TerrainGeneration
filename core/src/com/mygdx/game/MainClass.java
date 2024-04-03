package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;


import java.time.Duration;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MainClass extends ApplicationAdapter{
	ShapeRenderer painter;

	int offsetx=0;
	int offsety=0;
	Random rnd = new Random();

    private SpriteBatch batch;
    private BitmapFont font;

	int gridSize=5;
	//make sure It is dividable with 805

	Block[][] grid;
	static int seedOffset=0;
	static int worldSize=999;
	boolean readyToStart=false;

	@Override
	public void create () {
		//handleStartArguments();
        grid = new Block[worldSize][worldSize];



        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(8);

		painter = new ShapeRenderer();
		Gdx.input.setCursorCatched(false);




		for (int i = 0; i < (worldSize); i++) {
			for (int j = 0; j < (worldSize); j++) {
				if (	   PerlinNoise.perlin((float) i /30+seedOffset, (float) j /30+seedOffset) < -0.2){
					grid[i][j]= new Block(Texture.WATER);
				} else if (PerlinNoise.perlin((float) i /30+seedOffset, (float) j /30+seedOffset) < -0.1) {
					grid[i][j]= new Block(Texture.SAND);
				} else if (PerlinNoise.perlin((float) i /30+seedOffset, (float) j /30+seedOffset) < 0.1) {
					grid[i][j]= new Block(Texture.GRASS);
				} else if (PerlinNoise.perlin((float) i /30+seedOffset, (float) j /30+seedOffset) < 0.5) {
					grid[i][j]= new Block(Texture.STONE);
				} else if (PerlinNoise.perlin((float) i /30+seedOffset, (float) j /30+seedOffset) < 1) {
					grid[i][j]= new Block(Texture.SNOW);
				}

			}
		}



	}

	@Override
	public void render () {

		ScreenUtils.clear(1, 0, 0, 1);

		handleInputs();

		displayGrid();

        batch.begin();
        font.setColor(Color.RED);

        font.draw(batch, String.valueOf(gridSize), 0, 800);
        font.draw(batch, String.valueOf((800/gridSize)*(800/gridSize))+" V", 150, 800);
        batch.end();

	}
	
	@Override
	public void dispose () {

	}
	public void displayGrid() {
		painter.setAutoShapeType(true);
		painter.begin();

		painter.set(ShapeRenderer.ShapeType.Filled);

		for (int i = 0; i < (800 / gridSize); i++) {

			for (int j = 0; j < (800 / gridSize); j++) {

				painter.setColor(grid[i+offsetx][j+offsety].getTexture());
				painter.rect(i*gridSize,j*gridSize,gridSize,gridSize);

			}

		}
        painter.setColor(Color.BLACK);
        painter.rect(0,700,800,100);
		painter.end();
	}
	public void handleInputs() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
		if (Gdx.input.isKeyJustPressed(Input.Keys.L)) Gdx.input.setCursorCatched(!Gdx.input.isCursorCatched());


		if (Gdx.input.isKeyJustPressed(Input.Keys.P)) gridSize+=1;

		if (gridSize!=1){
			if (offsetx+(800/(gridSize-1))<worldSize) {
				if (offsety+(800/(gridSize-1))<worldSize) {
					if (Gdx.input.isKeyJustPressed(Input.Keys.O)) gridSize-=1;
				}

			}

		}

			if (800/gridSize+offsetx<=worldSize-2) {
			if (Gdx.input.isKeyPressed(Input.Keys.D)) offsetx+=2;
		}
		if (800/gridSize+offsety<=worldSize-2) {
			if (Gdx.input.isKeyPressed(Input.Keys.W)) offsety+=2;
		}


		if (offsetx>=2){
			if (Gdx.input.isKeyPressed(Input.Keys.A)) offsetx-=2;
		}
		if (offsety>=2) {
			if (Gdx.input.isKeyPressed(Input.Keys.S)) offsety-=2;
		}
	}
}
