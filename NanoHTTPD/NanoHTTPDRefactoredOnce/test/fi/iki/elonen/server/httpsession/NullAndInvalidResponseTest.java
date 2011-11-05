package fi.iki.elonen.server.httpsession;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import fi.iki.elonen.example.NullServer;

public class NullAndInvalidResponseTest extends HTTPSessionBase{
	@Before
	public void init() throws Exception {
		String sillyGetRequestStringWithTwoParms = "GET /?thing1=one&thing2=two\r\n";
		mockSocket = createSocket(sillyGetRequestStringWithTwoParms);
		mockServer = new NullServer();
		ourSession = new HTTPSession(mockSocket, mockServer);
	}
	
	@Test
	public void nullResponseThrowsException() throws Exception {
		try {
			ourSession.handleResponse(new ParsedRequest());
		} catch (InterruptedException ie) {
			assertTrue(responseToWriteResultTo.toString().contains("SERVER INTERNAL ERROR: Serve() returned a null response."));
		}
	}
	
	@Test 
	public void nullStatusThrowsError() {
			ResponseSender sender = new ResponseSender(mockSocket);
			
		try {	
			sender.blowUpIfNullStatus(null);
			fail("Should have thrown Error on null status.");
		} catch (Error e) {
			assertTrue(e.getMessage().contains("sendResponse(): Status can't be null."));
		}
	}
}
