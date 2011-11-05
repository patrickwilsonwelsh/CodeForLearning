package util.elements;

import util.browserdriver.BrowserDriver;


public class TextField extends ClickableElement {

	public TextField(String locator) {
		super(locator);
	}

	public void enter(String entry)  {
		BrowserDriver.type(locator, entry);
	}
	
	public String getText()  {
		return BrowserDriver.getText(locator);
	}

}
