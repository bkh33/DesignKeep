/*
     * Brandon Harrison
     * bkh33@drexel.edu
     * CS338:GUI Final Project
     * AddColor JDialog
     * Allows user to add a new color to the color list
*/

package dkeep;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileFilter;

import dkeep.DesignColor;
import dkeep.DesignImage;
import dkeep.Project;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.awt.Toolkit;

public class AddColor extends JDialog {

	private static final long serialVersionUID = -5277232258094072488L;
	private final JPanel contentPanel = new JPanel();
	private JTextField clrNameField;
	private JTextField rgb_RField;
	private JTextField rgb_GField;
	private JTextField rgb_BField;
	private JTextField hexField;
	protected DesignColor value;
	
		// Constructor / Init. JDialog
	public AddColor(final Project p) {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(AddColor.class.getResource("/resources/add_icon_32.png")));
		setTitle("Add New Color");
		setBackground(new Color(204, 153, 51));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 204, 102));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
			// namePanel JPanel holds color name field
		JPanel namePanel = new JPanel();
		namePanel.setBackground(new Color(255, 204, 102));
		contentPanel.add(namePanel);
		
			// Create name field label
		JLabel clrNameLbl = new JLabel("Color Name:  ");
		clrNameLbl.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		namePanel.add(clrNameLbl);
			// Create name field
		clrNameField = new JTextField();
		namePanel.add(clrNameField);
		clrNameField.setColumns(10);
		
			// rgbPanel JPanel holds RGB color fields and OK button
		JPanel rgbPanel = new JPanel();
		rgbPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		rgbPanel.setBackground(new Color(255, 204, 102));
		contentPanel.add(rgbPanel);
		
			// R label & field
		JLabel rgb_RLbl = new JLabel("R: ");
		rgb_RLbl.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		rgbPanel.add(rgb_RLbl);
		rgb_RField = new JTextField();
		rgbPanel.add(rgb_RField);
		rgb_RField.setColumns(3);
			// G label & field
		JLabel rgb_GLbl = new JLabel("   G: ");
		rgb_GLbl.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		rgbPanel.add(rgb_GLbl);
		rgb_GField = new JTextField();
		rgbPanel.add(rgb_GField);
		rgb_GField.setColumns(3);
			// B label & field
		JLabel rgb_BLbl = new JLabel("  B: ");
		rgb_BLbl.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		rgbPanel.add(rgb_BLbl);
		rgb_BField = new JTextField();
		rgbPanel.add(rgb_BField);
		rgb_BField.setColumns(3);
			// RGB field OK button
		JButton rgb_OkBtn = new JButton("OK");
			/*
			 * RGB OK button action listener adds a new DesignColor
			 * with the specified RGB color value if the RGB values 
			 * are valid and the color has a specified name. If the 
			 * RGB values are invalid, they are erased from the RGB 
			 * text fields.
			 */
		rgb_OkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!clrNameField.getText().equals("")) {
					DesignColor newClr = new DesignColor();
					int r, g, b;
					newClr.setCName(clrNameField.getText());
					if(rgbIsFilled()) {
						try {
							r = Integer.parseInt(rgb_RField.getText());
							g = Integer.parseInt(rgb_GField.getText());
							b = Integer.parseInt(rgb_BField.getText());
						} catch (NumberFormatException exception) {
							rgb_RField.setText("");
							rgb_GField.setText("");
							rgb_BField.setText("");
							return;
						}
						try {
							newClr.setColor(new Color(r, g, b));
						} catch (IllegalArgumentException exception) {
							rgb_RField.setText("");
							rgb_GField.setText("");
							rgb_BField.setText("");
							return;
						}
						setValue(newClr);
						setVisible(false);
					}
				}
			}
		});
		rgbPanel.add(rgb_OkBtn);
		
			// hexPanel JPanel holds hexadecimal input field, label, and OK button
		JPanel hexPanel = new JPanel();
		hexPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		hexPanel.setBackground(new Color(255, 204, 102));
		contentPanel.add(hexPanel);
			// Hex label
		JLabel hexLbl = new JLabel("Hex:  ");
		hexLbl.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		hexPanel.add(hexLbl);
			/*
			 * Hex Input field
			 * Always starts with '#'
			 */
		hexField = new JTextField();
		hexField.setText("#");
		hexPanel.add(hexField);
		hexField.setColumns(7);
			/*
			 * Hex OK button
			 * Action event creates a new DesignColor 
			 * from the specified hexadecimal color value, 
			 * if valid. If invalid, the values are erased 
			 * from the input field. If no name is specified, 
			 * color will not be added.
			 */
		JButton hex_OkBtn = new JButton("OK");
		hex_OkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!clrNameField.getText().equals("")) {
					DesignColor newClr = new DesignColor();
					newClr.setCName(clrNameField.getText());
					try {
						newClr.setColor(Color.decode(hexField.getText()));
					} catch (NumberFormatException exception) {
						hexField.setText("#");
						return;
					}
					setValue(newClr);
					setVisible(false);
				}
			}
		});
		hexPanel.add(hex_OkBtn);
		
			// imgPanel JPanel holds the "color from image" button
		JPanel imgPanel = new JPanel();
		imgPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		imgPanel.setBackground(new Color(255, 204, 102));
		contentPanel.add(imgPanel);
			// Create JFileChooser to choose image file
		final JFileChooser fc = new JFileChooser();
			// Set file filter to look for .png or .jpg files
		fc.setFileFilter(new FileFilter() {
			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
		        }
		        final String name = f.getName();
		        return name.endsWith(".png") || name.endsWith(".jpg");
		    }
		    public String getDescription() {
		    	return "*.png,*.jpg";
		    }
		});
			/*
			 * "Color from image button"
			 * Opens JFileChooser to select image file
			 * If image file is valid, opens ColorPicker JDialog.
			 * Once ColorPicker closes, if a valid color was chosen, 
			 * it's RGB values are filled into the RGB input fields.
			 */
		JButton clrFromImgBtn = new JButton("Choose Color From Image");
		clrFromImgBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = fc.showOpenDialog(AddColor.this);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File imgFile = fc.getSelectedFile();
					DesignImage img = new DesignImage(imgFile.getName(), imgFile);
					ColorPicker colorPick = new ColorPicker(img);
					colorPick.setModal(true);
					colorPick.pack();
					colorPick.setVisible(true);
					Color c = colorPick.getColor();
					colorPick.dispose();
					if(c != null) {
						rgb_RField.setText(Integer.toString(c.getRed()));
						rgb_GField.setText(Integer.toString(c.getGreen()));
						rgb_BField.setText(Integer.toString(c.getBlue()));
					}
				}
			}
		});
		imgPanel.add(clrFromImgBtn);
		
			// buttonPane JPanel holds cancel button
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(255, 204, 102));
		FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
		fl_buttonPane.setHgap(10);
		buttonPane.setLayout(fl_buttonPane);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
			// Cancel button
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(cancelButton);
	}
	
	/*
	 * void setValue(DesignColor val)
	 * Sets global var "value" to given DesignColor val.
	 * Used to retrieve the new color to be added by 
	 * the ProjectView window.
	 */
	protected void setValue(DesignColor val) {
		value = val;
	}
	
	/*
	 * Getter method for the global var "value"
	 */
	public DesignColor getValue() {
		return value;
	}
	
	/*
	 * Returns true if RGB input fields have text
	 */
	protected boolean rgbIsFilled() {
		if(rgb_RField.getText().equals(""))
			return false;
		if(rgb_RField.getText().equals(""))
			return false;
		if(rgb_BField.getText().equals(""))
			return false;
		return true;
	}
}
