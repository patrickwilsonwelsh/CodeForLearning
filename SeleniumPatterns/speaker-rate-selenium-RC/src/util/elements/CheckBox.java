package util.elements;


public class CheckBox extends ClickableElement {

  public CheckBox(String selector) {
    super(selector);
  }

  public void check() {
    browserDriver.check(selector);
  }
}
