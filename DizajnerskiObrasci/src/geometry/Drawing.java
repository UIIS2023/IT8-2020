package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Drawing extends JPanel{
	
	public Drawing() {
		setBackground(Color.WHITE);
	}

	public static void main (String[] args) {
		
		JFrame frame = new JFrame("Drawing");
		frame.setSize(800,600);
		frame.setVisible(true);
		Drawing drawing = new Drawing();//prosiruje JPanel
		frame.getContentPane().add(drawing);//poziv metode paint()
	}

	//redefinise metodu iz klase JComponent
	@Override
	public void paint(Graphics g) {
		
		//radi za ceo graficki kontekst a ne za oblik pojedinacno
				g.setColor(Color.GREEN);

				Point p = new Point(50,50, false, Color.RED);
				p.draw(g);
				
				Line l = new Line(new Point (100,100), new Point (200,200), false, Color.BLUE);
				l.draw(g);
				
				Rectangle r = new Rectangle(new Point(600, 300), 120, 170, true, Color.RED, Color.YELLOW);
				r.draw(g);
				
				Circle c = new Circle(new Point(500, 100), 80, false, Color.RED, Color.LIGHT_GRAY);
				c.draw(g);
				
				Donut d = new Donut(new Point(800, 100), 50, 25, true, Color.GREEN);
				d.draw(g);
				
				//Rectangle k1 = new Rectangle(new Point(500, 500), 50, 50);
				//k1.draw(g);
				
				//5. Zadatak
				/*int innerR=(int)(k1.getHeight()*Math.sqrt(2)/2);
				Donut d2 = new Donut
						(new Point(k1.getUpperLeftPoint().getX()+k1.getHeight()/2,k1.getUpperLeftPoint().getY()+k1.getHeight()/2),80,innerR);*/
				//d2.draw(g); 
				
				
				//vezbe 8
				//zadatak 1
				
				ArrayList<Shape> shapes = new ArrayList<>();
				shapes.add(p);
				shapes.add(l);
				shapes.add(c);
				shapes.add(d);
				shapes.add(r);
				
				//za pristup elementima arraya
				Iterator<Shape> it = shapes.iterator();
				
				while (it.hasNext()) {
					System.out.println("Selected:" + it.next().isSelected());//metoda next vraca naredni el u iteraciji
				}
				
				//treci el iz liste shape
				shapes.get(2).draw(g);
				shapes.get(shapes.size()-1).draw(g);
				
				//dodavanje i iscrtavanje nove linije u listu tako da ona bude 4. el u listi
				Line l1 = new Line (new Point(30,30), new Point(60,60));
				shapes.add(3,l1);
				shapes.get(3).draw(g);
				
				//uklanjanje 3. el
				shapes.remove(2);
				
				//setovanje svakog objekta iz liste kao selektovan pomocu iteratora
				while(it.hasNext()) {
					it.next().setSelected(true);
					//it.next pristupanje svakom
				}
				
				//iscrtavanje svakog objekta pomocu it
				while(it.hasNext()) {
					it.next().draw(g);
				}
				
				//setujemo kao selektovane samo povrsinske oblike
				while(it.hasNext()) {
					if(it.next() instanceof Shape) {
						it.next().setSelected(true);
					}
				}
				
				
				//zadatak 2
				/*g.setColor(Color.BLACK);
				shapes.get(3).draw(g);
				shapes.get(shapes.size()-1).draw(g);
				shapes.remove(1);
				shapes.get(1).draw(g);
				//preklopice se sa prethodno iscrtanim kvadratom
				shapes.get(3).draw(g);
				shapes.add(3, l1);
				
				it = shapes.iterator();
				while (it.hasNext()) {
					Shape s = it.next();
					if(s instanceof Circle || s instanceof Rectangle)
					s.draw(g);
				}
				
				
				//zadatak 3
				try {
					c1.setRadius(-10);
				}catch(Exception e) {
					e.printStackTrace();
				}
				//nije se prekinulo izvrsacanje programa
				//niti se promenio radius
				System.out.println(c1);*/
				
				
				//Zadatak 4.
				
				/*it = shapes.iterator();
				while(it.hasNext()) {
					Shape s = it.next();
					s.setSelected(true);
					s.draw(g);
				}*/
				
				//Zadatak 5.
				/*HashMap<String, Shape> hmShapes=new HashMap<String, Shape>();
				hmShapes.put("point", p);
				hmShapes.put("line", l);
				System.out.println(hmShapes.get("line"));*/
				
	}
	
	
}
