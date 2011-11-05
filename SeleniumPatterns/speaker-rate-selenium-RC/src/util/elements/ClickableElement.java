package util.elements;


public class ClickableElement extends BaseElement {

  public ClickableElement(String selector) {
    super(selector);
  }

  public void click() {
    browserDriver.click(selector);
  }
}