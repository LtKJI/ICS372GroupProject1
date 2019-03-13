package edu.metrostate.ics372groupproject1.scientificDataCollectionApp;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class CreateReadingPanel extends JPanel {
	
	/*
	 * This panel will be use to create a new reading, 
	 * it takes input from the user in a form and create 
	 * a reading which is added to a site
	 */
	private ArrayList<Study> allStudies; //reference to the global list of studies
	private Study newStudy;
	private String studyName, studyID, siteID, readingType, readingUnit, readingID;
	private double readingValue;
	private long readingDate;
	//Swing components
	private JFrame frame;
	private JTabbedPane tabpanel;
	private JLabel studyNameLabel, studyIDLabel, siteidLabel, readingTypeLabel, readingUnitLabel, readingIDLabel, readingValueLabel; 
	private JTextField studyNameField, studyIDField, siteidField, readingTypeField, readingUnitField, readingIDField, readingValueField;
	private JButton submitButton;

	/*
	 * FormPanel constructor that create the components for this panel
	 */
	public CreateReadingPanel (JFrame frame, ArrayList<Study> list, JTabbedPane tp) {
		this.frame = frame;
		tabpanel = tp;
		allStudies = list;
		initialized();
	}
	
	public void initialized() {
		
		setBorder(new TitledBorder ( new EtchedBorder (), "Add Reading"));
		setLayout(null);
		
		//study name 
		studyNameLabel = new JLabel("Study Name: ");
		studyNameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		studyNameLabel.setBounds(53, 69, 94, 22);
		add(studyNameLabel);
		
		studyNameField = new JTextField();
		studyNameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studyName = studyNameField.getText();
				studyNameField.transferFocus();
			}
		});
		studyNameField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		studyNameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		studyNameField.setBounds(222, 68, 301, 23);
		studyNameField.setColumns(10);
		add(studyNameField);
		
		//study id 
		studyIDLabel = new JLabel("Study ID: ");
		studyIDLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		studyIDLabel.setBounds(53, 110, 79, 21);
		add(studyIDLabel);
		
		studyIDField = new JTextField();
		studyIDField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studyID = studyIDField.getText();
				studyIDField.transferFocus();
			}
		});
		studyIDField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		studyIDField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		studyIDField.setBounds(222, 109, 105, 23);
		studyIDField.setColumns(10);
		add(studyIDField);
		
		//Site ID
		siteidLabel = new JLabel("Site ID: ");
		siteidLabel.setBounds(53, 153, 79, 23);
		siteidLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(siteidLabel);
		
		siteidField = new JTextField(10);
		siteidField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				siteID = siteidField.getText();
				siteidField.transferFocus();
			}
		});
		siteidField.setBounds(222, 154, 105, 22);
		siteidField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		siteidField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(siteidField);
		
		//Reading Type
		readingTypeLabel = new JLabel("Reading Type: ");
		readingTypeLabel.setBounds(53, 198, 94, 23);
		readingTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(readingTypeLabel);
		
		readingTypeField = new JTextField();
		readingTypeField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readingType = readingTypeField.getText();
				readingTypeField.transferFocus();
			}
		});
		readingTypeField.setBounds(222, 198, 226, 23);
		readingTypeField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		readingTypeField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		readingTypeField.setColumns(10);
		add(readingTypeField);
		
		//Reading unit
		readingUnitLabel = new JLabel("Reading Unit: ");
		readingUnitLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		readingUnitLabel.setBounds(53, 243, 94, 22);
		add(readingUnitLabel);
		
		readingUnitField = new JTextField();
		readingUnitField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readingUnit = readingUnitField.getText();
				readingUnitField.transferFocus();
			}
		});
		readingUnitField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		readingUnitField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		readingUnitField.setBounds(222, 244, 226, 22);
		readingUnitField.setColumns(10);
		add(readingUnitField);
		
		//Reading ID
		readingIDLabel = new JLabel("Reading ID: ");
		readingIDLabel.setBounds(53, 286, 94, 23);
		readingIDLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(readingIDLabel);
		
		readingIDField = new JTextField();
		readingIDField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readingID = readingIDField.getText();
				readingIDField.transferFocus();
			}
		});
		readingIDField.setBounds(222, 287, 105, 22);
		readingIDField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		readingIDField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		readingIDField.setColumns(10);
		add(readingIDField);
		
		//Reading Value
		readingValueLabel = new JLabel("Reading Value: ");
		readingValueLabel.setBounds(53, 330, 105, 23);
		readingValueLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(readingValueLabel);
		
		readingValueField = new JTextField();
		readingValueField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readingValue = Double.parseDouble(readingValueField.getText());
				readingValueField.transferFocus();
			}
		});
		readingValueField.setBounds(222, 331, 105, 21);
		readingValueField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		readingValueField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		readingValueField.setColumns(10);
		add(readingValueField);
		
		//Submit Button
		submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!allStudies.isEmpty()) {
					//check for existing study, if not, create one!
					boolean found = false;
					for (Study st : allStudies) {
						if (st.getStudyID().equals(studyID) && st.getStudyName().equals(studyName)) {
							newStudy = st;
							found = true;
							break;
						}
					}
					if (!found) {
						newStudy = new Study(studyID, studyName);
						allStudies.add(newStudy);
					} 
				} else {
					newStudy = new Study(studyID, studyName);
					allStudies.add(newStudy);
				}
				//Instantiate a new site that will contain the item created
				Site newSite = new Site(siteID);
				//for testing purpose
				newSite.setRecording(true);
				if(newSite.isRecording()) {
					Date date = new Date();
					readingDate = date.getTime();
					//Add new item to site
					newSite.addItem(new Item(siteID, readingType, readingUnit, readingID, readingValue, readingDate));
					newStudy.addSiteToStudy(newSite);
					JOptionPane.showMessageDialog(frame, "New study has been created!");
//					System.out.println(allStudies.toString());
				}else {
					JOptionPane.showMessageDialog(frame, "Site is not collecting!");
				}
				
				//reset all fields 
				studyNameField.setText("");
				studyIDField.setText("");
				siteidField.setText("");
				readingTypeField.setText("");
				readingUnitField.setText("");
				readingIDField.setText("");
				readingValueField.setText("");
			}
		});
		submitButton.setBounds(222, 386, 105, 31);
		submitButton.setToolTipText("submit the input ");
		submitButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		submitButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(submitButton);
		
		JButton returnToImport = new JButton("Return");
		returnToImport.setToolTipText("return to import tab");
		returnToImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabpanel.setSelectedIndex(0);
			}
		});
		returnToImport.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		returnToImport.setFont(new Font("Tahoma", Font.BOLD, 12));
		returnToImport.setBounds(453, 417, 94, 28);
		add(returnToImport);
	}
}
