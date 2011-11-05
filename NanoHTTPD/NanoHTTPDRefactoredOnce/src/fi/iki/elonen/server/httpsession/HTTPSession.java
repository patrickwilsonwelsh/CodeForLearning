package fi.iki.elonen.server.httpsession;

import java.net.Socket;

import fi.iki.elonen.server.BasicServer;
import fi.iki.elonen.server.IServer;

public class HTTPSession implements Runnable {
	protected IServer server;
	private Thread thread;
	private RequestParser requestParser;
	private ResponseSender responseSender;

	public HTTPSession(Socket s, IServer server) {
		startSession(s, server);
	}

	public void startSession(Socket s, IServer server) {
		initSession(s);
		initThreads(server);
	}

	private void initSession(Socket s) {
		requestParser = new RequestParser(s);
		responseSender = new ResponseSender(s);
	}

	private void initThreads(IServer server) {
		this.server = server;
		thread = new Thread(this);
		thread.setDaemon(true); // SMELL configurable?
		thread.start();
	}
	
	public HTTPSession(Socket s){
		initSession(s);
	}
	
	public void run() {
		try {
			handleSingleSession(); 
		} catch (Exception e) {
			try {
				sendInternalServerError(e);
			} catch (Throwable t) { 
				throw new RuntimeException(t);
			}
			
		throw new RuntimeException(e);
		} 
	}

	protected void sendInternalServerError(Exception e) throws Exception {
		responseSender.sendError(BasicServer.HTTP_INTERNALERROR, "SERVER INTERNAL ERROR: Exception: " + e.getMessage());
	}

	protected void handleSingleSession() throws Exception {
		ParsedRequest parsedRequest = requestParser.parseRequest();
		if (parsedRequest.errorCode() != null) {
			responseSender.sendError(parsedRequest.errorCode(), parsedRequest.getErrorMessage());
		} else {
			handleResponse(parsedRequest);
		}
		
		requestParser.closeSession();
	}

	protected void handleResponse(ParsedRequest parsedRequest) throws Exception {
		Response r = server.serve(parsedRequest); //SMELL decouple from nano?
		if (r == null) 
			responseSender.sendError(BasicServer.HTTP_INTERNALERROR,
				"SERVER INTERNAL ERROR: Serve() returned a null response.");
		else 
			responseSender.sendResponse(r.getStatus(), r.getMimeType(), r.getHeader(), r.getData());
	}

	protected Thread getThread() {
		return thread;
	}

	public void stop() {
		thread.interrupt();
		thread = null;
	}

}
