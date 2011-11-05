package util.elements;

import util.browserdriver.BrowserDriver;

public class CheckBox extends ClickableElement {

	public CheckBox(String locator) {
		super(locator);
	}
	
	public void check() {
		BrowserDriver.check(locator);
	}
}
