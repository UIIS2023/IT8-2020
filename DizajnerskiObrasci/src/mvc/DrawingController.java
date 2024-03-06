package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Stack;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import adapter.HexagonAdapter;
import command.AddShapeCmd;
import command.BringToBackCmd;
import command.BringToFrontCmd;
import command.Command;
import command.DeselectShapeCmd;
import command.RemoveShapeCmd;
import command.SelectShapeCmd;
import command.ToBackCmd;
import command.ToFrontCmd;
import command.UpdateCircleCmd;
import command.UpdateDonutCmd;
import command.UpdateHexagonCmd;
import command.UpdateLineCmd;
import command.UpdatePointCmd;
import command.UpdateRectangleCmd;
import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgHexagon;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import strategy.SaveLoad;
import strategy.TextFileSaveLoad;
import strategy.ObjectFileSaveLoad;

public class DrawingController extends Observable {
	
	
	private DrawingModel model;
	private DrawingFrame frame;
	
	private boolean d;
	private Point startP;
	private Color colorOutline;
	private Color colorInner;
	
	private boolean selected;
	private ArrayList<Shape> selectedShapes; 
	
	//COMMAND
	private int undoRedo = -1; //trenutna pozicija komande
	private Stack<Command> commands = new Stack<Command>(); //za undo, remove
	private Stack<Command> commandsRedo = new Stack<Command>(); //za redo
	private DefaultListModel <String> logger;
	private ArrayList <String> textShapes;
	private int logLine;
	   
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		super();
		this.model = model;
		this.frame = frame;
		this.logger = frame.getDefaultListModel();
		this.selectedShapes = this.model.getSelectedShapes();
	}
	
	public void mouseClicked(MouseEvent e) {
		
		Point click = new Point (e.getX(), e.getY());
		Command command = null;
		
		//CRTANJE
		if (frame.getTglbtnPoint().isSelected()) {
			
			this.colorOutline = frame.getOutlineColor();
			Point p = new Point(e.getX(), e.getY(), colorOutline);
			
			//frame.repaint();
			command = new AddShapeCmd(p , model);
			command.execute();
			commands.push(command);
			undoRedo++;
			logger.addElement(p.toString());
			
		} else if (frame.getTglbtnLine().isSelected()) {
			
			if(startP == null) {
				startP = click;
			} else {
				
				this.colorOutline = frame.getOutlineColor();
				Line l = new Line (startP, new Point (e.getX(), e.getY()), colorOutline);
				//model.add((Shape)(new Line (startP, new Point (e.getX(), e.getY()))));
				
				//frame.repaint();
				startP = null;
				command = new AddShapeCmd(l , model);
				command.execute();
				commands.push(command);
				undoRedo++;
				logger.addElement(l.toString());
			}
			
		} else if (frame.getTglbtnRectangle().isSelected()) {
			
			DlgRectangle dialogR = new DlgRectangle();
			
			dialogR.getTxtUpperX().setText(Integer.toString(e.getX()));
			dialogR.getTxtUpperX().setEditable(false);
			dialogR.getTxtUpperY().setText(Integer.toString(e.getY()));
			dialogR.getTxtUpperY().setEditable(false);
			
			dialogR.setVisible(true);
			
			if (dialogR.isOkRect()) {
				
				int widthR = (Integer.parseInt(dialogR.getTxtWidth().getText()));
				int heightR = (Integer.parseInt(dialogR.getTxtHeight().getText()));
				//this.colorOutline = frame.getOutlineColor();
				//this.colorInner = frame.getInnerColor();
				
				if (dialogR.getOutlineColor() == null) {
					this.colorOutline = frame.getOutlineColor();
				} else {
					this.colorOutline = dialogR.getOutlineColor();
					frame.setOutlineColor(dialogR.getOutlineColor());
				}
				
				if (dialogR.getInnerColor() == null) {
					this.colorInner = frame.getInnerColor();
				} else {
					this.colorInner = dialogR.getInnerColor();
					frame.setInnerColor(dialogR.getInnerColor());
				}
				
				Rectangle r2 = new Rectangle(new Point(e.getX(), e.getY()), widthR, heightR, colorOutline, colorInner);
				
				//frame.repaint();
				command = new AddShapeCmd(r2 , model);
				command.execute();
				commands.push(command);
				undoRedo++;
				logger.addElement(r2.toString());
			}
			
		} else if(frame.getTglbtnCircle().isSelected()) {
			
			DlgCircle dialogC = new DlgCircle();
			
			dialogC.getTxtXCenter().setText(Integer.toString(e.getX()));
			dialogC.getTxtXCenter().setEditable(false);
			dialogC.getTxtYCenter().setText(Integer.toString(e.getY()));
			dialogC.getTxtYCenter().setEditable(false);
			dialogC.setVisible(true);
			
			if(dialogC.isOkCircle()) {
				
				int radius1 = (Integer.parseInt(dialogC.getTxtRadius().getText()));
				//this.colorOutline = frame.getOutlineColor();
				//this.colorInner = frame.getInnerColor();
				
				if (dialogC.getOutlineColor() == null) {
					this.colorOutline = frame.getOutlineColor();
				} else {
					this.colorOutline = dialogC.getOutlineColor();
					frame.setOutlineColor(dialogC.getOutlineColor());
				}
				
				if (dialogC.getInnerColor() == null) {
					this.colorInner = frame.getInnerColor();
				} else {
					this.colorInner = dialogC.getInnerColor();
					frame.setInnerColor(dialogC.getInnerColor());
				}
				
				Circle c1 = new Circle (new Point(e.getX(), e.getY()), radius1, colorOutline, colorInner);
				
				//frame.repaint();
				command = new AddShapeCmd(c1 , model);
				command.execute();
				commands.push(command);
				undoRedo++;
				logger.addElement(c1.toString());
			}
			
		} else if(frame.getTglbtnDonut().isSelected()) {
			
			DlgDonut dialogD = new DlgDonut();
			
			dialogD.getTxtX().setText(Integer.toString(e.getX()));
			dialogD.getTxtY().setText(Integer.toString(e.getY()));
			dialogD.setVisible(true);
			
			if(dialogD.isOkDonut()) {
				int rad1 = (Integer.parseInt(dialogD.getTxtRadius().getText()));
				int inner1 = (Integer.parseInt(dialogD.getTxtInnerRadius().getText()));
				//this.colorOutline = frame.getOutlineColor();
				//this.colorInner = frame.getInnerColor();
				
				if (dialogD.getOutlineColor() == null) {
					this.colorOutline = frame.getOutlineColor();
				} else {
					this.colorOutline = dialogD.getOutlineColor();
					frame.setOutlineColor(dialogD.getOutlineColor());
				}
				
				if (dialogD.getInnerColor() == null) {
					this.colorInner = frame.getInnerColor();
				} else {
					this.colorInner = dialogD.getInnerColor();
					frame.setInnerColor(dialogD.getInnerColor());
				}
				
				Donut d1 = new Donut(new Point(e.getX(), e.getY()),rad1, inner1, colorOutline, colorInner);
				
				//frame.repaint();
				command = new AddShapeCmd(d1 , model);
				command.execute();
				commands.push(command);
				undoRedo++;
				logger.addElement(d1.toString());
			}
			
		}else if (frame.getTglbtnHexagon().isSelected()) {
			
			DlgHexagon dialogHex = new DlgHexagon();
			
			dialogHex.getTxtX().setText(Integer.toString(e.getX()));
			dialogHex.getTxtX().setEditable(false);
			dialogHex.getTxtY().setText(Integer.toString(e.getY()));
			dialogHex.getTxtY().setEditable(false);
			dialogHex.setVisible(true);
			
			if(dialogHex.isBtnOk()) {
				
				int radiusHex = (Integer.parseInt(dialogHex.getTxtRadius().getText()));
				
				if (dialogHex.getOutlineColor() == null) {
					this.colorOutline = frame.getOutlineColor();
				} else {
					this.colorOutline = dialogHex.getOutlineColor();
					frame.setOutlineColor(dialogHex.getOutlineColor());
				}
				
				if (dialogHex.getInnerColor() == null) {
					this.colorInner = frame.getInnerColor();
				} else {
					this.colorInner = dialogHex.getInnerColor();
					frame.setInnerColor(dialogHex.getInnerColor());
				}
				
				HexagonAdapter hexagon = new HexagonAdapter (new Point(e.getX(), e.getY()), radiusHex);
				
				//frame.repaint();
				command = new AddShapeCmd(hexagon , model);
				command.execute();
				commands.push(command);
				undoRedo++;
				logger.addElement(hexagon.toString());
			}
		}
		
		commandsRedo.clear();
		frame.repaint();
		
		//SELEKTOVANJE
		if(frame.getTglbtnSelect().isSelected()) {
			select(e.getX(), e.getY(), e.getModifiers());
		}
	
	}	
	
	
	//UNDO/REDO
	
	public void undo() {
		if (undoRedo < 0 || commands.size() == 0) {
			JOptionPane.showMessageDialog(null, "Undo command is not active");
			return;
		}
		
		//deselectAll(null);
		Command command = commands.get(commands.size()-1);
		commands.remove(command);
		commandsRedo.push(command);
		command.unexecute();
		setChanged();
		notifyObservers();
		frame.repaint();
		undoRedo--;
		logger.addElement("Undo: " + command.getClass().getName());
	}
	
	public void redo() {
		
		if (commandsRedo.size() < 1) {
			JOptionPane.showMessageDialog(null, "Redo command is not active");
			return;
		}
		
		Command command = commandsRedo.get(commandsRedo.size()-1);	
		commandsRedo.remove(command);
		commands.push(command);
		command.execute();
		setChanged();
		notifyObservers();
		undoRedo++;
		frame.repaint();
		logger.addElement("Redo: " + command.getClass().getName());
	}
	
	//DELETE, SELECT, MODIFY
	public void delete() {
		Command commandsRemove = null;
		
		if(model.getShapes().size() == 0) {
			JOptionPane.showMessageDialog(null, "Field is empty!");
			setD(true);
		} else {
			setD(false);
		}
		
		for (int i = 0; i< model.getShapes().size(); i++) {
			
			
			if(model.getShapes().get(i).isSelected()) {
				
				
				setD(true);
				int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Warning message", JOptionPane.YES_NO_OPTION);
				if (selectedOption == JOptionPane.YES_OPTION) {
					//model.getShapes().remove(i);
					commandsRemove = new RemoveShapeCmd(model.getShapes().get(i), model);
				    commandsRemove.execute();
				    setChanged();
					notifyObservers();
					commandsRedo.clear();
					commands.push(commandsRemove);
					undoRedo++;
				    //frame.repaint();
					logger.addElement("Remove: " + model.getShapes().get(i).toString());
					
				} else {
					model.getShapes().get(i).setSelected(false);
					selectedShapes.remove(model.getShapes().get(i));
					setChanged();
					notifyObservers();
					//frame.repaint();
					setD(true);
				}
				i = -1;
				frame.repaint();
			}			
		} 
			if (isD() == false) {
				JOptionPane.showMessageDialog(null, "You don't have any selected shapes!", "ERROR", JOptionPane.WARNING_MESSAGE);
				setD(true);
			}
	}	
	
	public void select(int x, int y, int m) {
		/*Shape newShape = null;
	
		for(int i=0;i<model.getShapes().size();i++) {
            Shape shape1 =model.getShapes().get(i);
            shape1.setSelected(false);
            if(model.getShapes().get(i).contains(x, y)) {
                newShape=shape1;   
                setD(false);
        }
           
    }
		if(newShape != null) {
			newShape.setSelected(true);
			//selectedShape.add(newShape);
		}
		frame.repaint();*/
		if (selectedShapes == null) {
			selectedShapes = new ArrayList<Shape>();
		}
		
		for (int i = model.getShapes().size()-1; i >= 0; i--) {
			Shape shape = model.getShapes().get(i);
			
			if(shape.contains(x, y)) {
				//KADA SE KLIKNE NA OBLIK DA SE DESELEKTUJE
				//AKO JE VISE OBLIKA SELEKTOVANO, SVI SE ODSELEKTUJU NA KLIK SA STRANE
				
				
				//shape.setSelected(true);
				//return;
				SelectShapeCmd selectShapeCmd = new SelectShapeCmd(shape, model);
				selectShapeCmd.execute();
				//commands.push(selectShapeCmd);
				//undoRedo++;
				selectedShapes.add(shape);
				
				//SELEKTOVANJE VISE OBLIKA SA CTRL
				if (m == 18) {
					logger.addElement("Select: " + shape.toString());
					setChanged();
					notifyObservers();
					break;
				} else {
					deselectAll(shape);
					logger.addElement("Select: " + shape.toString());
					setChanged();
					notifyObservers();
					break;
				}
				
			} else if (shape.contains(x, y) && shape.isSelected()){
				//deselect();
				//shape.setSelected(false);
				DeselectShapeCmd deselectShapeCmd = new DeselectShapeCmd(shape, model);
				deselectShapeCmd.execute();
				selectedShapes.remove(shape);
				setChanged();
				notifyObservers();
				//commands.push(deselectShapeCmd);
				//undoRedo++;
				logger.addElement("Deselect: " + shape.toString());
				break;
				
			} else {
				
				if (m == 18) {
					continue;
				}
				
				//deselect();
				//shape.setSelected(false);
				
				DeselectShapeCmd deselectShapeCmd = new DeselectShapeCmd(shape, model);
				
				if (shape.isSelected()) {
					logger.addElement("Deselect: " + shape.toString());
				}
				deselectShapeCmd.execute();
				selectedShapes.remove(shape);
				setChanged();
				notifyObservers();
			}
			frame.repaint(); 
		}
		
	}
		
	public void deselectAll (Shape shape1) {
		for (int i = model.getShapes().size()-1; i >= 0; i--) {
			Shape shape = model.getShapes().get(i);
			
			if (shape.equals(shape1)) {
				continue;
			}
			
			DeselectShapeCmd deselectShapeCmd = new DeselectShapeCmd(shape, model);
			
			if (shape.isSelected()) {
				logger.addElement("Deselect: " + shape.toString());
			}
			deselectShapeCmd.execute();
			//commands.push(deselectShapeCmd);
			//undoRedo++;
			selectedShapes.remove(shape);
		}
	}

	public void moveShape(String move) {
		
		if (selectedShapes.size() != 1) {
			JOptionPane.showMessageDialog(null, "You have to select only one shape!");
		} else {
			
			int selectedShapeIndex = model.getShapes().indexOf(selectedShapes.get(0));
			
			if (move.equals("toFront")) {
				
				if (selectedShapeIndex != model.getShapes().size() - 1) {
					
					ToFrontCmd toFront = new ToFrontCmd (selectedShapeIndex, model);
					toFront.execute();
					commands.push(toFront);
					undoRedo++;
					logger.addElement("ToFront: " + selectedShapes.get(0).toString());
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Your shape is to front!");
				}
				
			} else if (move.equals("toBack")) {
				
				if (selectedShapeIndex != 0) {
					
					ToBackCmd toBack = new ToBackCmd (selectedShapeIndex, model);
					toBack.execute();
					commands.push(toBack);
					undoRedo++;
					logger.addElement("ToBack: " + selectedShapes.get(0).toString());
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Your shape is to back!");
				}
			} else if (move.equals("bringToFront")) {
				
				if (selectedShapeIndex !=  model.getShapes().size() - 1) {
					
					BringToFrontCmd bringToFront = new BringToFrontCmd (selectedShapeIndex, model);
					bringToFront.execute();
					commands.push(bringToFront);
					undoRedo++;
					logger.addElement("BringToFront: " + selectedShapes.get(0).toString());
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Your shape is to front!");
				}
			} else if (move.equals("bringToBack")) {
				
				if (selectedShapeIndex != 0) {
					
					BringToBackCmd bringToBack = new BringToBackCmd (selectedShapeIndex, model);
					bringToBack.execute();
					commands.push(bringToBack);
					undoRedo++;
					logger.addElement("BringToBack: " + selectedShapes.get(0).toString());
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Your shape is to back!");
				}
				
			} 
			frame.repaint();
		}
	}
	
	public void saveData(String filePath) {
		SaveLoad objectFileSave = new ObjectFileSaveLoad(model);
		objectFileSave.saveData(filePath);
		SaveLoad textFileSave = new TextFileSaveLoad(Collections.list(logger.elements()));
		textFileSave.saveData(filePath);
	}
	
	public void loadData(String filePath) {
		if (filePath.endsWith(".txt")) {
			SaveLoad textFileLoad = new TextFileSaveLoad(this);
			textFileLoad.loadData(filePath);
			frame.getBtnReadLog().setVisible(true);
			logLine = 0;
		} else {
			SaveLoad objectFileLoad = new ObjectFileSaveLoad(model);
			objectFileLoad.loadData(filePath);
			frame.repaint();
		}
	}
	
	public void readLog() {
		if (textShapes.size() == 0) {
			JOptionPane.showMessageDialog(null, "File is empty");
		} else if (textShapes.size() == logLine) {
			JOptionPane.showMessageDialog(null, "You donr have new line in the log");
		} else {
			String[] toStringSplit = splitLogLine(textShapes.get(logLine));
			System.out.println(textShapes.get(logLine));
			
			if (toStringSplit[0].equals("Modify")) {
				
				toStringSplit = splitLogLine(textShapes.get(logLine).substring(8));
				
				if(toStringSplit[0].equals("Point")) {
					
					int indexSelect = model.getShapes().indexOf(selectedShapes.get(0));
					UpdatePointCmd command = new UpdatePointCmd((Point)model.getShapes().get(indexSelect), createPointLog(toStringSplit));
					command.execute();
					commands.push(command);
					undoRedo++;
					
				} else if(toStringSplit[0].equals("Line")) {
					
					int indexSelect = model.getShapes().indexOf(selectedShapes.get(0));
					UpdateLineCmd command = new UpdateLineCmd((Line)model.getShapes().get(indexSelect), createLineLog(toStringSplit));
					command.execute();
					commands.push(command);
					undoRedo++;
					
				} else if(toStringSplit[0].equals("Rectangle")) {
					
					int indexSelect = model.getShapes().indexOf(selectedShapes.get(0));
					UpdateRectangleCmd command = new UpdateRectangleCmd((Rectangle)model.getShapes().get(indexSelect), createRectangleLog(toStringSplit));
					command.execute();
					commands.push(command);
					undoRedo++;
					
				} else if(toStringSplit[0].equals("Circle")) {
					
					int indexSelect = model.getShapes().indexOf(selectedShapes.get(0));
					UpdateCircleCmd command = new UpdateCircleCmd((Circle)model.getShapes().get(indexSelect), createCircleLog(toStringSplit));
					command.execute();
					commands.push(command);
					undoRedo++;
					
				} else if(toStringSplit[0].equals("Donut")) {
					
					int indexSelect = model.getShapes().indexOf(selectedShapes.get(0));
					UpdateDonutCmd command = new UpdateDonutCmd((Donut)model.getShapes().get(indexSelect), createDonutLog(toStringSplit));
					command.execute();
					commands.push(command);
					undoRedo++;
					
				} else {
					
					int indexSelect = model.getShapes().indexOf(selectedShapes.get(0));
					UpdateHexagonCmd command = new UpdateHexagonCmd((HexagonAdapter)model.getShapes().get(indexSelect), createHexagonLog(toStringSplit));
					command.execute();
					commands.push(command);
					undoRedo++;
					
				} 
			} else if (toStringSplit[0].equals("Remove")) {
				
				toStringSplit = splitLogLine(textShapes.get(logLine).substring(8));
				
				if(toStringSplit[0].equals("Point")) {
					
					int index = model.getShapes().indexOf(createPointLog(toStringSplit));
					RemoveShapeCmd commandsRemove = new RemoveShapeCmd(model.getShapes().get(index), model);
					selectedShapes.remove(model.getShapes().get(index));
					commandsRemove.execute();
					commands.push(commandsRemove);
					undoRedo++;
					
				} else if(toStringSplit[0].equals("Line")) {
					
					int index = model.getShapes().indexOf(createLineLog(toStringSplit));
					RemoveShapeCmd commandsRemove = new RemoveShapeCmd(model.getShapes().get(index), model);
					selectedShapes.remove(model.getShapes().get(index));
					commandsRemove.execute();
					commands.push(commandsRemove);
					undoRedo++;
					
				} else if(toStringSplit[0].equals("Rectangle")) {
					
					int index = model.getShapes().indexOf(createRectangleLog(toStringSplit));
					RemoveShapeCmd commandsRemove = new RemoveShapeCmd(model.getShapes().get(index), model);
					selectedShapes.remove(model.getShapes().get(index));
					commandsRemove.execute();
					commands.push(commandsRemove);
					undoRedo++;
					
				} else if(toStringSplit[0].equals("Circle")) {
					
					int index = model.getShapes().indexOf(createCircleLog(toStringSplit));
					RemoveShapeCmd commandsRemove = new RemoveShapeCmd(model.getShapes().get(index), model);
					selectedShapes.remove(model.getShapes().get(index));
					commandsRemove.execute();
					commands.push(commandsRemove);
					undoRedo++;
					
				} else if(toStringSplit[0].equals("Donut")) {
					
					int index = model.getShapes().indexOf(createDonutLog(toStringSplit));
					RemoveShapeCmd commandsRemove = new RemoveShapeCmd(model.getShapes().get(index), model);
					selectedShapes.remove(model.getShapes().get(index));
					commandsRemove.execute();
					commands.push(commandsRemove);
					undoRedo++;
					
				} else {
					
					int index = model.getShapes().indexOf(createHexagonLog(toStringSplit));
					RemoveShapeCmd commandsRemove = new RemoveShapeCmd(model.getShapes().get(index), model);
					selectedShapes.remove(model.getShapes().get(index));
					commandsRemove.execute();
					commands.push(commandsRemove);
					undoRedo++;
					
				} 
				setChanged();
				notifyObservers();
				
			} else if (toStringSplit[0].equals("Select")) {
				
				toStringSplit = splitLogLine(textShapes.get(logLine).substring(8));
				
				if(toStringSplit[0].equals("Point")) {
					
					int index = model.getShapes().indexOf(createPointLog(toStringSplit));
					SelectShapeCmd selectShapeCmd = new SelectShapeCmd(model.getShapes().get(index), model);
					selectShapeCmd.execute();
					selectedShapes.add(model.getShapes().get(index));
					
				} else if(toStringSplit[0].equals("Line")) {
					
					int index = model.getShapes().indexOf(createLineLog(toStringSplit));
					SelectShapeCmd selectShapeCmd = new SelectShapeCmd(model.getShapes().get(index), model);
					selectShapeCmd.execute();
					selectedShapes.add(model.getShapes().get(index));
					
				} else if(toStringSplit[0].equals("Rectangle")) {
					
					int index = model.getShapes().indexOf(createRectangleLog(toStringSplit));
					SelectShapeCmd selectShapeCmd = new SelectShapeCmd(model.getShapes().get(index), model);
					selectShapeCmd.execute();
					selectedShapes.add(model.getShapes().get(index));
					
				} else if(toStringSplit[0].equals("Circle")) {
					
					int index = model.getShapes().indexOf(createCircleLog(toStringSplit));
					SelectShapeCmd selectShapeCmd = new SelectShapeCmd(model.getShapes().get(index), model);
					selectShapeCmd.execute();
					selectedShapes.add(model.getShapes().get(index));
					
				} else if(toStringSplit[0].equals("Donut")) {
					
					int index = model.getShapes().indexOf(createDonutLog(toStringSplit));
					SelectShapeCmd selectShapeCmd = new SelectShapeCmd(model.getShapes().get(index), model);
					selectShapeCmd.execute();
					selectedShapes.add(model.getShapes().get(index));
					
				} else {
					
					int index = model.getShapes().indexOf(createHexagonLog(toStringSplit));
					SelectShapeCmd selectShapeCmd = new SelectShapeCmd(model.getShapes().get(index), model);
					selectShapeCmd.execute();
					selectedShapes.add(model.getShapes().get(index));
					
				} 
				setChanged();
				notifyObservers();
				
		} else if (toStringSplit[0].equals("Deselect")) {
			
			toStringSplit = splitLogLine(textShapes.get(logLine).substring(10));
			
			if(toStringSplit[0].equals("Point")) {
				
				int index = model.getShapes().indexOf(createPointLog(toStringSplit));
				DeselectShapeCmd deselectShapeCmd = new DeselectShapeCmd(model.getShapes().get(index), model);
				deselectShapeCmd.execute();
				selectedShapes.remove(model.getShapes().get(index));
				
			} else if(toStringSplit[0].equals("Line")) {
				
				int index = model.getShapes().indexOf(createLineLog(toStringSplit));
				DeselectShapeCmd deselectShapeCmd = new DeselectShapeCmd(model.getShapes().get(index), model);
				deselectShapeCmd.execute();
				selectedShapes.remove(model.getShapes().get(index));
				
			} else if(toStringSplit[0].equals("Rectangle")) {
				
				int index = model.getShapes().indexOf(createRectangleLog(toStringSplit));
				DeselectShapeCmd deselectShapeCmd = new DeselectShapeCmd(model.getShapes().get(index), model);
				deselectShapeCmd.execute();
				selectedShapes.remove(model.getShapes().get(index));
				
			} else if(toStringSplit[0].equals("Circle")) {
				
				int index = model.getShapes().indexOf(createCircleLog(toStringSplit));
				DeselectShapeCmd deselectShapeCmd = new DeselectShapeCmd(model.getShapes().get(index), model);
				deselectShapeCmd.execute();
				selectedShapes.remove(model.getShapes().get(index));
				
			} else if(toStringSplit[0].equals("Donut")) {
				
				int index = model.getShapes().indexOf(createDonutLog(toStringSplit));
				DeselectShapeCmd deselectShapeCmd = new DeselectShapeCmd(model.getShapes().get(index), model);
				deselectShapeCmd.execute();
				selectedShapes.remove(model.getShapes().get(index));
				
			} else {
				
				int index = model.getShapes().indexOf(createHexagonLog(toStringSplit));
				DeselectShapeCmd deselectShapeCmd = new DeselectShapeCmd(model.getShapes().get(index), model);
				deselectShapeCmd.execute();
				selectedShapes.remove(model.getShapes().get(index));
				
			} 
			setChanged();
			notifyObservers();
		} else if (toStringSplit[0].equals("ToFront")) {
			
			int indexSelect = model.getShapes().indexOf(selectedShapes.get(0));
			ToFrontCmd toFront = new ToFrontCmd(indexSelect, model);
			toFront.execute();
			commands.push(toFront);
			undoRedo++;
			
		} else if (toStringSplit[0].equals("ToBack")) {
			
			int indexSelect = model.getShapes().indexOf(selectedShapes.get(0));
			ToBackCmd toBack = new ToBackCmd(indexSelect, model);
			toBack.execute();
			commands.push(toBack);
			undoRedo++;
			
		} else if (toStringSplit[0].equals("BringToFront")) {
			
			int indexSelect = model.getShapes().indexOf(selectedShapes.get(0));
			BringToFrontCmd bringToFront = new BringToFrontCmd(indexSelect, model);
			bringToFront.execute();
			commands.push(bringToFront);
			undoRedo++;
			
		} else if (toStringSplit[0].equals("BringToBack")) {
			
			int indexSelect = model.getShapes().indexOf(selectedShapes.get(0));
			BringToBackCmd bringToBack = new BringToBackCmd(indexSelect, model);
			bringToBack.execute();
			commands.push(bringToBack);
			undoRedo++;
			
		} else if (toStringSplit[0].equals("Undo")) {
			
			undo();
			logger.clear();
			
		} else if (toStringSplit[0].equals("Redo")) {
			
			redo();
			logger.clear();
			
		} else {
			
			if (toStringSplit[0].equals("Point")) {
				
				AddShapeCmd command = new AddShapeCmd(createPointLog(toStringSplit), model);
				command.execute();
				commands.push(command);
				undoRedo++;
				
			} else if (toStringSplit[0].equals("Line")) {
				
				AddShapeCmd command = new AddShapeCmd(createLineLog(toStringSplit), model);
				command.execute();
				commands.push(command);
				undoRedo++;
				
			} else if (toStringSplit[0].equals("Rectangle")) {
				
				AddShapeCmd command = new AddShapeCmd(createRectangleLog(toStringSplit), model);
				command.execute();
				commands.push(command);
				undoRedo++;
				
			} else if (toStringSplit[0].equals("Circle")) {
				
				AddShapeCmd command = new AddShapeCmd(createCircleLog(toStringSplit), model);
				command.execute();
				commands.push(command);
				undoRedo++;
				
			} else if (toStringSplit[0].equals("Donut")) {
				
				AddShapeCmd command = new AddShapeCmd(createDonutLog(toStringSplit), model);
				command.execute();
				commands.push(command);
				undoRedo++;
				
			} else {
				
				AddShapeCmd command = new AddShapeCmd(createHexagonLog(toStringSplit), model);
				command.execute();
				commands.push(command);
				undoRedo++;
				
			}
		}
			
		logLine++;
		frame.repaint();
			 
		}
	}
	
	private String[] splitLogLine (String textLine) {
		return textLine.split(":");
	}
	
	public Point createPointLog (String[] toStringSplit) {
		String textCoordinate = toStringSplit[1].split(" ")[0];	
		String coordinate = textCoordinate.substring(1, textCoordinate.length() - 1);
		int x = Integer.parseInt(coordinate.split(",")[0]);
		int y = Integer.parseInt(coordinate.split(",")[1]);
		String textColor = toStringSplit[2].substring(15, toStringSplit[2].length()-1);
		int r = Integer.parseInt(textColor.split(",")[0].substring(2));
		int g = Integer.parseInt(textColor.split(",")[1].substring(2));
		int b = Integer.parseInt(textColor.split(",")[2].substring(2));
		Point point = new Point(x, y, new Color(r, g, b));
		return point;
	}
	
	public Line createLineLog (String[] toStringSplit) {
		String textStartCoordinate = toStringSplit[2].split(" ")[0];	
		String startCoordinate = textStartCoordinate.substring(1, textStartCoordinate.length() - 1);
		int startX = Integer.parseInt(startCoordinate.split(",")[0]);
		int startY = Integer.parseInt(startCoordinate.split(",")[1]);
		
		String textEndCoordinate = toStringSplit[3].split(" ")[0];	
		String endCoordinate = textEndCoordinate.substring(1, textEndCoordinate.length() - 1);
		int endX = Integer.parseInt(endCoordinate.split(",")[0]);
		int endY = Integer.parseInt(endCoordinate.split(",")[1]);
		
		String textColor = toStringSplit[4].substring(15, toStringSplit[4].length()-1);
		int r = Integer.parseInt(textColor.split(",")[0].substring(2));
		int g = Integer.parseInt(textColor.split(",")[1].substring(2));
		int b = Integer.parseInt(textColor.split(",")[2].substring(2));
		Line line = new Line( new Point(startX, startY), new Point(endX, endY), new Color(r, g, b));
		return line;
	}
	
	public Rectangle createRectangleLog (String[] toStringSplit) {
		String textCoordinate = toStringSplit[2].split(" ")[0];	
		String coordinate = textCoordinate.substring(1, textCoordinate.length() - 1);
		int x = Integer.parseInt(coordinate.split(",")[0]);
		int y = Integer.parseInt(coordinate.split(",")[1]);
		
		String textWidth = toStringSplit[3].split(" ")[0];
		int width = Integer.parseInt(textWidth);
		
		String textHeight = toStringSplit[4].split(" ")[0];
		int height = Integer.parseInt(textHeight);
		
		String textOutlineColor = toStringSplit[5].split(" ")[0];
		String outlineColor = textOutlineColor.substring(15, textOutlineColor.length() - 1);
		int ro = Integer.parseInt(outlineColor.split(",")[0].substring(2));
		int go = Integer.parseInt(outlineColor.split(",")[1].substring(2));
		int bo = Integer.parseInt(outlineColor.split(",")[2].substring(2));
		
		String textInnerColor = toStringSplit[6].substring(15, toStringSplit[6].length() - 1);
		int ri = Integer.parseInt(textInnerColor.split(",")[0].substring(2));
		int gi = Integer.parseInt(textInnerColor.split(",")[1].substring(2));
		int bi = Integer.parseInt(textInnerColor.split(",")[2].substring(2));
		
		Rectangle rectangle = new Rectangle (new Point(x, y), width, height, new Color(ro, go, bo),
				new Color(ri, gi, bi));
		return rectangle;
	}
	
	public Circle createCircleLog (String[] toStringSplit) {
		String textCoordinate = toStringSplit[2].split(" ")[0];	
		String coordinate = textCoordinate.substring(1, textCoordinate.length() - 1);
		int x = Integer.parseInt(coordinate.split(",")[0]);
		int y = Integer.parseInt(coordinate.split(",")[1]);
		
		String textRadius = toStringSplit[3].split(" ")[0];
		int radius = Integer.parseInt(textRadius);
		
		String textOutlineColor = toStringSplit[4].split(" ")[0];
		String outlineColor = textOutlineColor.substring(15, textOutlineColor.length() - 1);
		int ro = Integer.parseInt(outlineColor.split(",")[0].substring(2));
		int go = Integer.parseInt(outlineColor.split(",")[1].substring(2));
		int bo = Integer.parseInt(outlineColor.split(",")[2].substring(2));
		
		String textInnerColor = toStringSplit[5].substring(15, toStringSplit[6].length() - 1);
		int ri = Integer.parseInt(textInnerColor.split(",")[0].substring(2));
		int gi = Integer.parseInt(textInnerColor.split(",")[1].substring(2));
		int bi = Integer.parseInt(textInnerColor.split(",")[2].substring(2));
		
		Circle circle = new Circle (new Point(x, y), radius, new Color(ro, go, bo), new Color(ri, gi, bi));
		return circle;
	}
	
	public Donut createDonutLog (String[] toStringSplit) {
		String textCoordinate = toStringSplit[2].split(" ")[0];	
		String coordinate = textCoordinate.substring(1, textCoordinate.length() - 1);
		int x = Integer.parseInt(coordinate.split(",")[0]);
		int y = Integer.parseInt(coordinate.split(",")[1]);
		
		String textRadius = toStringSplit[3].split(" ")[0];
		int radius = Integer.parseInt(textRadius);
		
		String textOutlineColor = toStringSplit[4].split(" ")[0];
		String outlineColor = textOutlineColor.substring(15, textOutlineColor.length() - 1);
		int ro = Integer.parseInt(outlineColor.split(",")[0].substring(2));
		int go = Integer.parseInt(outlineColor.split(",")[1].substring(2));
		int bo = Integer.parseInt(outlineColor.split(",")[2].substring(2));
		
		String textInnerColor = toStringSplit[5].split(" ")[0];
		String innerColor = textInnerColor.substring(15, textInnerColor.length() - 1);
		int ri = Integer.parseInt(textInnerColor.split(",")[0].substring(2));
		int gi = Integer.parseInt(textInnerColor.split(",")[1].substring(2));
		int bi = Integer.parseInt(textInnerColor.split(",")[2].substring(2));
		
		int innerRadius = Integer.parseInt(toStringSplit[6]);
		
		Donut donut = new Donut (new Point(x, y), radius, innerRadius,  new Color(ro, go, bo), new Color(ri, gi, bi));
		return donut;
	}
	
	public HexagonAdapter createHexagonLog (String[] toStringSplit) {
		String textCoordinate = toStringSplit[2].split(" ")[0];	
		String coordinate = textCoordinate.substring(1, textCoordinate.length() - 1);
		int x = Integer.parseInt(coordinate.split(",")[0]);
		int y = Integer.parseInt(coordinate.split(",")[1]);
		
		String textRadius = toStringSplit[3].split(" ")[0];
		int radius = Integer.parseInt(textRadius);
		
		String textOutlineColor = toStringSplit[4].split(" ")[0];
		String outlineColor = textOutlineColor.substring(15, textOutlineColor.length() - 1);
		int ro = Integer.parseInt(outlineColor.split(",")[0].substring(2));
		int go = Integer.parseInt(outlineColor.split(",")[1].substring(2));
		int bo = Integer.parseInt(outlineColor.split(",")[2].substring(2));
		
		String textInnerColor = toStringSplit[5].substring(15, toStringSplit[6].length() - 1);
		int ri = Integer.parseInt(textInnerColor.split(",")[0].substring(2));
		int gi = Integer.parseInt(textInnerColor.split(",")[1].substring(2));
		int bi = Integer.parseInt(textInnerColor.split(",")[2].substring(2));
		
		HexagonAdapter hexagon = new HexagonAdapter (new Point(x, y), radius, new Color(ro, go, bo), new Color(ri, gi, bi));
		return hexagon;
	}
	
	public void modify() {
		
		Command command = null;
		
		if(model.getShapes().size() == 0) {
			JOptionPane.showMessageDialog(null, "Field is empty!");
			setD(true);
		}else {
			setD(false);
		}  	
		
		for (int i=0; i<model.getShapes().size(); i++) {
			selected = false;
			if(model.getShapes().get(i) instanceof Point) {
				if (model.getShapes().get(i).isSelected()) {
					selected = true;
					Point pt = (Point) model.getShapes().get(i);
					DlgPoint dialog = new DlgPoint();
				
					dialog.getTxtX().setText(Integer.toString(pt.getX()));
					dialog.getTxtY().setText(Integer.toString(pt.getY()));
					dialog.setVisible(true);
				
				if(dialog.isConfirm()) {
					int pt1 = (Integer.parseInt(dialog.getTxtX().getText()));
					int pt2 = (Integer.parseInt(dialog.getTxtY().getText()));
					
					//this.colorOutline = dialog.getOutlineColor();
					Color c = dialog.getOutlineColor();
					
					if (c == null) {
						c = pt.getColor();
					} else {
						frame.getBtnOutlineColor().setBackground(dialog.getOutlineColor());
					}
					
					Point point1 = new Point(pt1,pt2, c);
					
					//model.remove(pt);//uklanja nemodifikovani
					//model.add(point1);
					
					command = new UpdatePointCmd(pt, point1);
					command.execute();
					commands.push(command);			
					undoRedo++;
				    frame.repaint();
				    logger.addElement("Modify: " + point1.toString());
				    
				}	else {//kada se klikne na x ili cancel
					model.getShapes().get(i).setSelected(false);
					frame.repaint();
				}
				}
				} else if (model.getShapes().get(i) instanceof Line) {
					if(model.getShapes().get(i).isSelected()) {
						selected = true;
						Line line = (Line) model.getShapes().get(i);
						DlgLine dlgLine = new DlgLine();
						
						dlgLine.getTxtStartX().setText( Integer.toString(line.getStartPoint().getX()));
						dlgLine.getTxtStartY().setText(Integer.toString(line.getStartPoint().getY()));
						dlgLine.getTxtEndX().setText(Integer.toString(line.getEndPoint().getX()));
						dlgLine.getTxtEndY().setText(Integer.toString(line.getEndPoint().getY()));
						dlgLine.setVisible(true);
						
						
						if(dlgLine.isOkLine()) {
							int l1 = (Integer.parseInt(dlgLine.getTxtStartX().getText()));
							int l2 = (Integer.parseInt(dlgLine.getTxtStartY().getText()));
							int l3 = (Integer.parseInt(dlgLine.getTxtEndX().getText()));
							int l4 = (Integer.parseInt(dlgLine.getTxtEndY().getText()));
							
							this.colorOutline = dlgLine.getOutlineColor1();
							
							if (this.colorOutline == null) {
								this.colorOutline = line.getColor();
							} else {
								frame.getBtnOutlineColor().setBackground(colorOutline);
							}
							
							Line line1 = new Line(new Point(l1, l2), new Point(l3, l4), colorOutline);
							line1.setColor(colorOutline);
							
							//model.getShapes().remove(i);//uklanja nemodifikovani
							//model.add(line1);
							
							command = new UpdateLineCmd(line, line1);
							command.execute();
							commands.push(command);	
							undoRedo++;
						    frame.repaint();
						    logger.addElement("Modify: " + line1.toString());
						    
						}else {
							model.getShapes().get(i).setSelected(false);
							frame.repaint();
						}
					}
				}else if(model.getShapes().get(i) instanceof Rectangle) {
					if(model.getShapes().get(i).isSelected()){
						selected = true;
						Rectangle rect = (Rectangle) model.getShapes().get(i);
						DlgRectangle dialogR = new DlgRectangle();
						
						dialogR.getTxtUpperX().setText(Integer.toString(rect.getUpperLeftPoint().getX()));
						dialogR.getTxtUpperY().setText(Integer.toString(rect.getUpperLeftPoint().getY()));					
						dialogR.getTxtWidth().setText(Integer.toString(rect.getWidth()));
						dialogR.getTxtHeight().setText(Integer.toString(rect.getHeight()));
						dialogR.setVisible(true);
						
						
						if(dialogR.isOkRect()) {
							int ul1 = (Integer.parseInt(dialogR.getTxtUpperX().getText()));
							int ul2 = (Integer.parseInt(dialogR.getTxtUpperY().getText()));
							int width1 = (Integer.parseInt(dialogR.getTxtWidth().getText()));
							int height1 = (Integer.parseInt(dialogR.getTxtHeight().getText()));
							
							this.colorOutline = dialogR.getOutlineColor();
							
							if (this.colorOutline == null) {
								this.colorOutline = rect.getColor();
							} else {
								frame.getBtnOutlineColor().setBackground(colorOutline);
							}
							
							this.colorInner = dialogR.getInnerColor();
							
							if (this.colorInner == null) {
								this.colorInner = rect.getInnerColor();
							} else {
								frame.getBtnInnerColor().setBackground(colorInner);
							}
							
							Rectangle rect1 = new Rectangle(new Point(ul1, ul2), width1,height1, colorOutline, colorInner);
							rect1.setColor(colorOutline);
							rect1.setInnerColor(colorInner);
							
							//model.getShapes().remove(i);
							//model.add(rect1);
							
							command = new UpdateRectangleCmd(rect, rect1);
							command.execute();
							commands.push(command);	
							undoRedo++;
						    frame.repaint();
							logger.addElement("Modify: " + rect1.toString());
						    
						}else {
							model.getShapes().get(i).setSelected(false);
							frame.repaint();
						}
					}
				}else if(model.getShapes().get(i) instanceof Circle && (model.getShapes().get(i) instanceof Donut)== false ) {
					if(model.getShapes().get(i).isSelected()) {
						selected = true;
						Circle circle1 = (Circle) model.getShapes().get(i);
						DlgCircle dialogC = new DlgCircle();
						
						dialogC.getTxtXCenter().setText("" + Integer.toString(circle1.getCenter().getX()));
						dialogC.getTxtYCenter().setText("" + Integer.toString(circle1.getCenter().getY()));
						dialogC.getTxtRadius().setText("" + Integer.toString(circle1.getRadius()));
						dialogC.setVisible(true);
						
						if(dialogC.isOkCircle()) {
							int cX = (Integer.parseInt(dialogC.getTxtXCenter().getText()));
							int cY = (Integer.parseInt(dialogC.getTxtYCenter().getText()));
							int radiusC= (Integer.parseInt(dialogC.getTxtRadius().getText()));
							
							this.colorOutline = dialogC.getOutlineColor();
							
							if (this.colorOutline == null) {
								this.colorOutline = circle1.getColor();
							} else {
								frame.getBtnOutlineColor().setBackground(colorOutline);
							}
							
							this.colorInner = dialogC.getInnerColor();
							
							if (this.colorInner == null) {
								this.colorInner = circle1.getInnerColor();
							} else {
								frame.getBtnInnerColor().setBackground(colorInner);
							}
							
							Circle circle3 = new Circle(new Point(cX, cY),radiusC, colorOutline, colorInner );
							circle3.setColor(colorOutline);
							circle3.setInnerColor(colorInner);
							
							//model.getShapes().remove(i);
							//model.add(circle3);
							
							command = new UpdateCircleCmd(circle1, circle3);
							command.execute();
							commands.push(command);	
							undoRedo++;
						    frame.repaint();
							logger.addElement("Modify: " + circle3.toString());
						
						}else {
							model.getShapes().get(i).setSelected(false);
							frame.repaint();
						}
					}
				}else if(model.getShapes().get(i) instanceof Donut) {
					if(model.getShapes().get(i).isSelected()) {
						selected = true;
						Donut donut1 = (Donut) model.getShapes().get(i);
						DlgDonut dialogd = new DlgDonut();
						
						dialogd.getTxtX().setText(" " + Integer.toString(donut1.getCenter().getX()));
						dialogd.getTxtY().setText(" " + Integer.toString(donut1.getCenter().getY()));
						dialogd.getTxtRadius().setText("" + Integer.toString(donut1.getRadius()));
						dialogd.getTxtInnerRadius().setText("" + Integer.toString(donut1.getInnerRadius()));
						dialogd.setVisible(true);
						
						if(dialogd.isOkDonut()) {
							int d1 = (Integer.parseInt(dialogd.getTxtX().getText()));
							int d2 = (Integer.parseInt(dialogd.getTxtY().getText()));
							int radiusD = (Integer.parseInt(dialogd.getTxtRadius().getText()));
							int innerD = (Integer.parseInt(dialogd.getTxtInnerRadius().getText()));
							
							this.colorOutline = dialogd.getOutlineColor();
							
							if (this.colorOutline == null) {
								this.colorOutline = donut1.getColor();
							} else {
								frame.getBtnOutlineColor().setBackground(colorOutline);
							}
							
							this.colorInner = dialogd.getInnerColor();
							
							if (this.colorInner == null) {
								this.colorInner = donut1.getInnerColor();
							} else {
								frame.getBtnInnerColor().setBackground(colorInner);
							}
							
							Donut donut2 = new Donut(new Point(d1, d2), radiusD, innerD, colorOutline, colorInner);
							donut2.setColor(colorOutline);
							donut2.setInnerColor(colorInner);
							
							//model.getShapes().remove(i);
							//model.add(donut2);
						
							command = new UpdateDonutCmd(donut1, donut2);
							command.execute();
							commands.push(command);		
							undoRedo++;
						    frame.repaint();
							logger.addElement("Modify: " + donut2.toString());
						    
						}else {
							model.getShapes().get(i).setSelected(false);
							frame.repaint();
						}
						
					}
				} else if (model.getShapes().get(i) instanceof HexagonAdapter){
					if(model.getShapes().get(i).isSelected()) {
						selected = true;
						HexagonAdapter hexagon = (HexagonAdapter) model.getShapes().get(i);
						DlgHexagon dialogHex = new DlgHexagon();
						
						dialogHex.getTxtX().setText("" + Integer.toString(hexagon.getX()));
						dialogHex.getTxtY().setText("" + Integer.toString(hexagon.getY()));
						dialogHex.getTxtRadius().setText("" + Integer.toString(hexagon.getR()));
						dialogHex.setVisible(true);
						
						if(dialogHex.isBtnOk()) {
							int hexX = (Integer.parseInt(dialogHex.getTxtX().getText()));
							int hexY = (Integer.parseInt(dialogHex.getTxtY().getText()));
							int radiusHex= (Integer.parseInt(dialogHex.getTxtRadius().getText()));
							
							this.colorOutline = dialogHex.getOutlineColor();
							
							if (this.colorOutline == null) {
								this.colorOutline = hexagon.getColor();
							} else {
								frame.getBtnOutlineColor().setBackground(colorOutline);
							}
							
							this.colorInner = dialogHex.getInnerColor();
							
							if (this.colorInner == null) {
								this.colorInner = hexagon.getInnerColor();
							} else {
								frame.getBtnInnerColor().setBackground(colorInner);
							}
							
							HexagonAdapter hexagon1 = new HexagonAdapter(new Point(hexX, hexY),radiusHex, colorOutline, colorInner );
							hexagon1.setColor(colorOutline);
							hexagon1.setInnerColor(colorInner);
							
							//model.getShapes().remove(i);
							//model.add(hexagon1);
							
							command = new UpdateHexagonCmd(hexagon, hexagon1);
							command.execute();
							commands.push(command);	
							undoRedo++;
						    frame.repaint();
							logger.addElement("Modify: " + hexagon1.toString());
						
						}else {
							model.getShapes().get(i).setSelected(false);
							frame.repaint();
						}
					}
				}
			} if (isSelected() == false) {
				//JOptionPane.showMessageDialog(null, "You don't have any selected shapes!", "ERROR", JOptionPane.WARNING_MESSAGE);
				setD(true);
				}   
		
		}
	
	
	
	
	public ArrayList<Shape> getSelectedShapes() {
		return selectedShapes;
	}

	public void setSelectedShapes(ArrayList<Shape> selectedShapes) {
		this.selectedShapes = selectedShapes;
	}

	public ArrayList<String> getTextShapes() {
		return textShapes;
	}

	public void setTextShapes(ArrayList<String> textShapes) {
		this.textShapes = textShapes;
	}

	public int getLogLine() {
		return logLine;
	}

	public void setLogLine(int logLine) {
		this.logLine = logLine;
	}

	public boolean isD() {
		return d;
	}
	
	public void setD (boolean d) {
		this.d = d;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected (boolean selected) {
		this.selected = selected;
	}

	}

