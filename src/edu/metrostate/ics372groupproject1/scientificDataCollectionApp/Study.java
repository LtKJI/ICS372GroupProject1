package edu.metrostate.ics372groupproject1.scientificDataCollectionApp;

import java.util.ArrayList;

public class Study {
	private String studyID;
	private String studyName;
	
	
	public Study() {
		this.studyID="";
		this.studyName="";
	}
	
	public Study(String studyID, String studyName) {
		this.studyID=studyID;
		this.studyName=studyName;		
	}

	public String getStudyID() {
		return studyID;
	}

	public void setStudyID(String studyID) {
		this.studyID = studyID;
	}
	
	public String getStudyName() {
		return studyName;
	}

	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}	
	
	@Override
	public String toString() {
		return "Study ID: " + this.studyID+" Study Name: "+this.studyName;
		
	}
	
}
