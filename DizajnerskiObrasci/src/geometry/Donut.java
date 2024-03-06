package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class Donut extends Circle implements Cloneable{
	
	private int innerRadius;

	public Donut() {
		
	}
	
    public Donut(Point center, int radius, int innerRadius) {
		//this.center - nije moguce jer je private
	    /*this.setCenter(center);
	    this.setRadius(radius);*/
	    super(center, radius); //mora biti prva naredba u okviru konstruktora
        this.innerRadius = innerRadius;
    }

    public Donut(Point center, int radius, int innerRadius, boolean selected) {
	   this(center, radius, innerRadius);
	  // this.setSelected(selected); //jer je selected u circle definisana kao private
	   setSelected(selected);
    }
    
    public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) {
 	   this(center, radius, innerRadius, selected);
 	   setColor(color);
     }
    
    public Donut (Point center, int radius, int innerRadius , Color color) {
 	   this(center, radius, innerRadius);
 	   this.setColor(color);
     }
    
    public Donut (Point center, int radius, int innerRadius , Color color, Color innerColor) {
  	   this(center, radius, innerRadius,color);
  	   setInnerColor(innerColor);
      }
    
    public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) {
  	   this(center, radius, innerRadius, selected, color);
  	   setInnerColor(innerColor);
      }
    
    public double area() {
 	   return super.area() - innerRadius*innerRadius*Math.PI;
    }
    
    
    public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut pomocni = (Donut) obj;
			if (getCenter().equals(pomocni.getCenter()) && getRadius() == pomocni.getRadius() && innerRadius == pomocni.innerRadius) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
    
    public boolean contains (int x, int y) {
 	    double dFromCenter = getCenter().distance(x, y);
 	    return super.contains(x, y) && dFromCenter > innerRadius;
    }
    
    public boolean contains (Point p) {
    	 double dFromCenter = getCenter().distance(p.getX(), p.getY());
  	    return super.contains(p.getX(), p.getY()) && dFromCenter > innerRadius;
    }
    
    
    @Override
	public void draw(Graphics g) {
    	
		super.draw(g);//crtanje spoljasnjeg kruga
		g.setColor(getColor());
    	g.drawOval(center.getX() - innerRadius, center.getY() -innerRadius, innerRadius*2, innerRadius*2);
	
    	
    	if (isSelected()) {
			g.setColor(Color.BLUE);
        g.drawRect(this.getCenter().getX() - innerRadius - 2,this.getCenter().getY() - 2, 4, 4);
        g.drawRect(this.getCenter().getX() + innerRadius - 2,this.getCenter().getY() - 2, 4, 4);
        g.drawRect(this.getCenter().getX() - 2,this.getCenter().getY() - innerRadius - 2, 4, 4);
        g.drawRect(this.getCenter().getX() - 2,this.getCenter().getY() + innerRadius - 2, 4, 4);
		}
	}
    
    
    public void fill(Graphics g) {
    	Ellipse2D el = new Ellipse2D.Float(center.getX() - innerRadius, center.getY() - innerRadius, 2*innerRadius, 2*innerRadius);
    	Ellipse2D outline = new Ellipse2D.Float(center.getX() - getRadius(), center.getY() - getRadius(), 2*getRadius(), 2*getRadius());
    	
    	Area outer = new Area(outline);
    	Area inner = new Area(el);
    	outer.subtract(inner);
    	
    	g.setColor(getInnerColor());
    	((Graphics2D)g).fill(outer);
    }

	@Override
	public int compareTo(Object o) {
		if (o instanceof Donut) {
		return (int)(this.area() - ((Donut)o).area());
		}
		return 0;
	}
	

	@Override
	public Donut clone() {
		Donut d = new Donut();
		
		d.getCenter().setX(this.getCenter().getX());
		d.getCenter().setY(this.getCenter().getY());
		d.setRadius(this.getRadius());
		d.setInnerRadius(this.getInnerRadius());
		d.setColor(this.getColor());
		d.setInnerColor(this.getInnerColor());
		
		return d;
	}

	public String toString () {
    	  return "Donut:" + super.toString().substring(7) + "innerRadius:" + innerRadius;
    	}
    
	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	

}
