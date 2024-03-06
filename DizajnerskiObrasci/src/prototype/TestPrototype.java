package prototype;

import java.awt.Color;

import geometry.Line;
import geometry.Point;

public class TestPrototype {
	
	public static void main (String[] args) {
		Point p1 = new Point(10, 10, Color.BLUE);
		Point p2 = new Point(30, 30, Color.RED);
		
		Line l1 = new Line(p1, p2, Color.BLUE);
		//Line l1Clone = (Line) l1.clone();
		
		System.out.println(l1);
		//System.out.println(l1Clone);
	}
	
}
