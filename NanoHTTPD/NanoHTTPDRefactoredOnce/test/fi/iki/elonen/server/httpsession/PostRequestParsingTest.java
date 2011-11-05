package fi.iki.elonen.server.httpsession;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

public class PostRequestParsingTest extends HTTPSessionBase {
  
	@Before
	public void init() throws Exception {
		String sillyGetRequestStringWithTwoParms = "POST /?thing1=one&thing2=two HTTP/1.1\r\n" +
											"Content-Length:32\r\n" +
											"From: frog@jmarshall.com\r\n" +
											"User-Agent: HTTPTool/1.0\r\n" +
											"Content-Type: application/x-www-form-urlencoded\r\n" +
											" \r\n" +
											"home=Cosby&favorite+flavor=flies\r\n";
		
		mockSocket = createSocket(sillyGetRequestStringWithTwoParms);
		parser = new RequestParser(mockSocket);
		parsedRequest = parser.parseRequest();
	}
	
	@Test
	public void canParseHttpMethod() throws Exception {
		assertEquals("POST", parsedRequest.getHttpMethod());
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
	public void canParseAllHeaderParameters() {
		Properties headerParameters = parsedRequest.getHeaderParameters();
		assertEquals("32", headerParameters.get("content-length"));
		assertEquals("frog@jmarshall.com", headerParameters.get("from"));
		assertEquals("HTTPTool/1.0", headerParameters.get("user-agent"));
		assertEquals("application/x-www-form-urlencoded", headerParameters.get("content-type"));
	}
	
	@Test
	public void canParsePostLine() {
		String postLine = parsedRequest.getPostLine();
		assertEquals("home=Cosby&favorite+flavor=flies", postLine);
		
		Properties requestParameters = parsedRequest.getRequestParameters();
		assertEquals("Cosby", requestParameters.getProperty("home"));
		assertEquals("flies", requestParameters.getProperty("favorite flavor"));
	}
}
