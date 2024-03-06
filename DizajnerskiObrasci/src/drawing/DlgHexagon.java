package drawing;

import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import adapter.HexagonAdapter;
import geometry.Circle;
import geometry.Point;

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgHexagon extends JDialog{
	
	private HexagonAdapter hexagon;
	private JTextField txtX, txtY, txtRadius;
	private Color innerColor, outlineColor;
	public JButton btnInnerColorHex, btnOutlineColorHex;
	private boolean btnOk;
	
	public static void main(String[] args) {
		try {
			DlgHexagon dialog = new DlgHexagon();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DlgHexagon() {
		setTitle("Hexagon");
		setBounds(100, 100, 431, 300);
		setModal(true);
		getContentPane().setBackground(new Color(255, 255, 103));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 107, 65, 163, 47, 0};
		gridBagLayout.rowHeights = new int[]{30, 21, 21, 19, 19, 19, 95, 19, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JButton btnOutlineColorHex_1 = new JButton("OUTLINE COLOR");
		btnOutlineColorHex_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outlineColor = JColorChooser.showDialog(null, "Choose outline color", btnOutlineColorHex_1.getBackground());
				if (outlineColor != null)
					btnOutlineColorHex_1.setBackground(outlineColor);
			}
		});
		
		JButton btnInnerColorHex_1 = new JButton("INNER COLOR");
		btnInnerColorHex_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				innerColor = JColorChooser.showDialog(null, "Choose inner color", btnInnerColorHex_1.getBackground());
				if (innerColor != null)
					btnInnerColorHex_1.setBackground(innerColor);
			}
		});
		GridBagConstraints gbc_btnInnerColorHex_1 = new GridBagConstraints();
		gbc_btnInnerColorHex_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnInnerColorHex_1.gridx = 1;
		gbc_btnInnerColorHex_1.gridy = 1;
		getContentPane().add(btnInnerColorHex_1, gbc_btnInnerColorHex_1);
		
		JLabel lblCenter = new JLabel("CENTER OF HEXAGON");
		GridBagConstraints gbc_lblCenter = new GridBagConstraints();
		gbc_lblCenter.insets = new Insets(0, 0, 5, 5);
		gbc_lblCenter.gridx = 3;
		gbc_lblCenter.gridy = 1;
		getContentPane().add(lblCenter, gbc_lblCenter);
		GridBagConstraints gbc_btnOutlineColorHex_1 = new GridBagConstraints();
		gbc_btnOutlineColorHex_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnOutlineColorHex_1.gridx = 1;
		gbc_btnOutlineColorHex_1.gridy = 2;
		getContentPane().add(btnOutlineColorHex_1, gbc_btnOutlineColorHex_1);
		
		JLabel lblXCenter = new JLabel("Coordinate X:");
		GridBagConstraints gbc_lblXCenter = new GridBagConstraints();
		gbc_lblXCenter.anchor = GridBagConstraints.EAST;
		gbc_lblXCenter.insets = new Insets(0, 0, 5, 5);
		gbc_lblXCenter.gridx = 2;
		gbc_lblXCenter.gridy = 3;
		getContentPane().add(lblXCenter, gbc_lblXCenter);
		
		txtX = new JTextField();
		GridBagConstraints gbc_txtX = new GridBagConstraints();
		gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtX.insets = new Insets(0, 0, 5, 5);
		gbc_txtX.gridx = 3;
		gbc_txtX.gridy = 3;
		getContentPane().add(txtX, gbc_txtX);
		txtX.setColumns(10);
		
		JLabel lblYCenter = new JLabel("Coordinate Y:");
		GridBagConstraints gbc_lblYCenter = new GridBagConstraints();
		gbc_lblYCenter.anchor = GridBagConstraints.EAST;
		gbc_lblYCenter.insets = new Insets(0, 0, 5, 5);
		gbc_lblYCenter.gridx = 2;
		gbc_lblYCenter.gridy = 4;
		getContentPane().add(lblYCenter, gbc_lblYCenter);
		
		txtY = new JTextField();
		GridBagConstraints gbc_txtY = new GridBagConstraints();
		gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtY.insets = new Insets(0, 0, 5, 5);
		gbc_txtY.gridx = 3;
		gbc_txtY.gridy = 4;
		getContentPane().add(txtY, gbc_txtY);
		txtY.setColumns(10);
		
		JLabel lblRadius = new JLabel("Radius:");
		GridBagConstraints gbc_lblRadius = new GridBagConstraints();
		gbc_lblRadius.anchor = GridBagConstraints.EAST;
		gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
		gbc_lblRadius.gridx = 2;
		gbc_lblRadius.gridy = 5;
		getContentPane().add(lblRadius, gbc_lblRadius);
		
		txtRadius = new JTextField();
		GridBagConstraints gbc_txtRadius = new GridBagConstraints();
		gbc_txtRadius.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRadius.insets = new Insets(0, 0, 5, 5);
		gbc_txtRadius.gridx = 3;
		gbc_txtRadius.gridy = 5;
		getContentPane().add(txtRadius, gbc_txtRadius);
		txtRadius.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 103));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.SOUTH;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 6;
		getContentPane().add(panel, gbc_panel);
		
		JButton btnOK = new JButton("OK");
		panel.add(btnOK);
		
		JButton btnCancel = new JButton("Cancel");
		panel.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtX.getText().trim().isEmpty() || txtY.getText().trim().isEmpty() || txtRadius.getText().trim().isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "All field are required!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else {
					
						if (Integer.parseInt(txtRadius.getText().toString()) <= 0 ||
								Integer.parseInt(txtX.getText().toString())<0 || Integer.parseInt(txtY.getText().toString())<0) {
							JOptionPane.showMessageDialog(null, "Insert values greather than 0!", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
						else {
							hexagon = new HexagonAdapter(new Point(Integer.parseInt(txtX.getText().toString()),
									Integer.parseInt(txtY.getText().toString())),
									Integer.parseInt(txtRadius.getText().toString()));
							hexagon.setColor(btnOutlineColorHex_1.getBackground());
							hexagon.setInnerColor(btnInnerColorHex_1.getBackground());
							setBtnOk(true);
							setVisible(false);
						}
					}
				}catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Enter numbers only!", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
			}
		});
	}

	public HexagonAdapter getHexagon() {
		return hexagon;
	}

	public void setHexagon(HexagonAdapter hexagon) {
		this.hexagon = hexagon;
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

	public Color getOutlineColor() {
		return outlineColor;
	}

	public void setOutlineColor(Color outlineColor) {
		this.outlineColor = outlineColor;
	}

	public JButton getBtnInnerColorHex() {
		return btnInnerColorHex;
	}

	public void setBtnInnerColorHex(JButton btnInnerColorHex) {
		this.btnInnerColorHex = btnInnerColorHex;
	}

	public JButton getBtnOutlineColorHex() {
		return btnOutlineColorHex;
	}

	public void setBtnOutlineColorHex(JButton btnOutlineColorHex) {
		this.btnOutlineColorHex = btnOutlineColorHex;
	}

	public boolean isBtnOk() {
		return btnOk;
	}

	public void setBtnOk(boolean btnOk) {
		this.btnOk = btnOk;
	}


}
