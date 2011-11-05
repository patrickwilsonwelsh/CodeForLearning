package bizdomain.pages;

import util.BasePage;

public class ContactsPage extends BasePage {
	private static final String PAGE_IS_LOADED_CSS = "div:contains('Contacts')";
	
	public ContactsPage() {
		super();
	}

	@Override
	public String getPageLoadedCssLocator() {
		return PAGE_IS_LOADED_CSS;
	}

}
