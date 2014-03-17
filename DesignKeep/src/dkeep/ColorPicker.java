/*
     * Brandon Harrison
     * bkh33@drexel.edu
     * CS338:GUI Final Project
     * ColorPicker JDialog
     * Opens an image in a JDialog and allows user 
     * to click the mouse button to retrieve pixel 
     * color information.
*/

package dkeep;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Robot;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dkeep.DesignImage;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.IOException;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;

public class ColorPicker extends JDialog implements MouseListener {

	private static final long serialVersionUID = 5965691544609924166L;
	protected BufferedImage img = null;
	private final JPanel contentPanel = new JPanel();
	private final JPanel buttonPane = new JPanel();
	protected PointerInfo pointer;
	protected Color color = null;
	protected Robot robot;
	
		/*
		 * Override imagePanel's (JPanel) paint component to draw an image.
		 * Get preferred dimension of image.
		 */
	@SuppressWarnings("serial")
	private final JPanel imagePanel = new JPanel(new BorderLayout()) {
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

		// Constructor / Init. JDialog
	public ColorPicker(DesignImage dImage) {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(ColorPicker.class.getResource("/resources/palette_32.png")));
		setTitle("Color Picker");
			// Try to initialize Robot object
		try {
			robot = new Robot();
		} catch (AWTException e1) {
			System.err.println("AWTException thrown | Robot Initialization | ColorPicker.java");
		}
		setBackground(new Color(204, 153, 51));
			// Try to create BufferedImage from DesignImage
		try {
			img = ImageIO.read(dImage.getImage());
		} catch (IOException e) {
			System.err.println("Image IOException | ColorPicker");
		}
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 204, 102));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		imagePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
			// Add mouse listener to imagePanel
		imagePanel.addMouseListener(this);
		contentPanel.add(imagePanel, BorderLayout.CENTER);
			// Add struts to view for color preview area
		Component horizontalStrutLeft = Box.createHorizontalStrut(30);
		contentPanel.add(horizontalStrutLeft, BorderLayout.WEST);
		Component horizontalStrutRight = Box.createHorizontalStrut(30);
		contentPanel.add(horizontalStrutRight, BorderLayout.EAST);
		Component verticalStrutBottom = Box.createVerticalStrut(31);
		contentPanel.add(verticalStrutBottom, BorderLayout.SOUTH);
		Component Top = Box.createVerticalStrut(30);
		contentPanel.add(Top, BorderLayout.NORTH);
			// buttonPane JPanel holds ColorPicker OK and Cancel buttons
		buttonPane.setBackground(new Color(255, 204, 102));
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
			// ColorPicker OK button confirms color selection
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
			// ColorPicker cancel button
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color = null;
				dispose();
			}
		});
		buttonPane.add(cancelButton);
	}
	
	/*
	 * Returns ColorPicker color
	 */
	public Color getColor(){
		return color;
	}
	
	/*
	 * Overridden MouseListener functions
	 * mouseClicked() is the only one used.
	 */

	/*
	 * Overridden mouseClicked(MouseEvent arg0) function
	 * On mouse click, mouse coordinate is located and the color 
	 * underneath it is determined. This color is set to the color 
	 * value of ColorPicker.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		int mouseX = MouseInfo.getPointerInfo().getLocation().x;
		int mouseY = MouseInfo.getPointerInfo().getLocation().y;
		color = robot.getPixelColor(mouseX, mouseY);
		contentPanel.setBackground(color);
		buttonPane.setBackground(color);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// Do nothing
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// Do nothing
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// Do nothing
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// Do nothing
	}
}
