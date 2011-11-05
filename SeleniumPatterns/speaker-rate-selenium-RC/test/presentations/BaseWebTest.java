package presentations;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;

import speakerrate.pages.Home;
import util.browserdriver.BrowserDriver;

public class BaseWebTest {
	protected Home homePage;
	protected static BrowserDriver browserDriver;

	@Before
	public void baseSetupMethod() throws Exception {
	  browserDriver = new BrowserDriver();
	  browserDriver.open(Home.SPEAKER_RATE_DOMAIN);
	  homePage = new Home();
	}

	@After
	public void tearDown() {
	}

	@AfterClass
	public static void baseTearDownSuite() throws Exception {
	  browserDriver.stopEverything();
	}
}
