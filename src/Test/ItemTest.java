package Test;

import java.util.Date;

import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.Item;
import junit.framework.TestCase;

public class ItemTest extends TestCase {
	Item item1 = new Item("12345", "Temp", "", "12547g", 25, 0);
	Item item2 = new Item("12345", null, null, null, 5, 0);
	
	public void testGetSiteID() {
		assertEquals("12345", item1.getSiteID());
		assertNotNull("site ID is not null", item1.getSiteID());
		Item item2 = new Item(null, "Temp", "", "12547g", 25, 0);
		assertNull(item2.getSiteID());
	}

	public void testGetReadingType() {
		Item item = new Item();
		assertEquals("Reading type is null", null, item.getReadingType());
		assertTrue(item.getReadingType() == null);
	}

	public void testSetReadingType() {
		Item item = new Item();
		String s = "Snow Fall";
		item.setReadingType(s);
		assertEquals(s, item.getReadingType());
	}

	public void testGetReadingID() {
		Item item1 = new Item();
		assertNull(item1.getReadingID());
	}

	public void testSetReadingID() {
		item2.setReadingID("new ID");
		assertTrue(item2.getReadingID().equals("new ID"));
		assertFalse(item2.getReadingID().equals("12345"));
	}

	public void testGetReadingValue() {
		assertTrue(item2.getReadingValue() == 5);
		assertFalse(item2.getReadingValue() == 3);
	}

	public void testSetReadingValue() {
		Item item1 = new Item("12345", "Rain", "Inch", "123", 5, 0);
		item1.setReadingValue(10);
		assertTrue(item1.getReadingValue() == 10);
		assertFalse(item1.getReadingValue() == 5);
	}

	public void testGetReadingDate() {
		assertTrue(item2.getReadingDate() == 0);
		item2.validateDate();
		assertFalse(item2.getReadingDate() == 0);
	}

	public void testSetReadingDate() {
		Date date = new Date();
		long today = date.getTime();
		item2.setReadingDate(today);
		assertEquals(today, item2.getReadingDate());
	}
	
	public void testdateIsValidatedToTodaysDate() {
		Date date = new Date();
		long expected = date.getTime();
		long actual = item1.validateDate();
		assertEquals(expected, actual);
		//reading date should not be alter
		long today = date.getTime();
		Item item = new Item("12345", "Temp", "","12547g",25, today);
		assertEquals(today, item.validateDate());
	}
	public void testValidateUnitForTemparatureReading() {
		Item item1 = new Item("12345", "Temperature", "", "12547g", 25, 0);
		item1.ValidateUnit();
		assertEquals("Fahrenheit", item1.getUnit());
		Item item2 = new Item("12345", "temp", "", "12547g", 25, 0);
		item2.ValidateUnit();
		assertEquals("Fahrenheit", item2.getUnit());
	}
	
	public void testValidateNullUnitOfReading() {
		Item item1 = new Item("12345", "Humidity", null, "12547g", 25, 0);
		item1.ValidateUnit();
		assertEquals("Percent", item1.getUnit());
	}
	public void testValidateUnitForHumidityReading() {
		Item item1 = new Item("12345", "Humidity", "", "12547g", 25, 0);
		item1.ValidateUnit();
		assertEquals("Percent", item1.getUnit());
		Item item2 = new Item("12345", "humidity", "", "12547g", 25, 0);
		item2.ValidateUnit();
		assertEquals("Percent", item2.getUnit());
	}
	
	public void testValidateUnitForParticulateReading() {
		Item item1 = new Item("12345", "particulate", null, "12547g", 25, 0);
		item1.ValidateUnit();
		assertEquals("PPM", item1.getUnit());
		Item item2 = new Item("12345", "Particulate", "", "12547g", 25, 0);
		item2.ValidateUnit();
		assertEquals("PPM", item2.getUnit());
	}

	public void testValidateUnitForPressureReading() {
		Item item1 = new Item("12345", "Pressure", "", "12547g", 25, 0);
		item1.ValidateUnit();
		assertEquals("Bar", item1.getUnit());
		Item item2 = new Item("12345", "bar_press", "", "12547g", 25, 0);
		item2.ValidateUnit();
		assertEquals("Bar", item2.getUnit());
	}
	
	public void testTwoItemEquality() {
		Item item1 = new Item("12345", "Humidity", "percent", "12547g", 25, 0);
		Item item2 = new Item("12345", "bar_press", "", "12547g", 25, 0);
		assertEquals(false, item1.equals(item2));
		
		Item item3 = new Item("12345", "Humidity", "", "12547g", 25, 0);
		assertEquals(true, item1.equals(item3));
		
		assertFalse(item2.equals(item3));
		Item item4 = new Item("12345", "Humidity", "Percent", "12547g", 25, 0);
		assertTrue(item1.equals(item4));
	}
}
