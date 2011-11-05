package util.elements;

import util.browserdriver.BrowserDriver;

public class RadioButton extends ClickableElement {

	public RadioButton(String locator) {
		super(locator);
	}
	
	public void click() {
		BrowserDriver.click(locator);
	}

	public boolean isChecked() {
		return BrowserDriver.isChecked(locator);
	}
		
}

