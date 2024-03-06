package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape1 implements Cloneable{
	
   protected Point center = new Point();
   protected int radius;
   protected boolean selected;
   
   public Circle () {
	   
   }
   
   public Circle (Point center, int radius) {
	   this.center = center;
	   this.radius = radius;
   }
   
   public Circle (Point center, int radius, boolean selected) {
	   this (center, radius);
	   setSelected(selected);
	   //menja se nakon uvodjenja klase Shape
   }
   
   public Circle (Point center, int radius, boolean selected, Color color) {
	   this (center, radius, selected);
	   this.setColor(color);
   }
  
   public Circle (Point center, int radius,  Color color) {
	   this (center, radius);
	   this.setColor(color);
   }
   
   public Circle (Point center, int radius,  Color color, Color innerColor) {
	   this (center, radius, color);
	   this.setInnerColor(innerColor);
   }
   
   public Circle (Point center, int radius, boolean selected, Color color, Color innerColor) {
	   this (center, radius, selected, color);
	   this.setInnerColor(innerColor);
   }
   
   public double area() {
	   return radius * radius * Math.PI;
   }
   
   public double circumference() {
	   return 2 * radius * Math.PI;
   }
   
   
   public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle pomocni = (Circle) obj;
			if (this.center.equals(pomocni.center) &&
					this.radius == pomocni.radius) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
   
   
   public boolean contains (int x, int y) {
	   return center.distance(x, y) <= radius;
   }
   
   public boolean contains (Point p) {
	   return center.distance(p.getX(), p.getY()) <= radius;
   }
   
   
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawOval(center.getX() - radius, center.getY() - radius, radius*2, radius*2);
		this.fill(g);
	
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(center.getX()-2, center.getY()-2, 4, 4);
			g.drawRect(center.getX()- radius -2, center.getY()-2, 4, 4);//levi kvadrat
			g.drawRect(center.getX()+ radius -2, center.getY()-2, 4, 4);//desni kvadrat
			g.drawRect(center.getX()-2, center.getY()-radius-2, 4, 4);//gornji kvadrat
			g.drawRect(center.getX()-2, center.getY()+radius-2, 4, 4);//donji kvadrat
		
		   
		}
}

	public void fill (Graphics g) {
		g.setColor(getInnerColor());
		//dodajemo i oduzimamo 1 da se ne bi preklopilo sa ivicom
		g.fillOval(this.center.getX() - this.radius + 1, this.center.getY() - this.radius + 1, this.radius*2 - 2, this.radius*2 - 2);
	}
	
	@Override
	public void moveTo(int x, int y) {
		center.moveTo(x, y);
	}
	
	@Override
	public void moveBy(int x, int y) {
		center.moveBy(x, y);
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof Circle) {
		return (int)(this.area() - ((Circle)o).area());
		}
		return 0;
	}
	
	
	@Override
	public Circle clone() {
		Circle c = new Circle();
		
		c.getCenter().setX(this.getCenter().getX());
		c.getCenter().setY(this.getCenter().getY());
		c.setRadius(this.getRadius());
		c.setColor(this.getColor());
		c.setInnerColor(this.getInnerColor());
		
		return c;
	}
	
	public Point getCenter() {
		return center;
	}
	
	public void setCenter(Point center) {
		this.center = center;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public void setRadius(int radius){
		if(radius < 0) {
			throw new NumberFormatException("The value of the radius should not be negative.");
		} else {
			this.radius = radius;
		}
	
	}
	
	
	public String toString () {
	  return "Circle: center:" + "(" + center.getX() + "," + center.getY() + ")" + " " + "radius:" + radius
			  + " " + "Outline color:" + getColor() 
				+ " " + "Inner color:" + getInnerColor(); 
	}


}
