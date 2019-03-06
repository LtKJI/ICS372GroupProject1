package edu.metrostate.ics372groupproject1.scientificDataCollectionApp;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class GraphicalUserInterface {
	private JFrame frame;
	private JTabbedPane tabpanel;
	private ArrayList<Site> siteList;

	//Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicalUserInterface window = new GraphicalUserInterface();
					window.frame.setVisible(true);
					window.frame.setPreferredSize(new Dimension(615, 760));
					window.frame.pack();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create the application (the constructor).
	public GraphicalUserInterface() {
		//call to instance method in the constructor
		initialize();
	}

	/* 
	 * Initialize the contents of the frame, and a tab panel that contains
	 * the two panel import panel and create panel.
	 */
	private void initialize() {
		siteList = new ArrayList<>(); //list of all sites
		frame = new JFrame("Scientific Data Recorder");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//maintain a reference to IOInterface to retrieve the serialized object
		
		tabpanel = new JTabbedPane();
		tabpanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		ImportReadingPanel importReadingPanel = new ImportReadingPanel(frame, siteList, tabpanel);
		CreateReadingPanel createReadingPanel = new CreateReadingPanel(frame, siteList, tabpanel);
		tabpanel.addTab("Import", importReadingPanel);
		tabpanel.setToolTipTextAt(0, "Import study from a file system");
		tabpanel.addTab("Create", createReadingPanel);
		tabpanel.setToolTipTextAt(1, "Create a reading for a site");
		frame.getContentPane().add(tabpanel);
		
	}

}
