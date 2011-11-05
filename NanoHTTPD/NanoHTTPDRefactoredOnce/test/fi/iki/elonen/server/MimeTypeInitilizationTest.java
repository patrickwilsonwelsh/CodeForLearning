package fi.iki.elonen.server;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
public class MimeTypeInitilizationTest {
	
	//REFACTOR Move to another TestCase class
	@Test(expected = IllegalArgumentException.class)
	public void portCannotBeNegative() throws IOException {
		new NanoHTTPD(-1, null); 
	}
	
	@Test
	public void mimeTypesAreInitializedAsExpected() throws Exception {
		assertEquals("text/html", BasicServer.theMimeTypes.get("htm"));
		assertEquals("text/html", BasicServer.theMimeTypes.get("html"));
		assertEquals("text/plain", BasicServer.theMimeTypes.get("txt"));
		assertEquals("text/plain", BasicServer.theMimeTypes.get("asc"));
		assertEquals("image/gif", BasicServer.theMimeTypes.get("gif"));
		assertEquals("image/jpeg", BasicServer.theMimeTypes.get("jpg")); 
		assertEquals("image/jpeg", BasicServer.theMimeTypes.get("jpeg")); 
		assertEquals("image/png", BasicServer.theMimeTypes.get("png"));
		assertEquals("audio/mpeg", BasicServer.theMimeTypes.get("mp3"));
		assertEquals("audio/mpeg-url", BasicServer.theMimeTypes.get("m3u"));
		assertEquals("application/pdf", BasicServer.theMimeTypes.get("pdf"));
		assertEquals("application/msword", BasicServer.theMimeTypes.get("doc"));
		assertEquals("application/x-ogg", BasicServer.theMimeTypes.get("ogg"));
		assertEquals("application/octet-stream", BasicServer.theMimeTypes.get("zip"));
		assertEquals("application/octet-stream", BasicServer.theMimeTypes.get("exe"));
		assertEquals("application/octet-stream", BasicServer.theMimeTypes.get("class"));
	}

}
