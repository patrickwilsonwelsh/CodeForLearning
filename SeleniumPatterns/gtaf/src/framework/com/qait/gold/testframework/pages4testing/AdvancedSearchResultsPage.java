package com.qait.gold.testframework.pages4testing;

public class AdvancedSearchResultsPage extends BasePage{

	public boolean verifyResultsAreReturned() {
		// TODO Auto-generated method stub
		verifySearchResultDataNotEmpty();
		return false;
	}

	private void verifySearchResultDataNotEmpty() {
		// TODO Auto-generated method stub
		
	}

	public boolean dataNotEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public SearchResultDetailedView getResultsDetailedView(int searchResultIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
