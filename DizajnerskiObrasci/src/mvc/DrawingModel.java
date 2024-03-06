package mvc;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import geometry.Shape;

public class DrawingModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();

	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	
	public void add(Shape shape) {
		shapes.add(shape);
	}
	
	
	public void remove(Shape shape) {
		shapes.remove(shape);
	}

	public ArrayList<Shape> getSelectedShapes() {
		return selectedShapes;
	}

	public void setSelectedShapes(ArrayList<Shape> selectedShapes) {
		this.selectedShapes = selectedShapes;
	}
	
}
