package fi.iki.elonen.example.fileserver;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;


public class ExceptionTest extends FileServerExampleTestBase {

	@Before
	public void init() throws Exception {
		fileServer = new FileServerExample(0);
		allowDirectoryListing = true;
	}

	@Test
	public void prohibitsExitFromCurrentDirectory_IfUriStartsWithParentDirectorySymbol() {
		response = fileServer.serveFile(new File("."), "GET ../ \r\n", new Properties(),
				allowDirectoryListing);
		dataStream = response.getData();
		assertEquals("FORBIDDEN: Won't serve ../ for security reasons.", convertStreamToString(dataStream));
		assertEquals("403 Forbidden", response.getStatus());
	}
	
	@Test
	public void prohibitsExitFromCurrentDirectory_IfUriEndsWithParentDirectorySymbol() {
		response = fileServer.serveFile(new File("."), "GET /.. \r\n", new Properties(),
				allowDirectoryListing);
		dataStream = response.getData();
		assertEquals("FORBIDDEN: Won't serve ../ for security reasons.", convertStreamToString(dataStream));
		assertEquals("403 Forbidden", response.getStatus());	
		}
	
	@Test
	public void probitsExitFromCurrentDirectory_IfUriIncludesEmbedded() {
		response = fileServer.serveFile(new File("."), "GET /../../ \r\n", new Properties(),
				allowDirectoryListing);
		dataStream = response.getData();
		assertEquals("FORBIDDEN: Won't serve ../ for security reasons.", convertStreamToString(dataStream));
		assertEquals("403 Forbidden", response.getStatus());	
	}
	
	@Test
	public void blowsUpIfDirectoryRequestIsJunk() {
		response = fileServer.serveFile(new File("randomjunk"), "GET / \r\n", new Properties(),
				allowDirectoryListing);
		dataStream = response.getData();
		assertEquals("INTERNAL ERROR: serveFile(): given homeDirectory is not a directory.", convertStreamToString(dataStream));
		assertEquals("500 Internal Server Error", response.getStatus());
	}

}
