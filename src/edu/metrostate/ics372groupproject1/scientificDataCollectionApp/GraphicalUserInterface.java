package edu.metrostate.ics372groupproject1.scientificDataCollectionApp;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/*
 * This class create the Graphical interface
 * It initializes the components and containers 
 * of the interface.
 */
public class GraphicalUserInterface {
	private JFrame frame;
	private static Record record = null;
	private final int OPTION_TYPE = JOptionPane.YES_NO_OPTION;
	private final int MESSAGE_TYPE = JOptionPane.QUESTION_MESSAGE;
	private static final String STATE_FILE = "STATE_FILE";
	private static final String TITLE = "Save on Exit";
	private static final String MESSAGE = "Would you like to save your study?"; //choice to save state or not
	private static final int YES = 0;
	private static final int NO = 1; 
	
	//Create the application (the constructor).
	public GraphicalUserInterface() throws Exception {
		record = JSONFile.loadState(STATE_FILE);
		//call to instance method in the constructor
		initialize();
	}

	/** 
	 * @param
	 * Initialize the contents of the frame, and a tab panel that contains
	 * the two panel import panel and create panel.
	 */
	private void initialize() {
		frame = new JFrame("Scientific Data Recorder");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				//Ask the user where they want to save the state or not
				int option = JOptionPane.showConfirmDialog(frame, MESSAGE, TITLE, OPTION_TYPE, MESSAGE_TYPE);
				if(option == YES) {
					//Save the state 
					try {
						JSONFile.writeToFile(record, STATE_FILE);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.exit(YES);
				}else if(option == NO) {
					//exit program without saving state
					System.exit(NO);;
				}else {
					//save the state if other circumstance force the program to exit
					try {
						JSONFile.writeToFile(record, STATE_FILE);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		//main panel that takes care of input files
		ImportReadingPanel importReadingPanel = new ImportReadingPanel(frame, record);
		frame.getContentPane().add(importReadingPanel);
		frame.setLocationByPlatform(true);
	}
	
	//Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicalUserInterface window = new GraphicalUserInterface();
					window.frame.setVisible(true);
					window.frame.setPreferredSize(new Dimension(625, 760));
					window.frame.pack();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
