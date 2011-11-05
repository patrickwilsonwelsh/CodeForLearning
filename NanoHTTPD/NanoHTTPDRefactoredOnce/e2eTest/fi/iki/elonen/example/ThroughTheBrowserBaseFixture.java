package fi.iki.elonen.example;
import org.junit.After;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import fi.iki.elonen.example.fileserver.FileServerExample;
import fi.iki.elonen.example.gameoflifeserver.GameOfLifeServerExample;
import fi.iki.elonen.example.helloserver.HelloServerExample;
import fi.iki.elonen.server.BasicServer;

public class ThroughTheBrowserBaseFixture {
	protected static final String HTTP_LOCALHOST = "http://localhost:";
	protected static Thread httpServerThread;
	private static boolean notAlreadyRunning = true;
	protected static String port;
	protected WebClient webClient;
	protected HtmlPage page;
	
	//REFACTOR Remove duplicate code using template method. 
	
	protected void launchGameofLifeServerExample(final String port) {
		if (notAlreadyRunning) {
			Runnable runnable = new Runnable() {
				public void run() {
					GameOfLifeServerExample.start(new String[] { port });
				}
			};
			launchServerThread(runnable);
		}
	}
	

	protected void launchFileServerExample(final String port) {
		if (notAlreadyRunning) {
			Runnable runnable = new Runnable() {
				public void run() {
					FileServerExample.start(new String[] { port });
				}
			};
			launchServerThread(runnable);
		}
	}


	protected void launchHelloServerExample(final String port) {
		if (notAlreadyRunning) {
			Runnable runnable = new Runnable() {
				public void run() {
					HelloServerExample.start(new String[] { port });
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
		if (!notAlreadyRunning) {
			notAlreadyRunning = true;
			BasicServer.stop();
		}
	}
}
