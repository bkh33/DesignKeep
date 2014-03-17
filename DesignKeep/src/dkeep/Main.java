/*
     * Brandon Harrison
     * bkh33@drexel.edu
     * CS338:GUI Final Project
     * Main JFrame
     * Displays Project list
*/

package dkeep;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.ListSelectionModel;

import java.awt.Component;

import javax.swing.DefaultListModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.Box;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import dkeep.Project;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {

	private static final long serialVersionUID = -3658325106813960951L;
	private JPanel contentPane;
	protected DefaultListModel<Project> listModel;
	protected JList<Project> projectList;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.pack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

		// Constructor / Init. JFrame
	public Main() {
		setBackground(new Color(204, 153, 51));
		//setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/resources/notebook.png")));
		setTitle("~DesignKeep~");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 468);
		
			// Create and set JMenuBar and JMenuItems
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
			// Add file->exit menu item
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		//mntmExit.setIcon(new ImageIcon(Main.class.getResource("/resources/file_cancel.png")));
		mnFile.add(mntmExit);
			// Add Help JMenu
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
			// Add About JMenuItem
		JMenuItem mntmAbout = new JMenuItem("About");
		//mntmAbout.setIcon(new ImageIcon(Main.class.getResource("/resources/help_about.png")));
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*try {
					//java.awt.Desktop.getDesktop().edit(readme);
				} catch (IOException e) {
					System.err.println("IOException thrown | Could not open readme.txt | Main.java");
				}*/
			}
		});
		//mntmAbout.setIcon(new ImageIcon(Main.class.getResource("/resources/help_about.png")));
		mnHelp.add(mntmAbout);
		
			// Set contentPane
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 102));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
			// Create "Projects" label
		JLabel lblMyProjects = new JLabel("Projects");
		lblMyProjects.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblMyProjects.setFont(new Font("Palatino Linotype", Font.BOLD, 27));
		lblMyProjects.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblMyProjects, BorderLayout.NORTH);
		
			// Set up JScrollPane for project list
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
			// Create and set Project JList
		listModel = new DefaultListModel<Project>();
		projectList = new JList<Project>(listModel);
		projectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		projectList.setBackground(new Color(255, 255, 204));
		projectList.setFont(new Font("Palatino Linotype", Font.PLAIN, 18));
		scrollPane.setViewportView(projectList);
		
			// Add rigid areas between sides of Project list and JFrame
		Component rigidAreaWest = Box.createRigidArea(new Dimension(52, 353));
		contentPane.add(rigidAreaWest, BorderLayout.WEST);
		Component rigidAreaEast = Box.createRigidArea(new Dimension(52, 353));
		contentPane.add(rigidAreaEast, BorderLayout.EAST);
		
			// Create and set southern JPanel for buttons
		JPanel panelSouth = new JPanel();
		panelSouth.setBackground(new Color(255, 204, 102));
		FlowLayout fl_panelSouth = (FlowLayout) panelSouth.getLayout();
		fl_panelSouth.setVgap(25);
		fl_panelSouth.setHgap(0);
		contentPane.add(panelSouth, BorderLayout.SOUTH);
		
			// Create new project button
		JButton btnOpenProject = new JButton("  Open");
		btnOpenProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(projectList.getSelectedIndex() != -1) {
					ProjectView projView = new ProjectView(projectList.getSelectedValue());
					projView.setModal(true);
					projView.pack();
					projView.setVisible(true);
				}
			}
		});
		btnOpenProject.setFont(new Font("Palatino Linotype", Font.BOLD, 16));
		//btnOpenProject.setIcon(new ImageIcon(Main.class.getResource("/resources/open_icon.png")));
		panelSouth.add(btnOpenProject);
		
			// Add horizontal strut for space in view
		Component hStrut1 = Box.createHorizontalStrut(20);
		panelSouth.add(hStrut1);
		
			// Create add project button
		JButton btnAddProject = new JButton(" Add");
		btnAddProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProject addNewProj = new AddProject();
				addNewProj.setModal(true);
				addNewProj.pack();
				addNewProj.setVisible(true);
				if(addNewProj.getValue() != null)
					listModel.addElement(addNewProj.getValue());
			}
		});
		//btnAddProject.setIcon(new ImageIcon(Main.class.getResource("/resources/add_icon.png")));
		btnAddProject.setFont(new Font("Palatino Linotype", Font.BOLD, 16));
		panelSouth.add(btnAddProject);
		
		// Add horizontal strut for space in view
		Component hStrut2 = Box.createHorizontalStrut(20);
		panelSouth.add(hStrut2);
		
		// Create remove project button
		JButton btnRemoveProject = new JButton("  Remove");
		btnRemoveProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(projectList.getSelectedIndex() != -1) {
					RemoveConfirm removeConf = new RemoveConfirm(projectList.getSelectedValue().getProjName());
					removeConf.setModal(true);
					removeConf.pack();
					removeConf.setVisible(true);
					if(removeConf.getValue() == 1)
						listModel.remove(projectList.getSelectedIndex());
					removeConf.dispose();
				}
			}
		});
		//btnRemoveProject.setIcon(new ImageIcon(Main.class.getResource("/resources/remove_icon.png")));
		btnRemoveProject.setFont(new Font("Palatino Linotype", Font.BOLD, 16));
		panelSouth.add(btnRemoveProject);
	}
}
