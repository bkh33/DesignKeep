/*
     * Brandon Harrison
     * bkh33@drexel.edu
     * CS338:GUI Final Project
     * AddFile JDialog
     * Allows user to add a new file to the file list
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import dkeep.DesignFile;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.awt.Toolkit;

public class AddFile extends JDialog {

	private static final long serialVersionUID = 1207868436309195610L;
	private final JPanel contentPanel = new JPanel();
	private JTextField fileNameField;
	private JTextField fileLocationField;
	public File newFile;
	protected DesignFile value;
	protected int modified = 0;

		// Constructor / Init. JDialog
	public AddFile() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(AddFile.class.getResource("/resources/add_icon_32.png")));
		setTitle("Add New File");
		setBackground(new Color(204, 153, 51));
		setBounds(100, 100, 450, 217);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 204, 102));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
			/*
			 * Create JFileChooser
			 * Only search for .txt, .js files
			 */
		final JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileFilter() {
	        public boolean accept(File f) {
	            if (f.isDirectory()) {
	                return true;
	            }
	            final String name = f.getName();
	            return name.endsWith(".txt") || name.endsWith(".js");
	        }
	        public String getDescription() {
	            return "*.txt,*.js";
	        }
	    });
			// namePanel JPanel holds custom file name field and label
		JPanel namePanel = new JPanel();
		FlowLayout fl_namePanel = (FlowLayout) namePanel.getLayout();
		fl_namePanel.setVgap(20);
		namePanel.setBackground(new Color(255, 204, 102));
		contentPanel.add(namePanel);
			// custom file name label
		JLabel fileNameLbl = new JLabel("File Name: ");
		fileNameLbl.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		namePanel.add(fileNameLbl);
			/*
			 * Custom file name field. 
			 * Contains DocumentListener to listen if user 
			 * has inserted any text into the field. If so, 
			 * the custom name field is not overwritten with 
			 * actual file name.
			 */
		fileNameField = new JTextField();
		fileNameField.getDocument().addDocumentListener(new DocumentListener() {
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
		namePanel.add(fileNameField);
		fileNameField.setColumns(10);
		
			// filePanel JPanel hold "Open file" button and file path field
		JPanel filePanel = new JPanel();
		filePanel.setBackground(new Color(255, 204, 102));
		contentPanel.add(filePanel);
			/*
			 * Open file button. 
			 * Action event opens JFileChooser to allow user 
			 * to choose file. If file is valid, file path field 
			 * is filled in with path information. If custom file 
			 * name field is not modified, file name is filled in.
			 */
		JButton openFileBtn = new JButton("Open File...");
		openFileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(AddFile.this);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					fileLocationField.setText(file.getAbsolutePath());
					if(modified == 0) {
						fileNameField.setText(file.getName());
					}
				}
			}
		});
		filePanel.add(openFileBtn);
			// fileLocationField path field
		fileLocationField = new JTextField();
		filePanel.add(fileLocationField);
		fileLocationField.setColumns(10);
		
			// buttonPane JPanel holds OK and Cancel buttons
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(255, 204, 102));
		FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
		fl_buttonPane.setHgap(10);
		buttonPane.setLayout(fl_buttonPane);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
			/*
			 * add file OK button.
			 * If file is valid, adds new DesignFile as specified file 
			 * and sets it to the JDialog value.
			 */
		JButton addFileOkBtn = new JButton("OK");
		addFileOkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!fileNameField.getText().equals("")) {
					if(!fileLocationField.getText().equals("")) {
						try {
							newFile = new File(fileLocationField.getText());
						} catch (NullPointerException exception) {
							System.err.println("Null Pointer Exception Caught!");
						}
						DesignFile f = new DesignFile(fileNameField.getText(), newFile);
						setValue(f);
						setVisible(false);
					}
				}
			}
		});
		buttonPane.add(addFileOkBtn);
		getRootPane().setDefaultButton(addFileOkBtn);
			// add file Cancel button
		JButton addFileCancelBtn = new JButton("Cancel");
		addFileCancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(addFileCancelBtn);
	}
	
	/*
	 * returns value of AddFile JDialog
	 */
	public DesignFile getValue() {
		return value;
	}
	
	/*
	 * Sets AddFile JDialog value to specified DesignFile
	 */
	protected void setValue(DesignFile file) {
		value = file;
	}
}
