package crappy;

import static org.junit.Assert.assertTrue;

import java.net.Socket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import com.thoughtworks.selenium.DefaultSelenium;

public class ClassicUglySeRCLoginTest {
	private static SeleniumServer seleniumServer;
	private static DefaultSelenium selenium;
	
    protected static final String SELENIUM_SERVER_HOST = "localhost";
    protected static final int SELENIUM_SERVER_PORT = 4444;	
    
    @Before
    public void setup() {
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
			ClassicUglySeRCLoginTest.selenium = new DefaultSelenium(
			SELENIUM_SERVER_HOST,
			SELENIUM_SERVER_PORT,
			"*firefox", "http://demo.fatfreecrm.com");
			selenium.start();
			selenium.setSpeed("0");
			selenium.setTimeout("60000");
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }

    
    @Test
    public void canLoginToApplication() {
    	selenium.open("http://demo.fatfreecrm.com/login");
    	selenium.waitForPageToLoad("60000");
    	
    	selenium.type("css=input[id=authentication_username]", "seleniumpatterns");
    	selenium.type("css=input[id=authentication_password]", "seleniumpatterns");
    	selenium.click("css=input[id=authentication_submit]");
    	selenium.waitForPageToLoad("60000");
    	
    	assertTrue(selenium.isElementPresent("css=div[id=welcome]"));
    	assertTrue(selenium.isElementPresent("css=div[id=welcome] span[id='welcome_username']"));
    	assertTrue(selenium.isElementPresent("css=div[id=welcome] a[id=jumper]"));
    	assertTrue(selenium.isElementPresent("css=div[id=welcome] a[href='/profile']"));
    	assertTrue(selenium.isElementPresent("css=div[id=welcome] a[href='/logout']"));
 	}
    
    
	
	@After
	public void tearDown() {
		selenium.close();
		selenium.stop();
		seleniumServer.stop();
	}

    

}
