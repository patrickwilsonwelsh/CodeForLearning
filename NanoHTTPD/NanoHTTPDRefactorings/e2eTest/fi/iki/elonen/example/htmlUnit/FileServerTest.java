package fi.iki.elonen.example.htmlUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import fi.iki.elonen.FileServer;
import fi.iki.elonen.example.ThroughTheBrowserBaseFixture;

public class FileServerTest extends ThroughTheBrowserBaseFixture {
	private static final String SECOND_LEVEL_FOLDER_2 = "secondLevelFolder_2";
	private static final String ROOT_LEVEL_FOLDER = "rootLevelFolder/";
	private static final String RESOURCES_FOLDER = "/resources/";
	
	@Before
	public void setup() throws Exception {
		port = 8089;
		launchFileServerExample(port);
		webClient = new WebClient();
	}
	
	@Test
	public void canOpenResourcesFolder() throws Exception {
		page = webClient.getPage(HTTP_LOCALHOST + port +   RESOURCES_FOLDER);
		assertResourcesFileIsAsExpected();		
	}

	@Test
	public void canClickNestedFolderLink_AndOpenIndexDotHtmFileAtSecondDirectoryLevel() throws Exception {
		page = webClient.getPage(HTTP_LOCALHOST + port +   RESOURCES_FOLDER);
		HtmlElement rootLevelFolderLink = page.getAnchorByText(ROOT_LEVEL_FOLDER);
		
		assertIndexFileContentsContainText(rootLevelFolderLink.<HtmlPage>click().asXml(), "Hey There, this is body text!");
	}
	
	@Test
	public void canOpenIndexDotHtmlFileAtThirdLevelFolder() throws Exception {
		page = webClient.getPage(HTTP_LOCALHOST + port +   RESOURCES_FOLDER + ROOT_LEVEL_FOLDER + "secondLevelFolder_1" + "/thirdLevelFolder_1/");
		assertIndexFileContentsContainText(page.asXml(), "Hey There, this is body text!");;
	}
	
	@Test
	public void canListContentsOfFile()  throws Exception {
		TextPage textPage = webClient.getPage(HTTP_LOCALHOST +
				port +   RESOURCES_FOLDER + ROOT_LEVEL_FOLDER + SECOND_LEVEL_FOLDER_2 + "/testFile1.txt");
		assertEquals("test text one", textPage.getContent());	
	}
	
	@Test
	public void canClickParentDirectory_DoubleDotsLink() throws Exception {
		page = webClient.getPage(HTTP_LOCALHOST + port +   RESOURCES_FOLDER);
		HtmlElement rootLevelFolderLink = page.getAnchorByText("..");
		
		assertRootLevelFolderIsAsExpected(rootLevelFolderLink);
		
	}

	@Test
	public void canListDirectoryContents() throws Exception {
		page = webClient.getPage(HTTP_LOCALHOST +
				port +   RESOURCES_FOLDER + ROOT_LEVEL_FOLDER + SECOND_LEVEL_FOLDER_2);
		
		assertTrue(page.getPage().asText().contains("Directory /resources/rootLevelFolder/secondLevelFolder_2/"));
		assertTrue(page.getPage().asText().contains(".."));
		assertTrue(page.getPage().asText().contains("testFile1.txt  (13 bytes)"));
		assertTrue(page.getPage().asText().contains("testFile2.txt  (13 bytes)"));
	}
	
	@Test
	public void canReturnErrorResponseIf_NotADirectory() throws Exception {
	try {
		page = webClient.getPage(HTTP_LOCALHOST + 
				port + RESOURCES_FOLDER + SECOND_LEVEL_FOLDER_2 
				+ "/nonExistentDirectory/thing");
		} catch (Exception expected) {
			assertTrue(expected.getMessage().contains("404 Not Found"));
		}		
	}

