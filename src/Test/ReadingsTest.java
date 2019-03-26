package Test;

import java.util.ArrayList;

import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.Item;
import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.Readings;
import junit.framework.TestCase;


public class ReadingsTest extends TestCase{
	
	public void testReadings() {
		Readings read = new Readings();
		assertNotNull("No object created", read);
	}

	public void testGetReadings() {
		Readings read = new Readings();
		Item item1 = new Item();
		ArrayList<Item> list = new ArrayList<Item>();
		list.add(item1);
		read.setReadings(list);
		assertEquals("The reading was not included",item1, list.get(0));
	}

	public void testToString() {
		Readings read = new Readings();
		Item item1 = new Item("12345", "Temp", "", "12547g", 25, 0);
		ArrayList<Item> list = new ArrayList<Item>();
		list.add(item1);
		read.setReadings(list);
		String expected = "{\r\n" + 
				"   \"site_readings\":[\r\n" + 
				"      {\r\n" + 
				"         \"site_id\":\"12345\",\r\n" + 
				"	  \"reading_type\":\"Temp\",\r\n" + 
				"         \"reading_id\":\"12547g\",\r\n" + 
				"	  \"reading_value\": 25,\r\n" + 
				"	  \"reading_date\": 0\r\n" + 
				"      }" + 
				"   ]\r\n" + 
				"}";
		assertEquals("Wrong string output", expected, read.toString());
	}

}
