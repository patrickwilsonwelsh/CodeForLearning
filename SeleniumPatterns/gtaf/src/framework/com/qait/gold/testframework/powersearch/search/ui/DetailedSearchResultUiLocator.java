package com.qait.gold.testframework.powersearch.search.ui;


/**
 * This class encapsulates all the UI elements of Detailed View page
 * This is mainly used by Action Dispatcher
 * @author QAIT
 */
public class DetailedSearchResultUiLocator {

	/**
	 * 
	 * @return
	 */
    public static String getTitle() {
        return "//h1";
    }

    /**
     * 
     * @return
     */
    public static String getPub() {
        return "//span[@class='title']/a";
    }

    /**
     * 
     * @return
     */
    public static String getFullText() {
        return "//div[@class='document-text']/body";
    }

    /**
     * 
     * @return
     */
    public static String getShowDetailsLink() {
        return "//div[@id='metadataDown_show']/a";
    }

    /**
     * 
     * @return
     */
    public static String getHideDetailsLink() {
        return "//div[@id='metadataDown_hide']/a";
    }

    /**
     * 
     * @return
     */
    public static String getDetailsBox() {
        return "//div[@id='showMeta']";
    }

    /**
     * 
     * @return
     */
    public static String getDetailsContainer() {
        return getDetailsBox() + "/table/tbody";
    }

    /**
     * 
     * @return
     */
    public static String getAuthorLabel() {
        return getDetailsContainer() + "/tr[1]/td[1]";//"/tr/td[contains(text(),'Author.*')";
    }

    /**
     * 
     * @return
     */
    public static String getAuthorName() {
        return getDetailsContainer() + "/tr[1]/td[2]";
    }

    /**
     * 
     * @return
     */
    public static String getDocumentTypeLabel() {
        return getDetailsContainer() + "/tr[2]/td[1]";
    }

    /**
     * 
     * @return
     */
    public static String getDocumentType() {
        return getDetailsContainer() + "/tr[2]/td[2]";
    }

    /**
     * 
     * @return
     */
    public static String getLibraryLinksContainer() {
        return "//ul[@id='docSummary-librarylinks']";
    }

    /**
     * 
     * @return
     */
    public static String getIllLibraryLink() {
        return getLibraryLinksContainer() + "/li[1]/a";
    }

    /**
     * 
     * @return
     */
    public static String getVs2LibraryLink() {
        return getLibraryLinksContainer() + "/li[2]/a";
    }

    /**
     * 
     * @return
     */
    public static String getHitHighlight() {
        return "//span[@CLASS='hitHighlite']";
    }

    /**
     * 
     * @return
     */
    public static String getCitationHeader() {
        return "//div[@class='citation_header']";
    }
}//end class DetailedSearchResultUiLocator

