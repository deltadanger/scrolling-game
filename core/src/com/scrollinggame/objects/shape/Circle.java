package com.scrollinggame.objects.shape;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.scrollinggame.objects.Shape;

public class Circle extends Shape {

	@Override
	public void draw(float centerX, float centerY, float size) {
		ShapeRenderer renderer = new ShapeRenderer();
		renderer.begin(ShapeType.Filled);
		renderer.setColor(color);
		renderer.circle(centerX, centerY, size/2);
		renderer.end();
	}
}
