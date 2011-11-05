package fi.iki.elonen.server.httpsession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import org.junit.Test;


public class BadSocketTest extends HTTPSessionBase {

	@Test
	public void throwsIOE_IfSocketInputStreamIsNull() {
		 Socket mockSocket = new Socket() {
				@Override
				public InputStream getInputStream() throws IOException {
					return null;
				}
			 };
			 
	     try {
	 		parser = new RequestParser(mockSocket);
	 		parser.parseRequest();
	    	 fail("Did not throw IOE on bad socket input stream.");
	     } catch (Exception e) {
	    	 assertEquals("Could not retrieve input stream from socket.", e.getMessage());
	     }
			 
	}
}
