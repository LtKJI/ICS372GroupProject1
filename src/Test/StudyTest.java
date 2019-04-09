package Test;

import java.util.ArrayList;

import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.Item;
import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.Readings;
import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.Site;
import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.Study;
import junit.framework.TestCase;

public class StudyTest extends TestCase{

	public void testGetStudyName() {
		Study myStudy = new Study("12345", "myStudy");
		assertEquals("myStudy", myStudy.getStudyName());
	}

	public void testSetStudyName() {
		Study myStudy = new Study();
		myStudy.setStudyName("myStudy");
		assertEquals("myStudy", myStudy.getStudyName());
	}

	public void testGetStudyID() {
		Study myStudy = new Study("12345", "myStudy");
		assertEquals("12345", myStudy.getStudyID());
	}

	public void testSetStudyID() {
		Study myStudy = new Study();
		myStudy.setStudyID("12345");
		assertEquals("12345", myStudy.getStudyID());
	}
	
	public void testAddSite() {
		Study myStudy = new Study("12345", "myStudy");
		Site mySite1 = new Site("54321");
		myStudy.addSite(mySite1);
		assertEquals("The site isn't in the study", true, myStudy.getAllSite().contains(mySite1));
	}

	public void testSetAllToCollect() {
		Study myStudy = new Study("12345", "myStudy");
		Site mySite1 = new Site("54321");
		myStudy.addSite(mySite1);
		mySite1.setRecording(false);
		myStudy.startAllSiteCollection();
		for(Site s : myStudy.getAllSite()) {
			assertEquals("It is not recording", true, s.isRecording());
		}		
	}

	public void testEndAllSiteCollection() {
		Study myStudy = new Study("12345", "myStudy");
		Site mySite1 = new Site("54321");
		myStudy.addSite(mySite1);
		mySite1.setRecording(true);
		myStudy.endAllSiteCollection();
		for(Site s : myStudy.getAllSite()) {
			assertEquals("It is not recording", false, s.isRecording());
		}		
	}
	
	public void testSetSiteForReading() {
		Readings read = new Readings();
		Item item1 = new Item("12345", "Temp", "", "12547g", 25, 0);
		ArrayList<Item> list = new ArrayList<Item>();
		list.add(item1);
		read.setReadings(list);
		Study myStudy = new Study("123", "myStudy");
		myStudy.setSiteForReading(read);
		assertNotNull("There are no sites", myStudy.getSiteByID("12345"));
	}
	
	public void testGetSiteByID() {
		Study myStudy = new Study("12345", "myStudy");
		Site mySite1 = new Site("54321");
		myStudy.addSite(mySite1);
		assertNotNull("Site isn't int the study", myStudy.getSiteByID("54321"));
	}
	
	public void testGetAllSite() {
		Study myStudy = new Study("12345", "myStudy");
		Site mySite1 = new Site("54321");
		myStudy.addSite(mySite1);
		Site mySite2 = new Site("54322");
		myStudy.addSite(mySite2);
		assertTrue("Sites not included", myStudy.getAllSite().contains(mySite1) && myStudy.getAllSite().contains(mySite2));
	}
	
	public void testEquals() {
		Study myStudy = new Study("12345", "myStudy");
		Study myStudy2 = new Study("12345", "myStudy");
		assertTrue("The aren't equal", myStudy.equals(myStudy2));
	}
	
	public void testToString() {
		Study myStudy = new Study("12345", "myStudy");
		Site mySite1 = new Site("54321");
		myStudy.addSite(mySite1);
		mySite1.setRecording(true);
		Item item1 = new Item("54321", "Temp", "", "12547g", 25, 0);
		mySite1.addItem(item1);
		String expected = "\nStudy_ID: " + myStudy.getStudyID() +"\nStudy_Name: "+ myStudy.getStudyName() +"\n" + mySite1.toString() + "\n";
		assertEquals("Wrong string output", expected, myStudy.toString());
	}
	
}
