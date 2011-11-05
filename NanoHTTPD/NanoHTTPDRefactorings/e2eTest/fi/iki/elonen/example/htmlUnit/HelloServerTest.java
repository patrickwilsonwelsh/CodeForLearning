package fi.iki.elonen.example.htmlUnit;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import fi.iki.elonen.HelloServer;
import fi.iki.elonen.example.ThroughTheBrowserBaseFixture;

public class HelloServerTest extends ThroughTheBrowserBaseFixture {
	@Before
	public void setup() throws Exception {
		port = 8089;
		launchHelloServerExample(port);
		webClient = new WebClient();
	}
	
	@Test
	public void canGetBack_Hello_EnteredName() throws Exception 	{
		page = webClient.getPage(HTTP_LOCALHOST + port +  "/");
		assertTrue(page.asXml().contains("Hello server"));
		HtmlElement nameField = page.getElementByName("username");
		nameField.type("bob");
		HtmlElement submitButton = page.getElementById("submit");
		
		HtmlPage helloBobPage = submitButton.click();
		assertTrue(helloBobPage.asXml().contains("Hello, bob!"));
	}
	
  @After
  public void killFileServer() throws Exception {
     HelloServer.kill();
  }
}
