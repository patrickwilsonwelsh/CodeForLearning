package util.browserdriver;


public class LocatorStrategyIdentifier {

	private static final String CSS_PREFIX = "css=";

	public static String prepareforSelenium(String locator) {
		if (isCss(locator)) return prependCssSuffix(locator);
		
		return locator;
	}

	private static String prependCssSuffix(String locator) {
		return CSS_PREFIX + locator;
	}

	public static boolean isCss(String locator) {
		return (!locator.contains("/"));
	}

	public static boolean isNonSeleniumCss(String locator) {
		return locator.contains(":eq(");
	}

}
