package fi.iki.elonen.server.httpsession;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

public class GetRequestParsingTest extends HTTPSessionBase {
	@Before
	public void init() throws Exception {
		String sillyGetRequestStringWithTwoParms = "GET /?thing1=one&thing2=two HTTP/1.1\r\n";
		mockSocket = createSocket(sillyGetRequestStringWithTwoParms);
		parser = new RequestParser(mockSocket);
		parsedRequest = parser.parseRequest();
	}
	
	@Test 
	public void canParseHttpMethod() throws Exception {
		assertEquals("GET", parsedRequest.getHttpMethod());
	}
	
	@Test
	public void canParseRequestUri() throws Exception {
		assertEquals("/", parsedRequest.getRequestUri());
	}
	
	@Test
	public void canParseHTTPType() {
		assertEquals("1.1", parsedRequest.getHTTPType());
	}

	@Test
	public void canParseParameters() {
		Properties requestParameters = parsedRequest.getRequestParameters();
		assertEquals("one", requestParameters.get("thing1"));
		assertEquals("two", requestParameters.get("thing2"));
	}
	
	@Test
	public void canDecodeUriIfNoRequestParameters() throws Exception {
		String input = "GET /\r\n";
		String uri = parser.decodeUri(input);
		assertEquals("GET /\r\n", uri);
	}
}
