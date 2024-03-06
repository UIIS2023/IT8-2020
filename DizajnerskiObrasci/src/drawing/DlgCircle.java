package drawing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import geometry.Point;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Color;

public class DlgCircle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtXCenter;
	private JTextField txtYCenter;
	private JTextField txtRadius;
	private boolean okCircle;
	private JButton btnInnerColor;
	private JButton btnOutlineColor;
	private Circle circle;
	private Color outlineColor;
	private Color innerColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgCircle dialog = new DlgCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCircle() {
		setTitle("Circle");
		setBounds(100, 100, 431, 300);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 103));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JButton btnInnerColor = new JButton("INNER COLOR");
			btnInnerColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					innerColor = JColorChooser.showDialog(null, "Choose inner color", btnInnerColor.getBackground());
					if (innerColor != null)
						btnInnerColor.setBackground(innerColor);
				}
			});
			btnInnerColor.setFont(new Font("Tahoma", Font.BOLD, 12));
			GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
			gbc_btnInnerColor.insets = new Insets(0, 0, 5, 5);
			gbc_btnInnerColor.gridx = 1;
			gbc_btnInnerColor.gridy = 1;
			contentPanel.add(btnInnerColor, gbc_btnInnerColor);
		}
		{
			Component verticalStrut = Box.createVerticalStrut(20);
			GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
			gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
			gbc_verticalStrut.gridx = 2;
			gbc_verticalStrut.gridy = 1;
			contentPanel.add(verticalStrut, gbc_verticalStrut);
		}
		{
			JLabel lblCenterOfCircle = new JLabel("CENTER OF CIRCLE");
			GridBagConstraints gbc_lblCenterOfCircle = new GridBagConstraints();
			gbc_lblCenterOfCircle.insets = new Insets(0, 0, 5, 5);
			gbc_lblCenterOfCircle.gridx = 8;
			gbc_lblCenterOfCircle.gridy = 1;
			contentPanel.add(lblCenterOfCircle, gbc_lblCenterOfCircle);
		}
		{
			JButton btnOutlineColor = new JButton("OUTLINE COLOR");
			btnOutlineColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					outlineColor = JColorChooser.showDialog(null, "Choose outline color", btnOutlineColor.getBackground());
					
					if(outlineColor != null)
						btnOutlineColor.setBackground(outlineColor);
				}
			});
			btnOutlineColor.setFont(new Font("Tahoma", Font.BOLD, 12));
			GridBagConstraints gbc_btnOutlineColor = new GridBagConstraints();
			gbc_btnOutlineColor.insets = new Insets(0, 0, 5, 5);
			gbc_btnOutlineColor.gridx = 1;
			gbc_btnOutlineColor.gridy = 2;
			contentPanel.add(btnOutlineColor, gbc_btnOutlineColor);
		}
		{
			JLabel lblCoordinateXcenter = new JLabel("Coordinate X:");
			GridBagConstraints gbc_lblCoordinateXcenter = new GridBagConstraints();
			gbc_lblCoordinateXcenter.anchor = GridBagConstraints.EAST;
			gbc_lblCoordinateXcenter.insets = new Insets(0, 0, 5, 5);
			gbc_lblCoordinateXcenter.gridx = 8;
			gbc_lblCoordinateXcenter.gridy = 2;
			contentPanel.add(lblCoordinateXcenter, gbc_lblCoordinateXcenter);
		}
		{
			txtXCenter = new JTextField();
			GridBagConstraints gbc_txtXCenter = new GridBagConstraints();
			gbc_txtXCenter.anchor = GridBagConstraints.WEST;
			gbc_txtXCenter.insets = new Insets(0, 0, 5, 0);
			gbc_txtXCenter.gridx = 9;
			gbc_txtXCenter.gridy = 2;
			contentPanel.add(txtXCenter, gbc_txtXCenter);
			txtXCenter.setColumns(10);
		}
		{
			JLabel lblCoordinateYCenter = new JLabel("Coordinate Y:");
			GridBagConstraints gbc_lblCoordinateYCenter = new GridBagConstraints();
			gbc_lblCoordinateYCenter.anchor = GridBagConstraints.EAST;
			gbc_lblCoordinateYCenter.insets = new Insets(0, 0, 5, 5);
			gbc_lblCoordinateYCenter.gridx = 8;
			gbc_lblCoordinateYCenter.gridy = 3;
			contentPanel.add(lblCoordinateYCenter, gbc_lblCoordinateYCenter);
		}
		{
			txtYCenter = new JTextField();
			GridBagConstraints gbc_txtYCenter = new GridBagConstraints();
			gbc_txtYCenter.insets = new Insets(0, 0, 5, 0);
			gbc_txtYCenter.anchor = GridBagConstraints.WEST;
			gbc_txtYCenter.gridx = 9;
			gbc_txtYCenter.gridy = 3;
			contentPanel.add(txtYCenter, gbc_txtYCenter);
			txtYCenter.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("RADIUS");
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.anchor = GridBagConstraints.EAST;
			gbc_lblRadius.insets = new Insets(0, 0, 0, 5);
			gbc_lblRadius.gridx = 8;
			gbc_lblRadius.gridy = 4;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}
		{
			txtRadius = new JTextField();
			GridBagConstraints gbc_txtRadius = new GridBagConstraints();
			gbc_txtRadius.anchor = GridBagConstraints.WEST;
			gbc_txtRadius.gridx = 9;
			gbc_txtRadius.gridy = 4;
			contentPanel.add(txtRadius, gbc_txtRadius);
			txtRadius.setColumns(10);
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
							if (txtXCenter.getText().trim().isEmpty() || txtYCenter.getText().trim().isEmpty() || txtRadius.getText().trim().isEmpty()) {
							
							JOptionPane.showMessageDialog(null, "All field are required!", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
						else {
							
								if (Integer.parseInt(txtRadius.getText().toString()) <= 0 ||
										Integer.parseInt(txtXCenter.getText().toString())<0 || Integer.parseInt(txtYCenter.getText().toString())<0) {
									JOptionPane.showMessageDialog(null, "Insert values greather than 0!", "ERROR", JOptionPane.ERROR_MESSAGE);
								}
								else {
									circle = new Circle (new Point(Integer.parseInt(txtXCenter.getText().toString()), Integer.parseInt(txtYCenter.getText().toString())),
											Integer.parseInt(txtRadius.getText().toString()), false, outlineColor, innerColor);
									setOkCircle(true);
									setVisible(false);
								}
							}
						}catch (Exception e2) {
								JOptionPane.showMessageDialog(null, "Enter numbers only!", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
					}
				});
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

	public JTextField getTxtXCenter() {
		return txtXCenter;
	}

	public void setTxtX(JTextField txtXCenter) {
		this.txtXCenter = txtXCenter;
	}

	public JTextField getTxtYCenter() {
		return txtYCenter;
	}

	public void setTxtYCenter(JTextField txtYCenter) {
		this.txtYCenter = txtYCenter;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public boolean isOkCircle() {
		return okCircle;
	}

	public void setOkCircle(boolean okCircle) {
		this.okCircle = okCircle;
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
	
	public Color getOutlineColor() {
		return outlineColor;
	}
	
	public void setOutlineColor(Color outlineColor) {
		this.outlineColor = outlineColor;
	}
	
	public Color getInnerColor() {
		return innerColor;
	}
	
	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}
	

}
