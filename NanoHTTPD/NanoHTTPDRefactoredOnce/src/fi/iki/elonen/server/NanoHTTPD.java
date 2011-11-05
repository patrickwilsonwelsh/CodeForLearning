package fi.iki.elonen.server;

import java.io.IOException;
import java.net.ServerSocket;

import fi.iki.elonen.server.httpsession.HTTPSession;

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
 * <li>Java 1.1 compatible</li>
 * <li>Released as open source, Modified BSD license</li>
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
 * license)
 */
public class NanoHTTPD {
	private int myTcpPort;
	private Thread thread;
	private ServerSocket socket;
	private HTTPSession session;

	public NanoHTTPD(int port, IServer server) throws IOException {
		runDaemon(port, server);
	}
	
	/**
	 * Starts a HTTP server to given port.
	 * <p>
	 * Throws an IOException if the socket is already in use
	 */
	protected synchronized void runDaemon(int port, final IServer server) throws IOException {
		this.myTcpPort = port;

		socket = new ServerSocket(myTcpPort);
		thread = new Thread(new Runnable() {
			public void run() {
				try {
					while (true)
						session = new HTTPSession(socket.accept(),server);
				} catch (IOException ioe) {
				} // TODO need to rethrow and test this, or skip try/catch altogether
			}
		});
		thread.setDaemon(true);
		thread.start();
	}
	
	public void stop() throws Exception {
		if (session != null) session.stop();
		socket.close();
		thread.interrupt();
	}
}
