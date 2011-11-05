package fi.iki.elonen.server.httpsession;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.junit.Test;

public class InvalidRequestTest extends HTTPSessionBase {
	private ByteArrayInputStream firstRequestBytes;

	@Test
	public void errorsOnMissingUri() throws Exception {
		try {
			String missingUri = "GET";
			parseRequest(missingUri);
		} catch (InterruptedException ie) {
			assertTrue(responseToWriteResultTo.toString().contains("BAD REQUEST: Missing URI. Usage: GET /example/file.html"));
			assertTrue(responseToWriteResultTo.toString().contains("HTTP/1.0 400 Bad Request"));
		}
	}

	@Test
	public void errorsOnMissingRequest() throws Exception {
		try {
			String missingUri = "   ";
			parseRequest(missingUri);
		} catch (InterruptedException ie) {
			assertTrue(responseToWriteResultTo.toString().contains("BAD REQUEST: Syntax error. Usage: GET /example/file.html"));
			assertTrue(responseToWriteResultTo.toString().contains("HTTP/1.0 400 Bad Request"));
		}
	}
	
	private void parseRequest(String missingUri) throws IOException, Exception {
		mockSocket = createSocketForMissingUri(missingUri);
		parser = new RequestParser(mockSocket);
		parsedRequest = parser.parseRequest();
	}
	
	private Socket createSocketForMissingUri(String inputString) throws IOException {
		responseToWriteResultTo = new ByteArrayOutputStream();
		firstRequestBytes = new ByteArrayInputStream(inputString.getBytes());
		
		 Socket mockSocket = new Socket() {
			@Override
			public OutputStream getOutputStream() throws IOException {
				return responseToWriteResultTo;
			}
			@Override
			public InputStream getInputStream() throws IOException {
				return firstRequestBytes;
			}			 
		};
		
		return mockSocket;
	}	

}
