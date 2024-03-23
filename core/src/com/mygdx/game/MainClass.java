package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.time.Duration;
import java.util.Random;

public class MainClass extends ApplicationAdapter{
	ShapeRenderer painter;
	long delta=0;
	int offsetx=50;
	int offsety=50;
	Random rnd = new Random();

	int gridSize=5;
	//make sure It's dividable with 800

	Block[][] grid = new Block[999][999];

	@Override
	public void create () {
		painter = new ShapeRenderer();
		Gdx.input.setCursorCatched(false);


		for (int i = 0; i < (999); i++) {
			for (int j = 0; j < (999); j++) {
				if (	   PerlinNoise.perlin((float) i /20, (float) j /20) < -0.2){
					grid[i][j]= new Block(Texture.WATER);
				} else if (PerlinNoise.perlin((float) i /20, (float) j /20) < -0.1) {
					grid[i][j]= new Block(Texture.SAND);
				} else if (PerlinNoise.perlin((float) i /20, (float) j /20) < 0.1) {
					grid[i][j]= new Block(Texture.GRASS);
				} else if (PerlinNoise.perlin((float) i /20, (float) j /20) < 0.5) {
					grid[i][j]= new Block(Texture.STONE);
				} else if (PerlinNoise.perlin((float) i /20, (float) j /20) < 1) {
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
		painter.end();
	}
	public void handleInputs() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
		if (Gdx.input.isKeyJustPressed(Input.Keys.L)) Gdx.input.setCursorCatched(!Gdx.input.isCursorCatched());


		if (Gdx.input.isKeyJustPressed(Input.Keys.P)) gridSize+=1;




		if (gridSize!=1){
			if (offsetx+(800/(gridSize-1))<999) {
				if (offsety+(800/(gridSize-1))<999) {
					if (Gdx.input.isKeyJustPressed(Input.Keys.O)) gridSize-=1;
				}

			}

		}
		if (800/gridSize+offsetx<997) {
			if (Gdx.input.isKeyPressed(Input.Keys.D)) offsetx+=2;
		}
		if (800/gridSize+offsety<997) {
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
