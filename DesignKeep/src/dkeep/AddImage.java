/*
     * Brandon Harrison
     * bkh33@drexel.edu
     * CS338:GUI Final Project
     * AddImage class
*/

package dkeep;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;

import java.awt.Color;

import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import dkeep.DesignImage;

import java.awt.Font;
import java.awt.Toolkit;

public class AddImage extends JDialog {

	private static final long serialVersionUID = 6550931735132720858L;
	private final JPanel contentPanel = new JPanel();
	private JTextField imgLocationField;
	private JTextField imgNameField;
	private File newImg;
	public DesignImage value;
	protected int modified = 0;

		// Constructor / Init. JDialog
	public AddImage() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(AddImage.class.getResource("/resources/add_icon_32.png")));
		setTitle("Add New Image");
		setBackground(new Color(204, 153, 51));
		setBounds(100, 100, 450, 158);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 204, 102));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
			// Create JFileChooser to show only .png or .jpg files
		final JFileChooser fc = new JFileChooser();
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
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
			// namePanel JPanel holds image name label and field
		JPanel namePanel = new JPanel();
		namePanel.setBackground(new Color(255, 204, 102));
		contentPanel.add(namePanel);
			// name label
		JLabel imgNameLbl = new JLabel("Image Name: ");
		imgNameLbl.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		namePanel.add(imgNameLbl);
			// name field
		imgNameField = new JTextField();
		imgNameField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// do nothing
			}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				modified = 1;
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// do nothing
			}
		});
		namePanel.add(imgNameField);
		imgNameField.setColumns(10);
			// openPanel JPanel holds "Open image" button and image path field
		JPanel openPanel = new JPanel();
		openPanel.setBackground(new Color(255, 204, 102));
		contentPanel.add(openPanel);
			// Open Image button
		JButton openImgBtn = new JButton("Open Image...");
		openPanel.add(openImgBtn);
			// Image path field
		imgLocationField = new JTextField();
		openPanel.add(imgLocationField);
		imgLocationField.setColumns(10);
			/*
			 * Open Image Button action event 
			 * opens JFileChooser to allow user to 
			 * choose image file. Fills in file name if name 
			 * field has not been modified. Fills in image 
			 * path into path field.
			 */
		openImgBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(AddImage.this);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File imgFile = fc.getSelectedFile();
					imgLocationField.setText(imgFile.getAbsolutePath());
					if(modified == 0)
						imgNameField.setText(imgFile.getName());
				}
			}
		});

			// buttonPane JPanel holds OK and Cancel buttons
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(255, 204, 102));
		FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
		fl_buttonPane.setHgap(10);
		buttonPane.setLayout(fl_buttonPane);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
			/*
			 * OK Button for AddImage
			 * If image name field and image location field have text, 
			 * create a new DesignImage and set the AddImage JDialog value 
			 * to it.
			 */
		JButton addImgOkBtn = new JButton("OK");
		addImgOkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!imgNameField.getText().equals("")) {
					if(!imgLocationField.getText().equals("")) {
						try {
							newImg = new File(imgLocationField.getText());
						} catch (NullPointerException exception) {
							System.err.println("Null Pointer Exception Caught!");
						}
						DesignImage img = new DesignImage(imgNameField.getText(), newImg);
						setValue(img);
						setVisible(false);
					}
				}
			}
		});
		buttonPane.add(addImgOkBtn);
		getRootPane().setDefaultButton(addImgOkBtn);
			// add image cancel button
		JButton addImgCancelBtn = new JButton("Cancel");
		addImgCancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(addImgCancelBtn);
	}
	
	/*
	 * setValue(DesignImage val)
	 * Sets the JDialog's value to the specified DesignImage val
	 */
	protected void setValue(DesignImage val) {
		value = val;
	}
	
	/*
	 * Returns the JDialog's DesignImage value
	 */
	public DesignImage getValue() {
		return value;
	}

}