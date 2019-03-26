package Test;


import java.io.File;

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
	String outFileName = "Test/outputFile";
	
	@Before public void initialize() {
		rd = new Readings();
		nSite = new Site("12513");
		nSite.addItem(nItem);
		nstudy = new Study("1", "TestStudy");
		nstudy.addSite(nSite);
	}
	
	@Test
	//test the read method of JSONFile
	public void testEqualityBetweenReadingsAndJSONFile() throws Exception{
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
	public void testLoadingState() throws Exception{
		Record mockRecord = Mockito.mock(Record.class);
		mockRecord.addStudy(nstudy);
		Mockito.verify(mockRecord).addStudy(nstudy);
		Mockito.when(mockRecord.get(Mockito.anyInt())).thenReturn(nstudy);
		JSONFile.loadState("testState");
		
	}
	@Test
	//test the write method of JSONFile
	public void testWriteToFile() throws Exception{
		Record mockRecord = Mockito.mock(Record.class);
//		JSONFile mockJsonFile = Mockito.mock(JSONFile.class, Mockito.CALLS_REAL_METHODS);
		mockRecord.addStudy(nstudy);
		Mockito.verify(mockRecord).addStudy(nstudy);
		Mockito.when(mockRecord.size()).thenReturn(1);
		assertEquals(1, mockRecord.size());
		
		
		
		
		Mockito.when(mockRecord.get(Mockito.anyInt())).thenReturn(nstudy);
		
//		JSONFile.writeToFile(mockRecord, outFileName);
//		Mockito.doCallRealMethod().when(mockJsonFile).writeToFile(Mockito.any(Record.class), 
//				Mockito.any(String.class));
		
//		Mockito.doNothing().when(mockJsonFile.writeToFile(mockRecord, outFileName));
//		JSONFile.writeToFile(mockRecord, outFileName);
	}

}
