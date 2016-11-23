package objects;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ShapeFactory {
	
	public static enum Mode {
		ORDER,
		RANDOM;
	}
	
	private List<Class<? extends Shape>> shapes;
	private Mode mode;
	
	public ShapeFactory(List<Class<? extends Shape>> shapes, Mode mode) {
		this.shapes = shapes;
		this.mode = mode;
	}
	
	public List<Shape> getShapeList(int length) {
		switch (this.mode) {
			case ORDER:
				return getOrderShapeList(length);
			case RANDOM:
				return getRandomShapeList(length);
		}
		return null;
	}
	
	public List<List<Shape>> getShapeMatrix(int width, int height) {
		switch (this.mode) {
			case ORDER:
				return getOrderShapeMatrix(width, height);
			case RANDOM:
				return getRandomShapeMatrix(width, height);
		}
		return null;
	}
	
	private Shape getRandomShape() {
		try {
			Shape result = shapes.get(new Random().nextInt(shapes.size())).getConstructor().newInstance();
			result.setColor(getColor());
			return result;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private List<Shape> getRandomShapeList(int length) {
		List<Shape> result = new ArrayList<Shape>();
		for (int i=0; i < length; i++) {
			result.add(getRandomShape());
		}
		return result;
	}

	/**
	 * Returns a list of `height` rows of length `width`
	 */
	private List<List<Shape>> getRandomShapeMatrix(int width, int height) {
		List<List<Shape>> result = new ArrayList<List<Shape>>();
		for (int i=0; i<height; i++) {
			result.add(getRandomShapeList(width));
		}
		return result;
	}
	
	private Shape getOrderShape(Shape current) {
		throw new NotImplementedException();
	}
	
	private List<Shape> getOrderShapeList(int length) {
		throw new NotImplementedException();
	}

	private List<List<Shape>> getOrderShapeMatrix(int width, int height) {
		throw new NotImplementedException();
	}
	
	private Color getColor() {
		return Color.WHITE;
	}
}
