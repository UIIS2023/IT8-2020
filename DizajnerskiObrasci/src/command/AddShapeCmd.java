package command;

import geometry.Shape;
import mvc.DrawingModel;

public class AddShapeCmd implements Command{
	
	private Shape shape;
	private DrawingModel model;
	
	public AddShapeCmd(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}
	
	@Override
	public void execute() {
		model.add(shape);
		if (this.shape.isSelected()) {
			model.getSelectedShapes().add(this.shape);
		}
	}

	@Override
	public void unexecute() {
		model.remove(shape);
		if (this.shape.isSelected()) {
			model.getSelectedShapes().remove(this.shape);
		}
	}

	
}
