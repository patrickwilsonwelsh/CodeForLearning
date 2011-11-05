package fi.iki.elonen.example.htmlUnit;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.WebClient;

import fi.iki.elonen.example.ThroughTheBrowserBaseFixture;

public class GameOfLifeServerTest extends ThroughTheBrowserBaseFixture {
	
	@Before
	public void setup() throws Exception {
		port = "8091";
		launchGameofLifeServerExample(port);
		webClient = new WebClient();
	}
	
	@Test
	public void canGetBackEmptyGrid() throws Exception {
		page = webClient.getPage(HTTP_LOCALHOST + port);
		assertTrue(page.asText().contains("....."));
	}
}
