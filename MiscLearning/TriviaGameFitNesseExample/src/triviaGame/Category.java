package triviaGame;

import java.util.HashMap;
import java.util.Map;

public class Category {
	public static final Category SCIENCE = new Category("science");
	public static final Category SPORTS = new Category("sports");
	public static final Category POP = new Category("pop");
	public static final Category ROCK = new Category("rock");
	public static final Category WINNING = new Category("winning");

	private static final Map<String, Category> categories = new HashMap<String, Category>();
	static {
		categories.put("science", Category.SCIENCE);
		categories.put("rock", Category.ROCK);
		categories.put("pop", Category.POP);
		categories.put("sports", Category.SPORTS);
		categories.put("winning", Category.WINNING);
	}

	public final String name;

	private Category(final String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public static Category parse(String string) {
		return categories.get(string);
	}
}