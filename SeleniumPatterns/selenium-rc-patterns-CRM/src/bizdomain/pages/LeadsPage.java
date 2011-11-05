package bizdomain.pages;

import util.BasePage;

public class LeadsPage extends BasePage {
	private static final String PAGE_IS_LOADED_CSS = "div:contains('Leads')";
	
	public LeadsPage() {
		super();
	}

	@Override
	public String getPageLoadedCssLocator() {
		return PAGE_IS_LOADED_CSS;
	}

}
