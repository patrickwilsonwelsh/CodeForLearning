package bizdomain.pages;

import util.BasePage;

public class OpportunitiesPage extends BasePage {
	private static final String PAGE_IS_LOADED_CSS = "div:contains('Opportunities')";
	
	public OpportunitiesPage() {
		super();
	}

	@Override
	public String getPageLoadedCssLocator() {
		return PAGE_IS_LOADED_CSS;
	}

}
