package fi.iki.elonen.server.httpsession;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.junit.Test;


public class ResponseSendingTest {
	private static final String MIME_TYPE = "mytype";
	private static final String HTTP_STATUS = "mystatus";
	private static final String INPUT_DATA = "blah";

	@Test 
	public void staticMockOfSocketToConfirmResponseResults() {
		final ByteArrayOutputStream responseToWriteResultTo = new ByteArrayOutputStream();
		
		 Socket mockSocket = new Socket() {
			@Override
			public OutputStream getOutputStream() throws IOException {
				return responseToWriteResultTo;
			}
		 };
		 
		 ResponseSender sender = new ResponseSender(mockSocket);
		 InputStream inputStream = new ByteArrayInputStream(INPUT_DATA.getBytes());
		 sender.sendResponse(HTTP_STATUS, MIME_TYPE, null, inputStream);
		 
		 assertTrue(responseToWriteResultTo.toString().contains(INPUT_DATA));
		 assertTrue(responseToWriteResultTo.toString().contains(HTTP_STATUS));
		 assertTrue(responseToWriteResultTo.toString().contains(MIME_TYPE));
	}

}
