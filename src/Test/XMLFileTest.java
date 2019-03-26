package Test;

import static org.junit.Assert.assertNotEquals;

import java.io.File;

import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.Readings;
import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.Study;
import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.XMLFile;
import junit.framework.TestCase;


public class XMLFileTest extends TestCase{
	
	XMLFile xmlfile = new XMLFile();
	File testFile = new File("../ICS372GroupProject1/src/example.xml");
	Readings reading = new Readings();
	
	public void testNumberOfItemInReading() throws Exception{
		reading = xmlfile.readXMLFile(testFile);
		assertEquals(4, reading.getReadings().size());
		assertTrue("the reading should have 4 items in it", reading.getReadings().size() == 4);
	}

	public void testEqualityOfTheFirstReadingType() {
		reading = xmlfile.readXMLFile(testFile);
		String type = reading.getReadings().get(0).getReadingType();
		assertEquals("Temperature", type);
		assertTrue(reading.getReadings().get(1).getReadingType().equals("Humidity"));
	}

	public void testEqualityOfGetStudy() {
		xmlfile.readXMLFile(testFile);
		Study importedStudy = xmlfile.getStudy();
		assertTrue(importedStudy.getStudyName().equals("University of Science Environmental Study"));
		
		assertTrue(importedStudy.getStudyID().equals("485"));
		Study expectedStudy = new Study("485", "University of Science Environmental Study");
		assertEquals(expectedStudy, importedStudy);
		
		Study newStudy = new Study("123", "Test study");
		assertNotEquals(newStudy, importedStudy);
	}

}
