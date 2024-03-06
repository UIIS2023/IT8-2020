package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Shape;

import javax.swing.JScrollPane;
import javax.swing.JList;

public class DrawingFrame extends JFrame implements Observer{
	
	private static final long serialVersionUID = 1L;
	private DrawingView view = new DrawingView();
	private DrawingController controller;
	
	private JPanel contentPane;
	ButtonGroup btnGroup = new ButtonGroup();
    private Point startP;
    
    //ToggleButton - shapes, select, modify, delete
	private JToggleButton tglbtnPoint;
	private JToggleButton tglbtnLine;
	private JToggleButton tglbtnCircle;
	private JToggleButton tglbtnRectangle;
	private JToggleButton tglbtnDonut;
	private JToggleButton tglbtnHexagon;
	private JToggleButton tglbtnModify;
	private JToggleButton tglbtnSelect;
	private JToggleButton tglbtnDelete;
	
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_3;
	
	//default color for drawing
	private Color innerColor = Color.WHITE;
	private Color outlineColor = Color.ORANGE;
	
	//JButton
	private JButton btnInnerColor;
	private JButton btnOutlineColor;
	private JButton btnUndo;
	private JButton btnRedo;
	private JButton btnToFront;
	private JButton btnToBack;
	private JButton btnBringToFront;
	private JButton btnBringToBack;
	//save, load, readLog
	private JButton btnSave;
	private JButton btnLoad;
	private JButton btnReadLog;
	
	private JScrollPane scrollPane;
	private DefaultListModel<String> defaultListModel = new DefaultListModel<String>();
	private JList list;
	
	public DrawingView getView() {
		return view;
	}

	public void setDrawingController(DrawingController drawingController) {
		this.controller = drawingController;
	}
	
	public DrawingFrame() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Zaric Branka IT8/2020");
		setBounds(100, 100, 450, 250);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlWest = new JPanel();
		contentPane.add(pnlWest, BorderLayout.WEST);
		pnlWest.setLayout(new GridLayout(0, 1, 0, 0));
		
		tglbtnPoint = new JToggleButton("POINT");
		tglbtnPoint.setBackground(new Color(255, 255, 103));
		btnGroup.add(tglbtnPoint);
		pnlWest.add(tglbtnPoint);
		
		tglbtnLine = new JToggleButton("LINE");
		tglbtnLine.setBackground(new Color(255, 255, 103));
		btnGroup.add(tglbtnLine);
		pnlWest.add(tglbtnLine);
		
		tglbtnRectangle = new JToggleButton("RECTANGLE");
		tglbtnRectangle.setBackground(new Color(255, 255, 103));
		btnGroup.add(tglbtnRectangle);
		pnlWest.add(tglbtnRectangle);
		
		tglbtnCircle = new JToggleButton("CIRCLE");
		tglbtnCircle.setBackground(new Color(255, 255, 103));
		btnGroup.add(tglbtnCircle);
		pnlWest.add(tglbtnCircle);
		
		tglbtnDonut = new JToggleButton("DONUT");
		tglbtnDonut.setBackground(new Color(255, 255, 103));
		btnGroup.add(tglbtnDonut);
		pnlWest.add(tglbtnDonut);
		
		tglbtnHexagon = new JToggleButton("HEXAGON");
		tglbtnHexagon.setBackground(new Color(255, 255, 103));
		btnGroup.add(tglbtnHexagon);
		pnlWest.add(tglbtnHexagon);
		
		JPanel pnlNorth = new JPanel();
		contentPane.add(pnlNorth, BorderLayout.SOUTH);
		pnlNorth.setLayout(new GridLayout(1, 0, 0, 0));
		
		tglbtnSelect = new JToggleButton("SELECT");
		tglbtnSelect.setBackground(new Color(255, 204, 51));
		tglbtnSelect.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnGroup.add(tglbtnSelect);
		pnlNorth.add(tglbtnSelect);
		
