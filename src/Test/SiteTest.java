package Test;

import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.Item;
import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.Site;
import junit.framework.TestCase;

public class SiteTest extends TestCase{

	public void testGetSiteID() {
		Site newSite = new Site("mySite");
		String expected = "siteId";
		assertNotNull("Null ID", newSite.getSiteID());
		assertNotSame("Wrong site id", newSite.getSiteID(), expected);
	}

	public void testSetSiteID() {
		Site newSite = new Site();
		newSite.setSiteID("siteId");
		String expected = "siteId";
		assertNotNull("Null ID", newSite.getSiteID());
		assertEquals("Wrong site id", newSite.getSiteID(), expected);
	}

	public void testGetItems() {
		Site newSite = new Site("12345");
		Item item1 = new Item("12345", "Temp", "Fahrenheit", "12547g", 25, 0);
		Item item2 = new Item("12345", "Temp", "Fahrenheit", "12547g", 26, 2);
		newSite.setRecording(true);
		newSite.addItem(item1);
		newSite.addItem(item2);
		assertTrue(newSite.getItems().contains(item1) && newSite.getItems().contains(item2));
	}

	public void testSetRecording() {
		Site newSite = new Site("mySite");
		newSite.setRecording(true);
		assertTrue(newSite.isRecording());
		newSite.setRecording(false);
		assertFalse(newSite.isRecording());
	}

	public void testSetItems() {
		Site newSite = new Site("12345");
		Item item1 = new Item("12345", "Temp", "Fahrenheit", "12547g", 25, 0);
		Item item2 = new Item("12345", "Temp", "Fahrenheit", "12547j", 26, 2);
		newSite.setRecording(true);
		newSite.addItem(item1);
		newSite.addItem(item2);
		assertTrue(newSite.getItems().contains(item2));
		assertTrue(newSite.getItems().contains(item1));
	}

	public void testAddItem() {
		Site newSite = new Site("12345");
		Item item1 = new Item("12345", "Temp", "Fahrenheit", "12547g", 25, 0);
		Item item2 = new Item("12345", "Temp", "Fahrenheit", "12547g", 26, 2);
		newSite.setRecording(true);
		newSite.addItem(item1);
		newSite.addItem(item2);
		assertTrue(newSite.getItems().contains(item1) && newSite.getItems().contains(item2));
	}

	public void testIsRecording() {
		Site newSite = new Site("mySite");
		newSite.setRecording(true);
		assertTrue(newSite.isRecording());
		newSite.setRecording(false);
		assertFalse(newSite.isRecording());
	}
	
	public void testSitecontainsAnItem() {
		Item item1 = new Item("12345", "Humidity", "percent", "12547g", 25, 0);
		Site site = new Site("12345");
		site.setRecording(true);
		assertFalse("site should not contains item1", site.getItems().contains(item1));
		
		site.addItem(item1);
		assertTrue("site should contains item1", site.getItems().contains(item1));
	}
	
	public void testSiteAddDuplicateItem() {
		Item item1 = new Item("12345", "Humidity", "percent", "12547g", 25, 0);
		Item item2 = new Item("12345", "Humidity", "percent", "12547g", 25, 0);
		Site site = new Site("12345");
		site.setRecording(true);
		site.addItem(item1);
		assertEquals(1, site.getItems().size());
		
		assertEquals(true, item1.equals(item2));
		
		site.addItem(item2);
		assertEquals(1, site.getItems().size());
		
		Item item3 = new Item("12345", "Humidity", "", "111111", 25, 0);
		site.addItem(item3);
		
		assertEquals(2, site.getItems().size());
	}

	public void testEquals() {
		Site newSite = new Site("mySite");
		Site newSite2 = new Site("mySite");
		assertTrue(newSite.equals(newSite2));
		Site newSite3 = new Site("differentSite");
		assertFalse(newSite.equals(newSite3));
	}
	
	public void testToString() {
		Site newSite = new Site("12345");
		newSite.setRecording(true);
		Item item1 = new Item("12345", "Humidity", "percent", "12547g", 25, 0);
		newSite.addItem(item1);
		String expected = item1.toString() + "\n\r";
		assertEquals("Wrong string output", expected, newSite.toString());
	}
}
