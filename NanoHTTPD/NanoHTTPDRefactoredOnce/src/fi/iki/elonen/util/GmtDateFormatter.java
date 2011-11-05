package fi.iki.elonen.util;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class GmtDateFormatter {

	public SimpleDateFormat gmtFormat() {
		SimpleDateFormat gmtFormat = new java.text.SimpleDateFormat("E, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
		gmtFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		return gmtFormat;
	}
}
