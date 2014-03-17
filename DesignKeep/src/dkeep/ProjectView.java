/*
     * Brandon Harrison
     * bkh33@drexel.edu
     * CS338:GUI Final Project
     * ProjectView JDialog
     * The main view for project information.
     * Includes a color, image, and file list.
     * Allows user to add, remove, and preview each type of structure.
*/

package dkeep;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import java.awt.GridLayout;

import javax.swing.JScrollPane;
import javax.swing.JList;

import dkeep.DesignColor;
import dkeep.DesignFile;
import dkeep.DesignImage;
import dkeep.Project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class ProjectView extends JDialog {

	private static final long serialVersionUID = 3429787847083633959L;
	private final JPanel contentPanel = new JPanel();
	protected Project value = null;
	protected JList<DesignColor> colorList; // List of colors
	protected JList<DesignImage> imageList; // List of images
	protected JList<DesignFile> fileList; // List of files
	protected DefaultListModel<DesignColor> colorListModel;
	protected DefaultListModel<DesignImage> imageListModel;
	protected DefaultListModel<DesignFile> fileListModel;

		// Constructor / Init. JDialog
	public ProjectView(final Project project) {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(ProjectView.class.getResource("/resources/notebook.png")));
		setTitle(project.getProjName());
		setBounds(100, 100, 450, 419);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 204, 102));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 3, 0, 0));
		colorListModel = new DefaultListModel<DesignColor>();
		imageListModel = new DefaultListModel<DesignImage>();
		fileListModel = new DefaultListModel<DesignFile>();
		
		// Fill lists with pre-existing items
		for(DesignColor c : project.getColorList())
			colorListModel.addElement(c);
		for(DesignImage i : project.getImageList())
			imageListModel.addElement(i);
		for(DesignFile f : project.getFileList())
			fileListModel.addElement(f);
		
			// Color panel West
		JPanel panelWest = new JPanel();
		contentPanel.add(panelWest);
		panelWest.setLayout(new BorderLayout(0, 0));
		JPanel colorBtnPanel = new JPanel();
		panelWest.add(colorBtnPanel, BorderLayout.SOUTH);
		colorBtnPanel.setLayout(new GridLayout(0, 1, 0, 0));
			/*
			 * Add new color button launches AddColor object
			 * Adds new color (if valid) to color list of project.
			 */
		JButton addClrBtn = new JButton("Add New");
		//addClrBtn.setIcon(new ImageIcon(ProjectView.class.getResource("/resources/add_icon.png")));
		addClrBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddColor newClr = new AddColor(project);
				newClr.setModal(true);
				newClr.pack();
				newClr.setVisible(true);
				colorListModel.addElement(newClr.getValue());
				project.addColor(newClr.getValue());
			}
		});
		addClrBtn.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		colorBtnPanel.add(addClrBtn);
			/*
			 * Remove color button
			 * First launches RemoveConfirm object to allow user confirmation 
			 * of removal. If confirmed, removes the specified color from 
			 * project list.
			 */
		JButton removeClrBtn = new JButton("Remove");
		//removeClrBtn.setIcon(new ImageIcon(ProjectView.class.getResource("/resources/remove_icon.png")));
		removeClrBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(colorList.getSelectedIndex() != -1) {
					RemoveConfirm removeConf = new RemoveConfirm(colorList.getSelectedValue().getCName());
					removeConf.setModal(true);
					removeConf.pack();
					removeConf.setVisible(true);
					if(removeConf.getValue() == 1) {
						colorListModel.removeElement(colorList.getSelectedValue());
						project.removeColor(colorList.getSelectedIndex()+1);
					}
					removeConf.dispose();
				}
			}
		});
		removeClrBtn.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		colorBtnPanel.add(removeClrBtn);
			/*
			 * Preview color button
			 * Launches PreviewColor object with specified DesignColor.
			 */
		JButton previewClrBtn = new JButton("Preview");
		//previewClrBtn.setIcon(new ImageIcon(ProjectView.class.getResource("/resources/open_icon.png")));
		previewClrBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(colorList.getSelectedIndex() != -1) {
					ColorPreview cPreview = new ColorPreview(colorList.getSelectedValue());
					cPreview.setModal(true);
					cPreview.setVisible(true);
				}
			}
		});
		previewClrBtn.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		colorBtnPanel.add(previewClrBtn);
			// Add color list label heading
		JLabel colorLabel = new JLabel("Colors");
		colorLabel.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		panelWest.add(colorLabel, BorderLayout.NORTH);
		colorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		colorLabel.setVerticalAlignment(SwingConstants.TOP);
			// JScrollPane and Color List
		JScrollPane scrollPaneWest = new JScrollPane();
		panelWest.add(scrollPaneWest, BorderLayout.CENTER);
		colorList = new JList<DesignColor>(colorListModel);
		colorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		colorList.setBackground(new Color(255, 255, 204));
		colorList.setFont(new Font("Palatino Linotype", Font.PLAIN, 16));
		scrollPaneWest.setViewportView(colorList);
			
			// Image panel center
		JPanel panelCenter = new JPanel();
		contentPanel.add(panelCenter);
		panelCenter.setLayout(new BorderLayout(0, 0));
		JPanel imageBtnPanel = new JPanel();
		panelCenter.add(imageBtnPanel, BorderLayout.SOUTH);
		imageBtnPanel.setLayout(new GridLayout(0, 1, 0, 0));
			/*
			 * Launches AddImage JDialog object.
			 * If DesignImage returned is valid, it is added 
			 * to the DesignImage list of the project.
			 */
		JButton addImgBtn = new JButton("Add New");
		//addImgBtn.setIcon(new ImageIcon(ProjectView.class.getResource("/resources/add_icon.png")));
		addImgBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddImage newImg = new AddImage();
				newImg.setModal(true);
				newImg.pack();
				newImg.setVisible(true);
				imageListModel.addElement(newImg.getValue());
				project.addImage(newImg.getValue());
			}
		});
		addImgBtn.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		imageBtnPanel.add(addImgBtn);
			/*
			 * Launches RemoveConfirm to confirm the removal of the specified image. 
			 * If confirmed, the DesignImage is removed from the project list.
			 */
		JButton removeImgBtn = new JButton("Remove");
		//removeImgBtn.setIcon(new ImageIcon(ProjectView.class.getResource("/resources/remove_icon.png")));
		removeImgBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(imageList.getSelectedIndex() != -1) {
					RemoveConfirm removeConf = new RemoveConfirm(imageList.getSelectedValue().getImgName());
					removeConf.setModal(true);
					removeConf.pack();
					removeConf.setVisible(true);
					if(removeConf.getValue() == 1) {
						imageListModel.removeElement(imageList.getSelectedValue());
						project.removeImage(imageList.getSelectedIndex()+1);
					}
					removeConf.dispose();
				}
			}
		});
		removeImgBtn.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		imageBtnPanel.add(removeImgBtn);
			/*
			 * Launches PreviewImage JDialog object with specified DesignImage.
			 */
		JButton previewImgBtn = new JButton("Preview");
		//previewImgBtn.setIcon(new ImageIcon(ProjectView.class.getResource("/resources/open_icon.png")));
		previewImgBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(imageList.getSelectedIndex() != -1) {
					DesignImage img = imageList.getSelectedValue();
					PreviewImage iPreview = new PreviewImage(img);
					iPreview.setModal(true);
					iPreview.pack();
					iPreview.setVisible(true);
				}
			}
		});
		previewImgBtn.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		imageBtnPanel.add(previewImgBtn);
			// Add image list label
		JLabel imageLabel = new JLabel("Images");
		imageLabel.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		panelCenter.add(imageLabel, BorderLayout.NORTH);
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imageLabel.setVerticalAlignment(SwingConstants.TOP);
			// Add image scroll pane and list
		JScrollPane scrollPaneCenter = new JScrollPane();
		panelCenter.add(scrollPaneCenter, BorderLayout.CENTER);
		imageList = new JList<DesignImage>(imageListModel);
		imageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		imageList.setBackground(new Color(255, 255, 204));
		imageList.setFont(new Font("Palatino Linotype", Font.PLAIN, 16));
		scrollPaneCenter.setViewportView(imageList);
		
			// File panel east
		JPanel panelEast = new JPanel();
		contentPanel.add(panelEast);
		panelEast.setLayout(new BorderLayout(0, 0));
		JPanel fileBtnPanel = new JPanel();
		panelEast.add(fileBtnPanel, BorderLayout.SOUTH);
		fileBtnPanel.setLayout(new GridLayout(0, 1, 0, 0));
			/*
			 * Launches AddFile JDialog object.
			 * If returned file is valid, it is added to the project's 
			 * DesignFile list.
			 */
		JButton addFileBtn = new JButton("Add New");
		//addFileBtn.setIcon(new ImageIcon(ProjectView.class.getResource("/resources/add_icon.png")));
		addFileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddFile newFile = new AddFile();
				newFile.setModal(true);
				newFile.pack();
				newFile.setVisible(true);
				fileListModel.addElement(newFile.getValue());
				project.addFile(newFile.getValue());
			}
		});
		addFileBtn.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		fileBtnPanel.add(addFileBtn);
			/*
			 * Launches RemoveConfirm to confirm the removal of the specified file. 
			 * If confirmed, the DesignFile is removed from the project list.
			 */
		JButton removeFileBtn = new JButton("Remove");
		//removeFileBtn.setIcon(new ImageIcon(ProjectView.class.getResource("/resources/remove_icon.png")));
		removeFileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fileList.getSelectedIndex() != -1) {
					RemoveConfirm removeConf = new RemoveConfirm(fileList.getSelectedValue().getFName());
					removeConf.setModal(true);
					removeConf.pack();
					removeConf.setVisible(true);
					if(removeConf.getValue() == 1) {
						fileListModel.removeElement(fileList.getSelectedValue());
						project.removeFile(fileList.getSelectedIndex()+1);
					}
					removeConf.dispose();
				}
			}
		});
		removeFileBtn.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		fileBtnPanel.add(removeFileBtn);
			/*
			 * Launches PreviewFile JDialog object to preview specified DesignFile.
			 */
		JButton previewFileBtn = new JButton("Preview");
		//previewFileBtn.setIcon(new ImageIcon(ProjectView.class.getResource("/resources/open_icon.png")));
		previewFileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fileList.getSelectedIndex() != -1) {
					DesignFile file = fileList.getSelectedValue();
					PreviewFile fPreview;
					try {
						fPreview = new PreviewFile(file);
						fPreview.setModal(true);
						fPreview.setVisible(true);
					} catch (IOException e1) {
						System.err.println("IOException thown (PreviewFile)");
					}
				}
			}
		});
		previewFileBtn.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		fileBtnPanel.add(previewFileBtn);
			// Add file list label
		JLabel fileLabel = new JLabel("Files");
		fileLabel.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		panelEast.add(fileLabel, BorderLayout.NORTH);
		fileLabel.setVerticalAlignment(SwingConstants.TOP);
		fileLabel.setHorizontalAlignment(SwingConstants.CENTER);
			// Add scrollpane and fileList
		JScrollPane scrollPaneEast = new JScrollPane();
		panelEast.add(scrollPaneEast, BorderLayout.CENTER);
		fileList = new JList<DesignFile>(fileListModel);
		fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fileList.setBackground(new Color(255, 255, 204));
		fileList.setFont(new Font("Palatino Linotype", Font.PLAIN, 16));
		scrollPaneEast.setViewportView(fileList);
			
			// buttonPane JPanel holds ProjectView OK button
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(255, 204, 102));
		buttonPane.setBorder(null);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setValue(project);
				dispose();
			}
		});
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	}
	
	// Set value of ProjectView JDialog
	protected void setValue(Project p) {
		value = p;
	}
	
	// Get value of ProjectView JDialog
	public Project getValue() {
			return value;
	}
}