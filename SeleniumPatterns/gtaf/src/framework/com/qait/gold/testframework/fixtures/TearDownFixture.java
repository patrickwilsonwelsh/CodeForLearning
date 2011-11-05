package com.qait.gold.testframework.fixtures;

import fitlibrary.DoFixture;

/**
 * This is the FitNesse tear down fixture. This is called after every test execution. 
 * @author QAIT
 */
public class TearDownFixture extends DoFixture {

	/**
	 * Close the test browser instance
	 */
    public static void closeBrowser() {
        SystemEnvironmentFixture.getSeleniumDriver().stop();
    }

}//end class TearDownFixture
