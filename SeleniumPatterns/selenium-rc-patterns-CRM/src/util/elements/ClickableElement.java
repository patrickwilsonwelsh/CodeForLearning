package util.elements;

import util.browserdriver.BrowserDriver;


public class ClickableElement extends BaseElement  {
	
	public ClickableElement(String locator) {
		super(locator);
	}
	
	public void click() {
		BrowserDriver.click(locator);
	}
}