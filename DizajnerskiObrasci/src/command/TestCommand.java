package command;

import java.awt.Color;

import adapter.HexagonAdapter;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import mvc.DrawingModel;

public class TestCommand {

	public static void main(String[] args) {
		Point p1 = new Point (10, 10, Color.RED);
		Point p2 = new Point (20, 20, Color.BLACK);
		DrawingModel model = new DrawingModel();
		
		Point p3 = new Point (30, 30, Color.BLUE);
		Point p4 = new Point (40, 40, Color.GREEN);
		
		Line l1 = new Line (p1, p2, Color.RED);
		Line l2 = new Line (p3, p4, Color.BLACK);
		
		Rectangle r1 = new Rectangle (p1, 25, 35, Color.BLACK, Color.RED);
		Rectangle r2 = new Rectangle (p2, 45, 55, Color.BLUE, Color.GRAY);
		
		Circle c1 = new Circle(p1, 34, Color.RED, Color.BLACK);
		Circle c2 = new Circle(p3, 23, Color.YELLOW, Color.BLUE);
		
		Donut d1 = new Donut(p2, 20, 10, Color.GREEN, Color.BLACK);
		Donut d2 = new Donut (p4, 60, 30, Color.GRAY, Color.YELLOW);
		
		HexagonAdapter hexagon1 = new HexagonAdapter(p1, 20);
		HexagonAdapter hexagon2 = new HexagonAdapter(p2, 30);
		
		/*TEST - POINT*/
		System.out.println(" ");
		System.out.println("POINT");
		AddShapeCmd addPointCmd = new AddShapeCmd (p1, model);
		addPointCmd.execute();
		System.out.println(model.getShapes().size());
		
		addPointCmd.unexecute();
		System.out.println(model.getShapes().size());
		
		
		UpdatePointCmd updatePointCmd = new UpdatePointCmd(p1, p2);
		updatePointCmd.execute();
		System.out.println(p1);
		
		updatePointCmd.unexecute();
		System.out.println(p1);
		
		/*TEST - LINE*/
		System.out.println(" ");
		System.out.println("LINE");
		
		UpdateLineCmd updateLineCmd = new UpdateLineCmd(l1, l2);
		updateLineCmd.execute();
		System.out.println(l1);
		
		p3.setX(50);
		System.out.println(l1);
		
		/*TEST - RECTANGLE*/
		System.out.println(" ");
		System.out.println("RECTANGLE");
		
		UpdateRectangleCmd updateRectangleCmd = new UpdateRectangleCmd(r1, r2);
		updateRectangleCmd.execute();
		System.out.println(r1);
		
		/*TEST - CIRCLE*/
		System.out.println(" ");
		System.out.println("CIRCLE");
		
		UpdateCircleCmd updateCircleCmd = new UpdateCircleCmd (c1, c2);
		updateCircleCmd.execute();
		System.out.println(c1);
		
		/*TEST - DONUT*/
		System.out.println(" ");
		System.out.println("DONUT");
		
		System.out.println(d1);
		UpdateDonutCmd updateDonutCmd = new UpdateDonutCmd (d1, d2);
		updateDonutCmd.execute();
		System.out.println(d1);
		
		/*TEST - HEXAGON*/
		System.out.println(" ");
		System.out.println("HEXAGON");
		
		System.out.println(hexagon1);
		System.out.println(hexagon2);
		UpdateHexagonCmd updateHexagonCmd = new UpdateHexagonCmd(hexagon1, hexagon2);
		updateHexagonCmd.execute();
		System.out.println(hexagon1);
	}

}
