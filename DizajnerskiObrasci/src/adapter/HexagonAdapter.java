package adapter;

import java.awt.Color;
import java.awt.Graphics;

import geometry.Point;
import geometry.Shape1;
import hexagon.Hexagon;

public class HexagonAdapter extends Shape1 implements Cloneable{
	
	private Hexagon hexagon = new Hexagon(new Point().getX(), new Point().getY(), 1);

	public HexagonAdapter () {
		
	}
	
	public HexagonAdapter(Hexagon hexagon) {
		super();
		this.hexagon = hexagon;
	}
	
	public HexagonAdapter (Point p, int radius)
	{
		hexagon = new Hexagon(p.getX(), p.getY(), radius);
	}
	
	public HexagonAdapter(Point p, int radius, Color colorOutline) {
		this (p, radius);
		this.setColor(colorOutline);
	}
	
	public HexagonAdapter(Point p, int radius, Color colorOutline, Color colorInner) {
		this (p, radius, colorOutline);
		this.setInnerColor(colorInner);
	}


	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveBy(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains (Point p) {
		return hexagon.doesContain(p.getX(), p.getY());
	}

	public boolean contains (int x, int y) {
		return hexagon.doesContain(x, y);
	}
	
	@Override
	public void fill(Graphics g) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
		
		if(isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(hexagon.getX()-2, hexagon.getY()-2, 5, 5);
			g.drawRect(hexagon.getX() + hexagon.getR()-2, hexagon.getY() - 2, 5, 5); //desni
			g.drawRect(hexagon.getX()- hexagon.getR() - 2, hexagon.getY() - 2, 5, 5); //levi
			g.drawRect(hexagon.getX() -2, hexagon.getY() - hexagon.getR() + 2, 5, 5);//gornji
			g.drawRect(hexagon.getX() - 3, hexagon.getY() + hexagon.getR()-10, 5, 5);// donji
			
		}
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			
			HexagonAdapter pomocna = (HexagonAdapter)obj;
			
			if (this.getX()==pomocna.getX() && this.getY()==pomocna.getY() && getR()==pomocna.getR()) {
				return true;
			} else {
				return false;
			}
		}
		
		return false;
	}
	
	@Override
	public HexagonAdapter clone() {
		HexagonAdapter hex = new HexagonAdapter();
		
		hex.setX(this.getX());
		hex.setY(this.getY());
		hex.setR(this.getR());
		hex.setColor(this.getColor());
		hex.setInnerColor(this.getInnerColor());
		
		return hex;
	}

	@Override
	public String toString() {
		return "Hexagon: center:" + "(" + hexagon.getX() + "," + hexagon.getY() + ")"
				+ " " + "radius:" + hexagon.getR() 
				+ " " + "Outline color:" + getColor() 
				+ " " + "Inner color:" + getInnerColor();
	}
	
	public Color getInnerColor() {
		return hexagon.getAreaColor();
	}
	
	public void setInnerColor (Color innerColor) {
		hexagon.setAreaColor(innerColor);
	}
	
	public Color getColor() {
		return hexagon.getBorderColor();
	}

	public void setColor(Color color) {
		hexagon.setBorderColor(color);
	}

	public int getX() {
		return hexagon.getX();
	}
	
	public void setX(int x) {
		hexagon.setX(x);
	}
	
	public int getY() {
		return hexagon.getY();
	}
	
	public void setY(int y) {
		hexagon.setY(y);
	}
	
	public int getR() {
		return hexagon.getR();
	}
	public void setR(int radius) {
		hexagon.setR(radius);
	}
	
}
