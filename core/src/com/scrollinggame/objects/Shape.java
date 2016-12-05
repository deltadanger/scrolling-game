package com.scrollinggame.objects;

import com.badlogic.gdx.graphics.Color;

public abstract class Shape {
	
	protected Color color = Color.WHITE;
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public abstract void draw(float centerX, float centerY, float size);
}
