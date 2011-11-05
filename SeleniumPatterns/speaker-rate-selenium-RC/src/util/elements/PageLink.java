package util.elements;

import util.BasePage;
import util.browserdriver.BrowserDriver;

public class PageLink<T extends BasePage> extends ClickableElement {

  public static <P extends BasePage> PageLink<P> create(String selector,
      Class<P> clazz) {
    return new PageLink<P>(selector, clazz);
  }

  private Class<T> clazz;

  private PageLink(String selector, Class<T> clazz) {
    super(selector);
    this.clazz = clazz;
  }

  public T clickToNewPage() {
    try {
      click();
      browserDriver.waitForPageToLoad(BrowserDriver.STANDARD_PAGE_LOAD_WAIT_TIME);
      return clazz.getConstructor().newInstance();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
