package bizdomain.pages;

import util.BasePage;

public class DashBoard extends BasePage {
	private static final String PAGE_IS_LOADED_CSS = "a[class=active]:contains('Dashboard')";
	public static final String FFCRM_DOMAIN = "http://demo.fatfreecrm.com/login";
	
	public static final String HOME_PAGE_URL = FFCRM_DOMAIN + "";

	public DashBoard() {
		super();
	}

	@Override
	public String getPageLoadedCssLocator() {
		return PAGE_IS_LOADED_CSS;
	}

	



}
