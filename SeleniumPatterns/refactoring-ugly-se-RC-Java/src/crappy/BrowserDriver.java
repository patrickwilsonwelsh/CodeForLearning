package crappy;

import java.net.Socket;

import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import com.thoughtworks.selenium.DefaultSelenium;

public class BrowserDriver {
	
	private static BrowserDriver instance;
	private static SeleniumServer seleniumServer;
	private static DefaultSelenium selenium;
	
    protected static final String SELENIUM_SERVER_HOST = "localhost";
    protected static final int SELENIUM_SERVER_PORT = 4444;
	private static final String STANDARD_TIMEOUT_IN_MILLISECONDS = "60000";	
	
	private BrowserDriver(){
		selenium = launchSelenium();
	
	}

	private DefaultSelenium launchSelenium() {
    	try {
    		
			Socket socket = null;
			
			try {
			    socket = new Socket(SELENIUM_SERVER_HOST, SELENIUM_SERVER_PORT);
			    throw new RuntimeException("Did not expect anything to be running on " + SELENIUM_SERVER_PORT);
			
			} catch(Exception e) {
			    System.out.println("Nothing is listening on port " + SELENIUM_SERVER_PORT);
			    System.out.println("Launching SeleniumServer on port " + SELENIUM_SERVER_PORT);
			    RemoteControlConfiguration config = new RemoteControlConfiguration();
			    config.setPort(SELENIUM_SERVER_PORT);
			    config.setEnsureCleanSession(true);
			    seleniumServer = new SeleniumServer(config);
			    seleniumServer.start();
			}
			
			finally {
			    if (socket != null) socket.close();
			}
			selenium = new DefaultSelenium(
			SELENIUM_SERVER_HOST,
			SELENIUM_SERVER_PORT,
			"*firefox", "http://demo.fatfreecrm.com");
			selenium.start();
			selenium.setSpeed("0");
			selenium.setTimeout("60000");
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return selenium;
	}

	public static BrowserDriver getInstance() {
		if (null == instance) instance = new BrowserDriver();
		
		return instance;
	}
	
	
	public void enterTextIn(String locator, String entry) {
		selenium.type(locator, entry);
		
	}

	public void openPage(String url) {
		selenium.open(url);
		
	}

	public void clickToNewPage(String locator) {
		selenium.click(locator);
		selenium.waitForPageToLoad(STANDARD_TIMEOUT_IN_MILLISECONDS);
	}

	public void stop() {
		selenium.close();
		selenium.stop();
		seleniumServer.stop();	
	}

}
