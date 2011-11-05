package fi.iki.elonen.example.fileserver;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import org.junit.After;

import fi.iki.elonen.example.ExampleTestBase;
import fi.iki.elonen.server.httpsession.Response;

public class FileServerExampleTestBase extends ExampleTestBase {
	protected InputStream dataStream;
	protected Response response;
	protected FileServerExample fileServer;
	protected boolean allowDirectoryListing;
	protected Properties header;
	
	protected Properties createFakeHeader() {
		header = new Properties();
		header.setProperty("accept-language", "en-us");
		header.setProperty("connection", "keep-alive");
		header.setProperty("accept", "application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
		header.setProperty("host", "localhost:8092");
		header.setProperty("accept-encoding", "gzip, deflate");
		header.setProperty("user-agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_8; en-us) AppleWebKit/531.9 (KHTML, like Gecko) Version/4.0.3 Safari/531.9");
	
		return header;
	}

	@SuppressWarnings("serial")
	protected File createFakeDirectoryThatAlwaysExists(String dir) {
		return new File(dir) {
			public boolean exists() {
				return true;
			}
			
			public boolean isDirectory() {
				return true;
			}
		};
	}

	@After
	public void tearDown() throws Exception{
		if (fileServer != null) fileServer.stop();
		fileServer = null;
	}	
}
