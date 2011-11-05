package util.elements;

import util.browserdriver.BrowserDriver;

public class BaseElement {
	public String locator;

	public BaseElement(String locator) {
		this.locator = locator;
	}
	
	public boolean isPresent() {
		return BrowserDriver.isElementPresent(locator);
	}
	
	public boolean isVisible() { 
		return BrowserDriver.isElementVisible(locator);
	}
	
	public String getValue() {
		return BrowserDriver.getValue(locator);
	}

	public String getText() {
		return BrowserDriver.getText(locator);
	}

}
