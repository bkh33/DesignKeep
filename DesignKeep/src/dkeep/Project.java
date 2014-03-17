/*
     * Brandon Harrison
     * bkh33@drexel.edu
     * CS338:GUI Final Project
     * Project class
*/

package dkeep;

import java.util.ArrayList;

public class Project {
	protected String pName;
	protected ArrayList<DesignColor> pColors;
	protected ArrayList<DesignImage> pImages;
	protected ArrayList<DesignFile> pFiles;
	
	// Default constructor
	public Project() {
		pName = "New Project";
		pColors = new ArrayList<DesignColor>();
		pImages = new ArrayList<DesignImage>();
		pFiles = new ArrayList<DesignFile>();
	}
	
	// Alt. constructor 1
	public Project(String name) {
		pName = name;
		pColors = new ArrayList<DesignColor>();
		pImages = new ArrayList<DesignImage>();
		pFiles = new ArrayList<DesignFile>();
	}
	
	// Alt. constructor 2
	public Project(Project p) {
		pName = p.pName;
		pColors = p.pColors;
		pImages = p.pImages;
		pFiles = p.pFiles;
	}
	
	/*
	 * Getter and setter methods for project structures
	 * This include project name, colors, images, and files
	 */
	
	// Get project name
	public String getProjName() {
		return pName;
	}
	
	// Set project name
	public void setProjName(String name) {
		pName = name;
	}
	
	// Get color list
	public ArrayList<DesignColor> getColorList() {
		return pColors;
	}
	
	// Add color to color list
	public void addColor(DesignColor c) {
		pColors.add(c);
	}
	
	// Remove color from color list
	public void removeColor(int index) {
		pColors.remove(index);
	}
	
	// Get image list
	public ArrayList<DesignImage> getImageList() {
		return pImages;
	}
	
	// Add image to image list
	public void addImage(DesignImage i) {
		pImages.add(i);
	}
	
	// Remove image from image list
	public void removeImage(int index) {
		pImages.remove(index);
	}
	
	// Get file list
	public ArrayList<DesignFile> getFileList() {
		return pFiles;
	}
	
	// Add file to file list
	public void addFile(DesignFile f) {
		pFiles.add(f);
	}
	
	// Remove file from file list
	public void removeFile(int index) {
		pFiles.remove(index);
	}
	
	// toString method for JList cell renderer
	public String toString() {
		return pName;
	}
}
