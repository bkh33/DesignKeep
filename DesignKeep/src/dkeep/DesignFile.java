/*
     * Brandon Harrison
     * bkh33@drexel.edu
     * CS338:GUI Final Project
     * DesignFile class
     * This is a custom file object
*/

package dkeep;

import java.io.File;

public class DesignFile {
	protected String fileName;
	protected File file;
	
	// Default constructor
	public DesignFile() {
		fileName = "New File";
		file = null;
	}
	
	// Alt. constructor
	public DesignFile(String name, File f) {
		fileName = name;
		file = f;
	}
	
	/*
	 * Getter and setter methods for DesignFile structs
	 * This include fileName and Java.io.File file
	 */
	
	// Get file name
	public String getFName() {
		return fileName;
	}
	
	// Set file name
	public void setFName(String name) {
		fileName = name;
	}
	
	// Get file
	public File getFile() {
		return file;
	}
	
	// Set file
	public void setFile(File f) {
		file = f;
	}
	
	// toString method for JList cell renderer
	public String toString() {
		return fileName;
	}
}
