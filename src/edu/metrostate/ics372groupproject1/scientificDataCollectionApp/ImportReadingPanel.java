package edu.metrostate.ics372groupproject1.scientificDataCollectionApp;

import javax.swing.JPanel;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
//import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class ImportReadingPanel extends JPanel {
	
	
	private JFrame frame;
	private JTextField siteIDField;
	private File JSONFile = null;
	private String siteID;
	private IOInterface myInterface;
	private Site selectedSite;
	private ArrayList <Site> siteList;
	private String fileName;
	private JFileChooser chooser;
	private JLabel header, fileNameLabel, siteId, statusField;
	private JTextArea mainDisplay;
	private JButton UploadButton, readButton, startButton, addButton, EndButton, viewButton, exportButton;
	private JScrollPane scrollPane;
	private JTabbedPane tabpanel;
	
	public ImportReadingPanel(JFrame frame, ArrayList<Site> list, JTabbedPane cp) {
		//initialize all the widgets
		this.frame = frame;
		siteList = list;
		myInterface = new IOInterface();
		tabpanel = cp;
		fileNameLabel = new JLabel();
		fileNameLabel.setBounds(238, 56, 206, 21);
		mainDisplay = new JTextArea();
		statusField = new JLabel();
		statusField.setBounds(42, 199, 229, 21);
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setBorder(new TitledBorder ( new EtchedBorder (), "Import Reading"));
		setLayout(null);
				
				
		header = new JLabel("To start recording , choose a JSON file to be read...");
		header.setBounds(148, 21, 314, 15);
		header.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		add(header);
				
		//Choose the file to be read(JSON)
		UploadButton = new JButton("Upload JSON");
		UploadButton.setBounds(35, 56, 83, 21);
		UploadButton.setToolTipText("Navigate to the JSON file.");
		UploadButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		UploadButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		UploadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JSONFile = chooseFile();
					fileNameLabel.setText(getFileName());
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(frame, e.getStackTrace());
				}
			}
		});
		add(UploadButton);
				
		fileNameLabel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		fileNameLabel.setFont(new Font("Tahoma", Font.ITALIC, 13));
		add(fileNameLabel);
				
				
		//This functional button will call the ReadJson() with the input JSON as parameter
		readButton = new JButton("Read File");
		readButton.setBounds(35, 97, 83, 21);
		readButton.setToolTipText("Read the selected JSON file.");
		readButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		readButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		readButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//Call to the method to read the JSON
				if(JSONFile != null) {
					try {
						myInterface.ReadJson(JSONFile);
						//mainDisplay the content of the input JSON
						mainDisplay.setText(mainDisplay.getText() +"\n"+ myInterface.getReadings());
					}catch(Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(frame, "Error Reading The File!");
					}
				}
				else {
					JOptionPane.showMessageDialog(frame, "Please choose a File to be read!");
				}
			}
		});
		add(readButton);
		
		//Label to indicate where to enter the site id
		siteId = new JLabel("Site ID:");
		siteId.setBounds(42, 141, 46, 15);
		siteId.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(siteId);
				
		//Text field that takes the site ID of selected site
		siteIDField = new JTextField();
		siteIDField.setBounds(238, 139, 86, 20);
		siteIDField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		siteIDField.setColumns(10);
		siteIDField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				siteID = siteIDField.getText();
				//create a new site which will contain the related readings and
				//add it to the list of sites
				selectedSite = new Site(siteID);
				if(!siteList.contains(selectedSite)) {
					siteList.add(selectedSite);						
				}
				//mainDisplay the siteID to the user
				siteIDField.setText("");
				statusField.setText("locationID: "+siteID);
			}
		});
		add(siteIDField);
				
		//this functional button allow a site collection to start saving
		startButton = new JButton("Start ");
		startButton.setBounds(324, 199, 103, 21);
		startButton.setToolTipText("Start site collection.");
		startButton.setBackground(UIManager.getColor("Button.background"));
		startButton.setForeground(Color.BLACK);
		startButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		startButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(siteID != null) {
					selectedSite.setRecording(true);
					//Show the selected site and status in the text field
					String info = "locationID: "+siteID +" is now Collecting.";
					statusField.setText(info);
				}
				else {
					JOptionPane.showMessageDialog(frame, "Please Enter a site ID to start collecting!");
				}
			}
		});
		add(startButton);
		
		//This button toggle the site boolean recording to true or false		
		EndButton = new JButton("End ");
		EndButton.setBounds(452, 199, 103, 21);
		EndButton.setToolTipText("End site collection");
		EndButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		EndButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		EndButton.setBackground(UIManager.getColor("Button.background"));
		EndButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(siteID != null) {
					//This is where the call to the method to stop saving will go
					selectedSite.setRecording(false);
					//Show the selected site and status in the text field
					String info = "locationID: "+siteID +" is no longer Collecting.";
					statusField.setText(info);
				}
				else {
					JOptionPane.showMessageDialog(frame, "Please Enter a site ID to stop collecting from!");
				}
			}
		});
		add(EndButton);
				
		//Label to display whether the site is collecting or end collection
		statusField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(statusField);
				
		//this Text area will contain the site reading 
		mainDisplay.setEditable(false);
		mainDisplay.setLineWrap(true);
		mainDisplay.setBorder(new TitledBorder ( new EtchedBorder (), "Display Reading"));
		scrollPane = new JScrollPane(mainDisplay);
		scrollPane.setBounds(42, 300, 513, 341);
		scrollPane.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		add(scrollPane);
				
		//Add collection to a specified site
		addButton = new JButton("Add Readings");
		addButton.setBounds(42, 257, 95, 21);
		addButton.setToolTipText("Add Items to Site.");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (siteID != null) {
					//referenced the selected site matching the site ID
					myInterface.getSiteReadings(siteID, selectedSite);
				}
				else {
					JOptionPane.showMessageDialog(frame, "Please enter a site to add collection to!");
				}
			}
		});
		addButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		addButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		add(addButton);

				
		//View the site collections
		viewButton = new JButton("View Reading");
		viewButton.setBounds(238, 257, 103, 21);
		viewButton.setToolTipText("Show Items for a Site.");
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (siteID != null) {
					//mainDisplay a selected site's reading
					mainDisplay.setText(selectedSite.toString());
				}
				else {
					JOptionPane.showMessageDialog(frame, "Nothing to mainDisplay!");
				}
			}
		});
		viewButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		viewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(viewButton);
				
		//this functional button will export the site collection in a JSON format
		exportButton = new JButton("Export JSON");
		exportButton.setBounds(473, 652, 83, 21);
		exportButton.setToolTipText("Export your site(s) to a JSON file.");
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//This is where the call to the method to write the JSON to a file will go
				try {
					String outputFileName = JOptionPane.showInputDialog(frame,"Name your Export File...");
					if(outputFileName.equals("")) {
						outputFileName = "SiteRecord";
					}
					myInterface.writeToFile(siteList, outputFileName);
					
					//successful export Message is mainDisplayed on the screen
					String message = String.format("%s has been written successfully! \n", outputFileName);
					JOptionPane.showMessageDialog(frame, message);
				}catch(Exception e) {
					JOptionPane.showMessageDialog(frame, "Export Cancelled!");
				}
			}
		});
		exportButton.setAutoscrolls(true);
		exportButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		exportButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, Color.DARK_GRAY, null));
		add(exportButton);
		
		JButton writeReading = new JButton("Create Reading");
		writeReading.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabpanel.setSelectedIndex(1);
			}
		});
		writeReading.setFont(new Font("Tahoma", Font.BOLD, 12));
		writeReading.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		writeReading.setBounds(443, 257, 112, 21);
		add(writeReading);
				
	}
			
		/*
		 * ChooseFile allow to browse the file directory, and to choose a file, it returns the chosen file
		 */	
	private File chooseFile() throws IOException {
		//Specify the current directory for the file chooser()
        File currentDir = new File(System.getProperty("user.dir")+"/src");
        chooser = new JFileChooser(currentDir);
        
        //filter on files with .text extension
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".JSON files", "json");
        this.chooser.setFileFilter(filter);
        
        //open the file chooser dialog box

        int status = chooser.showOpenDialog(frame);
        if(status == JFileChooser.APPROVE_OPTION) {

            //Construct the output file name
            fileName =  chooser.getSelectedFile().getName();
        }
        return chooser.getSelectedFile();
	}
		
	//method to get the input file name
	private String getFileName() {
		return fileName;
	}
}
