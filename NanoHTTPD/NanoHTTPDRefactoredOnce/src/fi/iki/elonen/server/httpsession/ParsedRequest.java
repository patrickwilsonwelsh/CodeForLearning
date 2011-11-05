package fi.iki.elonen.server.httpsession;

import java.util.Properties;

public class ParsedRequest {
	private Properties requestParameters;
	private Properties headerParameters;
	private String httpMethod;
	private String requestUri;
	private String httpType;
	private String postLine;
	private String errorCode;
	private String errorMessage;

	public ParsedRequest() {
		this.errorCode = null;
	}
	
	public String getRequestUri() {
		return requestUri;
	}

	protected void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	protected String getHttpType() {
		return httpType;
	}

	protected void setHttpType(String httpType) {
		this.httpType = httpType;
	}

	protected String getErrorCode() {
		return errorCode;
	}

	protected void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	protected void setRequestParameters(Properties requestParameters) {
		this.requestParameters = requestParameters;
	}

	protected void setHeaderParameters(Properties headerParameters) {
		this.headerParameters = headerParameters;
	}

	protected void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	protected String getPostLine() {
		return postLine;
	}

	protected void setPostLine(String postLine) {
		this.postLine = postLine;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	protected void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public String getHttpMethod() {
		return httpMethod;
	}
	
	public Properties getRequestParameters() {
		return requestParameters;
	}

	public String getHTTPType() {
		return httpType;
	}

	public Properties getHeaderParameters() {
		return headerParameters;
	}
	
	public void setErrorCode(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public String errorCode() {
		return errorCode;
	}
	
}
