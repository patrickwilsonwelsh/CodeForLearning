package fi.iki.elonen.server;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Hashtable;

import fi.iki.elonen.server.httpsession.ParsedRequest;
import fi.iki.elonen.server.httpsession.Response;
import fi.iki.elonen.util.PropertiesWriter;

@SuppressWarnings("unchecked")
public abstract class BasicServer implements IServer{
	public static final String HTTP_OK = "200 OK";
	public static final String HTTP_REDIRECT = "301 Moved Permanently";
	public static final String HTTP_FORBIDDEN = "403 Forbidden"; 
	public static final String HTTP_NOTFOUND = "404 Not Found";
	public static final String HTTP_BADREQUEST = "400 Bad Request";
	public static final String HTTP_INTERNALERROR = "500 Internal Server Error";
	public static final String HTTP_NOTIMPLEMENTED = "501 Not Implemented";

	public static final String MIME_PLAINTEXT = "text/plain";
	public static final String MIME_HTML = "text/html";
	public static final String MIME_DEFAULT_BINARY = "application/octet-stream";
	
	public static final String FROM = " from ";
	public static final String NOW_SERVING_FILES_IN_PORT = "Now serving files in port ";
	public static final String HIT_ENTER_TO_STOP = "Hit Enter to stop.";
	public static final String LOCAL_ABSOLUTE_PATH = new File("").getAbsolutePath();
	public static final String HOWDY_PART_ONE = "NanoHTTPD 1.11 (C) 2001,2005-2008 Jarno Elonen\n"
					+ "(Command line options: [port] [--license])\n";
	public  static final int DEFAULT_PORT_NUMBER = 8090;
	public  static final String LICENSE_ARG = "license";
	public static final String ESCAPED_QUOTE = "\"";

	public static final String CURRENT_DIRECTORY_SYMBOL = ".";
	public static final String OPEN_HTML_BODY = "<html><body>";
	public static final String FORWARD_SLASH = "/";
	public static final String PARENT_DIRECTORY_SYMBOL = "..";
	protected PropertiesWriter propertiesWriter;
	
	public Response serve(ParsedRequest parsedRequest) {
		return serve(parsedRequest.getRequestUri(), parsedRequest.getHttpMethod(), parsedRequest.getHeaderParameters(), 
				parsedRequest.getRequestParameters());
	}
	
	protected InputStream initInputReader() {
		if (inputReader != null) return inputReader;
		return System.in;
	}

	protected PrintWriter getLoggerInstance() {
		if (sloppyLogger != null) return sloppyLogger;
		return new PrintWriter(System.out, true);
	}

	protected static NanoHTTPD nano;
	protected static PrintWriter sloppyLogger;
	protected static InputStream inputReader;

	public PropertiesWriter getPropertiesWriter() {
		return propertiesWriter;
	}

	public static PrintWriter getSloppyLogger() {
		return sloppyLogger;
	}

	public static void setSloppyLogger(PrintWriter sloppyLogger) {
		BasicServer.sloppyLogger = sloppyLogger;
	}

	protected static InputStream getInputReader() {
		return inputReader;
	}

	public static void setInputReader(InputStream inputReader) {
		BasicServer.inputReader = inputReader;
	}

	protected void setPropertiesWriter(PropertiesWriter propertiesWriter) {
		this.propertiesWriter = propertiesWriter;
	}

	protected PropertiesWriter getPropertiesWriterInstance() { //REFACTOR Do we need this?
			if (propertiesWriter != null) return propertiesWriter; //REFACTOR Do we need this?
			
			propertiesWriter = new PropertiesWriter();
			return propertiesWriter;
	}

	public static int changePortIfRequested(String[] args, int lopt) {
		int port = DEFAULT_PORT_NUMBER;
		if (args.length > 0 && lopt != 0)
			port = Integer.parseInt(args[0]);
		return port;
	}
	
	public static int showLicenseIfRequested(String[] args,
			PrintWriter sloppyLogger) {
		int licenseArgumentIndex = -1;
		for (int i = 0; i < args.length; ++i)
			if (args[i].toLowerCase().endsWith(LICENSE_ARG)) {
				licenseArgumentIndex = i;
				sloppyLogger.println(LICENSE + "\n");
			}
		return licenseArgumentIndex;
	}
	
	protected static void serverFailureSoBlowUp(IOException ioe) {
		System.err.println("Couldn't start server:\n" + ioe);
		System.exit(-1);
	}
	
	public static void stop() throws Exception {
		nano.stop();
	}

	public static Hashtable theMimeTypes = new Hashtable();
	static {
		theMimeTypes.put("htm", "text/html");
		theMimeTypes.put("html", "text/html");
		theMimeTypes.put("txt", "text/plain");
		theMimeTypes.put("asc", "text/plain");
		theMimeTypes.put("gif", "image/gif");
		theMimeTypes.put("jpg", "image/jpeg");
		theMimeTypes.put("jpeg", "image/jpeg");
		theMimeTypes.put("png", "image/png");
		theMimeTypes.put("mp3", "audio/mpeg");
		theMimeTypes.put("m3u", "audio/mpeg-url");
		theMimeTypes.put("pdf", "application/pdf");
		theMimeTypes.put("doc", "application/msword");
		theMimeTypes.put("ogg", "application/x-ogg");
		theMimeTypes.put("zip", "application/octet-stream");
		theMimeTypes.put("exe", "application/octet-stream");
		theMimeTypes.put("class", "application/octet-stream");
	}
	
	/**
	 * The distribution license
	 */
	public static final String LICENSE = "Copyright (C) 2001,2005-2008 by Jarno Elonen <elonen@iki.fi>\n"
			+ "\n"
			+ "Redistribution and use in source and binary forms, with or without\n"
			+ "modification, are permitted provided that the following conditions\n"
			+ "are met:\n"
			+ "\n"
			+ "Redistributions of source code must retain the above copyright notice,\n"
			+ "this list of conditions and the following disclaimer. Redistributions in\n"
			+ "binary form must reproduce the above copyright notice, this list of\n"
			+ "conditions and the following disclaimer in the documentation and/or other\n"
			+ "materials provided with the distribution. The name of the author may not\n"
			+ "be used to endorse or promote products derived from this software without\n"
			+ "specific prior written permission. \n"
			+ " \n"
			+ "THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR\n"
			+ "IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES\n"
			+ "OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.\n"
			+ "IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,\n"
			+ "INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT\n"
			+ "NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,\n"
			+ "DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY\n"
			+ "THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT\n"
			+ "(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE\n"
			+ "OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.";

}
