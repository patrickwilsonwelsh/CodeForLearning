package com.qait.gold.testframework.fixtures;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import fitlibrary.DoFixture;

/**
 * This is the FitNesse SetUp fixture invoked from FitNesse to set things like
 * host, port, browser, timeout, base Url etc.
 * @author QAIT
 */
public class SystemEnvironmentFixture extends DoFixture {

    private static String host = "localhost";
    private static int port = 4444;
    private static String browser = "*chrome";
    private static String timeout = "60000";
    private static String baseUrl = "http://www.google.com/";
    private static Selenium selenium;

    /**
     * @return the host
     */
    public static String getHost() {
        return host;
    }

    /**
     * @param aHost the host to set
     */
    public static void theHostIs(String aHost) {
        host = aHost;
    }

    /**
     * @return the port
     */
    public static int getPort() {
        return port;
    }

    /**
     * @param aPort the port to set
     */
    public static void thePortIs(int aPort) {
        port = aPort;
    }

    /**
     * @return the browser
     */
    public static String getBrowser() {
        return browser;
    }

    /**
     * @param aBrowser the browser to set
     */
    public static void theBrowserIs(String aBrowser) {
        browser = aBrowser;
    }

    /**
     * @return the base Url
     */
    public static String getBaseUrl() {
        return browser;
    }

    /**
     * @param aBrowser the base Url to set
     */
    public static void theBaseUrlIs(String aBaseUrl) {
        baseUrl = aBaseUrl;
    }

    /**
     * @return the timeout
     */
    public static String getTimeout() {
        return timeout;
    }

    /**
     * @param aTimeout the timeout to set
     */
    public static void theTimeoutIs(String aTimeout) {
        timeout = aTimeout;
    }

    /**
     * Creates a new Selenium instance, starts a session, maximizes the current window
     * and sets the default timeout
     * @param host the host on which Selenium tests are to run
     * @param port the port on which Selenium RC runs
     * @param browser the browser on which to run the tests
     * @param baseUrl the base Url for Selenium tests
     */
    private static void initSelenium(String host,int port,String browser,String baseUrl) {
        selenium = new DefaultSelenium(host, port, browser, baseUrl);
        selenium.start();
        selenium.windowMaximize();
        selenium.setTimeout(timeout);
    }
    
    /**
     * 
     * @return the current Selenium instance
     */
    public static Selenium getSeleniumDriver() {

       return  getSeleniumDriver(host,port,browser,baseUrl);
    }

    /**
     * 
     * @param baseUrl the base Url for Selenium tests
     * @return the current Selenium instance
     */
    public static Selenium getSeleniumDriver(String baseUrl) {

       return  getSeleniumDriver(host,port,browser,baseUrl);
    }

    /**
     * This method returns the current Selenium instance or creates
     * a new one
     * @param host the host on which Selenium tests are to run
     * @param port the port on which Selenium RC runs
     * @param browser the browser on which to run the tests
     * @param baseUrl the base Url for Selenium tests
     * @return the current Selenium instance
     */
    public static Selenium getSeleniumDriver(String host,int port,String browser,String baseUrl)
    {
        if (selenium == null) {
            initSelenium(host,port,browser,baseUrl);
        }

        return selenium;
    }

    
}//end class SystemEnvironmentFixture

