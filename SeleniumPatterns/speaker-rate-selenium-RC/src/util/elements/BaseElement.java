package util.elements;

import util.browserdriver.BrowserDriver;

public abstract class BaseElement {
  public String selector;
  public BrowserDriver browserDriver;

  public BaseElement(String selector) {
    this.selector = selector;
    this.browserDriver = new BrowserDriver();
  }

  public final boolean isPresent() {
    return browserDriver.isElementPresent(selector);
  }

  public final boolean isVisible() {
    return browserDriver.elementIsVisible(selector);
  }
}
