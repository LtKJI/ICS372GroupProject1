package edu.metrostate.ics372groupproject1.scientificDataCollectionApp;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GraphicalUserInterface {
	private JFrame frame;
	private JTabbedPane tabpanel;
	private ArrayList<Study> studyList;

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
		studyList = new ArrayList<>();//list of all studies
		frame = new JFrame("Scientific Data Recorder");
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				//maintain a reference to IOInterface to retrieve the serialized object
//				IOInterface io = new IOInterface();
				JOptionPane.showMessageDialog(frame, "Please confirm to exit");
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		tabpanel = new JTabbedPane();
		tabpanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		//main panel that takes care of input files
		ImportReadingPanel importReadingPanel = new ImportReadingPanel(frame, studyList, tabpanel);
		//create new study and readings panel
		CreateReadingPanel createReadingPanel = new CreateReadingPanel(frame, studyList, tabpanel);
		tabpanel.addTab("Import", importReadingPanel);
		tabpanel.setToolTipTextAt(0, "Import study from a file system");
		tabpanel.addTab("Create", createReadingPanel);
		tabpanel.setToolTipTextAt(1, "Create a reading for a site");
		frame.getContentPane().add(tabpanel);
		
	}

}
