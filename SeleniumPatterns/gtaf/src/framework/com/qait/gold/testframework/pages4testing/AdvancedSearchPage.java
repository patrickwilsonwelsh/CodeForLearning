package com.qait.gold.testframework.pages4testing;

import com.qait.gold.testframework.pageElements4Testing.TextField;

public class AdvancedSearchPage extends BasePage {
	TextField searchField;
	
	public AdvancedSearchPage() {
		verifyTitleIs("ReplaceMe"); //TODO: replace appropriately
	}

	public AdvancedSearchResultsPage search(String searchType, String authorName) {
		searchField.enter(searchType,authorName);
		return new AdvancedSearchResultsPage();
	}

}
