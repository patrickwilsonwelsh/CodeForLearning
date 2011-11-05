package fi.iki.elonen.util;

import java.net.URLEncoder;
import java.util.StringTokenizer;

public class UriEncoder {

	/**
	 * URL-encodes everything between "/"-characters. Encodes spaces as '%20'
	 * instead of '+'.
	 */
	public String encodeUri(String plainUri) {
		String encodedUri = "";
		StringTokenizer st = new StringTokenizer(plainUri, "/ ", true);
		while (st.hasMoreTokens()) {
			String tok = st.nextToken();
			if (tok.equals("/"))
				encodedUri += "/";
			else if (tok.equals(" "))
				encodedUri += "%20";
			else {
				encodedUri += URLEncoder.encode(tok);  //TODO Can we detect JDK version and respond accordingly?
				// For Java 1.4 you'll want to use this instead:
				// try { newUri += URLEncoder.encode( tok, "UTF-8" ); } catch (
				// UnsupportedEncodingException uee )
			}
		}
		return encodedUri;
	}

}