	@Test
	public void canReturnErrorReponseIf_FileIsNotFound() throws Exception {
		try {
		page = webClient.getPage(HTTP_LOCALHOST +
				port +   RESOURCES_FOLDER + ROOT_LEVEL_FOLDER + SECOND_LEVEL_FOLDER_2 
				+ "/nonExistentfile.txt");
		} catch (Exception expected) {
			assertTrue(expected.getMessage().contains("404 Not Found"));
		}
	}
	
	@Test
	public void canReturnSecurityErrorResponseIf_AttemptingToAccessParentDirectory() throws Exception {
		try {
			page = webClient.getPage(HTTP_LOCALHOST + port + "/resources/..");
			} catch (Exception expected) {
				assertTrue(expected.getMessage().contains("403 Forbidden"));
			}
	}
	

	private void assertRootLevelFolderIsAsExpected(
			HtmlElement rootLevelFolderLink) throws IOException {
		assertTrue((rootLevelFolderLink.<HtmlPage>click()).asXml().contains("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"));
		assertTrue((rootLevelFolderLink.<HtmlPage>click()).asXml().contains("<html>"));
		assertTrue((rootLevelFolderLink.<HtmlPage>click()).asXml().contains("<head/>"));
		assertTrue((rootLevelFolderLink.<HtmlPage>click()).asXml().contains("<body>"));
		assertTrue((rootLevelFolderLink.<HtmlPage>click()).asXml().contains("<h1>"));
		assertTrue((rootLevelFolderLink.<HtmlPage>click()).asXml().contains("Directory"));
		assertTrue((rootLevelFolderLink.<HtmlPage>click()).asXml().contains("<a href=\"/.classpath\">"));
				
		assertTrue((rootLevelFolderLink.<HtmlPage>click()).asXml().contains("<a href=\"/.project\">"));
		assertTrue((rootLevelFolderLink.<HtmlPage>click()).asXml().contains("<a href=\"/bin/\">"));
		assertTrue((rootLevelFolderLink.<HtmlPage>click()).asXml().contains("<a href=\"/e2eTest/\">"));
		assertTrue((rootLevelFolderLink.<HtmlPage>click()).asXml().contains("<a href=\"/lib/\">"));
		assertTrue((rootLevelFolderLink.<HtmlPage>click()).asXml().contains("<a href=\"/resources/\">"));
		assertTrue((rootLevelFolderLink.<HtmlPage>click()).asXml().contains("<a href=\"/src/\">"));
		assertTrue((rootLevelFolderLink.<HtmlPage>click()).asXml().contains("<a href=\"/test/\""));
		assertTrue((rootLevelFolderLink.<HtmlPage>click()).asXml().contains("</body>"));
		assertTrue((rootLevelFolderLink.<HtmlPage>click()).asXml().contains("</html>"));
	}

	private void assertResourcesFileIsAsExpected() {
		assertTrue(page.asXml().contains("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"));		
		assertTrue(page.asXml().contains("<html>"));		
		assertTrue(page.asXml().contains("<head/>"));		
		assertTrue(page.asXml().contains("<body>"));		
		assertTrue(page.asXml().contains("<h1>"));		
		assertTrue(page.asXml().contains("Directory /resources/"));		
		assertTrue(page.asXml().contains("<a href=\"/\">"));		
		assertTrue(page.asXml().contains("<a href=\"/resources/example.txt\">"));		
		assertTrue(page.asXml().contains("<a href=\"/resources/rootLevelFolder"));		
		assertTrue(page.asXml().contains("</body>"));		
		assertTrue(page.asXml().contains("</html>"));
	}
	
	private void assertIndexFileContentsContainText(String page, String expectedBodyText) {
		assertTrue(page.contains("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"));		
		assertTrue(page.contains("<html>"));		
		assertTrue(page.contains("<head/>"));		
		assertTrue(page.contains("<body>"));		
		assertTrue(page.contains("<header/>"));		
		assertTrue(page.contains(expectedBodyText));		
		assertTrue(page.contains("</body>"));		
		assertTrue(page.contains("</html>"));
	}
	
	 @After
	  public void killFileServer() throws Exception {
	     FileServer.kill();
	  }
}
