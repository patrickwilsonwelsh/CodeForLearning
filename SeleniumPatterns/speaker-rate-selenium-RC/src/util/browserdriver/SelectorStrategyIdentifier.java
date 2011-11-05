package util.browserdriver;

public class SelectorStrategyIdentifier {

  private static final String CSS_SELECTOR_PREFIX = "css=";

  public static String prepareforSelenium(String selector) {
    return prependCssSelectorPrefix(selector);
  }

  public static String prependCssSelectorPrefix(String selector) {
    return CSS_SELECTOR_PREFIX + selector;
  }
}
