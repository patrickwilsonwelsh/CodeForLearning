package account.login;

import org.junit.After;
import org.junit.Test;

import util.browserdriver.BrowserDriver;

public class LoginTest {
		
	@Test
	public void canLoginToFatFreeCRM() throws Exception {
//		BrowserDriver.open(DashBoard.HOME_PAGE_URL);
//		DashBoard homePage = new LoginPage().login("patrickwilsonwelsh", "password");
		
//		assertTrue(homePage.isLoaded());
	}
	
	@After
	public void baseTearDownSuite() throws Exception {
		BrowserDriver.stopEverything();
	}
}
