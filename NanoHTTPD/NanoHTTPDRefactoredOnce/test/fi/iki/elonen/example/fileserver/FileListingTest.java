package fi.iki.elonen.example.fileserver;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;


public class FileListingTest extends FileServerExampleTestBase {
	private DirectoryLister lister; 

	@Before
	public void init() throws Exception {
		lister = new DirectoryLister(new File("/"), "/", createFakeHeader());
	}
	
	@Test
	public void errorsOnNonExistentFile() {
		response = lister.showFileInformation(new File("badName.txt"));
		dataStream = response.getData();
		assertEquals("Reading file failed.", convertStreamToString(dataStream));
		assertEquals("500 Internal Server Error", response.getStatus());
	}
	
	@Test
	public void displaysExistingTextFileContents() {
		lister.setFile(new File("./resources/example.txt"));	
		lister.setUri("/resources/example.txt");
		response = lister.showDirectoryOrFile();
		dataStream = response.getData();
		assertEquals("Test file contents.", convertStreamToString(dataStream));
		assertEquals("200 OK", response.getStatus());
	}
	
	@Test
	public void canSupportBasicSkippingWithinFiles() {
		header.setProperty("Range", "bytes=10");
		lister.setFile(new File("./resources/example.txt"));	
		lister.setUri("/resources/example.txt");
		response = lister.showDirectoryOrFile();
		dataStream = response.getData();
		assertEquals("contents.", convertStreamToString(dataStream));		
	}
	
	@Test
	public void canSupportBasicSkipping_AndDiscardsSecondRangeParameter() {
		header.setProperty("Range", "bytes=4-16");
		lister.setFile(new File("./resources/example.txt"));	
		lister.setUri("/resources/example.txt");
		response = lister.showDirectoryOrFile();
		dataStream = response.getData();
		assertEquals(" file contents.", convertStreamToString(dataStream));		
	}
}
