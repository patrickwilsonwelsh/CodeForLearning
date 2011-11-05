package com.qait.gold.testframework.powersearch.search.ui;

/**
 * This class encapsulates all the UI elements of Advanced Search page
 * This is mainly used by Action Dispatcher
 * @author QAIT
 */
public class AdvancedSearchUiLocator {

    /**get locator for search term input field
     *
     * @param inputFieldIndex
     * @return
     */
    public static String getSearchTermInputField(int inputFieldIndex) {
        return "inputFieldValue_" + inputFieldIndex;
    }

    /**get locator for search type drop down box
     *
     * @param searchTypeListIndex
     * @return
     */
    public static String getSearchTypeList(int searchTypeListIndex) {
        return "inputFieldName_" + searchTypeListIndex;
    }

    /**get search button locator*/
    public static String getSearchButton() {
        return "//input[@value='Search']";
    }

    /**get browse link,associated with the search index, locator
     *
     * @param browseLinkIndex
     * @param idPrefix
     * @return
     */
    public static String getBrowseLinkId(int browseLinkIndex, String idPrefix) {
        return "//a[@id='browseField_" + browseLinkIndex + "_" + idPrefix + "']";
    }
    
    /**
     * 
     * @param searchResultIndex
     * @return
     */
    public static String getSearchResultContainer(int searchResultIndex) {
        return getSearchResultBox() + "/" + getSearchResultLi(searchResultIndex);
    }

    /**
     * 
     * @return
     */
    public static String getSearchResultBox() {
        return "//div[@id='resultsBox']";
    }//end

    /**
     * 
     * @param searchResultIndex
     * @return
     */
    public static String getSearchResultLi(int searchResultIndex) {
        return "ul[" + searchResultIndex + "]/li[" + searchResultIndex + "]";
    }

    /**
     * 
     * @return
     */
    public static String getPubDetailsDiv() {
        return "div[@class='pub_details']";
    }

    /**
     * 
     * @return
     */
    public static String getTitleDiv() {
        return "span[@class='citation-title']/div[@class='pic_Title']";
    }

    /**
     * 
     * @return
     */
    public static String getPubDiv() {
        return "div[@class='pic_Pub']";
    }

    /**
     * 
     * @return
     */
    public static String getDetailsDiv() {
        return "div[@class='pic_Detail']";
    }

    /**
     * 
     * @return
     */
    public static String getSearchResultsSelectedTab() {
        return "//div[@id='pdsTabs']/ul/li/p[@class='selected']";
    }

    /**
     * 
     * @param searchResultIndex
     * @return
     */
    public static String getSearchResultDetailsContainer(int searchResultIndex) {
        return getSearchResultPubDetailsContainer(searchResultIndex) + "/" + getDetailsDiv() + "/span[@class='txt_Detail']";
    }

    /**
     * 
     * @param searchResultIndex
     * @return
     */
    public static String getSearchResultPubDetailsContainer(int searchResultIndex) {
        return getSearchResultContainer(searchResultIndex) + "/" + getPubDetailsDiv();
    }

    /**
     * 
     * @param searchResultIndex
     * @return
     */
    public static String getSearchResultPubContainer(int searchResultIndex) {
        return getSearchResultPubDetailsContainer(searchResultIndex) + "/" + getPubDiv() + "/a";
    }

    /**
     * 
     * @param searchResultIndex
     * @return
     */
    public static String getSearchResultTitleContainer(int searchResultIndex) {
        return getSearchResultPubDetailsContainer(searchResultIndex) + "/" + getTitleDiv() + "/a";
    }
}//end class AdvancedSearchUiLocator

