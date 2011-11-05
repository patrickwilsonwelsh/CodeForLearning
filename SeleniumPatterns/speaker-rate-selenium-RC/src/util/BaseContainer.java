package util;

import util.browserdriver.BrowserDriver;

public abstract class BaseContainer {
  protected BrowserDriver browserDriver = new BrowserDriver();
  
  public BaseContainer() {
    injectJquery();
  }

  protected void injectJquery() {
      browserDriver.injectJqueryIfAbsent();
  }

  public final boolean isLoaded() {
  	return browserDriver.isElementPresent(getPageLoadedCssSelector());
  }

  public final boolean isVisible() {
  	return browserDriver.elementIsVisible(getPageLoadedCssSelector());
  }

  public final void waitUntilLoaded() {
    browserDriver.waitForElementVisible(getPageLoadedCssSelector());
  }
  
  public final boolean containsText(String expectedText) {
    return browserDriver.textIsPresent(expectedText);
  }

  public abstract String getPageLoadedCssSelector();

}
