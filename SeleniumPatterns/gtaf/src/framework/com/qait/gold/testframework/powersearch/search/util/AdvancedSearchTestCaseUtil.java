package com.qait.gold.testframework.powersearch.search.util;

/**
 * utility methods 
 * @author QAIT
 */
public class AdvancedSearchTestCaseUtil {

	/**
	 * 
	 * @param selectedTabText
	 * @return
	 */
    public static String getSelectedTabLabel(String selectedTabText) {
        return selectedTabText.split("\\(")[0];
    }

    /**get short search index e.g. Author(au) here au is short search index
     *
     * @param searchTypeLabel
     * @return
     */
    public static String getSearchTypeShortLabel(String searchTypeLabel) {
        if (searchTypeLabel.indexOf("(") > 0) {
            int startIndex = searchTypeLabel.lastIndexOf("(");
            return searchTypeLabel.substring(startIndex + 1, searchTypeLabel.lastIndexOf(")"));
        }

        return null;
    }
}//end class AdvancedSearchTestCaseUtil

