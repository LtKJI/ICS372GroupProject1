package edu.metrostate.ics372groupproject1.scientificDataCollectionApp;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Site {
	
	private String siteID;
	private boolean recording;
	//Creating collection class to store objects read from JSON file.
	@SerializedName("site_readings")
	@Expose
	private ArrayList<Item> allItems = new ArrayList<>();
	
	public Site() {
		recording = false;
	}
	public Site (String id) {
		this();
		siteID = id;
	}
	
	public String getSiteID() {
		return siteID;
	}

	public void setSiteID(String siteID) {
		this.siteID = siteID;
	}

	public ArrayList<Item> getItems() {
		return allItems;
	}

	public void setRecording(boolean bool) {
		this.recording = bool;
	}
	
	public void setItems(ArrayList<Item> items) {
		for(Item i : items) {
			if (i.getReadingID() == this.siteID) {
				allItems.add(i);
			}
		}
	}
	
	/**
	 * @param
	 * addItem: takes a single Item as a parameter, adds it to 
	 * the instance of site's list of items
	 * @return
	 * return true if the new item is added to site
	 */
	public boolean addItem(Item i) {
		if(!allItems.contains(i) && recording) {
			allItems.add(i);
		}
		return allItems.contains(i);
	}
	
	/**
	 * @param
	 * addReadings: takes a Readings as a parameter, add readings to 
	 * the instance of site list of items
	 * @return
	 * return true if the new item is added to site
	 */
	public boolean addReadings(Readings readings) {
		boolean result = false;
		if(this.recording) {
			for(Item item : readings.getReadings()) {
				if(item.getSiteID() != null && item.getSiteID().equals(this.siteID) && !this.allItems.contains(item)) {
					allItems.add(item);
					result = allItems.contains(item);
				}
			}
		}
		return result;
	}
	
	/**
	 * @param
	 * return the status of a site either start collecting
	 * or end collecting
	 * @return
	 * return true if start collecting and false if collection ended
	 */
	public boolean isRecording() {
		return this.recording;
	}
	
	/**
	 * @param
	 * compare two sites for equality
	 * @return
	 * return true if equal and false other wise
	 */
	public boolean equals(Object object) {
		Site nsite = null;
		if(object instanceof Site) {
			nsite = (Site)object;
		}
		//true if both siteID field are equal
		return this.siteID.equals(nsite.getSiteID());
	}
	
	//to string method 
	public String toString() {
		String text = "";
		for(Item i: allItems) {
			text += i.toString() + "\n\r";
		}
		return text;
	}
	
	//return true is the site has not readings
	public boolean isEmpty() {
		return allItems.isEmpty();
	}
}