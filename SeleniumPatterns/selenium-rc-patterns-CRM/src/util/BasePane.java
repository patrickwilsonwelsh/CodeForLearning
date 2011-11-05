package util;

import util.browserdriver.BrowserDriver;

public abstract class BasePane {
	
	public BasePane() {
		waitUntilLoaded();
	}
	
	public boolean isLoaded() {
		return BrowserDriver.isElementPresent(getPageLoadedCssLocator());
	}
	
	public boolean isVisible() {
		return BrowserDriver.isElementVisible(getPageLoadedCssLocator());
	}

	public void waitUntilLoaded()  {
		BrowserDriver.waitForElementVisible(getPageLoadedCssLocator());
	}

	public abstract String getPageLoadedCssLocator();
}
