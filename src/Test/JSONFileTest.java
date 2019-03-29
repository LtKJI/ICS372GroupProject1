package Test;


import java.io.File;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.Item;
import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.JSONFile;
import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.Readings;
import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.Record;
import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.Site;
import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.Study;
import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class JSONFileTest extends TestCase{
	Readings rd;
	Item nItem = new Item( "12513","humidity","Percent", "48934j", 84, 1515354694451l);
	Site nSite;
	Study nstudy;
	Record mockRecord = Mockito.mock(Record.class);
	Record mockRecord1 = Mockito.mock(Record.class);
	String outFileName = "Test/outputStateFile";
	
	@Before public void initialize() {
		rd = new Readings();
		nSite = new Site("12513");
		nSite.setRecording(true);
		nSite.addItem(nItem);
		nstudy = new Study("1", "TestStudy");
		nstudy.addSite(nSite);
		
		//mock record class behaviors
		@SuppressWarnings("unchecked")
		Iterator<Study> mockiterator = Mockito.mock(Iterator.class);
		Mockito.when(mockRecord.iterator()).thenReturn(mockiterator);
		Mockito.when(mockiterator.hasNext()).thenReturn(true,false);
		Mockito.when(mockiterator.next()).thenReturn(nstudy);
		
	}
	
	@Test
	//test the read method of JSONFile
	public void testEqualityOfDataFromReadJson() throws Exception{
		File testJson = new File("../ICS372GroupProject1/src/Test/jsonTest.json");
		rd = JSONFile.readJSON(testJson);
		assertEquals(1, rd.getReadings().size());
		assertTrue(rd.getReadings().size() == 1);
		
		assertEquals(nItem, rd.getReadings().get(0));
		
		String actual = rd.getReadings().get(0).getSiteID();
		assertEquals("12513", actual);
		assertFalse(actual.equals("22222"));
		
		double value = rd.getReadings().get(0).getReadingValue();
		assertEquals(84d, value);
	}
	
	
	@Test
	//test the write method of JSONFile
	public void testStudyWrittenToFileAreEqual() throws Exception{
		//mock the record class
		mockRecord.addStudy(nstudy);
		Mockito.verify(mockRecord).addStudy(nstudy);
		
		//call the writeToFile method on mock record
		JSONFile.writeToFile(mockRecord, outFileName);
		
		assertEquals(0, mockRecord1.size());
	
		//testing load state from a file
		mockRecord1 = JSONFile.loadState(outFileName);
		int actual = mockRecord1.size();
		assertEquals(1, actual);
		
		//test the equality of nstudy and that of mockRecord1
		assertEquals(nstudy, mockRecord1.get(0));
		
		assertTrue(mockRecord1.get(0).getStudyID().equals("1"));
		
		assertEquals(nstudy.getStudyName(), mockRecord1.get(0).getStudyName());
		
		assertFalse(mockRecord1.get(0).getStudyName().equals("First Study"));
	}
	
	public void testloadStateStudyIsEqual() {
		Site loadedSite = mockRecord1.get(0).getAllSite().get(0);
		assertEquals(nItem, loadedSite);
		
		assertTrue(loadedSite.getSiteID().equals("12513"));
		
		Item loadedItem = loadedSite.getItems().get(0);
		assertEquals(nItem, loadedItem);
		
		assertTrue(loadedItem.getReadingType().equals("humidity"));
		
		assertEquals(nItem.getReadingValue(), loadedItem.getReadingValue());
	}
	
}
