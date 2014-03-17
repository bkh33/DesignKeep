/*
     * Brandon Harrison
     * bkh33@drexel.edu
     * CS338:GUI Final Project
     * DesignImage class
     * This is a custom image object
*/

package dkeep;

import java.io.File;

public class DesignImage {
	protected String imgName;
	protected File image;
	
	// Default constructor
	public DesignImage() {
		imgName = "New Image";
		image = null;
	}
	
	// Alt. constructor
	public DesignImage(String name, File img) {
		imgName = name;
		image = img;
	}
	
	/*
	 * Getter and setter methods for DesignImage structs
	 * This include imgName and Java.awt.Image image
	 */
	
	// Get image name
	public String getImgName() {
		return imgName;
	}
	
	// Set image name
	public void setImgName(String name) {
		imgName = name;
	}
	
	// Get image
	public File getImage() {
		return image;
	}
	
	// Set image
	public void setImage(File img) {
		image = img;
	}
	
	// toString method for JList cell renderer
	public String toString() {
		return imgName;
	}
}
