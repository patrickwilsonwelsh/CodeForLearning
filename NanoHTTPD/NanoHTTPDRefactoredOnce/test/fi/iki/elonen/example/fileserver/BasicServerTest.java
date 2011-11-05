package fi.iki.elonen.example.fileserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

public class BasicServerTest extends FileServerExampleTestBase {

	
	@Before
	public void init() throws Exception {
		fileServer = new FileServerExample(0);
		allowDirectoryListing = true;
		response = fileServer.serveFile(new File("."), "GET /?thing1=one&thing2=two HTTP/1.1\r\n", new Properties(),
				allowDirectoryListing);
		dataStream = response.getData();
	}

	@Test
	public void canGetResponseData() throws IOException {
		assertEquals("Error 404, file not found.", convertStreamToString(dataStream));
	}
	
	@Test
	public void canGetStatus() {
		assertEquals("404 Not Found", response.getStatus());
	}
		
	@Test
	public void canGetMimeType() {
		assertEquals("text/plain", response.getMimeType());
	}
	
	@Test
	public void headerIsIgnored() {
		assertTrue(response.getHeader().isEmpty());
	}
	
	@Test
	public void removesAllURLArguments() {
		assertEquals("GET /", fileServer.getUri());
	}
	
}
