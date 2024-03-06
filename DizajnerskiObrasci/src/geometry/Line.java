package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape implements Cloneable{

	private Point startPoint = new Point();
	private Point endPoint = new Point();
	private Color colorLine;
	
	public Line() {
		
	}
	
	public Line (Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public Line(Point startPoint, Point endPoint, Color colorLine) {
		this(startPoint, endPoint);
		this.colorLine = colorLine;
		setColor(colorLine);
	}
	
	public Line (Point startPoint, Point endPoint, boolean selected, Color colorLine) {
		this (startPoint, endPoint, colorLine);
		setSelected(selected);
	}
	

	public double length() {
	     return this.startPoint.distance(this.endPoint.getX(), this.endPoint.getY());
	     //return this.startPoint.distance(this.endPoint.x, this.endPoint.y); ne mo�e zbog private
	}
	
	
	 public boolean equals (Object obj) {
		   if (obj instanceof Line) {
			   Line pomocna = (Line)obj;
			   if (this.startPoint.equals(pomocna.startPoint) && this.endPoint.equals(pomocna.endPoint))
				   return true;
			   else
				   return false; 
		   }
		   else
			   return false;
		 }
	 
	 public boolean contains(int x, int y) {
		 return (this.startPoint.distance(x, y) + this.endPoint.distance(x, y) - this.length()<=2);
	 }
	 
	 
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(startPoint.getX(),startPoint.getY(),endPoint.getX(),endPoint.getY());
	
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(startPoint.getX()-2, startPoint.getY()-2, 4, 4);
			g.drawRect(endPoint.getX()-2, endPoint.getY()-2, 4, 4);
		}
	
	}

	@Override
	public void moveTo(int x, int y) {
		// necemo je implementirati
	}

	@Override
	public void moveBy(int x, int y) {
		startPoint.moveBy(x, y);
		endPoint.moveBy(x, y);
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Line) {
		return (int) (this.length()-((Line)o).length());
		}
		return 0;
	}
	


	@Override
	public Line clone()  {
		Line l = new Line();
		l.setStartPoint(new Point());
		l.setEndPoint(new Point());
		
		l.getStartPoint().setX(this.getStartPoint().getX());
		l.getStartPoint().setY(this.getStartPoint().getY());
		l.getEndPoint().setX(this.getEndPoint().getX());
		l.getEndPoint().setY(this.getEndPoint().getY());
		l.setColor(this.getColor());
		
		return l;
	}

	public void setStartPoint(Point startPoint) {
	    this.startPoint = startPoint;
	}
	
	public Point getStartPoint() {
	    return this.startPoint;	
	}
	
	public Point getEndPoint() {
		return endPoint;
	}
	
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	
	public String toString() {
		//(xStart, yStart) --> (xEnd, yEnd)
		
		return "Line: startPoint:" + "(" + startPoint.getX() + "," + startPoint.getY() + ")"
				  + " " + "endPoint:" + "(" + endPoint.getX() + "," + endPoint.getY() + ")"
				  + " " + "Outline color:" + getColor();
	}


	
	}
