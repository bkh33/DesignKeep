/*
     * Brandon Harrison
     * bkh33@drexel.edu
     * CS338:GUI Final Project
     * AddProject JDialog
*/

package dkeep;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dkeep.Project;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class AddProject extends JDialog {

	private static final long serialVersionUID = -216207651791972208L;
	private final JPanel contentPanel = new JPanel();
	private JTextField projectNameField;
	protected Project value = null;
	
		// Constructor / Init. JDialog
	public AddProject() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(AddProject.class.getResource("/resources/add_icon_32.png")));
		setBackground(new Color(204, 153, 51));
		setTitle("Add New Project");
		setBounds(100, 100, 448, 165);
		getContentPane().setLayout(new BorderLayout());
		FlowLayout fl_contentPanel = new FlowLayout();
		fl_contentPanel.setHgap(10);
		fl_contentPanel.setVgap(20);
		contentPanel.setBackground(new Color(255, 204, 102));
		contentPanel.setLayout(fl_contentPanel);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
			// Project name label
		JLabel nameLabel = new JLabel("Project Name:");
		nameLabel.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		contentPanel.add(nameLabel);
			// Project name field
		projectNameField = new JTextField();
		contentPanel.add(projectNameField);
		projectNameField.setColumns(10);
		
			// buttonPane JPanel hold OK and Cancel buttons for the JDialog
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(255, 204, 102));
		FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
		fl_buttonPane.setHgap(10);
		buttonPane.setLayout(fl_buttonPane);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
			/*
			 * Add project OK button
			 * Sets AddProject value to new project if 
			 * the project name field has text.
			 */
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!projectNameField.getText().equals("")) {
					Project newProj = new Project();
					newProj.setProjName(projectNameField.getText());
					setValue(newProj);
					setVisible(false);
				}
			}
		});
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
			// Add project cancel button
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(cancelButton);
	}
	
	/*
	 * Returns AddProject JDialog value
	 */
	public Project getValue() {
		return value;
	}
	
	/*
	 * Sets AddProject JDialog value to specified val
	 */
	protected void setValue(Project val) {
		value = val;
	}

}
