package drawing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Line;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtStartX;
	private JTextField txtStartY;
	private JTextField txtEndX;
	private JTextField txtEndY;
	private boolean okLine;
	public Line line;
	private Color cLine;
	private JButton btnColor;
	private Color outlineColor1; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
		setBounds(100, 100, 297, 300);
		setTitle("Line");
		setModal(true);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 103));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel pnl1 = new JPanel();
			pnl1.setBackground(new Color(255, 255, 103));
			GridBagConstraints gbc_pnl1 = new GridBagConstraints();
			gbc_pnl1.insets = new Insets(0, 0, 5, 0);
			gbc_pnl1.fill = GridBagConstraints.BOTH;
			gbc_pnl1.gridx = 0;
			gbc_pnl1.gridy = 1;
			contentPanel.add(pnl1, gbc_pnl1);
			{
				JButton btnColor = new JButton("Select color");
				btnColor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 outlineColor1 = JColorChooser.showDialog(null, "Choose outline color ", btnColor.getBackground());
					    if (outlineColor1 != null) {
					    	btnColor.setBackground(outlineColor1);
					    }
					}
				});
				pnl1.add(btnColor);
			}
		}
		{
			JPanel pnl2 = new JPanel();
			pnl2.setBackground(new Color(255, 255, 103));
			GridBagConstraints gbc_pnl2 = new GridBagConstraints();
			gbc_pnl2.fill = GridBagConstraints.BOTH;
			gbc_pnl2.gridx = 0;
			gbc_pnl2.gridy = 2;
			contentPanel.add(pnl2, gbc_pnl2);
			GridBagLayout gbl_pnl2 = new GridBagLayout();
			gbl_pnl2.columnWidths = new int[]{0, 0, 0, 0};
			gbl_pnl2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
			gbl_pnl2.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_pnl2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			pnl2.setLayout(gbl_pnl2);
			{
				JLabel lblStartPoint = new JLabel("START POINT");
				GridBagConstraints gbc_lblStartPoint = new GridBagConstraints();
				gbc_lblStartPoint.insets = new Insets(0, 0, 5, 5);
				gbc_lblStartPoint.gridx = 1;
				gbc_lblStartPoint.gridy = 0;
				pnl2.add(lblStartPoint, gbc_lblStartPoint);
			}
			{
				JLabel lblStartX = new JLabel("Coordinate X:");
				GridBagConstraints gbc_lblStartX = new GridBagConstraints();
				gbc_lblStartX.insets = new Insets(0, 0, 5, 5);
				gbc_lblStartX.anchor = GridBagConstraints.EAST;
				gbc_lblStartX.gridx = 1;
				gbc_lblStartX.gridy = 1;
				pnl2.add(lblStartX, gbc_lblStartX);
			}
			{
				txtStartX = new JTextField();
				GridBagConstraints gbc_txtStartX = new GridBagConstraints();
				gbc_txtStartX.insets = new Insets(0, 0, 5, 0);
				gbc_txtStartX.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtStartX.gridx = 2;
				gbc_txtStartX.gridy = 1;
				pnl2.add(txtStartX, gbc_txtStartX);
				txtStartX.setColumns(10);
			}
			{
				JLabel lblStartY = new JLabel("Coordinate Y:");
				GridBagConstraints gbc_lblStartY = new GridBagConstraints();
				gbc_lblStartY.anchor = GridBagConstraints.EAST;
				gbc_lblStartY.insets = new Insets(0, 0, 5, 5);
				gbc_lblStartY.gridx = 1;
				gbc_lblStartY.gridy = 2;
				pnl2.add(lblStartY, gbc_lblStartY);
			}
			{
				txtStartY = new JTextField();
				GridBagConstraints gbc_txtStartY = new GridBagConstraints();
				gbc_txtStartY.insets = new Insets(0, 0, 5, 0);
				gbc_txtStartY.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtStartY.gridx = 2;
				gbc_txtStartY.gridy = 2;
				pnl2.add(txtStartY, gbc_txtStartY);
				txtStartY.setColumns(10);
			}
			{
				JLabel lblEndPoint = new JLabel("END POINT");
				GridBagConstraints gbc_lblEndPoint = new GridBagConstraints();
				gbc_lblEndPoint.insets = new Insets(0, 0, 5, 5);
				gbc_lblEndPoint.gridx = 1;
				gbc_lblEndPoint.gridy = 3;
				pnl2.add(lblEndPoint, gbc_lblEndPoint);
			}
			{
				JLabel lblEndX = new JLabel("Coordinate X:");
				GridBagConstraints gbc_lblEndX = new GridBagConstraints();
				gbc_lblEndX.anchor = GridBagConstraints.EAST;
				gbc_lblEndX.insets = new Insets(0, 0, 5, 5);
				gbc_lblEndX.gridx = 1;
				gbc_lblEndX.gridy = 4;
				pnl2.add(lblEndX, gbc_lblEndX);
			}
			{
				txtEndX = new JTextField();
				GridBagConstraints gbc_txtEndX = new GridBagConstraints();
				gbc_txtEndX.insets = new Insets(0, 0, 5, 0);
				gbc_txtEndX.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtEndX.gridx = 2;
				gbc_txtEndX.gridy = 4;
				pnl2.add(txtEndX, gbc_txtEndX);
				txtEndX.setColumns(10);
			}
			{
				JLabel lblEndY = new JLabel("Coordinate Y:");
				GridBagConstraints gbc_lblEndY = new GridBagConstraints();
				gbc_lblEndY.anchor = GridBagConstraints.EAST;
				gbc_lblEndY.insets = new Insets(0, 0, 0, 5);
				gbc_lblEndY.gridx = 1;
				gbc_lblEndY.gridy = 5;
				pnl2.add(lblEndY, gbc_lblEndY);
			}
			{
				txtEndY = new JTextField();
				txtEndY.setText("");
				GridBagConstraints gbc_txtEndY = new GridBagConstraints();
				gbc_txtEndY.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtEndY.gridx = 2;
				gbc_txtEndY.gridy = 5;
				pnl2.add(txtEndY, gbc_txtEndY);
				txtEndY.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if (txtStartX.getText().trim().isEmpty() || txtStartY.getText().trim().isEmpty() || txtEndX.getText().trim().isEmpty() || txtEndY.getText().trim().isEmpty()) {
							JOptionPane.showMessageDialog(null, "All fields are required!", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
						else {
							
								if (Integer.parseInt(txtStartX.getText().toString())<0 || Integer.parseInt(txtStartY.getText().toString())<0
										|| Integer.parseInt(txtEndX.getText().toString())<0 || Integer.parseInt(txtEndY.getText().toString())<0 ) {
									JOptionPane.showMessageDialog(null, "Insert values greater than 0!", "ERROR", JOptionPane.ERROR_MESSAGE);
								}
								else {
								   line = new Line ( new Point (Integer.parseInt(txtStartX.getText().toString()), Integer.parseInt(txtStartY.getText().toString())),
								    		          new Point (Integer.parseInt(txtEndX.getText().toString()), Integer.parseInt(txtEndY.getText().toString())), false, cLine);
								   setOkLine(true);
								    setVisible(false);
								}
						}}catch (Exception e2) {
								JOptionPane.showMessageDialog(null, "Enter numbers only!", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
						
					}});
				okButton.setFont(new Font("Tahoma", Font.BOLD, 12));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.BOLD, 12));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public boolean isOkLine() {
		return okLine;
	}
	
	public void setOkLine (boolean okLine) {
		this.okLine = okLine;
	}
	
	public JTextField getTxtStartX() {
		return txtStartX;
	}
	
	public void setTxtStartX (JTextField txtStartX) {
		this.txtStartX = txtStartX;
	}
	
	public JTextField getTxtStartY() {
		return txtStartY;
	}
	
	public void setTxtStartY (JTextField txtStartY) {
		this.txtStartY = txtStartX;
	}
	
	public JTextField getTxtEndX() {
		return txtEndX;
	}
	
	public void setTxtEndX (JTextField txtEndX) {
		this.txtEndX = txtEndX;
	}
	
	public JTextField getTxtEndY() {
		return txtEndY;
	}
	
	public void setTxtEndY (JTextField txtEndY) {
		this.txtEndY = txtEndY;
	}
	
	public Color getCLine() {
		return cLine;
	}
	
	public void setCLine (Color cLine) {
		this.cLine = cLine;
	}

	public Line getLine() {
		return line;
	}
	
	public void setLine (Line line) {
		this.line = line;
	}
	
	public JButton getBtnColor() {
		return btnColor;
	}
	
	public void setBtnColor (JButton btnColor) {
		this.btnColor = btnColor;
	}
	
	public Color getOutlineColor1() {
		return outlineColor1;
	}
	
	public void setOutlineColor1(Color outlineColor1) {
		this.outlineColor1 = outlineColor1;
	}
}
