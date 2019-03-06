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
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class CreateReadingPanel extends JPanel {
	
	/*
	 * This panel will be use to create a new reading, 
	 * it takes input from the user in a form and create 
	 * a reading which is added to a site
	 */
	@SuppressWarnings("unused")
	private JFrame frame;
	private ArrayList<Site> siteList;
	private Item newItem;
	private String siteID, readingType, readingID;
	private double readingValue;
	private long readingDate;
	private JLabel siteidLabel, readingTypeLabel, readingIDLabel, readingValueLabel, readingDateLabel; 
	private JTextField siteidField, readingTypeField, readingIDField, readingValueField, readingDateField;
	private JButton submitButton;
	private JTabbedPane tabpanel;
	/*
	 * FormPanel constructor that create the components for this panel
	 */
	public CreateReadingPanel (JFrame frame, ArrayList<Site> list, JTabbedPane tp) {
		this.frame = frame;
		tabpanel = tp;
		siteList = list;
		setBorder(new TitledBorder ( new EtchedBorder (), "Add Reading"));
		setLayout(null);
		
		//Site ID
		siteidLabel = new JLabel("Site ID: ");
		siteidLabel.setBounds(51, 43, 79, 23);
		siteidLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(siteidLabel);
		
		siteidField = new JTextField(10);
		siteidField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				siteID = siteidField.getText();
				siteidField.transferFocus();
			}
		});
		siteidField.setBounds(222, 44, 197, 22);
		siteidField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		siteidField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(siteidField);
		
		//Reading Type
		readingTypeLabel = new JLabel("Reading Type: ");
		readingTypeLabel.setBounds(51, 95, 94, 23);
		readingTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(readingTypeLabel);
		
		readingTypeField = new JTextField();
		readingTypeField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readingType = readingTypeField.getText();
				readingTypeField.transferFocus();
			}
		});
		readingTypeField.setBounds(222, 95, 197, 23);
		readingTypeField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		readingTypeField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		readingTypeField.setColumns(10);
		add(readingTypeField);
		
		//Reading ID
		readingIDLabel = new JLabel("Reading ID: ");
		readingIDLabel.setBounds(51, 152, 94, 23);
		readingIDLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(readingIDLabel);
		
		readingIDField = new JTextField();
		readingIDField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readingID = readingIDField.getText();
				readingIDField.transferFocus();
			}
		});
		readingIDField.setBounds(222, 153, 197, 22);
		readingIDField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		readingIDField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		readingIDField.setColumns(10);
		add(readingIDField);
		
		//Reading Value
		readingValueLabel = new JLabel("Reading Value: ");
		readingValueLabel.setBounds(51, 204, 105, 23);
		readingValueLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(readingValueLabel);
		
		readingValueField = new JTextField();
		readingValueField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readingValue = Double.parseDouble(readingValueField.getText());
				readingValueField.transferFocus();
			}
		});
		readingValueField.setBounds(222, 205, 197, 21);
		readingValueField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		readingValueField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		readingValueField.setColumns(10);
		add(readingValueField);
		
//		//Reading Date
//		readingDateLabel = new JLabel("Reading Date:");
//		readingDateLabel.setBounds(51, 255, 99, 23);
//		readingDateLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
//		add(readingDateLabel);
//		
//		readingDateField = new JTextField();
//		readingDateField.setToolTipText("Enter MM/DD/YYYY or Enter key for today's date");
//		readingDateField.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(readingDateField.getText().equals("") || readingDateField == null) {
//				}else {
//					String inputDate = readingDateField.getText()
//				}
////				readingDate = Long.parseLong(readingDateField.getText());
////				date.setTime(time);
//			}
//		});
//		readingDateField.setBounds(222, 256, 197, 21);
//		readingDateField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		readingDateField.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		readingDateField.setColumns(10);
//		add(readingDateField);
		
		//Submit Button
		submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				readingDate = date.getTime();
				newItem = new Item(siteID, readingType, readingID, readingValue, readingDate);
				//iterate over the collection of site and find a match for created item
				Iterator<Site> iterate = siteList.iterator();
				while(iterate.hasNext()) {
					Site current = iterate.next();
					if(current.getSiteID().equals(siteID)) {
						if(current.isRecording()) {
							current.addItem(newItem);
							JOptionPane.showMessageDialog(frame, "reading for site "+siteID+" has been created!");
						}else {
							JOptionPane.showMessageDialog(frame, "Site is not collecting!");
						}
					}
				}
				siteidField.setText("");
				readingTypeField.setText("");
				readingIDField.setText("");
				readingValueField.setText("");
			}
		});
		submitButton.setBounds(270, 308, 105, 31);
		submitButton.setToolTipText("submit the input ");
		submitButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		submitButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(submitButton);
		
		JButton returnToImport = new JButton("Return");
		returnToImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabpanel.setSelectedIndex(0);
			}
		});
		returnToImport.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		returnToImport.setFont(new Font("Tahoma", Font.BOLD, 12));
		returnToImport.setBounds(456, 367, 94, 28);
		add(returnToImport);
	}
}
