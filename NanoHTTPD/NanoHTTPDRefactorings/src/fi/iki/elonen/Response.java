package fi.iki.elonen;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Response {
	public Response() {
		this.status = NanoHTTPD.HTTP_OK;
	}

	public Response(String status, String mimeType, InputStream data) {
		this.status = status;
		this.mimeType = mimeType;
		this.data = data;
	}

	/**
	 * Convenience method that makes an InputStream out of given text.
	 */
	public Response(String status, String mimeType, String txt) {
		this(status, mimeType, new ByteArrayInputStream(txt.getBytes()));
	}

	/**
	 * Adds given line to the header.
	 */
	public void addHeader(String name, String value) {
		header.put(name, value);
	}

	/**
	 * HTTP status code after processing, e.g. "200 OK", HTTP_OK
	 */
	public String status;

	/**
	 * MIME type of content, e.g. "text/html"
	 */
	public String mimeType;

	/**
	 * Data of the response, may be null.
	 */
	public InputStream data;

	/**
	 * Headers for the HTTP response. Use addHeader() to add lines.
	 */
	public Properties header = new Properties();
}
