package com.scrollinggame.screen;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import helper.AssetLoader;


public class MainScreen extends DefaultScreen{
	private Point touchStart;
	private Point drawStart;
	private Point drawOffset = new Point();
	
	private int objectSize;
	private int padding;
	
	public MainScreen() {
		objectSize = 50;
		padding = 30;
	}
	
	@Override
	public void render(float delta) {
		initBatcher();
		
		batcher.setColor(Color.WHITE);
		
		drawStart = new Point(Gdx.graphics.getWidth()/2 + drawOffset.x, Gdx.graphics.getHeight()/2 + drawOffset.y);
		
		int startX = drawStart.x - (drawStart.x / (OBJECT_SIZE+PADDING) + 1) * (OBJECT_SIZE+PADDING);
		int startY = drawStart.y - (drawStart.y / (OBJECT_SIZE+PADDING) + 1) * (OBJECT_SIZE+PADDING);;
		int endX = Gdx.graphics.getWidth() + OBJECT_SIZE;
		int endY = Gdx.graphics.getHeight() + OBJECT_SIZE;
		
		for (int x=startX; x < endX; x += OBJECT_SIZE+PADDING) {
			for (int y=startY; y < endY; y += OBJECT_SIZE+PADDING) {
				drawShape(x, y);
			}
		}
		
        batcher.end();
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touchStart = new Point(screenX, screenY);
		return false;
	};
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		drawOffset = new Point(screenX - touchStart.x, screenY - touchStart.y);
		return false;
	}
	
	private void drawShape(float x, float y) {
		batcher.draw(AssetLoader.pixel, x - OBJECT_SIZE/2, y - OBJECT_SIZE/2, OBJECT_SIZE, OBJECT_SIZE);
	}
}
