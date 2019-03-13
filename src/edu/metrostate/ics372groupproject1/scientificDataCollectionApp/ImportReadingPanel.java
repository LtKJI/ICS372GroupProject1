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
	
	private File importedFile = null;
	private String fileName;
	private String siteID;
	private Site selectedSite;
	private IOInterface myInterface; //reference to the IOInterface class
	private Study importedStudy = null; //will reference the current study at hand
	private ArrayList <Study> allStudies; // global list of studies collected
	// Swing components
	private JFrame frame;
	private JTabbedPane tabpanel;
	private JFileChooser chooser;
	private JLabel studyNameLabel, studyIDLabel, header, fileNameLabel, siteId, statusLabel;
	private JTextField siteIDField, studyNameField, studyIDField;
	private JTextArea mainDisplay;
	private JButton UploadButton, readButton, startButton, addButton, EndButton, viewButton, exportButton;
	private JScrollPane scrollPane;
	
	public ImportReadingPanel(JFrame frame, ArrayList<Study> list, JTabbedPane cp) {
		//initialize all the widgets
		this.frame = frame;
		myInterface = new IOInterface();
		allStudies = list;
		tabpanel = cp;
		initialize();
	}
		
	private void initialize() {
		
		//local study variable for the current study
		fileNameLabel = new JLabel();
		fileNameLabel.setForeground(new Color(0, 0, 128));
		fileNameLabel.setBounds(148, 56, 206, 21);
		mainDisplay = new JTextArea();
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setBorder(new TitledBorder ( new EtchedBorder (), "Import Reading"));
		setLayout(null);
		
		header = new JLabel("To start recording , choose a file to be read...");
		header.setBounds(148, 21, 314, 15);
		header.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		add(header);
				
		//Choose the file to be read
		UploadButton = new JButton("Upload File");
		UploadButton.setBounds(40, 56, 83, 21);
		UploadButton.setToolTipText("Navigate to the JSON file.");
		UploadButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		UploadButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		UploadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					importedFile = chooseFile();
					fileNameLabel.setText(getFileName());
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(frame, e.getStackTrace());
				}
			}
		});
		add(UploadButton);
				
		fileNameLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		add(fileNameLabel);
				
				
		//This functional button will call the ReadJson() with the input JSON as parameter
		readButton = new JButton("Read File");
		readButton.setBounds(40, 103, 83, 21);
		readButton.setToolTipText("Read the selected JSON file.");
		readButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		readButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		readButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//Call to the method to read the JSON
				if(importedFile != null) {
					try {
						//extract the file extension
						String extension = fileName.substring(fileName.lastIndexOf("."));
						if(extension.equals(".json")) {
							//parse JSON
							myInterface.ReadJson(importedFile);
							importedStudy = new Study();
							//Display the content of the input JSON
							mainDisplay.setText(mainDisplay.getText() +"\n"+ myInterface.getReadings());
							readButton.transferFocus();
						}else {
							//parse XML file
							myInterface.readXMLFile(importedFile);
							
							//set the study from that of the file
							importedStudy = myInterface.getMyStudy();
							studyNameField.setText(importedStudy.getStudyName());
							
							//set the study from that of the filed
							studyIDField.setText(importedStudy.getStudyID());
							
							//Display the content of the XML file
							mainDisplay.setText(myInterface.getReadings());
							readButton.transferFocus();
						}
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
		
		//Label and Field for a Study Name input
		studyNameLabel = new JLabel("Study Name: ");
		studyNameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		studyNameLabel.setBounds(40, 145, 83, 17);
		add(studyNameLabel);
		
		studyNameField = new JTextField();
		studyNameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Takes input from user for the creation of a study
				importedStudy.setStudyName(studyNameField.getText());
				studyNameField.transferFocus();
			}
		});
		studyNameField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		studyNameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		studyNameField.setBounds(152, 144, 235, 20);
		studyNameField.setColumns(10);
		add(studyNameField);
		
		//Label and Field for a Study ID input
		studyIDLabel = new JLabel("Study ID: ");
		studyIDLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		studyIDLabel.setBounds(414, 145, 63, 17);
		add(studyIDLabel);
		
		studyIDField = new JTextField();
		studyIDField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Takes input from user for the creation of a study
				importedStudy.setStudyID(studyIDField.getText());;
				studyIDField.transferFocus();
			}
		});
		studyIDField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		studyIDField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		studyIDField.setBounds(487, 146, 68, 20);
		studyIDField.setColumns(10);
		add(studyIDField);
		
		//Label to indicate where to enter the site id
		siteId = new JLabel("Site ID:");
		siteId.setBounds(40, 189, 53, 15);
		siteId.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(siteId);
				
		//Text field that takes the site ID of selected site
		siteIDField = new JTextField();
		siteIDField.setBounds(152, 187, 86, 20);
		siteIDField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		siteIDField.setColumns(10);
		siteIDField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				siteID = siteIDField.getText();
				//create a new site which will contain the related readings and
				//add it to the list of sites
				selectedSite = new Site(siteID);
				importedStudy.addSiteToStudy(selectedSite);
				if(!allStudies.contains(importedStudy)) {
					allStudies.add(importedStudy);
				}
				
				//mainDisplay the siteID to the user
				siteIDField.setText("");
				statusLabel.setText("Site ID: "+siteID);
			}
		});
		add(siteIDField);
				
		//this functional button allow a site collection to start saving
		startButton = new JButton("Start ");
		startButton.setBounds(397, 225, 68, 21);
		startButton.setToolTipText("Start site collection.");
		startButton.setBackground(UIManager.getColor("Button.background"));
		startButton.setForeground(Color.BLACK);
		startButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		startButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(importedStudy.getStudyID() != null || importedStudy.getStudyName() != null) {
					selectedSite.setRecording(true);
					//Show the selected site and status in the text field
					String info = "Site: "+siteID +" is now Collecting.";
					statusLabel.setText(info);
				}
				else {
					JOptionPane.showMessageDialog(frame, "Please Enter a site ID to start collecting!");
				}
			}
		});
		statusLabel = new JLabel();
		statusLabel.setForeground(new Color(0, 0, 128));
		statusLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		//Label to display whether the site is collecting or end collection
		statusLabel.setBounds(42, 225, 272, 21);
		add(statusLabel);
		add(startButton);
		
		//This button toggle the site boolean recording to true or false		
		EndButton = new JButton("End ");
		EndButton.setBounds(492, 225, 63, 21);
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
					String info = "Site: "+siteID +" is no longer Collecting.";
					statusLabel.setText(info);
				}
				else {
					JOptionPane.showMessageDialog(frame, "Please Enter a site ID to stop collecting from!");
				}
			}
		});
		add(EndButton);
				
		//this Text area will contain the site reading 
		mainDisplay.setEditable(false);
		mainDisplay.setLineWrap(true);
		mainDisplay.setBorder(new TitledBorder ( new EtchedBorder (), "Display Reading"));
		scrollPane = new JScrollPane(mainDisplay);
		scrollPane.setBounds(42, 300, 513, 341);
		scrollPane.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		add(scrollPane);
				
		//Add collection to a specified site
		addButton = new JButton("Add ");
		addButton.setBounds(42, 257, 68, 21);
		addButton.setToolTipText("Add Items to Site.");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (siteID != null) {
					//referenced the selected site matching the site ID
					myInterface.setSiteReadings(siteID, selectedSite);
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
		viewButton = new JButton("View ");
		viewButton.setBounds(152, 257, 68, 21);
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
					myInterface.writeToFile(allStudies, outputFileName);
					
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
		
		JButton writeReading = new JButton("Create ");
		writeReading.setToolTipText("create a new reading");
		writeReading.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabpanel.setSelectedIndex(1);
			}
		});
		writeReading.setFont(new Font("Tahoma", Font.BOLD, 12));
		writeReading.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		writeReading.setBounds(264, 257, 68, 21);
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
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".JSON & .XML files", "json", "xml");
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
