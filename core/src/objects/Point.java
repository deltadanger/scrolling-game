package objects;

import java.awt.geom.Point2D.Double;

public class Point extends Double {

	private static final long serialVersionUID = 6944699315713249465L;

	public Point(int x, int y) {
		super((double)x, (double)y);
	}

	public Point(double x, double y) {
		super(x, y);
	}

	public Point() {
		this(0,0);
	}

	public void add(Point other) {
		x += other.x;
		y += other.y;
	}

	public void add(double otherX, double otherY) {
		x += otherX;
		y += otherY;
	}

	public void sub(Point other) {
		x -= other.x;
		y -= other.y;
	}

	public Point copy() {
		return new Point(x,y);
	}

	public void reduceToZero(double reduction) {
		x = x > 0 ? Math.max(x - reduction, 0) : Math.min(x + reduction, 0);
		y = y > 0 ? Math.max(y - reduction, 0) : Math.min(y + reduction, 0);
	}
}
