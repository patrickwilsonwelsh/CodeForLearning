package fi.iki.elonen.util;

import static org.junit.Assert.assertEquals;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

import org.junit.Test;

public class PrintRequestHeadersTest {
	PropertiesWriter propsWriter = new PropertiesWriter();
	
	@Test
	public void canPaintSingleProperty() throws Exception {
		StringWriter canvas = new StringWriter();
		
		Properties property = new Properties();
		property.setProperty("wtf", "thing");
		propsWriter.writeProperties(property, "TEST_NAME", new PrintWriter(canvas));
		assertEquals("TEST_NAME: 'wtf' = 'thing'",	 canvas.toString().trim());
	}
}
