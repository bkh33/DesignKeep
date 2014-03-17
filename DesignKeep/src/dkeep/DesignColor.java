/*
     * Brandon Harrison
     * bkh33@drexel.edu
     * CS338:GUI Final Project
     * DesignColor class
     * This is a custom color object
*/

package dkeep;

import java.awt.Color;

public class DesignColor {
	protected String colorName;
	protected Color color;
	
	// Default constructor
	public DesignColor() {
		colorName = "New Color";
		color = null;
	}
	
	// Alt. constructor
	public DesignColor(String name, Color c) {
		colorName = name;
		color = c;
	}
	
	/*
	 * Getter and setter methods for DesignColor structs
	 * This include colorName and Java.awt.Color color
	 */
	
	// Get color name
	public String getCName() {
		return colorName;
	}
	
	// Set color name
	public void setCName(String name) {
		colorName = name;
	}
	
	// Get color
	public Color getColor() {
		return color;
	}
	
	// Set color
	public void setColor(Color c) {
		color = c;
	}
	
	// toString method for JList cell renderer
	public String toString() {
		return colorName;
	}
}
