package com.qait.gold.testframework.selenium.util;

import com.thoughtworks.selenium.DefaultSelenium;

public class SeleniumSingletonFactory {
    private static DefaultSelenium selenium;
    protected static String url;

    private SeleniumSingletonFactory() {
          //Cannot instantiate
    }

    public static DefaultSelenium getInstance() throws Exception {
        SeleniumProxySingleton.makeSureWeHaveAJettyProxyRunning();
        if (selenium == null) launchSeleniumBrowser();
        return selenium;
    }

    private static void launchSeleniumBrowser() throws Exception {
        url = "http://find.galegroup.com";
        selenium = new DefaultSelenium(SeleniumProxySingleton.SELENIUM_SERVER_HOST, SeleniumProxySingleton.SELENIUM_SERVER_PORT, getBrowserForTesting(), url);
        selenium.start();
        selenium.setSpeed("0");
        selenium.setTimeout("60000");
    }

    public static void stop() {
        selenium.close();
        selenium.stop();
        SeleniumProxySingleton.stopJettyProxy();
    }

    private static String getBrowserForTesting() {
        String defaultBrowser = "*firefox";  // "*iexplore" or "*firefox"
        String browserFromProperty = System.getProperty("seleniumTest.browser");
        return (null == browserFromProperty) ? defaultBrowser : browserFromProperty;
    }
}
