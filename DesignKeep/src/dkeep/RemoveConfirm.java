/*
     * Brandon Harrison
     * bkh33@drexel.edu
     * CS338:GUI Final Project
     * RemoveConfirm JDialog
     * Asks the user to confirm removal of an object
*/

package dkeep;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class RemoveConfirm extends JDialog {

	private static final long serialVersionUID = 4917524432640834097L;
	private final JPanel contentPanel = new JPanel();
	protected int value = 0;

		// Constructor / Init. JDialog
	public RemoveConfirm(String name) {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(RemoveConfirm.class.getResource("/resources/warning_24.png")));
		setTitle("Do you really want to do that?");
		setBackground(new Color(204, 153, 51));
		setBounds(100, 100, 450, 196);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 204, 102));
		FlowLayout fl_contentPanel = new FlowLayout();
		fl_contentPanel.setVgap(40);
		contentPanel.setLayout(fl_contentPanel);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
			// Add confirmation text label
		JLabel textLbl = new JLabel("Are you sure you want to remove " + name + "?");
		//textLbl.setIcon(new ImageIcon(RemoveConfirm.class.getResource("/resources/warning_48.png")));
		textLbl.setFont(new Font("Palatino Linotype", Font.BOLD, 16));
		contentPanel.add(textLbl);
			// buttonPane JPanel holds Yes and No confirmation buttons
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(255, 204, 102));
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
			// Add Yes button
		JButton yesBtn = new JButton("Yes");
		yesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setValue(1);
				setVisible(false);
			}
		});
		buttonPane.add(yesBtn);
		getRootPane().setDefaultButton(yesBtn);
			// Add No button
		JButton noBtn = new JButton("No");
		noBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		buttonPane.add(noBtn);
	}
	
		/*
		 * Sets value of RemoveConfirmation JDialog to specified int
		 */
	protected void setValue(int b) {
		value = b;
	}
	
		/*
		 * Returns value of RemoveConfirmation JDialog
		 */
	public int getValue() {
		return value;
	}

}
