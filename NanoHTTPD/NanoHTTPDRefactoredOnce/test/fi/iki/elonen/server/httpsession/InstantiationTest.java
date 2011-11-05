package fi.iki.elonen.server.httpsession;

import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import fi.iki.elonen.example.NullServer;

//TODO Intermittent failure. Ugh. ourSession does not always report isAlive() 
public class InstantiationTest extends HTTPSessionBase {
  @Ignore
	@Test
	public void instantiationStartsThread() throws Exception {
	  ourSession = null;
		String sillyGetRequestStringWithTwoParms = "GET /?thing1=one&thing2=two\r\n";
		
		mockSocket = createSocket(sillyGetRequestStringWithTwoParms);
		ourSession = new HTTPSession(mockSocket, new NullServer());
		assertTrue(ourSession.getThread().isAlive());
		ourSession = null;
	}
}
