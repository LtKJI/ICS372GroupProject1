package edu.metrostate.ics372groupproject1.scientificDataCollectionApp;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class IOInterface {
	private String fileName;
	private JFileChooser chooser;
	private File outputFile;
	private Component frame;
	private Gson myGson;
	private Site reading = new Site();
	
	//File chooser constructor
	public IOInterface(Component frame) {
		fileName = "";
		this.frame = frame;
		myGson = new Gson();
	}
	
	//method to choose a file, it returns the chosen file
	public File chooseFile() throws IOException {
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
	
	//Method to export reading to a file
	public void writeToFile(ArrayList<Site> site, String outputFileName) throws Exception{
		//path and construct of the output file
		outputFile = new File(System.getProperty("user.dir")+"/src/"+ outputFileName + ".json");
		//Instantiate a PrintWriter object
		PrintWriter writer = new PrintWriter(outputFile);
		//Write JSON object to the specified file on the disk
		myGson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
		String jsonString = myGson.toJson(site);
		writer.write(jsonString);
		//successful export Message 
		String message = String.format("%s has been written successfully! \n", outputFile.getName());
		JOptionPane.showMessageDialog(frame, message);
		writer.close();

	}
	
	//ReadJSON take a file and reads the content into Objects
	public void ReadJson(File input) throws Exception{
		//local variables to the read method
		BufferedReader reader = new BufferedReader(new FileReader(input));
		Site mySite = myGson.fromJson(reader, Site.class);
		//If mySite is not null, add it to the collection on sites
		if(mySite != null) {
			reading = mySite;
		}
		reader.close();
	}
	
	//method to get the input file name
	public String getFileName() {
		return fileName;
	}
	
	//get specified site from collection 
	public void getSite(String siteID, Site pickedSite) {
		for(Item item : reading.getItems()) {
			if(item.getSiteID().equals(siteID) && pickedSite.isRecording()) {
				//Only the item with matching Site ID are add 
				pickedSite.addItem(item);
			}
		}
	}

	//get all the site collections
	public String getListOfSite() {
		String sites = "";
		for(Item i : reading.getItems()) {
			sites += i.toString()+ "\n\n"; 
		}
		return sites;
	}
	
	// The method set up the path and name of the output file to write to
	public String getOutputFile () {
		return outputFile.getAbsolutePath();
	}
}
