package fi.iki.elonen.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;


public class GmtDateFormatterTest {
	private static final String CARRIAGE_RETURN_LINE_FEED = "\r\n";
	
	@Test
	public void canCreateGmtFormattedDateFormat() {
		GmtDateFormatter formatter = new GmtDateFormatter();
		
		SimpleDateFormat gmtDateFormat = formatter.gmtFormat();
		String dateString = gmtDateFormat.format(new Date()) + CARRIAGE_RETURN_LINE_FEED;
		String formatSymbols = gmtDateFormat.getDateFormatSymbols().getLocalPatternChars();

		assertTrue(dateString.contains("GMT"));
		assertEquals("Greenwich Mean Time", gmtDateFormat.getTimeZone().getDisplayName());
		assertEquals("GyMdkHmsSEDFwWahKzZ", formatSymbols);
	}
}
