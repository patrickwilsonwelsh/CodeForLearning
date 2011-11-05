package fi.iki.elonen;

import java.util.Properties;

public class RequestComponents {

	private String uri;
	public String getUri() {
		return uri;
	}

	public Properties getParms() {
		return parms;
	}

	public Object getHttpMethod() {
		return httpMethod;
	}

	public Properties getHeader() {
		return header;
	}

	private Properties parms;
	private Object httpMethod;
	private Properties header;

	public RequestComponents(String uri, String httpMethod, Properties header,
			Properties parms) {
		this.uri = uri;
		this.httpMethod = httpMethod;
		this.header = header; 
		this.parms = parms;
	}

}
