package com.qait.gold.testframework.pages4testing;

import com.qait.gold.testframework.pageElements4Testing.TextBlock;
import static org.junit.Assert.*;

public class SearchResultDetailedView {
	private	TextBlock documentTitle;
	private	TextBlock documentType;
	private TextBlock publication;
	private TextBlock actualAuthorName;
	private TextBlock searchResultTitle;
	private TextBlock searchResultPublication;
	private TextBlock searchResultDocumentType;
	
	public SearchResultDetailedView() {
		documentTitle = new TextBlock("ReplaceMe");	//TODO replace with document title element locator
		documentType = new TextBlock("ReplaceMe");	//TODO replace with document type element locator
		publication = new TextBlock("ReplaceMe");	//TODO replace with publication element locator
		actualAuthorName = new TextBlock("ReplaceMe"); //TODO replace with publication element locator
		searchResultTitle = new TextBlock("ReplaceMe");	//TODO replace with publication element locator
		searchResultPublication = new TextBlock("ReplaceMe");	//TODO replace with publication element locator
		searchResultDocumentType = new TextBlock("ReplaceMe");	//TODO replace with publication element locator
	}


	private boolean searchTermIsFound(String searchTerm) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean searchTermIsHighlightedInFullText(String authorName) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean allExpectedResultsMatchUp(String expectedAuthorName) {
		assertTrue(documentTitle.equals(searchResultTitle));
	    assertTrue(publication.equals(searchResultPublication));
	    assertTrue(documentType.equals(searchResultDocumentType));
	    assertTrue(actualAuthorName.equals(expectedAuthorName));
	    assertTrue(searchTermIsFound(expectedAuthorName));
	    assertTrue(searchTermIsHighlightedInFullText(expectedAuthorName));
		return true;
	}
}
