package fi.iki.elonen.example.fileserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import fi.iki.elonen.server.BasicServer;
public class StartupTest extends FileServerExampleTestBase {
	private static final String LINE_FEED_SPACE = "\n  ";
	private static final String LINE_FEED = "\n";
	private StringWriter canvas;
	private PrintWriter sloppyLogger;

	@Before
	public void init() throws Exception {
		canvas = new StringWriter();		
		sloppyLogger = new PrintWriter(canvas);
	}
	
	@Test
	public void returnsLicenseIfRequested_InLowerCase() {
		FileServerExample.showLicenseIfRequested(new String[]{"nothing", "-license", "nothing"}, 
				sloppyLogger);
		assertEquals(BasicServer.LICENSE,	 canvas.toString().trim());
	}
	
	@Test
	public void returnsLicenseIfRequested_InUpperCase() {
		FileServerExample.showLicenseIfRequested(new String[]{"-LICENSE", "nothing"}, 
				sloppyLogger);
		assertEquals(BasicServer.LICENSE,	 canvas.toString().trim());
	}
	
	@Test
	public void changesPortWhenRequested() {
		int expectedPort = 8090;
		assertEquals(expectedPort , FileServerExample.changePortIfRequested(new String[]{"8090"}, 3));
	}	
	
	@Test
	public void callingServeLogsHeaderAndParameters() throws Exception {
		FileServerExample.setSloppyLogger(sloppyLogger);
		BasicServer fileServerExample = new FileServerExample(0);
		
		Properties fakeHeader = new Properties();
		fakeHeader.put("Header1", "one");
		Properties fakeParameters = new Properties();
		fakeParameters.put("Parm2", "two");
		String uri = "/";
		
		fileServerExample.serve(uri, "GET", fakeHeader, fakeParameters);
		assertNotNull(fileServerExample.getPropertiesWriter());
		fileServerExample.getPropertiesWriter().writeProperties(fakeHeader, uri, sloppyLogger);
		String actual = canvas.toString().trim();
		
		assertTrue(actual.contains("GET '/' "));
		assertTrue(actual.contains("HDR: 'Header1' = 'one'"));
		assertTrue(actual.contains("PRM: 'Parm2' = 'two'"));
		assertTrue(actual.contains("/: 'Header1' = 'one'"));

	}
	
	private void verifySaysHowdy() {
		String actual = canvas.toString().trim();
		
		assertTrue(actual.contains(BasicServer.HOWDY_PART_ONE));
		assertTrue(actual.contains(BasicServer.NOW_SERVING_FILES_IN_PORT + "8090"));
		assertTrue(actual.contains(BasicServer.FROM));
		assertTrue(actual.contains(BasicServer.LOCAL_ABSOLUTE_PATH));
		assertTrue(actual.contains(BasicServer.HIT_ENTER_TO_STOP));
	}
	
}
