package Test;

import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.Item;
import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.Site;
import junit.framework.TestCase;

public class SiteTest extends TestCase{

	public void testGetSiteID() {
//		fail("Not yet implemented");
	}

	public void testSetSiteID() {
//		fail("Not yet implemented");
	}

	public void testGetItems() {
//		fail("Not yet implemented");
	}

	public void testSetRecording() {
//		fail("Not yet implemented");
	}

	public void testSetItems() {
//		fail("Not yet implemented");
	}

	public void testAddItem() {
//		fail("Not yet implemented");
	}

	public void testIsRecording() {
//		fail("Not yet implemented");
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

}
