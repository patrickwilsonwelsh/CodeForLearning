package fi.iki.elonen;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

import org.junit.Test;

public class PrintRequestHeadersTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void portCannotBeNegative() throws IOException {
		new NanoHTTPD(-1); 
	}
	
	@Test
	public void canPaintSingleProperty() throws Exception {
		StringWriter canvas = new StringWriter();
		NanoHTTPD nano = anyOldNanoHttpd(); 
		
		Properties property = new Properties();
		property.setProperty("wtf", "thing");
		nano.paintProperties(property, "TEST_NAME", new PrintWriter(canvas));
		assertEquals("  TEST_NAME: 'wtf' = 'thing'\n",	 canvas.toString());
	}

	private NanoHTTPD anyOldNanoHttpd() throws IOException {
		return new NanoHTTPD(0);
	}
}
