package fi.iki.elonen.example.helloserver;

import java.io.IOException;
import java.util.Properties;

import fi.iki.elonen.server.BasicServer;
import fi.iki.elonen.server.NanoHTTPD;
import fi.iki.elonen.server.httpsession.Response;

/**
 * An example of subclassing BasicServer to make a custom HTTP server.
 */
public class HelloServerExample extends BasicServer {
	public HelloServerExample() throws IOException {
		this(8080);
	}

	public HelloServerExample(int port) throws IOException {
		nano = new NanoHTTPD(port, this);
	}

	public Response serve(String uri, String httpMethod, Properties header,
			Properties parms) {

		String msg = "<html><body><h1>Hello server</h1>\n";
		if (parms.getProperty("username") == null)
			msg += "<form action='?' method='get'>\n"
					+ "  <p>Your name: <input type='text' name='username'></p>\n"
					+ "  <p><input id='submit' type='submit' value='Submit'></p>\n"
					+ "</form>\n";
		else
			msg += "<p>Hello, " + parms.getProperty("username") + "!</p>";

		msg += "</body></html>\n";
		return new Response(HTTP_OK, MIME_HTML, msg);
	}

	public static void main(String[] args) {
		start(args);
	}

	public static void start(String[] args) {
		int licenseArgumentIndex = showLicenseIfRequested(args, sloppyLogger);
		int port = changePortIfRequested(args, licenseArgumentIndex);

		try {
			new HelloServerExample(port);
		} catch (IOException ioe) {
			serverFailureSoBlowUp(ioe);
		}
		System.out.println("Listening on port " + port
				+ ". Hit Enter to stop.\n");
		try {
			System.in.read();
		} catch (Throwable ignored) {
		}
	}
}
