package account.login;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import util.browserdriver.BrowserDriver;
import bizdomain.pages.DashBoard;
import bizdomain.pages.LoginPage;

public class LoginTest {
		
	@Test
	public void canLoginToFatFreeCRM() throws Exception {
		BrowserDriver.open(DashBoard.HOME_PAGE_URL);
		DashBoard homePage = new LoginPage().login(LoginPage.USERNAME, LoginPage.PASSWORD);
		
		assertTrue(homePage.isLoaded());
	}
	
	@After
	public void baseTearDownSuite() throws Exception {
		BrowserDriver.stopEverything();
	}
}
