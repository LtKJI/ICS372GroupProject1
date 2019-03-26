package Test;

import java.util.Date;

import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.Item;
import junit.framework.TestCase;

public class ItemTest extends TestCase {

	public void testGetSiteID() {
		Item item1 = new Item("12345", "Temp", "", "12547g", 25d, 0);
		assertEquals("12345", item1.getSiteID());
		assertNotNull("site ID is not null", item1.getSiteID());
		Item item2 = new Item(null, "Temp", "", "12547g", 25d, 0);
		assertNull(item2.getSiteID());
	}

	public void testGetReadingType() {
		Item item1 = new Item();
		assertEquals("Reading type is null", null, item1.getReadingType());
		assertTrue(item1.getReadingType() == null);
	}

	public void testSetReadingType() {
		Item item1 = new Item();
		String s = "Snow Fall";
		item1.setReadingType(s);
		assertEquals(s, item1.getReadingType());
	}

	public void testGetReadingID() {
		Item item1 = new Item();
		assertNull(item1.getReadingID());
	}

	public void testSetReadingID() {
		Item item1 = new Item("12345", null, null, null, 5, 0);
		item1.setReadingID("new ID");
		assertTrue(item1.getReadingID().equals("new ID"));
		assertFalse(item1.getReadingID().equals("12345"));
	}

	public void testGetReadingValue() {
		Item item1 = new Item("12345", null, null, null, 5, 0);
		assertTrue(item1.getReadingValue() == 5);
		assertFalse(item1.getReadingValue() == 3);
	}

	public void testSetReadingValue() {
		Item item1 = new Item("12345", "Rain", "Inch", "123", 5, 0);
		item1.setReadingValue(10);
		assertTrue(item1.getReadingValue() == 10);
		assertFalse(item1.getReadingValue() == 5);
	}

	public void testGetReadingDate() {
		Item item1 = new Item("12345", null, null, null, 5, 0);
		assertTrue(item1.getReadingDate() == 0);
		item1.validateDate();
		assertFalse(item1.getReadingDate() == 0);
	}

	public void testSetReadingDate() {
		Item item1 = new Item("12345", null, null, null, 5, 0);
		Date date = new Date();
		long today = date.getTime();
		item1.setReadingDate(today);
		assertEquals(today, item1.getReadingDate());
	}
	
	public void testdateIsValidatedToTodaysDate() {
		Item item1 = new Item("12345", "Temp", "", "12547g", 25d, 0);
		Date date = new Date();
		long expected = date.getTime();
		long actual = item1.validateDate();
		assertEquals(expected, actual);
		//reading date should not be alter
		long today = date.getTime();
		Item item2 = new Item("12345", "Temp", "","12547g",25d, today);
		assertEquals(today, item2.validateDate());
	}
	public void testValidateUnitForTemparatureReading() {
		Item item1 = new Item("12345", "Temperature", "", "12547g", 25d, 0);
		item1.ValidateUnit();
		assertEquals("Fahrenheit", item1.getUnit());
		Item item2 = new Item("12345", "temp", "", "12547g", 25d, 0);
		item2.ValidateUnit();
		assertEquals("Fahrenheit", item2.getUnit());
	}
	
	public void testValidateNullUnitOfReading() {
		Item item1 = new Item("12345", "Humidity", null, "12547g", 25d, 0);
		item1.ValidateUnit();
		assertEquals("Percent", item1.getUnit());
	}
	public void testValidateUnitForHumidityReading() {
		Item item1 = new Item("12345", "Humidity", "", "12547g", 25d, 0);
		item1.ValidateUnit();
		assertEquals("Percent", item1.getUnit());
		Item item2 = new Item("12345", "humidity", "", "12547g", 25d, 0);
		item2.ValidateUnit();
		assertEquals("Percent", item2.getUnit());
	}
	
	public void testValidateUnitForParticulateReading() {
		Item item1 = new Item("12345", "particulate", null, "12547g", 25d, 0);
		item1.ValidateUnit();
		assertEquals("PPM", item1.getUnit());
		Item item2 = new Item("12345", "Particulate", "", "12547g", 25d, 0);
		item2.ValidateUnit();
		assertEquals("PPM", item2.getUnit());
	}

	public void testValidateUnitForPressureReading() {
		Item item1 = new Item("12345", "Pressure", "", "12547g", 25d, 0);
		item1.ValidateUnit();
		assertEquals("Bar", item1.getUnit());
		Item item2 = new Item("12345", "bar_press", "", "12547g", 25d, 0);
		item2.ValidateUnit();
		assertEquals("Bar", item2.getUnit());
	}
}
