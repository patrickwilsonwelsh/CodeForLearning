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

	public void typeKeys(String locator, String searchString) throws Exception {
		BrowserDriver.typeKeys(locator, searchString);
		Thread.sleep(2000);
	}

}
