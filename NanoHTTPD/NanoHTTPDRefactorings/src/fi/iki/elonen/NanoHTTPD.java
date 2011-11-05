package fi.iki.elonen;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TimeZone;

/**
 * A simple, tiny, nicely embeddable HTTP 1.0 server in Java
 * 
 * <p>
 * NanoHTTPD version 1.11, Copyright &copy; 2001,2005-2008 Jarno Elonen
 * (elonen@iki.fi, http://iki.fi/elonen/)
 * 
 * <p>
 * <b>Features + limitations: </b>
 * <ul>
 * 
 * <li>Only one Java file</li>
 * <li>Java 1.1 compatible</li>
 * <li>Released as open source, Modified BSD licence</li>
 * <li>No fixed config files, logging, authorization etc. (Implement yourself if
 * you need them.)</li>
 * <li>Supports parameter parsing of GET and POST methods</li>
 * <li>Supports both dynamic content and file serving</li>
 * <li>Never caches anything</li>
 * <li>Doesn't limit bandwidth, request time or simultaneous connections</li>
 * <li>Default code serves files and shows all HTTP parameters and headers</li>
 * <li>File server supports directory listing, index.html and index.htm</li>
 * <li>File server does the 301 redirection trick for directories without '/'</li>
 * <li>File server supports simple skipping for files (continue download)</li>
 * <li>File server uses current directory as a web root</li>
 * <li>File server serves also very long files without memory overhead</li>
 * <li>Contains a built-in list of most common mime types</li>
 * <li>All header names are converted lowercase so they don't vary between
 * browsers/clients</li>
 * 
 * </ul>
 * 
 * <p>
 * <b>Ways to use: </b>
 * <ul>
 * 
 * <li>Run as a standalone app, serves files from current directory and shows
 * requests</li>
 * <li>Subclass serve() and embed to your own program</li>
 * <li>Call serveFile() from serve() with your own base directory</li>
 * 
 * </ul>
 * 
 * See the end of the source file for distribution license (Modified BSD
 * licence)
 */
public class NanoHTTPD {

	/**
	 * Starts a HTTP server to given port.
	 * <p>
	 * Throws an IOException if the socket is already in use
	 */

	private HTTPSession session;
	
