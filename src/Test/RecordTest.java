package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.Record;
import edu.metrostate.ics372groupproject1.scientificDataCollectionApp.Study;
import junit.framework.TestCase;

public class RecordTest extends TestCase {

	Record record1=Record.getInstance();
	Record record2=Record.getInstance();
	
	Study study1=new Study("1","StudyOne");
	Study study2=new Study("2","StudyTwo");
	Study study3=new Study("3","StudyThree");
	
	public void testRecord() {
		System.out.println(record1);
		System.out.println(record2);
		assertSame(record1,record2);
	}
	
	public void testClearStudy() {
		record1.addStudy(study1);
		record1.addStudy(study2);
		record1.addStudy(study3);
		
		assertEquals(study1,record1.getStudy("1", "StudyOne"));
		assertEquals(study2,record1.getStudy("2", "StudyTwo"));
		assertEquals(study3,record1.getStudy("3","StudyThree"));
		
		record1.clearStudy();
		
		assertNull(record1.getStudy("1", "StudyOne"));
		assertNull(record1.getStudy("2", "StudyTwo"));
		assertNull(record1.getStudy("3","StudyThree"));
	}

}
