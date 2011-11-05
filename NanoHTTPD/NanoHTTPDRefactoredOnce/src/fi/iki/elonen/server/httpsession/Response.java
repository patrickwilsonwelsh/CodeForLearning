package fi.iki.elonen.server.httpsession;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Properties;

import fi.iki.elonen.server.BasicServer;

public class Response {
	private String status;
	private String mimeType;
	private InputStream data;
	private Properties header = new Properties();

	public String getStatus() {
		return status;
	}

	public String getMimeType() {
		return mimeType;
	}

	public Properties getHeader() {
		return header;
	}
	
	public InputStream getData() {
		return data;
	}
	
	public Response() {
		this.status = BasicServer.HTTP_OK;
	}

	public Response(String status, String mimeType, InputStream data) {
		this.status = status;
		this.mimeType = mimeType;
		this.data = data;
	}

	public Response(String status, String mimeType, String txt) {
		this(status, mimeType, new ByteArrayInputStream(txt.getBytes()));
	}

	public void addHeader(String name, String value) {
		header.put(name, value);
	}
}

