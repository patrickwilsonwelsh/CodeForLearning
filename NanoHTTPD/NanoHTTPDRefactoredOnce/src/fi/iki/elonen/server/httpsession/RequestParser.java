package fi.iki.elonen.server.httpsession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Properties;
import java.util.StringTokenizer;

import fi.iki.elonen.server.BasicServer;
import fi.iki.elonen.util.UriDecoder;

public class RequestParser {
	private InputStream socketInputStream;
	private StringTokenizer requestTokens;
	private BufferedReader inputReader;
	private UriDecoder decoder;
	private Socket mySocket;
	
	private static final String CARRIAGE_RETURN_LINE_FEED = "\r\n";
	private ParsedRequest parsedRequest;
	private boolean weHaveRequestParameters = false;
	private String requestParametersString;

	
	public RequestParser(Socket s) {
		this.mySocket = s;
		decoder = new UriDecoder();
		parsedRequest = new ParsedRequest();
	}
	
	protected ParsedRequest parseRequest() throws Exception {
		inputReader = createInputReader();
		requestTokens = readSingleRequestLine();
		
		parsedRequest.setHttpMethod(parseHttpMethod());
		parsedRequest.setRequestUri(parseUri());
		//REFACTOR Instead of these "set" shenanigans, provide addParameter() method to ParsedRequest
	    parsedRequest.setRequestParameters(parseRequestParameters());
		parsedRequest.setHttpType(parseHTTPType());  //TODO Note in readme this is new...
		parsedRequest.setHeaderParameters(parseAllHeaderParameters());
		handlePOSTMethod(parsedRequest.getHttpMethod());  //SMELL Inappropriate intimacy
		
		return parsedRequest;
	}

	private Properties parseRequestParameters() throws Exception {
		Properties requestParameters = new Properties();
		if (weHaveRequestParameters) 
			requestParameters = decoder.decodeRequestParameters(requestParametersString, requestParameters);
		
		return requestParameters;
	}

	private String parseHTTPType() {
		String httpType = "";
		
		if (requestTokens.hasMoreTokens()) {
			httpType = readHTTPType();
		}
		return httpType;
	}

	private String readHTTPType() {
		String typeToken = requestTokens.nextToken();
		String type = "";
		
		if (weHaveATypeToken(typeToken)) {
			type = extractTheType(typeToken); 
		}
		
		return type;
	}

	private String extractTheType(String type) {
		int p = type.indexOf('/');
		type = type.substring(p + 1).trim();
		return type;
	}

	private boolean weHaveATypeToken(String typeToken) {
		return (typeToken != null)  && typeToken.trim().length() > 0;
	}

	private BufferedReader createInputReader() throws IOException {
		socketInputStream = mySocket.getInputStream();
		if (socketInputStream == null) 
			throw new IOException("Could not retrieve input stream from socket.");
		inputReader = new BufferedReader( new InputStreamReader(socketInputStream));
		
		return inputReader;
	}

	private StringTokenizer readSingleRequestLine() throws IOException {
		String input = inputReader.readLine();

		String requestLine = new String();
		if (input != null) requestLine = input;
		
		return new StringTokenizer(requestLine);
	}

	private String parseHttpMethod() throws Exception {
		if (!emptyRequest())  return requestTokens.nextToken();
		return "";
	}
	
	private String parseUri() throws Exception {
		if (!missingUri()) return decodeUri(requestTokens.nextToken());
		return "";
	}

	private Properties parseAllHeaderParameters() throws IOException {
		Properties headerParameters = new Properties();
		
		requestTokens = readSingleRequestLine();
		if (requestTokens.hasMoreTokens()) {
			headerParameters = readRemainingRequestHeaderParameters();
		}
		
		return headerParameters;
	}

	private void handlePOSTMethod(String httpMethod) throws Exception {
		if (httpMethod.equalsIgnoreCase("POST")) {
			long size = parseContentLength();
			parsedRequest.setPostLine(parsePostLine(size));
			//REFACTOR Instead of these shenanigans, provide addParameter() method to ParsedRequest
			Properties decodedPostLineParameters = addPostLineRequestParameters(); 
			parsedRequest.setRequestParameters(decodedPostLineParameters);
		}
	}

	//REFACTOR Instead of these shenanigans, provide addParameter() method to ParsedRequest
	private Properties addPostLineRequestParameters()
			throws InterruptedException {
		return decoder.decodeRequestParameters(parsedRequest.getPostLine(), 
				parsedRequest.getRequestParameters());
	}

	private long parseContentLength() {
		long size = 0x7FFFFFFFFFFFFFFFl;
		String contentLength = parsedRequest.getHeaderParameters().getProperty("content-length");
		if (contentLength != null) {
			try {
				size = Integer.parseInt(contentLength);
			} catch (NumberFormatException ex) {
				throw new RuntimeException("content-length could not be parsed as integer", ex);
			}  
		}
		return size;
	}

	//SMELL Is this really how we want to read the postLine, with character arrays?  Why?
	private String parsePostLine(long contentLength) throws IOException {
		String postLine = "";
		char buf[] = new char[512];
		int index = inputReader.read(buf);
		
		while (index >= 0 
				&& contentLength > 0 
				&& !postLine.endsWith(CARRIAGE_RETURN_LINE_FEED)) {
			contentLength -= index;
			postLine = postLine.concat(String.valueOf(buf, 0, index));
			if (contentLength > 0) index = inputReader.read(buf);
		}
		
		return postLine.trim();
	}

	//SMELL We are using at least two mechanisms for line parsing so far; use Tokenizer here again instead?
	private Properties readRemainingRequestHeaderParameters() throws IOException {
		String line = requestTokens.nextToken();
		Properties headerParameters = new Properties();
		
		while (weHaveATypeToken(line)) {
			int p = line.indexOf(':');
			headerParameters.put(line.substring(0, p).trim().toLowerCase(),
					line.substring(p + 1).trim()); 
			line = inputReader.readLine();
		}
		
		return headerParameters;
	}

	public String decodeUri(String uri) throws Exception {
		int questionMarkIndex = uri.indexOf('?');
		if (weHaveRequestParameters(questionMarkIndex)) {
			requestParametersString = uri.substring(questionMarkIndex+1);
			uri = decoder.decodePercentAndPlus(uri.substring(0, questionMarkIndex));
		} else {
			requestParametersString = uri;
			uri = decoder.decodePercentAndPlus(uri);
		}
		
		if (uri == UriDecoder.BAD_URI_INDICATOR) 
			parsedRequest.setErrorCode(BasicServer.HTTP_BADREQUEST, "BAD REQUEST: Bad percent-encoding.");
		
		return uri;
	}

	private boolean weHaveRequestParameters(int questionMarkIndex) {
		weHaveRequestParameters = questionMarkIndex >= 0;
		return weHaveRequestParameters;
	}
	
	private boolean missingUri() throws Exception {
		if (!requestTokens.hasMoreTokens()) {
			parsedRequest.setErrorCode(BasicServer.HTTP_BADREQUEST,
					"BAD REQUEST: Missing URI. Usage: GET /example/file.html");
			return true;
		}
		
		return false;
	}

	private boolean emptyRequest() throws Exception {
		if (!requestTokens.hasMoreTokens()) {
			parsedRequest.setErrorCode(BasicServer.HTTP_BADREQUEST,
					"BAD REQUEST: Syntax error. Usage: GET /example/file.html");
			return true;
		}
		return false;
	}

	protected void closeSession() throws IOException {
		inputReader.close();
	}
}
