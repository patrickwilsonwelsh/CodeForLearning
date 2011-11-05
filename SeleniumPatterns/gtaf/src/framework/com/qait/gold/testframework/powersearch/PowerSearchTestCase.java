package com.qait.gold.testframework.powersearch;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.qait.gold.testframework.selenium.util.SeleniumSingletonFactory;
import com.qait.gold.testframework.BaseTestCase;
import com.qait.gold.testframework.fixtures.powersearch.PowerSearchSystemEnvironmentFixture;
import com.qait.gold.testframework.powersearch.util.PowerSearchPageTitlePatterns;
import com.qait.gold.testframework.powersearch.util.PowerSearchTestCaseUtil;
import com.thoughtworks.selenium.DefaultSelenium;

/**
 * This class acts as base test case for Power Search product test cases
 * @author QAIT
 */
public class PowerSearchTestCase extends BaseTestCase {

    public DefaultSelenium selenium;
	
    @Override
    public void setUp() throws Exception {
    	
    }
    
    @BeforeSuite
    public void setupSelenium() throws Exception {
        SeleniumSingletonFactory.getInstance();
    }

    @AfterSuite
    public void tearDownSelenium() throws Exception {
    	//SeleniumSingletonFactory.stop();
    }

    /**open Power Search default search page
     * 
     */
    public boolean openPowerSearchDefaultPage() {
        return openAndVerifyPageTitle(PowerSearchSystemEnvironmentFixture.getProductUrl() + "&userGroupName=" + PowerSearchSystemEnvironmentFixture.getUserGroupName() + "&password=" + PowerSearchSystemEnvironmentFixture.getPassword(),
                PowerSearchTestCaseUtil.createDefaultPageTitlePattern(), getDefaultWaitTime(), true);
    }

    /**open advanced search page and verify its title
     *
     */
    public boolean openAndVerifyAdvancedSearchPage() {
        PowerSearchTestCaseUtil.clickAndWaitAdvancedSearch(selenium, getDefaultWaitTime());
        return verifyPageTitle(PowerSearchPageTitlePatterns.POWER_SEARCH_ADVANCED_SEARCH);
    }

}//end class PowerSearchTestCase

