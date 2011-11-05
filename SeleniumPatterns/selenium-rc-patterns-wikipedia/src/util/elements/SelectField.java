package util.elements;

import util.browserdriver.BrowserDriver;

public class SelectField extends BaseElement {

	public SelectField(String locator) {
		super(locator);
	}
	
	public void select(String selection) {
		BrowserDriver.select(locator, selection);
	}

}
