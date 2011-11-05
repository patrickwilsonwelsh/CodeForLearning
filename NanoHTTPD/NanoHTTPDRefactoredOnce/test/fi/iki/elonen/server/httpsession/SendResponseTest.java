package fi.iki.elonen.server.httpsession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

public class SendResponseTest extends HTTPSessionBase {
	private static final String MIME_TYPE = "mytype";
	private static final String HTTP_STATUS = "mystatus";
	private static final String INPUT_DATA = "blah";
	private InputStream dataInputStream;

	@Before
	public void init() throws Exception {
		String sillyGetRequestStringWithTwoParms = "GET /?thing1=one&thing2=two\r\n";
		mockSocket = createSocket(sillyGetRequestStringWithTwoParms);
		dataInputStream = new ByteArrayInputStream(INPUT_DATA.getBytes());
		responseSender = new ResponseSender(mockSocket);
	}
	
	@Test
	public void responseContainsHeaders_AndGMTLabel() {
		responseSender.sendResponse(HTTP_STATUS, MIME_TYPE, null, dataInputStream);
		assertTrue(responseToWriteResultTo.toString().contains("HTTP"));		
		assertTrue(responseToWriteResultTo.toString().contains("Content-Type:"));		
		assertTrue(responseToWriteResultTo.toString().contains("Date:"));
		assertTrue(responseToWriteResultTo.toString().contains("GMT"));
	}
	
	
	@Test
	public void sendsResponseWhenPassedNullHeader() throws IOException{
		responseSender.sendResponse(HTTP_STATUS, MIME_TYPE, null, dataInputStream);
		
		assertTrue(responseToWriteResultTo.toString().contains(INPUT_DATA));
		assertTrue(responseToWriteResultTo.toString().contains(HTTP_STATUS));
		assertTrue(responseToWriteResultTo.toString().contains(MIME_TYPE));
	}
	
	@Test
	public void whenGivenHeaderWhoseDateIsNull_InsertsDateInResponse() {
		Properties propsWithNullDate = new Properties();
		propsWithNullDate.remove("Date");
		assertEquals(null, propsWithNullDate.getProperty("Date"));
		responseSender.sendResponse(HTTP_STATUS, MIME_TYPE, propsWithNullDate, dataInputStream);
		assertTrue(responseToWriteResultTo.toString().contains("Date:"));
	}
	
	@Test
	public void echos_MultiPartHeader() {
		Properties multiPartHeader = new Properties();
		
		multiPartHeader.setProperty("Thing1", "One");
		multiPartHeader.setProperty("Thing2", "Two");
		multiPartHeader.setProperty("Thing3", "Three");
		
		responseSender.sendResponse(HTTP_STATUS, MIME_TYPE, multiPartHeader, dataInputStream);
		assertTrue(responseToWriteResultTo.toString().contains("Thing1: One"));
		assertTrue(responseToWriteResultTo.toString().contains("Thing2: Two"));
		assertTrue(responseToWriteResultTo.toString().contains("Thing3: Three"));
	}
}
