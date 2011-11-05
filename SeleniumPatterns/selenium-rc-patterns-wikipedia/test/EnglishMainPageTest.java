import static org.junit.Assert.assertTrue;

import org.junit.Test;

import bizdomain.common.BaseWebTest;


public class EnglishMainPageTest extends BaseWebTest{
	@Test
	public void shouldContainTodaysFeaturedArticle() {
		assertTrue(englishMainPage.todaysFeaturedArticle.isVisible());
	}
	
}
