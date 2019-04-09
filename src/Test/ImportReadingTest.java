package Test;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.ImportReadingPanel;
import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.Record;
import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.Study;
import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class ImportReadingTest extends TestCase{
	
	ImportReadingPanel mockpanel = Mockito.mock(ImportReadingPanel.class);
	Study study1 = new Study("125", "test study");
	Study study2 = new Study("369", "test study two");
	Record mockrecord = Mockito.mock(Record.class);
	
	@Test
	public void testGetFileName() {
		Mockito.when(mockpanel.getFileName()).thenReturn("testFile");
		assertEquals("testFile", mockpanel.getFileName());
	}

	@Test
	public void testGetImportedStudy() {
		Mockito.when(mockpanel.getImportedStudy()).thenReturn(study1);
		assertEquals("125", mockpanel.getImportedStudy().getStudyID());
		
		assertEquals("test study", mockpanel.getImportedStudy().getStudyName());
	}
	
	@Before public void setup() {
		mockrecord.addStudy(study1);
		Mockito.verify(mockrecord).addStudy(study1);
		mockrecord.get(0);
		Mockito.verify(mockrecord).get(0);
		Mockito.when(mockrecord.get(0)).thenReturn(study1);
	}
	
	@Test
	public void testGetStudyFromRecord() {
		JFrame frame = Mockito.mock(JFrame.class);
		ImportReadingPanel panel = new ImportReadingPanel(frame, mockrecord);
		Study actual = panel.getStudyFromRecord(study1);
		assertEquals(study1, actual);
	}
}
