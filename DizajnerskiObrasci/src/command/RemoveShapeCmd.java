package command;

import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command {
	
	private Shape shape;
	private DrawingModel model;
	private int index;
	
	public RemoveShapeCmd(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}
	
	@Override
	public void execute() {
		this.index = model.getShapes().indexOf(this.shape);
		model.remove(shape);
		model.getSelectedShapes().remove(this.shape);
	}

	@Override
	public void unexecute() {
		//model.add(shape);
		model.getShapes().add(this.index, this.shape);
		model.getSelectedShapes().add(this.shape);
	}

}
