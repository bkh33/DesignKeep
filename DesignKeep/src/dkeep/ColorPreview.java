/*
     * Brandon Harrison
     * bkh33@drexel.edu
     * CS338:GUI Final Project
     * ColorPreview JDialog
     * Opens a JDialog and sets the background 
     * to a specified color for previewing.
*/

package dkeep;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dkeep.DesignColor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class ColorPreview extends JDialog {

	private static final long serialVersionUID = -7115051531717010859L;
	private final JPanel contentPanel = new JPanel();

		// Constructor / Init. JDialog
	public ColorPreview(DesignColor c) {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(ColorPreview.class.getResource("/resources/eye_32.png")));
		setBackground(new Color(204, 153, 51));
		setTitle(c.getCName());
		setBounds(100, 100, 223, 210);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(c.getColor());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
			// buttonPane JPanel holds ColorPreview OK button
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(c.getColor());
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
			// Add color preview OK button
		JButton clrPrvwOkBtn = new JButton("OK");
		clrPrvwOkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		buttonPane.add(clrPrvwOkBtn);
		getRootPane().setDefaultButton(clrPrvwOkBtn);
	}
}
