package bizdomain.pages;

import util.BasePage;

public class AccountsPage extends BasePage {
	private static final String PAGE_IS_LOADED_CSS = "span[id=create_account_title]:contains('Accounts')";
	
	public AccountsPage() {
		super();
	}

	@Override
	public String getPageLoadedCssLocator() {
		return PAGE_IS_LOADED_CSS;
	}

}
