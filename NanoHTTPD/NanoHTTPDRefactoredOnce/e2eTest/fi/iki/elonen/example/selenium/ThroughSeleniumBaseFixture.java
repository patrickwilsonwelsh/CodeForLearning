package fi.iki.elonen.example.selenium;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;

import com.thoughtworks.selenium.DefaultSelenium;

import fi.iki.elonen.example.ThroughTheBrowserBaseFixture;

public class ThroughSeleniumBaseFixture extends ThroughTheBrowserBaseFixture {
	protected DefaultSelenium selenium;
	
	@Before
	public void init() throws Exception {
	  int proxyPort = 4444;
		SeleniumProxySingleton.makeSureWeHaveAJettyProxyRunning(proxyPort);
		launchSelenium(proxyPort);
	}

	private void launchSelenium(int proxyPort) {
		selenium = new DefaultSelenium("localhost", proxyPort, "*firefox","http://localhost");
		selenium.start();
	}

	@After
	public void stop() throws Exception {
		if (null != selenium) selenium.stop();
		httpServerThread.interrupt();
	}

	protected void assertTextPresent(String string) {
		assertTrue(selenium.isTextPresent(string));
	}

}
