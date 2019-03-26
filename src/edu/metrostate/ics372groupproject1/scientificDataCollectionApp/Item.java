package edu.metrostate.ics372groupproject1.scientificDataCollectionApp;


import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @param
 * Item class represent the reading object of the site
 * It keeps track of the attributes relating to a particular 
 * reading at a specified site.
 *
 */

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
	
	@SerializedName("reading_unit")
	@Expose
	private String unit = "";
	
	@SerializedName("reading_date")
	@Expose
	private long readingDate;
	
	
	//The constructors of the Item class
	public Item() {
		
	}

	public Item(String siteID, String readingType, String unit, String readingID, double readingValue, long readingDate) {
		this.siteID = siteID;
		this.readingType = readingType;
		this.readingID = readingID;
		this.readingValue = readingValue;
		this.unit = unit;
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
	
	//defining equality among items
	public boolean equals(Object object) {
		Item nitem = null;
		if(object instanceof Item) {
			nitem = (Item)object;
		}
		boolean equalSiteID = this.siteID.equals(nitem.getSiteID());
		boolean equalReadingType = this.readingType.equals(nitem.getReadingType());
		boolean equalReadingID = this.readingID.equals(nitem.getReadingID());
		boolean equalReadingValue = this.readingValue == nitem.getReadingValue();
		//return true if equal and false other wise
		return equalSiteID && equalReadingType && equalReadingID && equalReadingValue;
	}
	
	//To string method
	public String toString() {
		return "{\nSite_id = " + siteID + "\nreading_type = " + readingType + 
				"\nreading_id = " + readingID + "\nreading_value = " + readingValue + 
				"\nreading_unit = "+ unit + "\nreading_date = " + readingDate +
				"\n\r}";
	}
	
	/**
	 * Check the date field, if no date in file
	 * replace 0 by imported date
	 */
	public long validateDate() {
		if(this.readingDate == 0) {
			Date date = new Date();
			this.readingDate = date.getTime();
		}
		return this.readingDate;
	}
	
	/**
	 * Provide a unit for item if it is absent
	 */
	public void ValidateUnit() {
		if(this.unit == null || this.unit.equals("")) {
			if(this.readingType.equals("Temperature")|| this.readingType.equals("temp")) {
				this.unit = "Fahrenheit";
			}else if(this.readingType.equals("Humidity")|| this.readingType.equals("humidity")){
				this.unit = "Percent";
			}else if(this.readingType.equals("Pressure")|| this.readingType.equals("bar_press")){
				this.unit = "Bar";
			}else if(this.readingType.equals("Particulate")|| this.readingType.equals("particulate")){
				this.unit = "PPM";
			}
			
		}
	}
}