		tglbtnModify = new JToggleButton("MODIFY");
		tglbtnModify.setEnabled(false);
		tglbtnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.modify();
			}
		});
		tglbtnModify.setBackground(new Color(255, 204, 51));
		tglbtnModify.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnGroup.add(tglbtnModify);
		pnlNorth.add(tglbtnModify);
		
		
		tglbtnDelete = new JToggleButton("DELETE");
		tglbtnDelete.setEnabled(false);
		tglbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.delete();
			}
		});
		tglbtnDelete.setBackground(new Color(255, 204, 51));
		tglbtnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnGroup.add(tglbtnDelete);
		pnlNorth.add(tglbtnDelete);
		
		//UNDO, REDO, TO FRONT, TO BACK, BRING TO FRONT, BRING TO BACK
		btnUndo = new JButton("UNDO");
		pnlNorth.add(btnUndo);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
		});
		btnGroup.add(btnUndo);
		
		btnRedo = new JButton("REDO");
		pnlNorth.add(btnRedo);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		btnGroup.add(btnRedo);
		
		btnToFront = new JButton("TO FRONT");
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.moveShape("toFront"); 
			}
		});
		pnlNorth.add(btnToFront);
		
		btnToBack = new JButton("TO BACK");
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.moveShape("toBack"); 
			}
		});
		pnlNorth.add(btnToBack);
		
		btnBringToFront = new JButton("BRING TO FRONT");
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.moveShape("bringToFront"); 
			}
		});
		pnlNorth.add(btnBringToFront);
		
		btnBringToBack = new JButton("BRING TO BACK");
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.moveShape("bringToBack"); 
			}
		});
		pnlNorth.add(btnBringToBack);
		
		view.setBackground(Color.white);
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		getContentPane().add(view, BorderLayout.CENTER);
		
		
		//DUGMAD ZA BOJE
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setBackground(new Color(204, 204, 204));
		
		btnInnerColor = new JButton("INNER COLOR");
		btnInnerColor.setBackground(innerColor);
		panel_1.add(btnInnerColor);
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				innerColor = JColorChooser.showDialog(null, "Choose inner color", btnInnerColor.getBackground());
				
				if (innerColor != null)
					btnInnerColor.setBackground(innerColor);
			}
		});
		btnGroup.add(btnInnerColor);
		
		btnOutlineColor = new JButton("OUTLINE COLOR");
		btnOutlineColor.setBackground(outlineColor);
		btnOutlineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outlineColor = JColorChooser.showDialog(null, "Choose outline color", btnOutlineColor.getBackground());
				
				if(outlineColor != null)
					btnOutlineColor.setBackground(outlineColor);
			}
		});
		panel_1.add(btnOutlineColor);
		btnGroup.add(btnOutlineColor);
		
		//SAVE
		btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser file = new JFileChooser();
				if (file.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
					controller.saveData(file.getSelectedFile().getPath());
				}
			}
		});
		panel_1.add(btnSave);
		
		//LOAD
		btnLoad = new JButton("LOAD");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser file = new JFileChooser();
				if (file.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
					controller.loadData(file.getSelectedFile().getPath());
				}
			}
		});
		panel_1.add(btnLoad);
		
		//READ LOG
		btnReadLog = new JButton("READ LOG");
		btnReadLog.setVisible(false);
		btnReadLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.readLog();
			}
		});
		panel_1.add(btnReadLog);
		
		panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		scrollPane = new JScrollPane();
		panel_3.add(scrollPane);
		
		list = new JList();
		list.setModel(defaultListModel);
		scrollPane.setViewportView(list);
	}
	
		
	
	public void setTglbtnSelect (JToggleButton tglbtnSelect) {
		this.tglbtnSelect = tglbtnSelect;
	}
	
	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}
	
	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}
	
	public void setTglbtnPoint (JToggleButton tglbtnPoint) {
		this.tglbtnPoint = tglbtnPoint;
	}
	
	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}
	
	public void setTglbtnLine (JToggleButton tglbtnLine) {
		this.tglbtnLine = tglbtnLine;
	}
	
	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}
	
	public void setTglbtnCircle (JToggleButton tglbtnCircle) {
		this.tglbtnCircle = tglbtnCircle;
	}
	
	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}
	
	public void setTglbtnRectangle (JToggleButton tglbtnRectangle) {
		this.tglbtnRectangle = tglbtnRectangle;
	}
	
	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}
	
	public void setTglbtnDonut (JToggleButton tglbtnDonut) {
		this.tglbtnDonut = tglbtnDonut;
	}

	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public void setTglbtnHexagon(JToggleButton tglbtnHexagon) {
		this.tglbtnHexagon = tglbtnHexagon;
	}

	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
	}

	public JButton getBtnOutlineColor() {
		return btnOutlineColor;
	}

	public void setBtnOutlineColor(JButton btnOutlineColor) {
		this.btnOutlineColor = btnOutlineColor;
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		getBtnInnerColor().setBackground(innerColor);
		this.innerColor = innerColor;
	}

	public Color getOutlineColor() {
		return outlineColor;
	}

	public void setOutlineColor(Color outlineColor) {
		getBtnOutlineColor().setBackground(outlineColor);
		this.outlineColor = outlineColor;
	}

	public JButton getBtnReadLog() {
		return btnReadLog;
	}

	public void setBtnReadLog(JButton btnReadLog) {
		this.btnReadLog = btnReadLog;
	}

	public DefaultListModel<String> getDefaultListModel() {
		return defaultListModel;
	}

	public void setDefaultListModel(DefaultListModel<String> defaultListModel) {
		this.defaultListModel = defaultListModel;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		DrawingController controller = (DrawingController)arg0;
		ArrayList<Shape> selectedShapes = controller.getSelectedShapes();
		
		if (selectedShapes.size() == 1) {
			tglbtnModify.setEnabled(true);
			tglbtnDelete.setEnabled(true);
		} else if (selectedShapes.size() > 1) {
			tglbtnModify.setEnabled(false);
			tglbtnDelete.setEnabled(true);
		} else {
			tglbtnModify.setEnabled(false);
			tglbtnDelete.setEnabled(false);
		}
	}
	
	
}
