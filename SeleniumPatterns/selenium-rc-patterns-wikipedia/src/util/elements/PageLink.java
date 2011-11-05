package util.elements;

import util.browserdriver.BrowserDriver;

public class PageLink extends ClickableElement {
	private Class<?> clazz;

	public PageLink(String locator, Class<?> clazz) {
		super(locator);
		this.clazz = clazz;
	}

	public Object clickToNewPage() {
		try {
			click();
			BrowserDriver.waitForPageToLoad(BrowserDriver.STANDARD_PAGE_LOAD_WAIT_TIME);
			return clazz.getConstructor().newInstance();
		} catch(Exception e) { 
			throw new RuntimeException(e);
		}
	}
}
