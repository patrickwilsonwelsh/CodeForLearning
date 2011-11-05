package bizdomain.common;

import org.junit.AfterClass;
import org.junit.Before;

import util.browserdriver.BrowserDriver;
import bizdomain.pages.EnglishMainPage;


public class BaseWebTest {
	protected EnglishMainPage englishMainPage;
	
	@Before
	public void baseSetupMethod() throws Exception {
		BrowserDriver.open(EnglishMainPage.WIKIPEDIA_DOMAIN);
		englishMainPage = new EnglishMainPage();
	}
	
	@AfterClass
	public static void baseTearDownSuite() throws Exception {
		BrowserDriver.stopEverything();
	}
}
