package com.qait.gold.testframework.powersearch.util;

import com.thoughtworks.selenium.Selenium;

/**
 *define utility methods for power search
 * @author QAIT
 */
public class PowerSearchTestCaseUtil {

    /**click on advanced search link
     *
     * @param selenium
     */
    public static void clickAdvancedSearch(Selenium selenium) {
        clickAndWaitAdvancedSearch(selenium, null);
    }

    /**click on advanced search link
     *
     * @param selenium
     * @param waitTime
     */
    public static void clickAndWaitAdvancedSearch(Selenium selenium, String waitTime) {
        selenium.click("link=Advanced Search");

        if (waitTime != null) {
            selenium.waitForPageToLoad(waitTime);
        }

        
    }

    /**create pattern to verify power search page's title
     * Power Search's default page is customizable so it could Home Page, Advanced Search...
     * @return
     */
    public static String createDefaultPageTitlePattern()
    {
        StringBuffer sb = new StringBuffer();

        sb.append("(");
        sb.append(PowerSearchPageTitlePatterns.POWER_SEARCH_HOME_PAGE);
        sb.append(")");
        sb.append("|");

        sb.append("(");
        sb.append(PowerSearchPageTitlePatterns.POWER_SEARCH_BROWSE_SUBJECTS);
        sb.append(")");
        sb.append("|");

        sb.append("(");
        sb.append(PowerSearchPageTitlePatterns.POWER_SEARCH_ADVANCED_SEARCH);
        sb.append(")");
        sb.append("|");

        sb.append("(");
        sb.append(PowerSearchPageTitlePatterns.POWER_SEARCH_BROWSE_PUBLICATIONS);
        sb.append(")");

        return sb.toString();
    }
    
}//end class PowerSearchTestCaseUtil

