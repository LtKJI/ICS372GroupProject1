package edu.metrostate.ics372groupproject1.scientificDataCollectionApp;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class IOInterface {

	private File outputFile;
//	private Component frame;
	private Gson myGson;
	private Site reading = new Site();
	
	//File chooser constructor
	public IOInterface() {
		myGson = new Gson();
	}
	public IOInterface(Component frame) {
//		this.frame = frame;
		myGson = new Gson();
	}
	
	/*
	 * ReadJSON method takes a file and reads the content into Objects
	 */	
	public void ReadJson(File input) throws Exception{
		//Instantiates a BufferReader object that takes the input file as an argument 
		BufferedReader reader = new BufferedReader(new FileReader(input));
		Site mySite = myGson.fromJson(reader, Site.class);
		
		//If mySite is not null, add it to the collection on sites
		if(mySite != null) {
			reading = mySite;
		}
		reader.close();
	}
	
	/*
	 * WriteToFile method takes as a parameters a list of sites
	 * and a file name. It write the sites in the list to a file on the disk
	 */
	public void writeToFile(ArrayList<Site> site, String outputFileName) throws Exception{
		//path and construct of the output file
		outputFile = new File(System.getProperty("user.dir")+"/src/"+ outputFileName + ".json");
		//Instantiate a PrintWriter object
		PrintWriter writer = new PrintWriter(outputFile);
		//Write JSON object to the specified file on the disk
		myGson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
		String jsonString = myGson.toJson(site);
		writer.write(jsonString);
		
		writer.close();
		
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
