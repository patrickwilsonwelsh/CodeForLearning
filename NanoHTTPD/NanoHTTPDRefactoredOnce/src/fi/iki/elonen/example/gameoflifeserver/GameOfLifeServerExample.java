package fi.iki.elonen.example.gameoflifeserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import fi.iki.elonen.server.BasicServer;
import fi.iki.elonen.server.NanoHTTPD;
import fi.iki.elonen.server.httpsession.Response;

public class GameOfLifeServerExample extends BasicServer {

	private static final String SINGLE_ROW_OF_FIVE_DOTS = ".....";
	private static final String LINE_BREAK = "<BR>";

	public GameOfLifeServerExample(int port) throws IOException {
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
			new GameOfLifeServerExample(port);
		} catch (IOException ioe) {
			serverFailureSoBlowUp(ioe);
		}
		printInstructions(sloppyLogger, port);

		try {
			inputReader.read();
			} catch (Throwable t) {
		};
	}
	
	private static void printInstructions(PrintWriter sloppyLogger, int port) {
	}

	public Response serve(String uri, String httpMethod, Properties header,
			Properties parms) {

		return new Response(BasicServer.HTTP_OK, "text/html", generateEmptyGrid());
	}

	private String  generateEmptyGrid() {
		return   SINGLE_ROW_OF_FIVE_DOTS + LINE_BREAK
		       + SINGLE_ROW_OF_FIVE_DOTS + LINE_BREAK
		       + SINGLE_ROW_OF_FIVE_DOTS + LINE_BREAK
		       + SINGLE_ROW_OF_FIVE_DOTS + LINE_BREAK
		       + SINGLE_ROW_OF_FIVE_DOTS + LINE_BREAK;
	}
}
