package strategy;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import mvc.DrawingController;

public class TextFileSaveLoad implements SaveLoad {
	
	private ArrayList<String> shapes;
	private DrawingController controller;

	public TextFileSaveLoad(ArrayList<String> shapes) {
		this.shapes = shapes;
	}

	public TextFileSaveLoad(DrawingController controller) {
		this.controller = controller;
	}

	@Override
	public void saveData(String filePath) {
		// TODO Auto-generated method stub
		try {
			PrintWriter printWriter = new PrintWriter(filePath + ".txt");
			for(String line: shapes) {
				printWriter.println(line);
			}
			printWriter.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void loadData(String filePath) {
		// TODO Auto-generated method stub
		try {
			//drawingController.lineNumber = 0;
			this.controller.setTextShapes((ArrayList<String>)Files.readAllLines(Paths.get(filePath)));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}
