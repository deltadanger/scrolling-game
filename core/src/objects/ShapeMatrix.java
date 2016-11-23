package objects;

import java.util.ArrayList;
import java.util.List;

public class ShapeMatrix {

	private List<List<Shape>> matrix; // A list of rows
	
	public ShapeMatrix(List<List<Shape>> matrix) {
		this.matrix = matrix;
	}
	
	public ShapeMatrix() {
		this(new ArrayList<List<Shape>>());
	}

	public void appendRow(List<Shape> row) {
		matrix.add(row);
	}
	
	public void prependRow(List<Shape> row) {
		matrix.add(0, row);
	}
	
	public void removeFirstRow() {
		if (matrix.size() > 0) {
			matrix.remove(0);
		}
	}
	
	public void removeLastRow() {
		if (matrix.size() > 0) {
			matrix.remove(matrix.size()-1);
		}
	}
	
	public void appendCol(List<Shape> col) {
		while (matrix.size() < col.size()) {
			matrix.add(new ArrayList<Shape>());
		}
		
		for (int i=0; i < matrix.size(); i++) {
			List<Shape> row = matrix.get(i);
			row.add(col.get(i));
		}
	}
	
	public void prependCol(List<Shape> col) {
		while (matrix.size() < col.size()) {
			matrix.add(new ArrayList<Shape>());
		}
		
		for (int i=0; i < matrix.size(); i++) {
			List<Shape> row = matrix.get(i);
			row.add(0, col.get(i));
		}
	}
	
	public void removeFirstCol() {
		for (List<Shape> row : matrix) {
			if (row.size() > 0) {
				row.remove(0);
			}
		}
	}
	
	public void removeLastCol() {
		for (List<Shape> row : matrix) {
			if (row.size() > 0) {
				row.remove(row.size()-1);
			}
		}
	}
	
	public Shape get(int x, int y) {
		return matrix.get(y).get(x);
	}

	public int getWidth() {
		if (matrix.size() == 0) {
			return 0;
		}
		return matrix.get(0).size();
	}
	
	public int getHeight() {
		return matrix.size();
	}
}
