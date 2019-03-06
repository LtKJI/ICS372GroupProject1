package edu.metrostate.ics372groupproject1.scientificDataCollectionApp;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class XMLSAXParserHandler extends DefaultHandler {

	//List to hold Item object
	private List<Item> itemList=null;
	private Item item=null;
	private Study study=null;
	private StringBuilder stringBuilder=null; //data
	
	//getter method for itemList
	public List<Item> getItemList(){
		return itemList;
	}
	public Study getStudy() {
		return study;
	}
	
	boolean bStudyID=false;
	boolean bStudyName=false;
	
	
	boolean bReadingType=false;
	boolean bReadingID=false;
	boolean bReadingValue=false;
	boolean bSiteID=false;
	boolean bReadingUnit=false;
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
		//New addition
		if(qName.equalsIgnoreCase("Study")) {
			String id=attributes.getValue("id");			
			//initialize Study object and set ID attribute
			study=new Study();
			study.setStudyID(id);
			bStudyName=true;
			
			
		}
		
		
		//End of new addition
		
		
		else if(qName.equalsIgnoreCase("Reading")) {
			
			String type=attributes.getValue("type");
			String id=attributes.getValue("id");
			
			//initialize item object and set ID and Type attributes
			item=new Item();
			item.setReadingID(id);
			item.setReadingType(type);
			
			//initialize list
			if(itemList==null) {
				itemList=new ArrayList<>();
			}			
		}
		else if (qName.equalsIgnoreCase("Value")) {
			//Set boolean values for fields, will be used in setting Item variables
			String unit=attributes.getValue("unit");
			item.setUnit(unit);
			bReadingValue=true;
		}
		else if(qName.equalsIgnoreCase("Site")) {
			bSiteID=true;
		}
		
		//create a data container
		stringBuilder=new StringBuilder();
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException{
		if(bStudyName) {
			study.setStudyName(stringBuilder.toString());
			bStudyName=false;
		}
		else if(bReadingValue) {
			item.setReadingValue(Double.parseDouble(stringBuilder.toString()));
			bReadingValue=false;
		}
		else if (bSiteID) {
			item.setSiteID(stringBuilder.toString());
			bSiteID=false;
		}
		
		if(qName.equalsIgnoreCase("Reading")) {
			itemList.add(item);
		}
		
	}
	
	
	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		stringBuilder.append(new String(ch, start, length));
	}
	
}
