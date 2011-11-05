/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qait.gold.testframework.powersearch.search;

import com.qait.gold.testframework.powersearch.PowerSearchTestCase;
import com.qait.gold.testframework.powersearch.search.action.AdvancedSearchActionDispatcher;
import com.qait.gold.testframework.powersearch.search.ui.AdvancedSearchUiLocator;
import com.qait.gold.testframework.powersearch.search.ui.DetailedSearchResultUiLocator;
import com.qait.gold.testframework.util.BaseTestCaseUtil;

/**
 *Base Test case for Advanced Search test cases
 * @author QAIT
 */
public class AdvancedSearchTestCase extends PowerSearchTestCase {

    private int searchTermFieldIndex = 0;
    private int searchTypeFieldIndex = 0;
    //Stores the current selected search results
    private SearchResult searchResult = null;

    public AdvancedSearchTestCase() throws Exception {
        super();
        setUp();
    }

    /**open advanced search page
     *
     */
    public boolean openAdvancedSearchPage() {
        return (openPowerSearchDefaultPage() && openAndVerifyAdvancedSearchPage());
    }

    /**perform search
     *
     */
    public void search() {
        AdvancedSearchActionDispatcher.clickSearchButton(selenium, getDefaultWaitTime());
    }

    /**verify search results are populated
     *
     */
    public boolean verifyResultsAreReturned() {
        return selenium.isElementPresent(AdvancedSearchUiLocator.getSearchResultContainer(1));
    }

    /**verify that no results are populated
     *
     */
    public boolean verifyNoResultsAreReturned() {
        return !selenium.isElementPresent(AdvancedSearchUiLocator.getSearchResultContainer(1));
    }

    /**type search term in the next empty input field.
     * 
     * @param searchTerm
     */
    public void typeSearchTerm(String searchTerm) {
        typeSearchTerm(searchTerm, searchTermFieldIndex++);
    }

    /**type search term in the given input field
     *
     * @param searchTerm
     * @param inputFieldIndex
     */
    private void typeSearchTerm(String searchTerm, int inputFieldIndex) {
        AdvancedSearchActionDispatcher.typeSearchTerm(selenium, searchTerm, inputFieldIndex);
    }

    /**get selected search type from the first drop down box
     *
     * @param SearchTypeLabel
     */
    private void getSelectedSearchType() {
        getSelectedSearchType(0);
    }

    /**get selected search type from the given drop down box
     *
     * @param SearchTypeLabel
     */
    private void getSelectedSearchType(int searchTypeListIndex) {
        AdvancedSearchActionDispatcher.getSelectSearchType(selenium, searchTypeListIndex);
    }

    /**select search type from the next unselected drop down box
     *
     * @param SearchTypeLabel
     */
    public void selectSearchType(String SearchTypeLabel) {
        selectSearchType(SearchTypeLabel, searchTypeFieldIndex++);
    }

    /**select search type from the given drop down box
     *
     * @param searchTypeLabel
     * @param searchTypeListIndex
     */
    private void selectSearchType(String searchTypeLabel, int searchTypeListIndex) {
        AdvancedSearchActionDispatcher.selectSearchType(selenium, searchTypeLabel, searchTypeListIndex);
    }

    /**check if given search type is browsable or not for the first drop down box
     *
     * @param searchTypeLabel
     * @return
     */
    public boolean isBrowseFieldVisible(String searchTypeLabel) {
        return isBrowseFieldVisible(searchTypeLabel, 0);
    }//end

    /**check if given search type is browsable or not for a given drop down box
     *
     * @param searchTypeLabel
     * @param searchTypeListIndex
     * @return
     */
    public boolean isBrowseFieldVisible(String searchTypeLabel, int searchTypeListIndex) {
        return AdvancedSearchActionDispatcher.isBrowseFieldVisible(selenium, searchTypeLabel, searchTypeListIndex);
    }//end

    /**
     * 
     * @return the first search result
     */
    private SearchResult getSearchResult() {
        return getSearchResult(1);
    }

    /**
     * 
     * @param searchResultIndex
     * @return return the result at searchResultIndex index starting at 1
     */
    private SearchResult getSearchResult(int searchResultIndex) {
        return AdvancedSearchActionDispatcher.getSearchResult(selenium, searchResultIndex);
    }
    
