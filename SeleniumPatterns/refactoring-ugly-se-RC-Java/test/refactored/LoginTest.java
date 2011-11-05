package refactored;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import crappy.BrowserDriver;
import crappy.LandingPage;
import crappy.LoginPage;

public class LoginTest {
	private BrowserDriver browserDriver;	
    
    @Before
    public void setup() {
    	browserDriver = BrowserDriver.getInstance();
    }
    
    @Test
    public void commonNavigationMenu_IsPresentOnAllMainPages() {
    	LoginPage loginPage = new LoginPage();
    	LandingPage landingPage = loginPage.login("seleniumpatterns", "seleniumpatterns");   
	}
	
	@After
	public void tearDown() {
		browserDriver.stop();
	}

}
