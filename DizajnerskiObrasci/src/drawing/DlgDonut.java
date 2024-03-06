package drawing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Donut;
import geometry.Point;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Color;

public class DlgDonut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;
	private JTextField txtInnerRadius;
	public boolean okDonut;
	public Donut donut;
	public JButton btnInnerColor;
	public JButton btnOutlineColor;
	private Color outlineColor;
	private Color innerColor;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDonut dialog = new DlgDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgDonut() {
		setTitle("Donut");
		setBounds(100, 100, 450, 300);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 103));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
			JLabel lblCenterOfDonut = new JLabel("CENTER OF DONUT");
			GridBagConstraints gbc_lblCenterOfDonut = new GridBagConstraints();
			gbc_lblCenterOfDonut.insets = new Insets(0, 0, 5, 5);
			gbc_lblCenterOfDonut.gridx = 9;
			gbc_lblCenterOfDonut.gridy = 1;
			contentPanel.add(lblCenterOfDonut, gbc_lblCenterOfDonut);
		}
		{
			Component verticalStrut = Box.createVerticalStrut(20);
			GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
			gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
			gbc_verticalStrut.gridx = 3;
			gbc_verticalStrut.gridy = 2;
			contentPanel.add(verticalStrut, gbc_verticalStrut);
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
			gbc_btnOutlineColor.gridy = 3;
			contentPanel.add(btnOutlineColor, gbc_btnOutlineColor);
		}
		{
			JLabel lblCoordinateXCenter = new JLabel("Coordinate X:");
			GridBagConstraints gbc_lblCoordinateXCenter = new GridBagConstraints();
			gbc_lblCoordinateXCenter.anchor = GridBagConstraints.EAST;
			gbc_lblCoordinateXCenter.insets = new Insets(0, 0, 5, 5);
			gbc_lblCoordinateXCenter.gridx = 9;
			gbc_lblCoordinateXCenter.gridy = 3;
			contentPanel.add(lblCoordinateXCenter, gbc_lblCoordinateXCenter);
		}
		{
			txtX = new JTextField();
			txtX.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_txtX = new GridBagConstraints();
			gbc_txtX.anchor = GridBagConstraints.EAST;
			gbc_txtX.insets = new Insets(0, 0, 5, 0);
			gbc_txtX.gridx = 10;
			gbc_txtX.gridy = 3;
			contentPanel.add(txtX, gbc_txtX);
			txtX.setColumns(10);
		}
		{
			JLabel lblCoordinateYCenter = new JLabel("Coordinate Y:");
			GridBagConstraints gbc_lblCoordinateYCenter = new GridBagConstraints();
			gbc_lblCoordinateYCenter.anchor = GridBagConstraints.EAST;
			gbc_lblCoordinateYCenter.insets = new Insets(0, 0, 5, 5);
			gbc_lblCoordinateYCenter.gridx = 9;
			gbc_lblCoordinateYCenter.gridy = 4;
			contentPanel.add(lblCoordinateYCenter, gbc_lblCoordinateYCenter);
		}
		{
			txtY = new JTextField();
			txtY.setText("");
			GridBagConstraints gbc_txtY = new GridBagConstraints();
			gbc_txtY.insets = new Insets(0, 0, 5, 0);
			gbc_txtY.anchor = GridBagConstraints.WEST;
			gbc_txtY.gridx = 10;
			gbc_txtY.gridy = 4;
			contentPanel.add(txtY, gbc_txtY);
			txtY.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("RADIUS");
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.anchor = GridBagConstraints.EAST;
			gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblRadius.gridx = 9;
			gbc_lblRadius.gridy = 5;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}
		{
			txtRadius = new JTextField();
			GridBagConstraints gbc_txtRadius = new GridBagConstraints();
			gbc_txtRadius.anchor = GridBagConstraints.WEST;
			gbc_txtRadius.insets = new Insets(0, 0, 5, 0);
			gbc_txtRadius.gridx = 10;
			gbc_txtRadius.gridy = 5;
			contentPanel.add(txtRadius, gbc_txtRadius);
			txtRadius.setColumns(10);
		}
		{
			JLabel lblInnerRadius = new JLabel("INNER RADIUS");
			GridBagConstraints gbc_lblInnerRadius = new GridBagConstraints();
			gbc_lblInnerRadius.anchor = GridBagConstraints.BASELINE;
			gbc_lblInnerRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblInnerRadius.gridx = 9;
			gbc_lblInnerRadius.gridy = 6;
			contentPanel.add(lblInnerRadius, gbc_lblInnerRadius);
		}
		{
			txtInnerRadius = new JTextField();
			txtInnerRadius.setText("");
			GridBagConstraints gbc_txtInnerRadius = new GridBagConstraints();
			gbc_txtInnerRadius.anchor = GridBagConstraints.WEST;
			gbc_txtInnerRadius.insets = new Insets(0, 0, 5, 0);
			gbc_txtInnerRadius.gridx = 10;
			gbc_txtInnerRadius.gridy = 6;
			contentPanel.add(txtInnerRadius, gbc_txtInnerRadius);
			txtInnerRadius.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (txtX.getText().trim().isEmpty() || txtY.getText().trim().isEmpty()
								|| txtRadius.getText().trim().isEmpty() || txtInnerRadius.getText().trim().isEmpty()) {
						
							JOptionPane.showMessageDialog(null, "All values are required!", "Error", JOptionPane.ERROR_MESSAGE);

						} else {
							try {
								if (Integer.parseInt(txtInnerRadius.getText().toString()) <= 0
										|| Integer.parseInt(txtRadius.getText().toString()) <= 0
										|| Integer.parseInt(txtX.getText().toString()) < 0
										|| Integer.parseInt(txtY.getText().toString()) < 0)
									JOptionPane.showMessageDialog(null, "Insert values greater then 0!", "Error", JOptionPane.ERROR_MESSAGE);
								else {
									if (Integer.parseInt(txtInnerRadius.getText().toString()) < Integer.parseInt(txtRadius.getText().toString())) {
										donut = new Donut(new Point(Integer.parseInt(txtX.getText().toString()), Integer.parseInt(txtY.getText().toString())),
												Integer.parseInt(txtInnerRadius.getText().toString()),
												Integer.parseInt(txtInnerRadius.getText().toString()), false,
												outlineColor, innerColor);

										setOkDonut(true);
										setVisible(false);
									} else {
										JOptionPane.showMessageDialog(null,"Please insert inner radius less than outher radius!", "Error",JOptionPane.ERROR_MESSAGE);
									}

								}
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null, "Enter numbers only!", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
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
	
	public JTextField getTxtRadius() {
		return txtRadius;
	}
	
	public void setTxtRadius (JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}
	
	public JTextField getTxtInnerRadius() {
		return txtInnerRadius;
	}
	
	public void setTxtInnerRadius (JTextField txtInnerRadius) {
		this.txtInnerRadius = txtInnerRadius;
	}
	
	public boolean isOkDonut() {
		return okDonut;
	}
	
	public void setOkDonut(boolean okDonut) {
		this.okDonut = okDonut;
	}

	public Donut getDonut() {
		return donut;
	}
	
	public void setDonut (Donut donut) {
		this.donut = donut;
	}
	
	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}
	
	public void setBtnInnerColor (JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
	}

	public JButton getBtnOutlineColor() {
		return btnOutlineColor;
	}
	
	public void setBtnOutlineColor(JButton btnOutlineColor) {
		
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
