package drawing;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JToggleButton;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;


public class DlgPoint extends JDialog {
	private JTextField txtX;
	private JTextField txtY;
   //private Color c;
	private Point p;
	private boolean confirm;
	private JButton btnColor;
	private Color color;
	private Color outlineColor; 
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgPoint dialog = new DlgPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgPoint() {
		setTitle("Point");
		setModal(true);
		setBounds(100, 100, 412, 273);
		
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel pnlSouth = new JPanel();
			getContentPane().add(pnlSouth, BorderLayout.SOUTH);
			{
				JToggleButton tglbtnOk = new JToggleButton("OK");
				tglbtnOk.setFont(new Font("Tahoma", Font.BOLD, 12));
				tglbtnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if (txtX.getText().trim().isEmpty() || txtY.getText().trim().isEmpty()) {
								JOptionPane.showMessageDialog(null, "All fields are required!", "ERROR", JOptionPane.ERROR_MESSAGE);
							}else {
								p = new Point (Integer.parseInt(txtX.getText().toString()), Integer.parseInt(txtY.getText().toString()), false, color);
								setConfirm(true);
								setVisible(false);
							}
							
					}catch (Exception e5){
						JOptionPane.showMessageDialog(null, "Enter numbers only!", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					}});
				pnlSouth.add(tglbtnOk);
			}
			{
				JToggleButton tglbtnCancel = new JToggleButton("Cancel");
				tglbtnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
				tglbtnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				pnlSouth.add(tglbtnCancel);
			}
		}
		{
			JPanel pnlCenter = new JPanel();
			pnlCenter.setBackground(new Color(255, 255, 103));
			getContentPane().add(pnlCenter, BorderLayout.CENTER);
			GridBagLayout gbl_pnlCenter = new GridBagLayout();
			gbl_pnlCenter.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
			gbl_pnlCenter.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_pnlCenter.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_pnlCenter.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			pnlCenter.setLayout(gbl_pnlCenter);
			{
				JLabel lblNewLabel_1 = new JLabel("");
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 4;
				gbc_lblNewLabel_1.gridy = 0;
				pnlCenter.add(lblNewLabel_1, gbc_lblNewLabel_1);
			}
			{
				JPanel pnlWest = new JPanel();
				pnlWest.setBackground(new Color(255, 255, 103));
				GridBagConstraints gbc_pnlWest = new GridBagConstraints();
				gbc_pnlWest.insets = new Insets(0, 0, 5, 5);
				gbc_pnlWest.gridx = 2;
				gbc_pnlWest.gridy = 4;
				pnlCenter.add(pnlWest, gbc_pnlWest);
				{
					GridBagLayout gbl_pnlWest = new GridBagLayout();
					gbl_pnlWest.columnWidths = new int[]{75, 0};
					gbl_pnlWest.rowHeights = new int[]{21, 0, 0, 0};
					gbl_pnlWest.columnWeights = new double[]{1.0, Double.MIN_VALUE};
					gbl_pnlWest.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
					pnlWest.setLayout(gbl_pnlWest);
				}
				{
					JButton btnColor = new JButton("Select color");
					btnColor.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							outlineColor = JColorChooser.showDialog(null, "Choose outline color ", btnColor.getBackground());
						    if (outlineColor != null) {
						    	btnColor.setBackground(outlineColor);
						    }
							
						}
					});
					GridBagConstraints gbc_btnColor = new GridBagConstraints();
					gbc_btnColor.insets = new Insets(0, 0, 5, 0);
					gbc_btnColor.gridx = 0;
					gbc_btnColor.gridy = 0;
					pnlWest.add(btnColor, gbc_btnColor);
				}
			}
			{
				Component verticalStrut = Box.createVerticalStrut(20);
				GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
				gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
				gbc_verticalStrut.gridx = 0;
				gbc_verticalStrut.gridy = 5;
				pnlCenter.add(verticalStrut, gbc_verticalStrut);
			}
			{
				JLabel lblX = new JLabel("Coordinate X:");
				GridBagConstraints gbc_lblX = new GridBagConstraints();
				gbc_lblX.anchor = GridBagConstraints.EAST;
				gbc_lblX.insets = new Insets(0, 0, 5, 5);
				gbc_lblX.gridx = 4;
				gbc_lblX.gridy = 5;
				pnlCenter.add(lblX, gbc_lblX);
			}
			{
				txtX = new JTextField();
				GridBagConstraints gbc_txtX = new GridBagConstraints();
				gbc_txtX.insets = new Insets(0, 0, 5, 0);
				gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtX.gridx = 5;
				gbc_txtX.gridy = 5;
				pnlCenter.add(txtX, gbc_txtX);
				txtX.setColumns(10);
			}
			{
				JLabel lblY = new JLabel("Coordinate Y:");
				GridBagConstraints gbc_lblY = new GridBagConstraints();
				gbc_lblY.anchor = GridBagConstraints.EAST;
				gbc_lblY.insets = new Insets(0, 0, 5, 5);
				gbc_lblY.gridx = 4;
				gbc_lblY.gridy = 6;
				pnlCenter.add(lblY, gbc_lblY);
			}
			{
				txtY = new JTextField();
				GridBagConstraints gbc_txtY = new GridBagConstraints();
				gbc_txtY.insets = new Insets(0, 0, 5, 0);
				gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtY.gridx = 5;
				gbc_txtY.gridy = 6;
				pnlCenter.add(txtY, gbc_txtY);
				txtY.setColumns(10);
			}
		}
	}
	
	public JTextField getTxtX() {
		return txtX;
	}
	
	public void setTxtX (JTextField txtX) {
		this.txtX = txtX;
	}
	
	public JTextField getTxtY() {
		return txtY;
	}
	
	public void setTxtY (JTextField txtY) {
		this.txtY = txtY;
	}
	
	public boolean isConfirm() {
		return confirm;
	}
	
	public void setConfirm (boolean confirm) {
		this.confirm = confirm;
	}
	
	public JButton getBtnColor() {
		return btnColor;
	}
	
	public void setBtnColor (JButton btnColor) {
		this.btnColor = btnColor;
	}
	
	public Point getP() {
		return p;
	}
	
	public void setP (Point p) {
		this.p = p;
	}
	
	public Color getOutlineColor() {
		return outlineColor;
	}
	
	public void setOutlineColor(Color outlineColor) {
		this.outlineColor = outlineColor;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}