	public NanoHTTPD(int port) throws IOException {
		myTcpPort = port;

		final ServerSocket ss = new ServerSocket(myTcpPort);
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					while (true) {
						session = new HTTPSession(ss.accept());
						session = null;
					}
				} catch (IOException ioe) {
				}

			}
		});
		t.setDaemon(true);
		t.start();
	}
	// ==================================================
	// API parts
	// ==================================================

	/**
	 * Override this to customize the server.
	 * <p>
	 * 
	 * (By default, this delegates to serveFile() and allows directory listing.)
	 * 
	 * @parm uri Percent-decoded URI without parameters, for example
	 *       "/index.cgi"
	 * @parm method "GET", "POST" etc.
	 * @parm parms Parsed, percent decoded parameters from URI and, in case of
	 *       POST, data.
	 * @parm header Header entries, percent decoded
	 * @return HTTP response, see class Response for details
	 */
	@SuppressWarnings("unchecked")
	// Suppression to maintain backward compatibility with Ye Old Java.
	public Response serve(RequestComponents unit) {

		return new Response(); //REFACTOR return some instruction in Response about how to look at 
		//reference implementations to learn how to extend NanoHTTPD to create a working server. 
	}

	@SuppressWarnings("unchecked")
	public void paintProperties(Properties header, String propertyTypeName,
			PrintWriter sloppyLogger) {
		Enumeration e = header.propertyNames();
		while (e.hasMoreElements()) {
			String value = (String) e.nextElement();
			sloppyLogger.println("  " + propertyTypeName + ": '" + value + "' = '"
					+ header.getProperty(value) + "'");
		}
	}
	

	/**
	 * Some HTTP response status codes
	 */
	// SMELL Primitive obsession: what data structure should this be?
	public static final String HTTP_OK = "200 OK",
			HTTP_REDIRECT = "301 Moved Permanently",
			HTTP_FORBIDDEN = "403 Forbidden", 
			HTTP_NOTFOUND = "404 Not Found",
			HTTP_BADREQUEST = "400 Bad Request",
			HTTP_INTERNALERROR = "500 Internal Server Error",
			HTTP_NOTIMPLEMENTED = "501 Not Implemented";

	/**
	 * Common mime types for dynamic content
	 */
	public static final String MIME_PLAINTEXT = "text/plain",
			MIME_HTML = "text/html",
			MIME_DEFAULT_BINARY = "application/octet-stream";

	// ==================================================
	// Socket & server code
	// ==================================================


	/**
	 * Handles one session, i.e. parses the HTTP request and returns the
	 * response.
	 */
	// SMELL can we decouple this from NanoHTTPD?
	private class HTTPSession implements Runnable {
		private RequestComponents requestComponents;
		private BufferedReader in;
		
		public HTTPSession(Socket s) {
			startThread(s);
		}

		private void startThread(Socket s) {
			mySocket = s;
			Thread t = new Thread(this);
			t.setDaemon(true); //SMELL configurable?
			t.start();
		}
		
		public RequestComponents getRequestComponents() {
			return requestComponents;
		}
		
		public BufferedReader getInputReader() {
			return in;
		}

		public void run() {
			try {
				//TODO Need several tests around socket and reader contents and responses...
				InputStream is = mySocket.getInputStream();
				if (is == null) return;
				in = new BufferedReader(new InputStreamReader(is));

				// SMELL More duplication (in the text and conditional)
				// Read the request line
				StringTokenizer requestTokens = new StringTokenizer(in.readLine());
				String httpMethod = handleBadRequestSyntax(requestTokens);
				String uri = requestTokens.nextToken();

				// Decode parameters from the URI
				Properties parms = new Properties();
				uri = decodeParameters(uri, parms); // REFACTOR Should not have to pass in uri...

				Properties header = new Properties();
				parseHeaders(requestTokens, header);

				// If the method is POST, there may be parameters
				// in data section, too, read it:
				//REFACTOR More primitives that could, at least, be static finals
				//REFACTOR Extract method
				if (httpMethod.equalsIgnoreCase("POST")) {
					handlePOST(parms, header);
				}

				//REFACTOR How to decouple from serve() call? 
				requestComponents = new RequestComponents(uri, httpMethod, header, parms);
				Response r = serve(requestComponents);
				
				if (r == null)
					sendError(HTTP_INTERNALERROR,
							"SERVER INTERNAL ERROR: Serve() returned a null response.");
				else
					sendResponse(r.status, r.mimeType, r.header, r.data);

				in.close();
			} catch (IOException ioe) {
				try {
					sendError(HTTP_INTERNALERROR,
							"SERVER INTERNAL ERROR: IOException: "
									+ ioe.getMessage());
				} catch (Throwable t) {
				} // SMELL Swallowing errors and exceptions both??!
			} catch (InterruptedException ie) {
				// Thrown by sendError (which one?), ignore and exit the thread.
			}
		}

		private void parseHeaders(StringTokenizer requestTokens,
				Properties header) throws IOException {
			// If there's another token, it's protocol version,
			// followed by HTTP headers. Ignore version but parse headers.
			// NOTE: this now forces header names uppercase since they are
			// case insensitive and vary by client.
			if (requestTokens.hasMoreTokens()) {
				String line = in.readLine();
				while (line.trim().length() > 0) {
					int p = line.indexOf(':');
					header.put(line.substring(0, p).trim().toLowerCase(),line.substring(p + 1).trim()); // SMELL Train-wreck
					line = in.readLine();
				}
			}
		}

		private String decodeParameters(String uri, Properties parms)
				throws InterruptedException {
			int qmi = uri.indexOf('?');
			if (qmi >= 0) {
				decodeParms(uri.substring(qmi + 1), parms);
				uri = decodePercent(uri.substring(0, qmi));
			} else
				uri = decodePercent(uri);
			return uri;
		}

		private String handleBadRequestSyntax(StringTokenizer requestTokens)
				throws InterruptedException {
			if (!requestTokens.hasMoreTokens())
				sendError(HTTP_BADREQUEST,
						"BAD REQUEST: Syntax error. Usage: GET /example/file.html");

			String httpMethod = requestTokens.nextToken();

			if (!requestTokens.hasMoreTokens())
				sendError(HTTP_BADREQUEST,
						"BAD REQUEST: Missing URI. Usage: GET /example/file.html");
			return httpMethod;
		}

		private void handlePOST(Properties parms, Properties header)
				throws IOException, InterruptedException {
			long size = 0x7FFFFFFFFFFFFFFFl;
			String contentLength = header.getProperty("content-length");
			if (contentLength != null) {
				try {
					size = Integer.parseInt(contentLength);
				} catch (NumberFormatException ex) {
				} // WTF swallowing ex
			}
			String postLine = "";
			char buf[] = new char[512];
			int read = in.read(buf);
			while (read >= 0 && size > 0 && !postLine.endsWith("\r\n")) {
				size -= read;
				postLine += String.valueOf(buf, 0, read); //SMELL Joshua Bloch no-no (String +=)
				if (size > 0)
					read = in.read(buf);
			}
			postLine = postLine.trim();
			decodeParms(postLine, parms);
		}

		/**
		 * Decodes the percent encoding scheme. <br/>
		 * For example: "an+example%20string" -> "an example string"
		 */
		// SMELL Library method? (URI.decode?)
		private String decodePercent(String str) throws InterruptedException {
			try {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < str.length(); i++) {
					char c = str.charAt(i);
					switch (c) {
					case '+':
						sb.append(' ');
						break;
					case '%':
						sb.append((char) Integer.parseInt(str.substring(i + 1,
								i + 3), 16));
						i += 2;
						break;
					default:
						sb.append(c);
						break;
					}
				}
				return new String(sb.toString().getBytes());
			} catch (Exception e) {
				sendError(HTTP_BADREQUEST, "BAD REQUEST: Bad percent-encoding.");
				return null;
			}
		}

		/**
		 * Decodes parameters in percent-encoded URI-format ( e.g.
		 * "name=Jack%20Daniels&pass=Single%20Malt" ) and adds them to given
		 * Properties.
		 */
		private void decodeParms(String parms, Properties p)
				throws InterruptedException {
			if (parms == null)
				return;

			StringTokenizer st = new StringTokenizer(parms, "&");
			while (st.hasMoreTokens()) {
				String e = st.nextToken();
				int sep = e.indexOf('=');
				if (sep >= 0)
					p.put(decodePercent(e.substring(0, sep)).trim(),
							decodePercent(e.substring(sep + 1)));
			}
		}

		/**
		 * Returns an error message as a HTTP response and throws
		 * InterruptedException to stop furhter request processing.
		 */
		private void sendError(String status, String msg)
				throws InterruptedException {
			sendResponse(status, MIME_PLAINTEXT, null,
					new ByteArrayInputStream(msg.getBytes()));
			throw new InterruptedException();
		}

		/**
		 * Sends given response to the socket.
		 */
		// SMELL Interleaves formatting and sending the response
		private void sendResponse(String status, String mime,
				Properties header, InputStream data) {
			try {
				if (status == null)
					throw new Error("sendResponse(): Status can't be null.");

				OutputStream out = mySocket.getOutputStream();
				PrintWriter pw = new PrintWriter(out);
				pw.print("HTTP/1.0 " + status + " \r\n");

				if (mime != null)
					pw.print("Content-Type: " + mime + "\r\n");

				if (header == null || header.getProperty("Date") == null)
					pw.print("Date: " + gmtFormat.format(new Date()) + "\r\n");

				if (header != null) {
					Enumeration e = header.keys();
					while (e.hasMoreElements()) {
						String key = (String) e.nextElement();
						String value = header.getProperty(key);
						pw.print(key + ": " + value + "\r\n");
					}
				}

				pw.print("\r\n");
				pw.flush();

				if (data != null) {
					byte[] buff = new byte[2048];
					while (true) {
						int read = data.read(buff, 0, 2048);
						if (read <= 0)
							break;
						out.write(buff, 0, read);
					}
				}
				out.flush();
				out.close();
				if (data != null)
					data.close();
			} catch (IOException ioe) {
				// Couldn't write? No can do.
				try {
					mySocket.close();
				} catch (Throwable t) {
				}
			}
		}

		private Socket mySocket;

	};

	/**
	 * URL-encodes everything between "/"-characters. Encodes spaces as '%20'
	 * instead of '+'.
	 */
	protected String encodeUri(String uri) {
		String newUri = "";
		StringTokenizer st = new StringTokenizer(uri, "/ ", true);
		while (st.hasMoreTokens()) {
			String tok = st.nextToken();
			if (tok.equals("/"))
				newUri += "/";
			else if (tok.equals(" "))
				newUri += "%20";
			else {
				newUri += URLEncoder.encode(tok);
				// For Java 1.4 you'll want to use this instead:
				// try { newUri += URLEncoder.encode( tok, "UTF-8" ); } catch (
				// UnsupportedEncodingException uee )
			}
		}
		return newUri;
	}

	private int myTcpPort;
	File myFileDir;

	/**
	 * Hashtable mapping (String)FILENAME_EXTENSION -> (String)MIME_TYPE
	 */
	// REFACTOR Likely small seam: replace with actual map
	protected static Hashtable theMimeTypes = new Hashtable();
	static {
		StringTokenizer st = new StringTokenizer("htm		text/html "
				+ "html		text/html " + "txt		text/plain " + "asc		text/plain "
				+ "gif		image/gif " + "jpg		image/jpeg " + "jpeg		image/jpeg "
				+ "png		image/png " + "mp3		audio/mpeg "
				+ "m3u		audio/mpeg-url " + "pdf		application/pdf "
				+ "doc		application/msword " + "ogg		application/x-ogg "
				+ "zip		application/octet-stream "
				+ "exe		application/octet-stream "
				+ "class		application/octet-stream ");
		while (st.hasMoreTokens())
			theMimeTypes.put(st.nextToken(), st.nextToken());
	}

	/**
	 * GMT date formatter
	 */
	private static java.text.SimpleDateFormat gmtFormat;
	static {
		gmtFormat = new java.text.SimpleDateFormat(
				"E, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
		gmtFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	}

	/**
	 * The distribution licence
	 */
	protected static final String LICENCE = "Copyright (C) 2001,2005-2008 by Jarno Elonen <elonen@iki.fi>\n"
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
