package fi.iki.elonen.example;
import org.junit.After;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import fi.iki.elonen.FileServer;
import fi.iki.elonen.HelloServer;

public class ThroughTheBrowserBaseFixture {
	protected static final String HTTP_LOCALHOST = "http://localhost:";
	protected static Thread httpServerThread;
	private static boolean notAlreadyRunning = true;
	protected static int port;
	protected WebClient webClient;
	protected HtmlPage page;
	
	//REFACTOR Remove duplicate code using template method. 
	
	protected void launchFileServerExample(final int port) {
		if (notAlreadyRunning) {
			Runnable runnable = new Runnable() {
				public void run() {
					FileServer.start(new String[] { String.valueOf(port) });
				}
			};
			launchServerThread(runnable);
		}
	}


	protected void launchHelloServerExample(final int port) {
		if (notAlreadyRunning) {
			Runnable runnable = new Runnable() {
				public void run() {
					HelloServer.start(port);
				}
			};
			launchServerThread(runnable);
		}
	}

	protected Thread launchServerThread(Runnable runnable) {
		httpServerThread = new Thread(runnable);
		httpServerThread.start();
		notAlreadyRunning = false;
		return httpServerThread;
	}
	
	@After
	public void tearDown() throws Exception {
		killServerExample();
	}
	
	protected void killServerExample() throws Exception {
		httpServerThread.interrupt();
	}
}
