package preferences;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;

import objects.Shape;
import objects.shape.Circle;
import objects.shape.Square;
import objects.shape.Triangle;

public class Preferences {
	
	private static int DEFAULT_OBJECT_SIZE = 50;
	private static int DEFAULT_PADDING = 30;
	private static boolean DEFAULT_PAGE_SCROLL = true;
	
	private static List<Class<? extends Shape>> DEFAULT_SHAPE_LIST = new ArrayList<Class<? extends Shape>>() {
		private static final long serialVersionUID = -3207233171735645409L;

		{
			this.add(Circle.class);
			this.add(Square.class);
			this.add(Triangle.class);
		}
	};
	
	private static List<Color> DEFAULT_COLOR_LIST = new ArrayList<Color>() {
		private static final long serialVersionUID = -5630037118477387114L;

		{
			this.add(Color.WHITE);
			this.add(Color.BLUE);
			this.add(Color.RED);
			this.add(Color.CYAN);
			this.add(Color.GREEN);
			this.add(Color.GRAY);
			this.add(Color.MAGENTA);
			this.add(Color.ORANGE);
			this.add(Color.LIME);
			this.add(Color.PINK);
			this.add(Color.PURPLE);
			this.add(Color.TEAL);
			this.add(Color.YELLOW);
		}
	};
	
	
	public static Preferences load() {
		Preferences prefs = new Preferences(
				DEFAULT_OBJECT_SIZE, 
				DEFAULT_PADDING,
				DEFAULT_PAGE_SCROLL,
				DEFAULT_SHAPE_LIST,
				DEFAULT_COLOR_LIST);
		
		return prefs;
	}

	public int objectSize;
	public int padding;
	public boolean pageScroll;
	public List<Class<? extends Shape>> shapes = new ArrayList<Class<? extends Shape>>();
	public List<Color> colors = new ArrayList<Color>();
	
	public Preferences(int objectSize, int padding, boolean pageScroll,
			List<Class<? extends Shape>> shapes, List<Color> colors) {
		super();
		this.objectSize = objectSize;
		this.padding = padding;
		this.pageScroll = pageScroll;
		this.shapes = shapes;
		this.colors = colors;
	}
	
}
