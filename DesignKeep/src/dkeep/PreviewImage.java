/*
     * Brandon Harrison
     * bkh33@drexel.edu
     * CS338:GUI Final Project
     * PreviewImage JDialog
     * Opens specified image in a JDialog for previewing
*/

package dkeep;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dkeep.DesignImage;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class PreviewImage extends JDialog {

	private static final long serialVersionUID = -3747423328908091795L;
	protected BufferedImage img = null;
	
		/*
		 * Override contentPanel's (JPanel) paint component to draw an image.
		 * Get preferred dimension of image.
		 */
	@SuppressWarnings("serial")
	private final JPanel contentPanel = new JPanel(new BorderLayout()) {
		@Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }

        @Override
        public Dimension getPreferredSize() {
            Dimension size = super.getPreferredSize();
            size.width = Math.max(img.getWidth(), size.width);
            size.height = Math.max(img.getHeight(), size.height);
            return size;
        }
	};
		// Constructor / Init JDialog
	public PreviewImage(DesignImage dImage) {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(PreviewImage.class.getResource("/resources/eye_32.png")));
		setBackground(new Color(204, 153, 51));
			// Try to create BufferedImage from DesignImage
		try {
			img = ImageIO.read(dImage.getImage());
		} catch (IOException e) {
			System.err.println("Image IOException : PreviewImage");
		}
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
			// buttonPane holds PreviewImage OK button
		JPanel buttonPane = new JPanel();
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