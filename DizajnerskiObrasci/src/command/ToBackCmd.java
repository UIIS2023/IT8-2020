package command;

import geometry.Shape;
import mvc.DrawingModel;

public class ToBackCmd implements Command{

	private Shape s;
	private DrawingModel model;
	private int selectedShapeIndex;

	
	public ToBackCmd(int selectedShapeIndex, DrawingModel model) {
		this.s = model.getShapes().get(selectedShapeIndex);
		this.model = model;
		this.selectedShapeIndex = selectedShapeIndex;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.getShapes().set(this.selectedShapeIndex, model.getShapes().get(this.selectedShapeIndex - 1));
		model.getShapes().set(this.selectedShapeIndex - 1, this.s);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.getShapes().set(this.selectedShapeIndex - 1, model.getShapes().get(this.selectedShapeIndex));
		model.getShapes().set(this.selectedShapeIndex, this.s);
	}
	
}
