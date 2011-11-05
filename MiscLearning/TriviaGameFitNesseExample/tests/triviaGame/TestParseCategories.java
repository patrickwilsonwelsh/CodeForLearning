package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
import junit.framework.TestCase;

public class TestParseCategories extends TestCase {
	public void testValidStrings() {
		assertEquals(Category.SCIENCE, Category.parse("science"));
		assertEquals(Category.ROCK, Category.parse("rock"));
		assertEquals(Category.POP, Category.parse("pop"));
		assertEquals(Category.SPORTS, Category.parse("sports"));
		assertEquals(Category.WINNING, Category.parse("winning"));
	}
}