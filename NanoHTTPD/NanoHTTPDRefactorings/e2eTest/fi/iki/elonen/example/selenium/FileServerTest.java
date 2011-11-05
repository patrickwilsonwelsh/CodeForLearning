package fi.iki.elonen.example.selenium;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FileServerTest extends ThroughSeleniumBaseFixture {
	@Test
	public void canListDirectoryContents() throws Exception {
		port = 8089;
		launchFileServerExample(port);
		selenium.open("http://localhost:" +
				port +
				"/resources/rootLevelFolder/secondLevelFolder_2");

		String directoryListing = "Directory /resources/rootLevelFolder/secondLevelFolder_2/" + "\n" + 
				".." + "\n" +
				"testFile1.txt  (13 bytes)" + "\n" +
				"testFile2.txt  (13 bytes)" ;
		assertEquals(directoryListing, selenium.getBodyText());
	}



}
