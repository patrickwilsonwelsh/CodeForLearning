package fi.iki.elonen.example.selenium;

import org.junit.Test;


public class HelloServerTest extends ThroughSeleniumBaseFixture {
	@Test
	public void canEnterBob() throws Exception {
		launchHelloServerExample(8080);
		selenium.open("http://localhost:" + 8080);
		assertTextPresent("Hello server");
		
		selenium.type("username", "bob");
		selenium.click("submit");
		selenium.waitForPageToLoad("1000");
		assertTextPresent("Hello, bob!");
	}
}
