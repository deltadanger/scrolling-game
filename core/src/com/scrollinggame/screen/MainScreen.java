package com.scrollinggame.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import objects.ShapeFactory;
import objects.ShapeMatrix;
import preferences.Preferences;


public class MainScreen extends DefaultScreen{
	private static final float PAGE_SCROLL_DURATION = 0.7f; // Seconds
	private static final float FULL_SCROLL_DURATION = 2f; // Seconds
	
	private Vector2 mousePos;
	private Vector2 oldMousePos;
	private Vector2 touchStart;
	private Vector2 drawStart;
	private boolean isDragging = false;
	
	private int objectSize;
	private int padding;
	private int sizeWithPadding;
	private boolean pageScroll;
	
	private int baseSpeed;
	private double decelerating_factor;
	private Vector2 currentSpeed = new Vector2();
	
	private ShapeMatrix matrix;
	private ShapeFactory shapeFactory;
	
	public MainScreen() {
		Preferences prefs = Preferences.load();
		objectSize = prefs.objectSize;
		padding = prefs.padding;
		pageScroll = prefs.pageScroll;
//		pageScroll = false;
		baseSpeed = 80;
		
		sizeWithPadding = objectSize + padding;
		shapeFactory = new ShapeFactory(prefs.shapes, prefs.colors, ShapeFactory.Mode.RANDOM);

		matrix = new ShapeMatrix();
		initialiseMatrix();

		drawStart = new Vector2();
	}
	
	@Override
	public void render(float delta) {
		initBatcher();
		
		if (isDragging) {
			drawStart.add(mousePos.cpy().sub(oldMousePos));
			oldMousePos = mousePos.cpy();
		} else {
			drawStart.sub(currentSpeed);
			decelerate(delta);
		}
		
		updateMatrixAndDrawPosition();

		double x = drawStart.x;
		double y = drawStart.y;
		
		for (int i=0; i<matrix.getWidth(); i++) {
			for (int j=0; j<matrix.getHeight(); j++) {
				
				matrix.get(i, j).draw((float) x, (float) y, objectSize);
				
				y += sizeWithPadding;
			}
			x += sizeWithPadding;
			y = drawStart.y;
		}
		
        batcher.end();
	}


	private void initialiseMatrix() {
		int matrixWidth = Gdx.graphics.getWidth() / sizeWithPadding + 2;
		int matrixHeight = Gdx.graphics.getHeight() / sizeWithPadding + 2;
		
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
	
	private void updateMatrixAndDrawPosition() {
		while (drawStart.x < -sizeWithPadding) {
			drawStart.x += sizeWithPadding;
			matrix.removeFirstCol();
			matrix.appendCol(shapeFactory.getShapeList(matrix.getHeight()));
		}
		
		while (drawStart.x > 0) {
			drawStart.x -= sizeWithPadding;
			matrix.removeLastCol();
			matrix.prependCol(shapeFactory.getShapeList(matrix.getHeight()));
		}
		
		while (drawStart.y < -sizeWithPadding) {
			drawStart.y += sizeWithPadding;
			matrix.removeFirstRow();
			matrix.appendRow(shapeFactory.getShapeList(matrix.getWidth()));
		}
		
		while (drawStart.y > 0) {
			drawStart.y -= sizeWithPadding;
			matrix.removeLastRow();
			matrix.prependRow(shapeFactory.getShapeList(matrix.getWidth()));
		}
	}

	private void decelerate(float delta) {
		double speedReduction = decelerating_factor * delta;
		int xSign = currentSpeed.x > 0 ? 1 : -1;
		int ySign = currentSpeed.y > 0 ? 1 : -1;
		currentSpeed.x = (float) (xSign * Math.max(0, Math.abs(currentSpeed.x) - speedReduction));
		currentSpeed.y = (float) (ySign * Math.max(0, Math.abs(currentSpeed.y) - speedReduction));
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int Vector2er, int button) {
		touchStart = new Vector2(screenX, screenY);
		
		mousePos = new Vector2(screenX, -screenY);
		oldMousePos = mousePos.cpy();
		
		isDragging = true;
		return true;
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int Vector2er) {
		mousePos = new Vector2(screenX, -screenY);
		
		Vector2 drawOffset = new Vector2(touchStart.x - screenX, screenY - touchStart.y);
		
		float newSpeed = drawOffset.dst(0, 0);
		decelerating_factor = newSpeed  / FULL_SCROLL_DURATION;
		if (pageScroll) {
			newSpeed = baseSpeed;
			decelerating_factor = baseSpeed / PAGE_SCROLL_DURATION;
		}
		
		if (Math.abs(drawOffset.x) > Math.abs(drawOffset.y)) {
			if (drawOffset.x > 0) {
				currentSpeed = new Vector2(newSpeed, 0);
			} else {
				currentSpeed = new Vector2(-newSpeed, 0);
			}
		} else {
			if (drawOffset.y > 0) {
				currentSpeed = new Vector2(0, newSpeed);
			} else {
				currentSpeed = new Vector2(0, -newSpeed);
			}
		}
		
		return false;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int Vector2er, int button) {
		isDragging = false;
		return false;
	}
}
