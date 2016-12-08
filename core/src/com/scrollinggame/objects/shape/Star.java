package com.scrollinggame.objects.shape;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.utils.ShortArray;
import com.scrollinggame.objects.Shape;

public class Star extends Shape {

	@Override
	public void draw(float centerX, float centerY, float size) {
		int i=0, j, k;

		double rExt = size*0.65;
		double rInt = rExt*0.38;
		double startAngle = 2*Math.PI * 1/4;
		
		double[] polarPoints = new double[] {
			rExt, startAngle,
			rInt, startAngle + 2*Math.PI / 10 * 1,
			rExt, startAngle + 2*Math.PI / 10 * 2,
			rInt, startAngle + 2*Math.PI / 10 * 3,
			rExt, startAngle + 2*Math.PI / 10 * 4,
			rInt, startAngle + 2*Math.PI / 10 * 5,
			rExt, startAngle + 2*Math.PI / 10 * 6,
			rInt, startAngle + 2*Math.PI / 10 * 7,
			rExt, startAngle + 2*Math.PI / 10 * 8,
			rInt, startAngle + 2*Math.PI / 10 * 9,
		};
		
		float[] points = new float[polarPoints.length];
		while (i < polarPoints.length) {
			points[i] = (float) (polarPoints[i] * Math.cos(polarPoints[i+1])) + centerX;
			points[i+1] = (float) (polarPoints[i] * Math.sin(polarPoints[i+1])) + centerY;
			i += 2;
		}
		
		ShortArray triangles = new EarClippingTriangulator().computeTriangles(points);
		
		ShapeRenderer renderer = new ShapeRenderer();
		renderer.begin(ShapeType.Filled);
		renderer.setColor(color);
		
		i = 0;
		
		while (i < triangles.size) {
			j = i+1;
			k = i+2;
			
			renderer.triangle(
				points[triangles.get(i)*2], points[triangles.get(i)*2+1],
				points[triangles.get(j)*2], points[triangles.get(j)*2+1],
				points[triangles.get(k)*2], points[triangles.get(k)*2+1]
			);
			
			i += 3;
		}
		renderer.end();
	}
}
