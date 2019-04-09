package Test;

import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.Item;
import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.Readings;
import junit.framework.TestCase;


public class ReadingsTest extends TestCase{
	
	ArrayList<Item> list = new ArrayList<Item>();
	Item item1 = new Item("1258", "humidity", "Percent", "22222", 10, 0);
	Item item2 = new Item("12345", "Temp", "Fahrenheit", "12547g", 25, 1515354694451l);
	
	
	public void testReadings() {
		Readings read = new Readings();
		assertNotNull("No object created", read);
	}

	public void testGetReadings() throws Exception{
		Readings read = new Readings();
		list.add(item1);
		list.add(item2);
		read.setReadings(list);
		assertTrue(read.getReadings().contains(item1));
		assertEquals("The reading has the same item as list", read.getReadings().get(0), list.get(0));
	}

	public void testToString() {
		Readings read = new Readings();
		list.add(item2);
		read.setReadings(list);
		String expected = item2.toString();		
		assertNotEquals("Wrong string output", expected, read.toString());
		
		expected += "\n\n";
		assertEquals("the strings should equal", expected, read.toString());
	}

}
