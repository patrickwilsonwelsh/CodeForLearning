package fi.iki.elonen.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class EncodeUriTest {
	@Test
	public void canReplaceSpacesWithPercents() {
		UriEncoder encoder = new UriEncoder();
		
		String input = "/here are /a bunch /of spaces";
		assertEquals("/here%20are%20/a%20bunch%20/of%20spaces", encoder.encodeUri(input));
	}
}
