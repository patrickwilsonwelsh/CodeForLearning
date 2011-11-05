package bizdomain.pages;

import util.BasePage;

public class CampaignsPage extends BasePage {
	private static final String PAGE_IS_LOADED_CSS = "div:contains('Campaigns')";
	
	public CampaignsPage() {
		super();
	}

	@Override
	public String getPageLoadedCssLocator() {
		return PAGE_IS_LOADED_CSS;
	}

}
