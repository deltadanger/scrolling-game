package com.scrollinggame.screen;


import objects.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import helper.AssetLoader;


public class MainScreen extends DefaultScreen{
	private Point touchStart;
	private Point drawStart;
	private Point drawOffset = new Point();
	private boolean isDragging = false;
	
	private int objectSize;
	private int padding;
	private double decelerating_factor;
	private int baseSpeed;
	private Point currentSpeed = new Point();
	
	public MainScreen() {
		objectSize = 50;
		padding = 30;
		decelerating_factor = 2000;
		baseSpeed = 500;
	}
	
	@Override
	public void render(float delta) {
		initBatcher();
		
		batcher.setColor(Color.WHITE);
		
		drawStart = new Point(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		
		if (isDragging) {
			drawStart.add(drawOffset);
		} else {
			drawStart.sub(currentSpeed);
			double speedReduction = decelerating_factor * delta;
			currentSpeed.reduceToZero(speedReduction);
		}
		
		double startX = (drawStart.x - (int)(drawStart.x / (objectSize+padding) + 1) * (objectSize+padding));
		double startY = (drawStart.y - (int)(drawStart.y / (objectSize+padding) + 1) * (objectSize+padding));
		float endX = Gdx.graphics.getWidth() + objectSize;
		float endY = Gdx.graphics.getHeight() + objectSize;
		
		for (double x=startX; x < endX; x += objectSize+padding) {
			for (double y=startY; y < endY; y += objectSize+padding) {
				drawShape(x, y);
			}
		}
		
        batcher.end();
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touchStart = new Point(screenX, screenY);
		isDragging = true;
		return false;
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		drawOffset = new Point(screenX - touchStart.x, screenY - touchStart.y);
		
		if (Math.abs(drawOffset.x) > Math.abs(drawOffset.y)) {
			if (drawOffset.x > 0) {
				currentSpeed = new Point(baseSpeed, 0);
			} else {
				currentSpeed = new Point(-baseSpeed, 0);
			}
		} else {
			if (drawOffset.y > 0) {
				currentSpeed = new Point(0, baseSpeed);
			} else {
				currentSpeed = new Point(0, -baseSpeed);
			}
		}
		
		return false;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		drawOffset = new Point();
		isDragging = false;
		return false;
	}
	
	private void drawShape(double x, double y) {
		batcher.draw(AssetLoader.pixel, (float) x - objectSize/2, (float) y - objectSize/2, objectSize, objectSize);
	}
}
