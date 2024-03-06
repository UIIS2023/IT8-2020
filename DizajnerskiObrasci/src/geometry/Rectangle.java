package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape1 implements Cloneable{
	
    private Point upperLeftPoint = new Point();
    private int width;
    private int height;
    
    public Rectangle() {
    	
    }
    
    public Rectangle(Point upperLefPoint, int width, int height) {
    	this.upperLeftPoint = upperLefPoint;
    	this.width = width;
    	this.height = height;
    }
    
    public Rectangle(Point upperLeftPoint, int width, int height, boolean selected) {
    	this (upperLeftPoint, width, height);
    	setSelected(selected);
    }
    
    public Rectangle(Point upperLeftPoint, int width, int height, Color color) {
    	this (upperLeftPoint, width, height);
    	setColor(color);
    }
    
    public Rectangle(Point upperLeftPoint, int width, int height,  Color color, Color innerColor) {
    	this (upperLeftPoint, width, height, color);
    	setColor(color);
    	setInnerColor(innerColor);
    }
    
    public Rectangle(Point upperLeftPoint, int width, int height,  boolean selected, Color color, Color innerColor) {
    	this (upperLeftPoint, width, height, color, innerColor);
    	setSelected(selected);
    }
    
    public int area() {
    	return this.width*this.height; //ne mora this
    }
    
    public int circumference() {
    	return this.width*2 + this.height*2;
    }
    
    
	 public boolean equals (Object obj) {
		   if (obj instanceof Rectangle) {
			   Rectangle pomocna = (Rectangle)obj;
			   if (this.upperLeftPoint.equals(pomocna.upperLeftPoint) &&  
					   this.width == pomocna.width && this.height == pomocna.height);
				   return true;
		   }
		   else
			   return false;
		 }
	 
	 
    public boolean contains (int x, int y) {
    	if (upperLeftPoint.getX() <= x && x<=upperLeftPoint.getX() + width
    			&& upperLeftPoint.getY() <= y && y<=upperLeftPoint.getY() + height)
    		return true;
    	return false;
    }
    
    public boolean contains (Point p) {
    	if (upperLeftPoint.getX() <= p.getX() && p.getX()<=upperLeftPoint.getX() + width
    			&& upperLeftPoint.getY() <= p.getY() && p.getY()<=upperLeftPoint.getY() + height)
    		return true;
    	return false;
    }
    
    
	 
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
		this.fill(g);
	 
		
		if(isSelected()) {
			g.setColor(Color.BLUE);
	        g.drawRect(upperLeftPoint.getX() - 2,upperLeftPoint.getY() - 2, 4, 4);
	        g.drawRect(upperLeftPoint.getX() + width - 2,upperLeftPoint.getY() - 2, 4, 4);
	        g.drawRect(upperLeftPoint.getX() - 2,upperLeftPoint.getY() + height - 2, 4, 4);
	        g.drawRect(upperLeftPoint.getX() + width - 2,upperLeftPoint.getY() + height - 2, 4, 4);
		}
	
	}
	
	public void fill (Graphics g) {
		g.setColor(getInnerColor());
		g.fillRect(this.getUpperLeftPoint().getX() + 1, this.getUpperLeftPoint().getY() + 1, this.width - 2, this.height - 2);
	}
	
	@Override
	public void moveTo(int x, int y) {
		upperLeftPoint.moveTo(x, y);
		
	}

	@Override
	public void moveBy(int x, int y) {
		upperLeftPoint.moveBy(x,y);
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Rectangle) {
			return this.area() - ((Rectangle)o).area();
		}
		return 0;
	}
	

	@Override
	public Rectangle clone() {
		Rectangle r = new Rectangle();
		
		r.getUpperLeftPoint().setX(this.getUpperLeftPoint().getX());
		r.getUpperLeftPoint().setY(this.getUpperLeftPoint().getY());
		r.setWidth(this.getWidth());
		r.setHeight(this.getHeight());
		r.setColor(this.getColor());
		r.setInnerColor(this.getInnerColor());
		
		return r;
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	
	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public String toString () {
		return "Rectangle: Upper left point:" + "(" + upperLeftPoint.getX() + "," + upperLeftPoint.getY() + ")" 
				+ " " + "width = " +  width + " " + "height = " + height
				+ " " + "Outline color:" + getColor() 
				+ " " + "Inner color:" + getInnerColor(); 
	}
    
}
