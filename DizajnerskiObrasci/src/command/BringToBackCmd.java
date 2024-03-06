package command;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToBackCmd implements Command{

	private Shape s;
	private DrawingModel model;
	private int selectedShapeIndex;

	
	public BringToBackCmd(int selectedShapeIndex, DrawingModel model) {
		this.s = model.getShapes().get(selectedShapeIndex);
		this.model = model;
		this.selectedShapeIndex = selectedShapeIndex;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.getShapes().remove(this.selectedShapeIndex);
		model.getShapes().add(0, this.s);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.getShapes().remove(this.s);
		model.getShapes().add(this.selectedShapeIndex, this.s);
	}
	
}
