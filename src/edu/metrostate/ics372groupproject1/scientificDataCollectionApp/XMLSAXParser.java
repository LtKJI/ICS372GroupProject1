package edu.metrostate.ics372groupproject1.scientificDataCollectionApp;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class XMLSAXParser {
	public static void main(String[] args) {
		SAXParserFactory saxParserFactory=SAXParserFactory.newInstance();
		try {
			SAXParser saxParser=saxParserFactory.newSAXParser();
			XMLSAXParserHandler handler=new XMLSAXParserHandler();
			saxParser.parse(new File("C:\\Users\\sashi\\Desktop\\SASHI METROSTATE\\ICS 372 - OOAD\\Group Assignment 2\\example.xml"),handler);
			
			//Get Item List
			List<Item> items=handler.getItemList();
			Study study=handler.getStudy();
			
			//print Study information
			System.out.println(study.toString());
			//print Item information
			for(Item item:items) {
				System.out.println("------------");
				System.out.println(item);
				System.out.println("Unit:" +item.getUnit());
				
			}		
		}
		catch (ParserConfigurationException | SAXException | IOException e) {
	        e.printStackTrace();
	    }
		
	}
}
