/*
     * Brandon Harrison
     * bkh33@drexel.edu
     * CS338:GUI Final Project
     * PreviewFile JDialog
     * Opens a text or javascript file and prints it into 
     * a JTextArea inside a JDialog for previewing.
*/

package dkeep;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTextArea;

import dkeep.DesignFile;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Toolkit;

public class PreviewFile extends JDialog {

	private static final long serialVersionUID = -698805827567491608L;
	private final JPanel contentPanel = new JPanel();

		// Constructor / Init. JDialog
	public PreviewFile(DesignFile file) throws IOException {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(PreviewFile.class.getResource("/resources/eye_32.png")));
		BufferedReader in = null;
		String line = null;
		setBackground(new Color(204, 153, 51));
		setBounds(100, 100, 656, 462);
		setTitle(file.getFName());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 204, 102));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
			// Create JTextArea for file preview area
		JTextArea textArea = new JTextArea();
			// Try to open file with BufferedReader
		try {
			in = new BufferedReader(new FileReader(file.getFile()));
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException thrown (PreviewFile)");
			dispose();
		}
			// Try to read a line of the BufferedReader
		try {
			line = in.readLine();
		} catch (IOException e) {
			System.err.println("IOException thrown (PreviewFile)");
			dispose();
		}
			// For each line in file, read and append to JTextArea
		while((line = in.readLine()) != null) {
			textArea.append(line + "\n");
		}
		// Try to close the BufferedReader
		try {
			in.close();
		} catch (IOException e) {
			System.err.println("IOException thrown - closing BufferedReader (PreviewFile)");
			dispose();
		}
		contentPanel.setLayout(new BorderLayout(0, 0));
			// Create JScrollPane for JTextArea
		JScrollPane scrollPane = new JScrollPane(textArea);
		contentPanel.add(scrollPane, BorderLayout.CENTER);
			// buttonPane JPanel holds PreviewFile OK button
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(255, 204, 102));
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
			// Add OK button
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	}
}
