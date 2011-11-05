package fi.iki.elonen.example.fileserver;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import fi.iki.elonen.server.BasicServer;
import fi.iki.elonen.server.NanoHTTPD;
import fi.iki.elonen.server.httpsession.Response;
import fi.iki.elonen.util.PropertiesWriter;

public class FileServerExample extends BasicServer {
	private DirectoryLister directoryLister;
	public FileServerExample(int port) throws IOException {
		nano = new NanoHTTPD(port, this);
		sloppyLogger = getLoggerInstance();
		inputReader = initInputReader();
	}

	public static void main(String[] args) {
		start(args);
	}
	
	public static void start(String[] args) {
		int licenseArgumentIndex = showLicenseIfRequested(args, sloppyLogger);
		int port = changePortIfRequested(args, licenseArgumentIndex);

		try {
			new FileServerExample(port);
		} catch (IOException ioe) {
			serverFailureSoBlowUp(ioe);
		}
		printInstructions(sloppyLogger, port);

		try {
			inputReader.read();
			} catch (Throwable t) {
		};
	}

	static void printInstructions(PrintWriter sloppyLogger, int port) {
		sloppyLogger.println(HOWDY_PART_ONE);
		sloppyLogger.println(NOW_SERVING_FILES_IN_PORT + port + FROM +
				ESCAPED_QUOTE + LOCAL_ABSOLUTE_PATH + ESCAPED_QUOTE);
		sloppyLogger.println(HIT_ENTER_TO_STOP);
	}

	public Response serve(String fullUri, String httpMethod, Properties header,
			Properties parms) {
		PrintWriter sloppyLogger = getLoggerInstance();
		sloppyLogger.println(httpMethod + " '" + fullUri + "' ");
		PropertiesWriter writer = getPropertiesWriterInstance();
		writer.writeProperties(header, "HDR", sloppyLogger);
		writer.writeProperties(parms, "PRM", sloppyLogger);

		return serveFile(new File(CURRENT_DIRECTORY_SYMBOL), fullUri, header, true);
	}

	public Response serveFile(File homeDirectory, String uri, Properties header, boolean allowDirectoryListing) {
		directoryLister = new DirectoryLister(homeDirectory, uri, header);
		return directoryLister.showDirectoryOrFile();
	}

	public String getUri() {
		return directoryLister.getUri();
	}

}
