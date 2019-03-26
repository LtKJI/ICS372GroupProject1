package edu.metrostate.ics372groupproject1.scientificDataCollectionApp;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This class embody the input JSON file
 * An object which represent the collection of items
 *
 */

public class Readings {
	
	//class members declaration
	@SerializedName("site_readings")
	@Expose
	private ArrayList<Item> readings = new ArrayList<>();
	
	
	public Readings() {
		//empty constructor
	}
	
	public ArrayList<Item> getReadings() {
		return this.readings;
	}
	
	public void setReadings(ArrayList<Item> list) {
		this.readings = list;
	}
	
	//override to toString method
	@Override
	public String toString() {
		String text = "";
		for(Item i : readings) {
			text += i.toString() + "\n\n"; // add the next item as string to text
		}
		return  text;
	}

}
