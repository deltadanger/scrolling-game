package com.scrollinggame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import helper.AssetLoader;


public class MainScreen extends DefaultScreen{

	private static final int OBJECT_SIZE = 50;
	private static final int PADDING = 30;
	
	@Override
	public void render(float delta) {
		initBatcher();
		
		batcher.setColor(Color.WHITE);
		int i = 0;
		int j;
		
		while (i < Gdx.graphics.getWidth()) {
			j = 0;
			while (j < Gdx.graphics.getHeight()) {
				drawShape(i, j);
				j += OBJECT_SIZE + PADDING;
			}
			i += OBJECT_SIZE + PADDING;
		}
		
        batcher.end();
	}
	
	
	private void drawShape(int x, int y) {
		batcher.draw(AssetLoader.pixel, x, y, OBJECT_SIZE, OBJECT_SIZE);
	}
}
