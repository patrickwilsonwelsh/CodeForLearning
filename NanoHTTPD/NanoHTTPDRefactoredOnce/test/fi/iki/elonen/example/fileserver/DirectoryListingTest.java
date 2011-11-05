package fi.iki.elonen.example.fileserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class DirectoryListingTest extends FileServerExampleTestBase {
	private static final String HTML_HEADER_HEADER_BODY = "<html><header></header><body>";
	private static final String END_BODY_HTML = "</body></html>";
	private static final String HTML_BODY = "<html><body>";
	private static final String FOO_HTM = "foo.htm";
	private static final String INDEX_HTM = "index.htm";
	private static final String INDEX_HTML = "index.html";
	private static final String SLASH = System.getProperty(File.separator);
	private static final String DOT_SLASH = "." + SLASH;
	private static final String RESOURCE_FOLDER_CONTENTS = "<html><body><h1>Directory " +
	    SLASH +
			"</h1><br/><a href=\"/example.txt\">example.txt</a> &nbsp;<font size=2>(19 bytes)</font><br/><b><a href=\"/rootLevelFolder/\">rootLevelFolder/</a><br/></b>";
	private static final String THIRD_LEVEL_FOLDER_FOR_TEST = "resources" +
			"/" +
			"rootLevelFolder" +
			"/" +
			"secondLevelFolder_1" +
			"/" +
			"thirdLevelFolder_1";
	private static final String ROOT_LEVEL_FOLDER_FOR_TEST = "resources" +
			"/" +
			"rootLevelFolder";
	private static final String RESOURCES_FOLDER_FOR_TEST = "resources" +
			"/";
	private DirectoryLister lister;
	
	@Before
	public void init() throws Exception {
		File homeDirectory = new File(SLASH);
		String uri = SLASH;
		lister = new DirectoryLister(homeDirectory, uri, createFakeHeader());
		lister.setAllowDirectoryListing(true);
	}
	
	@Test
	public void redirectsAndAddsMissingSlashIfUriDoesNotEndWithSlash() throws Exception {
		String uriWithMissingEndingSlash = "/src";
		lister.setUri(uriWithMissingEndingSlash);
		
		lister.setHomeDirectory(createFakeDirectoryThatAlwaysExists("."));
		response = lister.showDirectoryOrFile();
		
		assertEquals(HTML_BODY +
				"Redirected: <a href=\"/src/\">/src/</a>" +
				END_BODY_HTML, convertStreamToString(response.getData()));
		assertEquals("301 Moved Permanently", response.getStatus());		
	}

	@Test
	public void errorsIfDirectoryListingNotAllowed() throws Exception {
		lister.setAllowDirectoryListing(false);
		
		response = lister.showDirectoryOrFile();
		dataStream = response.getData();
		assertEquals("403 Forbidden", response.getStatus());		
		assertEquals("FORBIDDEN: No directory listing.", convertStreamToString(dataStream));
	}
	
	@Test
	public void canAppendHtmlFile_ToFile() {
		assertEquals(DOT_SLASH + INDEX_HTML, lister.addIndexDotHtmlToUri(new File("."), INDEX_HTML).getPath());
		assertEquals(DOT_SLASH + INDEX_HTM, lister.addIndexDotHtmlToUri(new File("."), INDEX_HTM).getPath());
		assertEquals(DOT_SLASH + "whatever", lister.addIndexDotHtmlToUri(new File("."), "whatever").getPath());
	}
	
	@Test
	public void canDetectIndexDotHtmlFileInDirectory() {
		lister.setFile(new File(DOT_SLASH + THIRD_LEVEL_FOLDER_FOR_TEST));	
		assertTrue(lister.directoryContainsFileNamed(lister.getFile(), INDEX_HTML));
		assertFalse(lister.directoryContainsFileNamed(lister.getFile(), FOO_HTM));
	}
	
	@Test
	public void canDetectIndexDotHtmFileInDirectory() {
		lister.setFile(new File(DOT_SLASH + ROOT_LEVEL_FOLDER_FOR_TEST));	
		assertTrue(lister.directoryContainsFileNamed(lister.getFile(), INDEX_HTM));
		assertFalse(lister.directoryContainsFileNamed(lister.getFile(), FOO_HTM));
	}
	
	@Test
	public void canDisplayIndexFileContents_IfIndexDotHtmlFileFound() {
		lister.setFile(new File(DOT_SLASH + THIRD_LEVEL_FOLDER_FOR_TEST));		
		lister.setUri(THIRD_LEVEL_FOLDER_FOR_TEST);
		lister.setHomeDirectory(createFakeDirectoryThatAlwaysExists("."));
		response = lister.showDirectoryOrFile();
		dataStream = response.getData();
		
		assertEquals(DOT_SLASH + THIRD_LEVEL_FOLDER_FOR_TEST + SLASH + INDEX_HTML, lister.getFile().getPath());
		assertEquals(HTML_HEADER_HEADER_BODY +
				"Hey There, this is body text!" +
				END_BODY_HTML, convertStreamToString(dataStream));
	}
	
	@Test
	public void canDisplayIndexFileContents_IfIndexDotHtmFileFound() {
		lister.setFile(new File(DOT_SLASH + ROOT_LEVEL_FOLDER_FOR_TEST));	
		lister.setUri(ROOT_LEVEL_FOLDER_FOR_TEST);
		lister.setHomeDirectory(createFakeDirectoryThatAlwaysExists("."));
		
		response = lister.showDirectoryOrFile();
		dataStream = response.getData();
		
		assertEquals(DOT_SLASH + ROOT_LEVEL_FOLDER_FOR_TEST + SLASH + INDEX_HTM, lister.getFile().getPath());
		assertEquals(HTML_HEADER_HEADER_BODY +
				"Hey There, this is body text!" +
				END_BODY_HTML, convertStreamToString(dataStream));
	}
	
	@Test
	public void canDisplayRegularDirectoryContents() {
		lister.setFile(new File(DOT_SLASH + RESOURCES_FOLDER_FOR_TEST));	
		lister.setUri(RESOURCES_FOLDER_FOR_TEST);
		System.out.println(lister.getUri());
		
		response = lister.showDirectoryOrFile();
		dataStream = response.getData();
		String directoryContents = convertStreamToString(dataStream);
		
    System.out.println(directoryContents);
		assertEquals(RESOURCE_FOLDER_CONTENTS, directoryContents);
	}
	
}
