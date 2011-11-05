package fi.iki.elonen.server.httpsession;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.mockito.Mockito.*;

import org.junit.After;

import fi.iki.elonen.server.IServer;

public class HTTPSessionBase {
	protected HTTPSession ourSession;
	protected Socket mockSocket;
	protected ParsedRequest parsedRequest;
	protected RequestParser parser;
	protected ResponseSender responseSender;
	protected ByteArrayOutputStream responseToWriteResultTo;
	protected IServer mockServer;
	
	 protected Socket createSocket(String inputString) throws IOException {
	    mockSocket = mock(Socket.class);
	    
	    when(mockSocket.getInputStream()).thenReturn(getFirstRequestBytes(inputString));
	    when(mockSocket.getOutputStream()).thenReturn(responseToWriteResultTo);
	    mockSocket.close();
	    mockSocket.close();

	    verify(mockSocket, times(2)).close();
	    
	    return mockSocket;
	  }

  private ByteArrayInputStream getFirstRequestBytes(String inputString) {
    responseToWriteResultTo = new ByteArrayOutputStream();
    ByteArrayInputStream firstRequestBytes = new ByteArrayInputStream(inputString.getBytes());
    return firstRequestBytes;
  } 

	protected HTTPSession createThreadlessHTTPSession() {
		return new HTTPSession(mockSocket);
	}
	
	@After
	public void tearDown() {
		ourSession = null;
		mockSocket = null;
		mockServer = null;
	}
	
}
