package com.qait.gold.testframework;

import com.qait.gold.testframework.fixtures.powersearch.PowerSearchSystemEnvironmentFixture;
import com.qait.gold.testframework.util.BaseTestCaseUtil;
import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Selenium;

/**
 * This class is the base test case for Gold Automation Test Framework
 * Product specific test cases will extend from this class
 * @author QAIT
 */
abstract public class BaseTestCase extends SeleneseTestCase {

    abstract public void setUp() throws Exception;
    
    /**open web page and verify its title
     * @param pageUrl
     * @param pageTitle
     * @param waitTime
     * @param clearCookies
     */
    public boolean openAndVerifyPageTitle(String pageUrl, String pageTitle, String waitTime, boolean clearCookies) {
       return BaseTestCaseUtil.openAndVerifyPageTitle(selenium, pageUrl, pageTitle, waitTime, clearCookies);
    }//end

    /**verify current page's title
     * @param pageTitle
     */
    public boolean verifyPageTitle(String pageTitle) {
        return BaseTestCaseUtil.verifyPageTitle(selenium, pageTitle);
    }//end

    //default wait time for Selenium functions
     private String defaultWaitTime = PowerSearchSystemEnvironmentFixture.getTimeout();

    /**
     * @return the defaultWaitTime
     */
    public String getDefaultWaitTime() {
        return defaultWaitTime;
    }

    /**
     * @param defaultWaitTime the defaultWaitTime to set
     */
    public void setDefaultWaitTime(String defaultWaitTime) {
        this.defaultWaitTime = defaultWaitTime;
    }

    /**
     * @param string the string to test for not empty
     */
    public void assertNotEmpty(String string)
    {
        assertTrue(string.length() > 0);
    }

    /**
     * @param string the string to test for empty
     */
    public void assertEmpty(String string)
    {
        assertTrue(string.length() < 1);
    }

    /**
     * return the current Selenium instance
     * @return
     */
    public Selenium getSelenium() {
        return this.selenium;
    }

}//end class

