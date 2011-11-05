package fi.iki.elonen.server.httpsession;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

import fi.iki.elonen.server.BasicServer;
import fi.iki.elonen.util.GmtDateFormatter;

public class ResponseSender {
	private PrintWriter responsePrintWriter;
	private OutputStream responseOutputStream;
	private Socket mySocket;
	private SimpleDateFormat gmtDateFormatter;
	private static final String CARRIAGE_RETURN_LINE_FEED = "\r\n"; //REFACTOR Remove duplicate
	
	public ResponseSender(Socket s) {
		this.mySocket = s;
		gmtDateFormatter = new GmtDateFormatter().gmtFormat();
	}

	//Refactor pass in entire response here, not its bits...
	protected void sendResponse(String status, String mime, Properties header, InputStream data) {
		try {
			blowUpIfNullStatus(status);
			initializeResponseWriter();
			printAllHeaderProperties(status, mime, header);
			if (data != null)  echoDataToSocket(data, responseOutputStream);
			flushAndClose(data, responseOutputStream);
			
		} catch (IOException ioe) {
			ioe.printStackTrace(); // Couldn't write? No can do.
		}
	}
	
	protected void blowUpIfNullStatus(String status) throws Error {
		if (status == null) throw new Error("sendResponse(): Status can't be null.");
	}
	
	private void initializeResponseWriter() throws IOException {
		responseOutputStream = mySocket.getOutputStream();
		responsePrintWriter = new PrintWriter(responseOutputStream);
	}

	private void printAllHeaderProperties(String status, String mime, Properties header) {
		printHttpStatus(status);
		printMimeType(mime);
		generateDateIfNecessary(header);
		printAllRemainingHeaderProperties(header);
		responsePrintWriter.flush();
	}
	
	@SuppressWarnings({ "unchecked"})
	private void printAllRemainingHeaderProperties(Properties header) {
		if (header != null) {
			Enumeration e = header.keys();
			while (e.hasMoreElements()) {
				printNextProperty(header, e);
			}
		}
		responsePrintWriter.print(CARRIAGE_RETURN_LINE_FEED);
	}
	
	private void printHttpStatus(String status) {
		responsePrintWriter.print("HTTP/1.0 " + status + CARRIAGE_RETURN_LINE_FEED);
	}
	
	private void generateDateIfNecessary(Properties header) {
		if (header == null || header.getProperty("Date") == null)
			responsePrintWriter.print("Date: " + gmtDateFormatter.format(new Date()) + CARRIAGE_RETURN_LINE_FEED);
	}

	private void printMimeType(String mime) {
		if (mime != null)
			responsePrintWriter.print("Content-Type: " + mime + CARRIAGE_RETURN_LINE_FEED);
	}

	@SuppressWarnings("unchecked")
	private void printNextProperty(Properties header, Enumeration e) {
		String key = (String) e.nextElement();
		String value = header.getProperty(key);
		responsePrintWriter.print(key + ": " + value + CARRIAGE_RETURN_LINE_FEED);
	}

	private void echoDataToSocket(InputStream data,
			OutputStream responseOutputStream) throws IOException {
		byte[] buff = new byte[2048];
		while (true) {
			int read = data.read(buff, 0, 2048);
			if (read <= 0)
				break;
			responseOutputStream.write(buff, 0, read);
		}
	}
	
	private void flushAndClose(InputStream data,
			OutputStream responseOutputStream) throws IOException {
		responseOutputStream.flush();
		responseOutputStream.close();
		if (data != null) data.close();
		mySocket.close();
	}

	protected void sendError(String status, String msg) throws Exception {
		sendResponse(status, BasicServer.MIME_PLAINTEXT, null, new ByteArrayInputStream(msg.getBytes()));
		throw new InterruptedException();
	}


}