    /**
     * click on the result at searchResultIndex to open the detailed view
     * @param searchResultIndex
     */
    public void openSearchResultDetailedView(int searchResultIndex) {
        AdvancedSearchActionDispatcher.openSearchResultDetailedView(selenium, searchResultIndex, getDefaultWaitTime());
    }

    /**
     * store the result at searchResultIndex for future use
     * @param searchResultIndex
     */
    public void storeSearchResult(int searchResultIndex) {

        searchResult = getSearchResult(searchResultIndex);

    }

    /**
     * @return
     */
    public boolean verifySearchResultDataNotEmpty() {

        try {
            assertNotNull(searchResult);
            assertNotNull(searchResult.getTitle());
            assertNotEmpty(searchResult.getTitle());
            assertNotNull(searchResult.getPub());
            assertNotEmpty(searchResult.getPub());
            assertNotNull(searchResult.getDetails());
            assertNotEmpty(searchResult.getDetails());
        } catch (AssertionError e) {
            return false;
        }

        return true;
    }
    
    /**
     * 
     * @return 
     */
    public boolean verifySearchResultTitleMatchesDetailedViewTitle() {

        if (BaseTestCaseUtil.match(selenium.getText(DetailedSearchResultUiLocator.getTitle()),
                searchResult.getTitle(), true)) {
            return true;
        }

        return false;

    }

    /**
     * 
     * @return
     */
    public boolean verifySearchResultPubMatchesDetailedViewPub() {

        if (BaseTestCaseUtil.match(selenium.getText(DetailedSearchResultUiLocator.getPub()),
                searchResult.getPub(), true)) {
            return true;
        }

        return false;
    }

    /**
     * 
     * @return
     */
    public boolean verifySearchResultDocumentTypeMatchesDetailedViewDocumentType() {
        if (selenium.getText(DetailedSearchResultUiLocator.getDocumentType()).indexOf(
                searchResult.getDocumentType().trim().split("((es)|(s))$")[0]) >= 0) {
            return true;
        }

        return false;
    }

    /**
     * 
     * @param searchTerm
     * @return
     */
    public boolean verifySearchTermIsFoundInDetailedView(String searchTerm) {
        if (verifyTitleContainsSearchTermInDetailedView(searchTerm) || verifyPubContainsSearchTermInDetailedView(searchTerm) ||
                verifyFulltextContainsSearchTermInDetailedView(searchTerm)) {
            return true;
        }

        return false;
    }

    /**
     * 
     * @param searchTerm
     * @return
     */
    public boolean verifyTitleContainsSearchTermInDetailedView(String searchTerm) {
        return selenium.getText(DetailedSearchResultUiLocator.getTitle()).indexOf(searchTerm) >= 0;
    }

    public boolean verifyPubContainsSearchTermInDetailedView(String searchTerm) {
        return BaseTestCaseUtil.matchIgnoreCase(selenium.getText(DetailedSearchResultUiLocator.getPub()),
                searchTerm, true);
    }

    /**
     * 
     * @param searchTerm
     * @return
     */
    public boolean verifyFulltextContainsSearchTermInDetailedView(String searchTerm) {

        if (selenium.isElementPresent(DetailedSearchResultUiLocator.getFullText())) {
            return BaseTestCaseUtil.matchIgnoreCase(selenium.getText(DetailedSearchResultUiLocator.getFullText()),
                    searchTerm, true);
        }

        return true;
    }

    /**
     * 
     * @param searchTerm
     * @return
     */
    public boolean verifySearchTermIsHighlightedInFullTextInDetailedView(String searchTerm) {
        if (selenium.isElementPresent(DetailedSearchResultUiLocator.getFullText()) &&
                verifyFulltextContainsSearchTermInDetailedView(searchTerm)) {
            return selenium.getText(DetailedSearchResultUiLocator.getHitHighlight()).equalsIgnoreCase(searchTerm);
        }
        return true;
    }

    /**
     * 
     * @param authorName
     * @return
     */
    public boolean verifyAuthorInDetailedView(String authorName) {

        if (BaseTestCaseUtil.matchIgnoreCase(selenium.getText(DetailedSearchResultUiLocator.getAuthorName()),
                authorName, true)) {
            return true;
        }

        return false;
    }
}//end class AdvancedSearchTestCase

