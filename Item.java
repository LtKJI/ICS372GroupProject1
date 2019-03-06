package edu.metrostate.ics372groupproject1.scientificDataCollectionApp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {	
	@SerializedName("site_id")
	@Expose
	private String siteID;
	
	@SerializedName("reading_type")
	@Expose
	private String readingType;
	
	@SerializedName("reading_id")
	@Expose
	private String readingID;
	
	@SerializedName("reading_value")
	@Expose
	private double readingValue;
	
	@SerializedName("reading_date")
	@Expose
	private long readingDate;
	
	private String unit;
	
	public Item() {
		
	}
	
	public Item(String siteID, String readingType, String readingID, double readingValue, long readingDate) {
		this.siteID = siteID;
		this.readingType = readingType;
		this.readingID = readingID;
		this.readingValue = readingValue;
		this.readingDate = readingDate;	
	}

	public String getSiteID() {
		return siteID;
	}

	public void setSiteID(String siteID) {
		this.siteID = siteID;
	}

	public String getReadingType() {
		return readingType;
	}

	public void setReadingType(String readingType) {
		this.readingType = readingType;
	}

	public String getReadingID() {
		return readingID;
	}

	public void setReadingID(String readingID) {
		this.readingID = readingID;
	}

	public double getReadingValue() {
		return readingValue;
	}

	public void setReadingValue(double readingValue) {
		this.readingValue = readingValue;
	}

	public long getReadingDate() {
		return readingDate;
	}

	public void setReadingDate(long readingDate) {
		this.readingDate = readingDate;
	}
	
	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

		//To string method
		public String toString() {
			return "Site id = " + siteID + ":\nreading type = " + readingType + 
					"\nreading id = " + readingID + "\nreading value = " + readingValue + 
					"\nreading date = " + readingDate;
		}
}
