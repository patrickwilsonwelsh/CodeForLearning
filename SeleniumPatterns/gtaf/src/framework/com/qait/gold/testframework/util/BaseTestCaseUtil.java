package com.qait.gold.testframework.util;

import com.thoughtworks.selenium.Selenium;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * utility methods to be used in all test cases
 * @author QAIT
 */
public class BaseTestCaseUtil {

    /**open web page and verify its title
     *
     * @param selenium
     * @param pageUrl
     * @param pageTitle
     * @param waitTime
     * @param clearCookies
     */
    public static boolean openAndVerifyPageTitle(Selenium selenium, String pageUrl, String pageTitle, String waitTime,
            boolean clearCookies) {

        if (clearCookies) {
            selenium.deleteAllVisibleCookies();
        }

        selenium.open(pageUrl);

        if (waitTime != null) {
            selenium.waitForPageToLoad(waitTime);
        }

        if (pageTitle != null) {
            return verifyPageTitle(selenium, pageTitle);
        }
        
        return true;
    }//end

    /**open web page and verify its title
     *
     * @param selenium
     * @param pageTitle
     */
    public static boolean verifyPageTitle(Selenium selenium, String pageTitle) {
        return selenium.getTitle().matches(pageTitle);
    }//end

    /**
     * match pattern in a given string ignoring case
     * @param content
     * @param regx
     * @param quoteRegx
     * @return
     */
    public static boolean matchIgnoreCase(String content, String regx, boolean quoteRegx) {
        return getIndex(content, regx, true, quoteRegx) >= 0;

    }//end matchIgnoreCase

    /**
     * match pattern in a given string with case
     * @param content
     * @param regx
     * @param quoteRegx
     * @return
     */
    public static boolean match(String content, String regx, boolean quoteRegx) {
        return getIndex(content, regx, false, quoteRegx) >= 0;

    }//end matchIgnoreCase

    /**
     * 
     * @param content
     * @param regx
     * @param ignoreCase
     * @param quoteRegx
     * @return first matching index of a given pattern
     */
    public static int getIndex(String content, String regx, boolean ignoreCase, boolean quoteRegx) {

        if (quoteRegx) {
        }
        regx = Pattern.quote(regx);

        Pattern pattern = Pattern.compile(regx, Pattern.UNICODE_CASE);

        if (ignoreCase) {
            pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        }
        
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            if (matcher.start() != matcher.end()) {
                return matcher.start();
            }

        }//end while

        return -1;

    }//end getIndexIgnoreCase

}//end class BaseTestCaseUtil

