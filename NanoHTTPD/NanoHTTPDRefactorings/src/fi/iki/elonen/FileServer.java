package fi.iki.elonen;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class FileServer extends NanoHTTPD {

	private static FileServer serverInstance;

  public FileServer(int port) throws IOException {
		super(port);
	}

	@SuppressWarnings("unchecked")
	// Suppression to maintain backward compatibility with Ye Old Java.
	public Response serve(RequestComponents unit) {
		//SMELL Implementation of logging
		PrintWriter sloppyLogger = new PrintWriter(System.out, true);
		sloppyLogger.println(unit.getHttpMethod() + " '" + unit.getUri() + "' ");

		paintProperties(unit.getHeader(), "HDR", sloppyLogger);
		paintProperties(unit.getParms(), "PRM", sloppyLogger);

		boolean allowDirectoryListing = true;
		return serveFile(unit.getUri(), unit.getHeader(), new File("."), allowDirectoryListing);
	}

	/**
	 * Serves file from homeDir and its' subdirectories (only). Uses only URI,
	 * ignores all headers and HTTP parameters.
	 */
	// TODO Class boundary
	public Response serveFile(String uri, Properties header, File homeDir, boolean allowDirectoryListing) {
		// Make sure we won't die of an exception later
		if (!homeDir.isDirectory())
			return new Response(HTTP_INTERNALERROR, MIME_PLAINTEXT,
					"INTERNAL ERRROR: serveFile(): given homeDir is not a directory.");

		// Remove URL arguments
		uri = uri.trim().replace(File.separatorChar, '/');
		if (uri.indexOf('?') >= 0)
			uri = uri.substring(0, uri.indexOf('?'));

		// Prohibit getting out of current directory
		if (uri.startsWith("..") || uri.endsWith("..")
				|| uri.indexOf("../") >= 0)
			return new Response(HTTP_FORBIDDEN, MIME_PLAINTEXT,
					"FORBIDDEN: Won't serve ../ for security reasons.");

		File f = new File(homeDir, uri);
		if (!f.exists())
			return new Response(HTTP_NOTFOUND, MIME_PLAINTEXT,
					"Error 404, file not found.");

		// List the directory, if necessary
		if (f.isDirectory()) {
			// Browsers get confused without '/' after the
			// directory, send a redirect.
			if (!uri.endsWith("/")) {
				uri += "/";
				Response r = new Response(HTTP_REDIRECT, MIME_HTML,
						"<html><body>Redirected: <a href=\"" + uri + "\">"
								+ uri + "</a></body></html>");
				r.addHeader("Location", uri);
				return r;
			}

			// First try index.html and index.htm
			if (new File(f, "index.html").exists())
				f = new File(homeDir, uri + "/index.html");
			else if (new File(f, "index.htm").exists())
				f = new File(homeDir, uri + "/index.htm");

			// No index file, list the directory
			else if (allowDirectoryListing) {
				String[] files = f.list();
				String msg = "<html><body><h1>Directory " + uri + "</h1><br/>";

				if (uri.length() > 1) {
					String u = uri.substring(0, uri.length() - 1);
					int slash = u.lastIndexOf('/');
					if (slash >= 0 && slash < u.length())
						msg += "<b><a href=\"" + uri.substring(0, slash + 1)
								+ "\">..</a></b><br/>";
				}

				for (int i = 0; i < files.length; ++i) {
					File curFile = new File(f, files[i]);
					boolean dir = curFile.isDirectory();
					if (dir) {
						msg += "<b>";
						files[i] += "/";
					}

					msg += "<a href=\"" + encodeUri(uri + files[i]) + "\">"
							+ files[i] + "</a>";

					// Show file size
					if (curFile.isFile()) {
						long len = curFile.length();
						msg += " &nbsp;<font size=2>(";
						if (len < 1024)
							msg += curFile.length() + " bytes";
						else if (len < 1024 * 1024)
							msg += curFile.length() / 1024 + "."
									+ (curFile.length() % 1024 / 10 % 100)
									+ " KB";
						else
							msg += curFile.length() / (1024 * 1024) + "."
									+ curFile.length() % (1024 * 1024) / 10
									% 100 + " MB";

						msg += ")</font>";
					}
					msg += "<br/>";
					if (dir)
						msg += "</b>";
				}
				return new Response(HTTP_OK, MIME_HTML, msg);
			} else {
				return new Response(HTTP_FORBIDDEN, MIME_PLAINTEXT,
						"FORBIDDEN: No directory listing.");
			}
		}

		try {
			// Get MIME type from file name extension, if possible
			String mime = null;
			int dot = f.getCanonicalPath().lastIndexOf('.');
			if (dot >= 0)
				mime = (String) theMimeTypes.get(f.getCanonicalPath()
						.substring(dot + 1).toLowerCase());
			if (mime == null)
				mime = MIME_DEFAULT_BINARY;

			// Support (simple) skipping:
			long startFrom = 0;
			String range = header.getProperty("Range");
			if (range != null) {
				if (range.startsWith("bytes=")) {
					range = range.substring("bytes=".length());
					int minus = range.indexOf('-');
					if (minus > 0)
						range = range.substring(0, minus);
					try {
						startFrom = Long.parseLong(range);
					} catch (NumberFormatException nfe) {
					}
				}
			}

			FileInputStream fis = new FileInputStream(f);
			fis.skip(startFrom);
			Response r = new Response(HTTP_OK, mime, fis);
			r.addHeader("Content-length", "" + (f.length() - startFrom));
			r.addHeader("Content-range", "" + startFrom + "-"
					+ (f.length() - 1) + "/" + f.length());
			return r;
		} catch (IOException ioe) {
			return new Response(HTTP_FORBIDDEN, MIME_PLAINTEXT,
					"FORBIDDEN: Reading file failed.");
		}
	}

	
	/**
	 * Starts as a standalone file server and waits for Enter.
	 */
	public static void main(String[] args) {
		start(args);
	}

	public static void start(String[] args) {
		PrintWriter sloppyLogger = new PrintWriter(System.out, true);
		sloppyLogger.println("NanoHTTPD 1.11 (C) 2001,2005-2008 Jarno Elonen\n"
				+ "(Command line options: [port] [--licence])\n");

		// Show licence if requested
		int lopt = -1;
		for (int i = 0; i < args.length; ++i)
			if (args[i].toLowerCase().endsWith("licence")) {
				lopt = i;
				sloppyLogger.println(LICENCE + "\n");
			}

		// Change port if requested
		int port = 8090;
		if (args.length > 0 && lopt != 0)
			port = Integer.parseInt(args[0]);

		if (args.length > 1 && args[1].toLowerCase().endsWith("licence"))
			sloppyLogger.println(LICENCE + "\n");

		serverInstance = null;
		try {
			serverInstance = new FileServer(port);
		} catch (IOException ioe) {
			System.err.println("Couldn't start server:\n" + ioe);
			System.exit(-1);
		}
		serverInstance.myFileDir = new File("");

		sloppyLogger.println("Now serving files in port " + port + " from \""
				+ new File("").getAbsolutePath() + "\"");
		sloppyLogger.println("Hit Enter to stop.\n");

		try {
			System.in.read();
			} catch (Throwable t) {
		};
	}

  public static void kill() {
    serverInstance = null;
  }


}
