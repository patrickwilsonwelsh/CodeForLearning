package fi.iki.elonen.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fi.iki.elonen.server.httpsession.HTTPSession;
import fi.iki.elonen.server.httpsession.HTTPSessionBase;


public class DecodeUriTest extends HTTPSessionBase {
	
	private String input = "GET /\r\n";
	private UriDecoder decoder = new UriDecoder();

	@Before
	public void init() throws Exception {
		
		mockSocket = createSocket(input);
		ourSession = new HTTPSession(mockSocket);		
	}
	
	@Test
	public void canDecodePercentEncodedString() throws Exception {
		assertEquals("an example string", decoder.decodePercentAndPlus("an+example%20string"));
	}
	
	@Test
	public void errorsOnBadPercentEncoding() throws Exception {
		try {
			decoder.decodePercentAndPlus("an%+%example%20%%2020of+bad+encoding");
		} catch (InterruptedException ie) {
			assertTrue(responseToWriteResultTo.toString().contains("BAD REQUEST: Bad percent-encoding."));
			assertTrue(responseToWriteResultTo.toString().contains("HTTP/1.0 400 Bad Request"));
		}
	}

}
