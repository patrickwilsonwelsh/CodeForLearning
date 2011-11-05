package fi.iki.elonen.example.helloserver;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fi.iki.elonen.example.ExampleTestBase;
import fi.iki.elonen.server.httpsession.Response;

public class ServeTest extends ExampleTestBase {
	private HelloServerExample helloServer;
	private Response initialResponse;
	private InputStream dataStream;
	
	@Before
	public void init() throws Exception {
		helloServer = new HelloServerExample();
	}

	@Test
	public void canServeInitialSubmitForm() throws Exception {
		initialResponse = helloServer.serve("/", "GET", null, new Properties());
		dataStream = initialResponse.getData();
		
		assertEquals("<html><body><h1>Hello server</h1><form action='?' method='get'>  " +
				"<p>Your name: <input type='text' name='username'></p>  " +
				"<p><input id='submit' type='submit' value='Submit'></p></form></body></html>", 
				convertStreamToString(dataStream));
	}
	
	@Test
	public void canSayHelloJoe() throws Exception {
		Properties propsWithName = new Properties();
		propsWithName.setProperty("username", "Joe");
		initialResponse = helloServer.serve("/", "GET", null, propsWithName);
		dataStream = initialResponse.getData();
		
		assertEquals("<html><body><h1>Hello server</h1><p>Hello, Joe!</p></body></html>", convertStreamToString(dataStream));
	}
	
	@After
	public void tearDown() throws Exception{
		HelloServerExample.stop();
	}
	
}
