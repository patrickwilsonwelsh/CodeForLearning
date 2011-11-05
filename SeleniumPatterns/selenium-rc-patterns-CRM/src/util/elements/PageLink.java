package util.elements;

import util.browserdriver.BrowserDriver;

public class PageLink<T> extends ClickableElement {
	private Class<T> clazz;

	public PageLink(String locator, Class<T> clazz) {
		super(locator);
		this.clazz = clazz;
	}

	public T clickToNewPage() {
		try {
			click();
			BrowserDriver.waitForPageToLoad(BrowserDriver.STANDARD_PAGE_LOAD_WAIT_TIME);
			return clazz.getConstructor().newInstance();
		} catch(Exception e) { 
			throw new RuntimeException(e);
		}
	}
}
