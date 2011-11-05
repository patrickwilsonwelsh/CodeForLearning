package util;

import util.browserdriver.BrowserDriver;
import bizdomain.common.CommonComponents;

public abstract class BasePage {
	public static CommonComponents common;
	
	public BasePage()  {
		common = CommonComponents.getInstance();
		waitUntilLoaded();
	}
	
	public boolean isLoaded() {
		return BrowserDriver.isElementPresent(getPageLoadedCssLocator());
	}
	
	public boolean isVisible() {
		return BrowserDriver.isElementVisible(getPageLoadedCssLocator());
	}

	public void waitUntilLoaded() {
		BrowserDriver.waitForElementVisible(getPageLoadedCssLocator());
	}

	public abstract String getPageLoadedCssLocator();

}
