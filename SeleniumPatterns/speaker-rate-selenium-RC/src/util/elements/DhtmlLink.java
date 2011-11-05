package util.elements;

import util.BasePane;

public class DhtmlLink<T extends BasePane> extends ClickableElement {

  public static <P extends BasePane> DhtmlLink<P> create(String selector,
      Class<P> clazz) {
    return new DhtmlLink<P>(selector, clazz);
  }

  private Class<T> clazz;

  private DhtmlLink(String selector, Class<T> clazz) {
    super(selector);
    this.clazz = clazz;
  }

  public T clickToNewContainer() {
    try {
      click();
      return clazz.getConstructor().newInstance();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
