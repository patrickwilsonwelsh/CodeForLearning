package com.qait.gold.tests.powersearch.search.advanced;

import com.qait.gold.testframework.fixtures.TearDownFixture;
import com.qait.gold.testframework.pages4testing.AdvancedSearchPage;
import com.qait.gold.testframework.pages4testing.AdvancedSearchResultsPage;
import com.qait.gold.testframework.pages4testing.SearchResultDetailedView;
import com.qait.gold.testframework.powersearch.search.AdvancedSearchTestCase;
import org.junit.After;
import org.junit.Test;

/**
 * A simple JUnit test case to demonstrate the use of the framework
 * @author QAIT
 */
public class SimpleAuthorSearchTestCase extends AdvancedSearchTestCase{

    
    public SimpleAuthorSearchTestCase() throws Exception {
    	setUp();
    }

    @Override
    public void setUp() throws Exception
    {
    	super.setUp();
    }
    
	private String authorName = "William";
    private String searchType = "label=regexp:Author*";

    @Test
    /**
	 * This test case performs a search on author name and verifies the
	 * results returned
     */
    public void testSimpleAuthorSearchTestCase()
    {
        int searchResultIndex = 1;

        assertTrue(openAdvancedSearchPage());
        typeSearchTerm(authorName);
        selectSearchType(searchType);
        search();
        assertTrue(verifyResultsAreReturned());
        storeSearchResult(searchResultIndex);
        assertTrue(verifySearchResultDataNotEmpty());
        openSearchResultDetailedView(searchResultIndex);
        
        assertTrue(verifySearchResultTitleMatchesDetailedViewTitle());
        assertTrue(verifySearchResultPubMatchesDetailedViewPub());
        assertTrue(verifySearchResultDocumentTypeMatchesDetailedViewDocumentType());
        assertTrue(verifyAuthorInDetailedView(authorName));
        assertTrue(verifySearchTermIsFoundInDetailedView(authorName));
        assertTrue(verifySearchTermIsHighlightedInFullTextInDetailedView(authorName));
    }
    
@Test
public void testSimpleAuthorSearchTestCase_UsingPageFacadeDSLSemantics() {
    AdvancedSearchPage searchPage = new AdvancedSearchPage();
    AdvancedSearchResultsPage resultsPage = searchPage.search(searchType, authorName);
    assertTrue(resultsPage.verifyResultsAreReturned());
    
    int searchResultIndex = 1;
    SearchResultDetailedView detailedView = resultsPage.getResultsDetailedView(searchResultIndex);
    assertTrue(detailedView.allExpectedResultsMatchUp(authorName));
}   
    

    @After
    @Override
    public void tearDown()
    {
        TearDownFixture.closeBrowser();
    }

}//end class SimpleAuthorSearchTestCase
