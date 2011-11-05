package com.qait.gold.testframework.powersearch.search.action;

import com.qait.gold.testframework.powersearch.search.SearchResult;
import com.qait.gold.testframework.powersearch.search.ui.AdvancedSearchUiLocator;
import com.qait.gold.testframework.powersearch.search.util.AdvancedSearchTestCaseUtil;
import com.thoughtworks.selenium.Selenium;

/**
 * This class models all the actions that can be performed on the
 * Advanced Search page	
 * @author QAIT
 */
public class AdvancedSearchActionDispatcher {

    /**click search button
     *
     * @param selenium
     * @param waitTime
     */
    public static void clickSearchButton(Selenium selenium, String waitTime) {
        selenium.click(AdvancedSearchUiLocator.getSearchButton());

        if (waitTime != null) {
            selenium.waitForPageToLoad(waitTime);
        }

    }

     /**type search term in the given input field
     *
     * @param searchTerm
     * @param inputFieldIndex
     */
    public static void typeSearchTerm(Selenium selenium,String searchTerm, int inputFieldIndex) {
        selenium.type(AdvancedSearchUiLocator.getSearchTermInputField(inputFieldIndex), searchTerm);
    }

     /**get selected search type from the given drop down box
     *
     * @param searchTypeLabel
     * @param searchTypeListIndex
     */
    public static void getSelectSearchType(Selenium selenium, int searchTypeListIndex) {
        selenium.getSelectedLabel(AdvancedSearchUiLocator.getSearchTypeList(searchTypeListIndex));
    }

    /**select search type from the given drop down box
     *
     * @param searchTypeLabel
     * @param searchTypeListIndex
     */
    public static void selectSearchType(Selenium selenium,String searchTypeLabel, int searchTypeListIndex) {
        selenium.select(AdvancedSearchUiLocator.getSearchTypeList(searchTypeListIndex), searchTypeLabel);
    }

     /**get all the select options from a given drop down box
     *
     * @param searchTypelistIndex
     * @return
     */
    public static String[] getAllSearchTypeLabels(Selenium selenium,int searchTypelistIndex) {
        return selenium.getSelectOptions(AdvancedSearchUiLocator.getSearchTypeList(searchTypelistIndex));
    }

    /**check if given search type is browsable or not for the first drop down box
     *
     * @param selenium
     * @param searchTypeLabel
     * @param searchTypeListIndex
     * @return
     */
    public static boolean isBrowseFieldVisible(Selenium selenium, String searchTypeLabel, int searchTypeListIndex) {
        String idPrefix = AdvancedSearchTestCaseUtil.getSearchTypeShortLabel(searchTypeLabel);

        if (idPrefix != null) {

            return selenium.isElementPresent(AdvancedSearchUiLocator.getBrowseLinkId(searchTypeListIndex, idPrefix)) &&
                    selenium.isVisible(AdvancedSearchUiLocator.getBrowseLinkId(searchTypeListIndex, idPrefix));
        }

        return false;
    }//end

    /**
     * 
     * @param selenium
     * @param searchResultIndex
     * @return
     */
    public static SearchResult getSearchResult(Selenium selenium, int searchResultIndex) {
        SearchResult searchResult = null;

        if (selenium.isElementPresent(AdvancedSearchUiLocator.getSearchResultPubDetailsContainer(searchResultIndex))) {
            searchResult = new SearchResult();
            searchResult.setTitle(getSearchResultTitle(selenium, searchResultIndex));
            searchResult.setPub(getSearchResultPub(selenium, searchResultIndex));
            searchResult.setDetails(getSearchResultDetails(selenium, searchResultIndex));
            searchResult.setDocumentType(getSelectedSearchResultTabLabel(selenium));
        }

        return searchResult;
    }//end

    /**
     * 
     * @param selenium
     * @param searchResultIndex
     * @return
     */
    public static String getSearchResultTitle(Selenium selenium, int searchResultIndex) {
        return selenium.getText(AdvancedSearchUiLocator.getSearchResultTitleContainer(searchResultIndex));
    }

    /**
     * 
     * @param selenium
     * @param searchResultIndex
     * @return
     */
    public static String getSearchResultPub(Selenium selenium, int searchResultIndex) {
        return selenium.getText(AdvancedSearchUiLocator.getSearchResultPubContainer(searchResultIndex));
    }

    /**
     * 
     * @param selenium
     * @param searchResultIndex
     * @return
     */
    public static String getSearchResultDetails(Selenium selenium, int searchResultIndex) {
        return selenium.getText(AdvancedSearchUiLocator.getSearchResultDetailsContainer(searchResultIndex));
    }

    /**
     * 
     * @param selenium
     * @return
     */
    public static String getSelectedSearchResultTabLabel(Selenium selenium) {
        return AdvancedSearchTestCaseUtil.getSelectedTabLabel(selenium.getText(AdvancedSearchUiLocator.getSearchResultsSelectedTab()));
    }

    /**
     * 
     * @param selenium
     * @param searchResultIndex
     * @param waitTime
     */
    public static void openSearchResultDetailedView(Selenium selenium,int searchResultIndex,String waitTime)
    {
        selenium.click(AdvancedSearchUiLocator.getSearchResultTitleContainer(searchResultIndex));

        if(waitTime != null)
        {
            selenium.waitForPageToLoad(waitTime);
        }
    }
}//end class AdvancedSearchActionDispatcher

