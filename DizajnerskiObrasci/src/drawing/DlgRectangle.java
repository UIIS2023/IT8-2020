package drawing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Rectangle;
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

public class DlgRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUpperY;
	private JTextField txtWidth;
	private JTextField txtHeight;
	private JTextField txtUpperX;
	public boolean okRect;
	public Rectangle rect;
	private JButton btnInner;
	private JButton btnOutlineColor;
	private Color innerColor;
	private Color outlineColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectangle() {
		setTitle("Rectangle");
		setBounds(100, 100, 455, 300);
		setModal(true);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 103));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JButton btnInner = new JButton("INNER COLOR");
			btnInner.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
                innerColor = JColorChooser.showDialog(null, "Choose inner color", btnInner.getBackground());
					
					if (innerColor != null)
						btnInner.setBackground(innerColor);
				}
			});
			btnInner.setFont(new Font("Tahoma", Font.BOLD, 12));
			GridBagConstraints gbc_btnInner = new GridBagConstraints();
			gbc_btnInner.insets = new Insets(0, 0, 5, 5);
			gbc_btnInner.gridx = 0;
			gbc_btnInner.gridy = 1;
			contentPanel.add(btnInner, gbc_btnInner);
		}
		{
			Component verticalStrut = Box.createVerticalStrut(20);
			GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
			gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
			gbc_verticalStrut.gridx = 1;
			gbc_verticalStrut.gridy = 1;
			contentPanel.add(verticalStrut, gbc_verticalStrut);
		}
		{
			JLabel lblUpperLeftPoint = new JLabel("UPPER LEFT POINT");
			GridBagConstraints gbc_lblUpperLeftPoint = new GridBagConstraints();
			gbc_lblUpperLeftPoint.insets = new Insets(0, 0, 5, 5);
			gbc_lblUpperLeftPoint.gridx = 9;
			gbc_lblUpperLeftPoint.gridy = 1;
			contentPanel.add(lblUpperLeftPoint, gbc_lblUpperLeftPoint);
		}
		{
			JButton btnOutlineColor = new JButton("OUTLINE COLOR");
			btnOutlineColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					outlineColor = JColorChooser.showDialog(null, "Choose outline color", btnOutlineColor.getBackground());
					
					if (outlineColor != null)
						btnOutlineColor.setBackground(outlineColor);
				}
			});
			btnOutlineColor.setFont(new Font("Tahoma", Font.BOLD, 12));
			GridBagConstraints gbc_btnOutlineColor = new GridBagConstraints();
			gbc_btnOutlineColor.insets = new Insets(0, 0, 5, 5);
			gbc_btnOutlineColor.gridx = 0;
			gbc_btnOutlineColor.gridy = 2;
			contentPanel.add(btnOutlineColor, gbc_btnOutlineColor);
		}
		{
			JLabel lblUpperLeftPointX = new JLabel("Coordinate X:");
			GridBagConstraints gbc_lblUpperLeftPointX = new GridBagConstraints();
			gbc_lblUpperLeftPointX.insets = new Insets(0, 0, 5, 5);
			gbc_lblUpperLeftPointX.anchor = GridBagConstraints.EAST;
			gbc_lblUpperLeftPointX.gridx = 9;
			gbc_lblUpperLeftPointX.gridy = 2;
			contentPanel.add(lblUpperLeftPointX, gbc_lblUpperLeftPointX);
		}
		{
			txtUpperX = new JTextField();
			GridBagConstraints gbc_txtUpperX = new GridBagConstraints();
			gbc_txtUpperX.anchor = GridBagConstraints.WEST;
			gbc_txtUpperX.insets = new Insets(0, 0, 5, 0);
			gbc_txtUpperX.gridx = 10;
			gbc_txtUpperX.gridy = 2;
			contentPanel.add(txtUpperX, gbc_txtUpperX);
			txtUpperX.setColumns(10);
		}
		{
			JLabel lblUpperLeftPointY = new JLabel("Coordinate Y:");
			GridBagConstraints gbc_lblUpperLeftPointY = new GridBagConstraints();
			gbc_lblUpperLeftPointY.anchor = GridBagConstraints.EAST;
			gbc_lblUpperLeftPointY.insets = new Insets(0, 0, 5, 5);
			gbc_lblUpperLeftPointY.gridx = 9;
			gbc_lblUpperLeftPointY.gridy = 3;
			contentPanel.add(lblUpperLeftPointY, gbc_lblUpperLeftPointY);
		}
		{
			txtUpperY = new JTextField();
			GridBagConstraints gbc_txtUpperY = new GridBagConstraints();
			gbc_txtUpperY.anchor = GridBagConstraints.WEST;
			gbc_txtUpperY.insets = new Insets(0, 0, 5, 0);
			gbc_txtUpperY.gridx = 10;
			gbc_txtUpperY.gridy = 3;
			contentPanel.add(txtUpperY, gbc_txtUpperY);
			txtUpperY.setColumns(10);
		}
		{
			JLabel lblWidth = new JLabel("WIDTH");
			GridBagConstraints gbc_lblWidth = new GridBagConstraints();
			gbc_lblWidth.anchor = GridBagConstraints.EAST;
			gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
			gbc_lblWidth.gridx = 9;
			gbc_lblWidth.gridy = 4;
			contentPanel.add(lblWidth, gbc_lblWidth);
		}
		{
			txtWidth = new JTextField();
			GridBagConstraints gbc_txtWidth = new GridBagConstraints();
			gbc_txtWidth.anchor = GridBagConstraints.WEST;
			gbc_txtWidth.insets = new Insets(0, 0, 5, 0);
			gbc_txtWidth.gridx = 10;
			gbc_txtWidth.gridy = 4;
			contentPanel.add(txtWidth, gbc_txtWidth);
			txtWidth.setColumns(10);
		}
		{
			JLabel lblHeight = new JLabel("HEIGHT");
			GridBagConstraints gbc_lblHeight = new GridBagConstraints();
			gbc_lblHeight.anchor = GridBagConstraints.EAST;
			gbc_lblHeight.insets = new Insets(0, 0, 0, 5);
			gbc_lblHeight.gridx = 9;
			gbc_lblHeight.gridy = 5;
			contentPanel.add(lblHeight, gbc_lblHeight);
		}
		{
			txtHeight = new JTextField();
			GridBagConstraints gbc_txtHeight = new GridBagConstraints();
			gbc_txtHeight.anchor = GridBagConstraints.WEST;
			gbc_txtHeight.gridx = 10;
			gbc_txtHeight.gridy = 5;
			contentPanel.add(txtHeight, gbc_txtHeight);
			txtHeight.setColumns(10);
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
							if (txtUpperX.getText().trim().isEmpty() || txtUpperY.getText().trim().isEmpty() ||
									txtWidth.getText().trim().isEmpty() || txtHeight.getText().trim().isEmpty()) 
								JOptionPane.showMessageDialog(null, "All values are required!", "ERROR", JOptionPane.ERROR_MESSAGE);
							
							else if((Integer.parseInt(txtWidth.getText().toString()) <= 0 || Integer.parseInt(txtHeight.getText().toString())<= 0)) {
									JOptionPane.showMessageDialog(null, "Insert values greater than o!", "ERROR", JOptionPane.ERROR_MESSAGE);
								}
								else {
									rect = new Rectangle (new Point (Integer.parseInt(getTxtUpperX().getText().toString()), Integer.parseInt(getTxtUpperY().getText().toString())), 
											Integer.parseInt(getTxtWidth().getText().toString()), Integer.parseInt(getTxtHeight().getText().toString()), false, outlineColor, innerColor);
									setOkRect(true);
									setVisible(false);
								}
						} catch (NumberFormatException e1) {
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
	
	public JTextField getTxtUpperX() {
		return txtUpperX;
	}
	
	public void setTxtUpperX (JTextField txtUpperX) {
		this.txtUpperX = txtUpperX;
	}
	
	public JTextField getTxtUpperY() {
		return txtUpperX;
	}
	
	public void setTxtUpperY (JTextField txtUpperY) {
		this.txtUpperY = txtUpperY;
	}
	
	public JTextField getTxtWidth() {
		return txtWidth;
	}
	
	public void setTxtWidth (JTextField txtWidth) {
		this.txtWidth = txtWidth;
	}
	
	public JTextField getTxtHeight() {
		return txtHeight;
	}
	
	public void setTxtHeight (JTextField txtHeight) {
		this.txtHeight = txtHeight;
	}

	public boolean isOkRect() {
		return okRect;
	}
	
	public void setOkRect (boolean okRect) {
		this.okRect = okRect;
	}
	
	public Rectangle getRect() {
		return rect;
	}
	
	public void setRect (Rectangle rect) {
		this.rect = rect;
	}
	
	public JButton getBtnInner() {
		return btnInner;
	}

	public void setBtnInner(JButton btnInner) {
		this.btnInner = btnInner;
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
