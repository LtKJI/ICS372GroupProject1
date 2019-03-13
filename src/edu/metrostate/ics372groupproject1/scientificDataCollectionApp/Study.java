
package edu.metrostate.ics372groupproject1.scientificDataCollectionApp;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Study {
	
	@SerializedName("study_name")
	@Expose
	private String studyName;
	
	@SerializedName("study_id")
	@Expose
	private String studyID;
	
	@SerializedName("site_readings")
	@Expose
	private ArrayList<Site> sitesInStudy = new ArrayList<>();
	
	//constructors
	public Study() {
		this.studyID = "";
		this.studyName = "";
	}
  
	public Study(String studyID, String studyName) {
		this.studyID = studyID;
		this.studyName = studyName;		
	}
	
	public String getStudyName() {
		return studyName;
	}

	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}

	public String getStudyID() {
		return studyID;
	}

	public void setStudyID(String studyId) {
		this.studyID = studyId;
	}
	//adding sites to a study, takes a site as parameter
	public void addSiteToStudy(Site site) {
		sitesInStudy.add(site);
	}
	//set all site to start collecting
	public void startAllSiteCollection() {
		for(Site s : sitesInStudy) {
			s.setRecording(true);
		}
	}
	//end all sites collection
	public void endAllSiteCollection() {
		for(Site s : sitesInStudy) {
			s.setRecording(false);
		}
	}
	//get the list of sites in study
	public ArrayList<Site> getSiteInStudy() {
		return this.sitesInStudy;
	}
	@Override
	public String toString() {
		String text = "";
		for(Site s : sitesInStudy) {
			text += s.toString() + "\n";
		}
		return "\nStudy_ID: " + this.studyID +"\nStudy_Name: "+ this.studyName + text;
	}
	
}	

