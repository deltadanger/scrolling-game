package com.scrollinggame.screen;


import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import objects.Point;
import objects.Shape;
import objects.ShapeFactory;
import objects.ShapeMatrix;
import objects.shape.Circle;
import objects.shape.Square;
import objects.shape.Triangle;


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
	
	private ShapeMatrix matrix;
	private ShapeFactory shapeFactory;
	
	public MainScreen() {
		objectSize = 50;
		padding = 30;
		decelerating_factor = 2000;
		baseSpeed = 500;
		
		List<Class<? extends Shape>> shapes = new ArrayList<Class<? extends Shape>>();
		shapes.add(Circle.class);
		shapes.add(Square.class);
		shapes.add(Triangle.class);
		shapeFactory = new ShapeFactory(shapes, ShapeFactory.Mode.RANDOM);

		matrix = new ShapeMatrix();
		initialiseMatrix();
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

		
		double x = (drawStart.x - (int)(drawStart.x / (objectSize+padding) + 1) * (objectSize+padding));
		double startY = (drawStart.y - (int)(drawStart.y / (objectSize+padding) + 1) * (objectSize+padding));
		double y = startY;
		
		for (int i=0; i<matrix.getWidth(); i++) {
			for (int j=0; j<matrix.getHeight(); j++) {
				
				matrix.get(i, j).draw((float) x, (float) y, objectSize);
				
				y += objectSize+padding;
			}
			x += objectSize+padding;
			y = startY;
		}
		
        batcher.end();
	}
	
	private void initialiseMatrix() {

		int matrixWidth = Gdx.graphics.getWidth() / (objectSize+padding) + 2;
		int matrixHeight = Gdx.graphics.getHeight() / (objectSize+padding) + 2;
		
		
		int newHeight = matrix.getHeight();
		if (newHeight == 0) {
			newHeight = matrixHeight;
		}
		while (matrix.getWidth() < matrixWidth) {
			matrix.appendCol(shapeFactory.getShapeList(newHeight));
		}
		while (matrix.getWidth() > matrixWidth) {
			matrix.removeLastCol();
		}

		int newWidth = matrix.getWidth();
		if (newWidth == 0) {
			newWidth = matrixWidth;
		}
		while (matrix.getHeight() < matrixHeight) {
			matrix.appendRow(shapeFactory.getShapeList(newWidth));
		}
		while (matrix.getHeight() > matrixHeight) {
			matrix.removeLastRow();
		}
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
}
