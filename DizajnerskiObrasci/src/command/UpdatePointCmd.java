package command;

import geometry.Point;

public class UpdatePointCmd implements Command {

	//treba nam tacka koju hocemo da menjamo, referenca na nju 
	//nova tacka - kako zelimo da izgleda ta koju menjamo
	
	private Point oldState;
	private Point newState;
	private Point original = new Point();
	
	public UpdatePointCmd (Point oldState, Point newState) {
		this.oldState = oldState;
		this.newState = newState;
	}
	
	//moramo sacuvati trenutno stanje - original
	//kada se menja sa starog na novo stanje i ako hocemo da vratimo na 
	//prethodno, staro smo preklopili sa novim, prvobitna vrednost se mora
	//cuvati negde
	
	@Override
	public void execute() {
		/*original.setX(oldState.getX());
		original.setY(oldState.getY());
		original.setColor(oldState.getColor());*/
		original = oldState.clone();
		
		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
		oldState.setColor(newState.getColor());
	}

	
	
	@Override
	public void unexecute() {
		oldState.setX(original.getX());
		oldState.setY(original.getY());
		oldState.setColor(original.getColor());
	}

}
