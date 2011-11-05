package util.browserdriver;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import util.elements.TextLabel;
import bizdomain.pages.EnglishMainPage;

import com.thoughtworks.selenium.DefaultSelenium;

public class BrowserDriver {
	public static final String FIREFOX = "*firefox";
	public static final String IEXPLORE = "*iexplore";
	
	
	public static final String STANDARD_PAGE_LOAD_WAIT_TIME =  "60000";
	public static final String STANDARD_DHTML_LOAD_WAIT_TIME = "60000";
	private static final String TRUE = "true";

	protected static String url;
	public static String selectedBrowser = FIREFOX;

	private static DefaultSelenium seleniumSingleton;
	
	private static Object Latch = new Object();
	private static final int MS_PER_SECOND = 1000;
	public static final String CSS_PREFIX = "css=";

	private BrowserDriver() {
		throw new RuntimeException("Do not instantiate me.");
	}

	private static DefaultSelenium getInstance() {
		synchronized (Latch) {
			if (seleniumSingleton == null) {
				seleniumSingleton = launchStaticSeleniumBrowser();
			}
			return seleniumSingleton;
		}
	}

	private static DefaultSelenium launchStaticSeleniumBrowser() {
		try {

			SeleniumProxySingleton.makeSureWeHaveAJettyProxyRunning();
			seleniumSingleton = new DefaultSelenium(
					SeleniumProxySingleton.SELENIUM_SERVER_HOST,
					SeleniumProxySingleton.SELENIUM_SERVER_PORT,
					getBrowserForTesting(), EnglishMainPage.WIKIPEDIA_DOMAIN);
			seleniumSingleton.start();
			seleniumSingleton.setSpeed("0");
			seleniumSingleton.setTimeout(STANDARD_PAGE_LOAD_WAIT_TIME);
			seleniumSingleton.windowMaximize();
			
			return seleniumSingleton;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String getBrowserForTesting() {
		return selectedBrowser;
	}

	public static void stopEverything() {
		killSelenium();
		SeleniumProxySingleton.stopJettyProxy();
	}

	public static void killSelenium() {
		synchronized (Latch) {
			if (seleniumSingleton != null) {
				try {
					seleniumSingleton.close();
					seleniumSingleton.stop();
				} catch (RuntimeException e) {
					throw (e);
				}
			}
			seleniumSingleton = null;
		}
	}

	public static void restartSelenium() {
		synchronized (Latch) {
			killSelenium();
			seleniumSingleton = getInstance();
		}
	}

	public static boolean isElementVisible(String cssLocator) {
		injectJqueryIfAbsent();
		return executeJavascript(JqueryCodeFactory.getVisibilityCode(cssLocator)).equals(TRUE);
	}

	public static void assertNoAlertPresent() {
		assertTrue(!getInstance().isAlertPresent());
	}

	public static void open(String url) throws Exception {
		getInstance().open(url);
		injectJqueryIfAbsent();
	}

	public static void check(String locator) {
		getInstance().check(LocatorStrategyIdentifier.prepareforSelenium(locator));
	}

	public static boolean isElementPresent(String locator) {
		if (LocatorStrategyIdentifier.isNonSeleniumCss(locator)) return isElementVisible(locator);
		return getInstance().isElementPresent(LocatorStrategyIdentifier.prepareforSelenium(locator));
	}

	public static String getValue(String locator) {
		return getInstance().getValue(LocatorStrategyIdentifier.prepareforSelenium(locator));
	}

	public static void chooseCancelOnNextConfirmation() {
		getInstance().chooseCancelOnNextConfirmation();
	}

	public static void chooseOkOnNextConfirmation() {
		getInstance().chooseOkOnNextConfirmation();
	}

	public static void click(String locator) {
		if (LocatorStrategyIdentifier.isNonSeleniumCss(locator)) executeJavascript(JqueryCodeFactory.getClickCode(locator));
		else {
			String preparedLocator = LocatorStrategyIdentifier.prepareforSelenium(locator);
			getInstance().click(preparedLocator);
		}
	}
	
	public static String getAlert() {
		return getInstance().getAlert();
	}

	public static String getConfirmation() {
		return getInstance().getConfirmation();
	}

	public static String getText(String locator) {
		if (LocatorStrategyIdentifier.isNonSeleniumCss(locator)) return executeJavascript(JqueryCodeFactory.getTextCode(locator));
		return getInstance().getText(LocatorStrategyIdentifier.prepareforSelenium(locator));
	}

	public static boolean isChecked(String locator) {
		return getInstance().isChecked(LocatorStrategyIdentifier.prepareforSelenium(locator));
	}

	public static void select(String locator, String selection) {
		getInstance().select(LocatorStrategyIdentifier.prepareforSelenium(locator), selection);

	}

	public static void type(String locator, String entry) {
		getInstance().type(LocatorStrategyIdentifier.prepareforSelenium(locator), entry);

	}

	public static void clickOkOnAlert() {
		getAlert();
	}

	public static void selectFrame(String locator) {
		getInstance().selectFrame(LocatorStrategyIdentifier.prepareforSelenium(locator));
	}

	public static void assertLabelContainsText(TextLabel element, String expectedText) {
		assertTrue(element.getText().contains(expectedText));
	}

	public static String executeJavascript(String javascript) {
		return getInstance().getEval(javascript);
	}
	
	public static void injectJqueryIfAbsent()  {
		if (jqueryDoesNotExistOnPage()) executeJavascript(JqueryCodeFactory.getJqueryLibraryString());
	}

	private static boolean jqueryDoesNotExistOnPage() {
		return getInstance().getEval(JqueryCodeFactory.getJqueryUndefinedString()).equals(TRUE);
	}
	
	public static void sleepForASecond() {
		try {
			Thread.sleep(MS_PER_SECOND);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void sleep(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static void waitForElementVisible(String cssLocator)  {
		for (int second = 0;; second++) {
			if (second >= Integer.valueOf(BrowserDriver.STANDARD_DHTML_LOAD_WAIT_TIME) / MS_PER_SECOND)
				fail("Timeout waiting for element to become visible.");

			if (isElementVisible(cssLocator)) break;
			sleepForASecond();
		}
	}
	
	public static void waitForElement_NOT_Visible(String cssLocator) throws Exception {
		for (int second = 0;; second++) {
			if (second >= Integer
					.valueOf(BrowserDriver.STANDARD_DHTML_LOAD_WAIT_TIME) / MS_PER_SECOND)
				fail("Timeout waiting for element to become not visible.");

			try {
				boolean elementPresent = isElementVisible(cssLocator);

				if (!elementPresent)
					break;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			sleepForASecond();
		}
	}
	
	public static void waitForPageToLoad(String standardPageLoadWaitTime) throws Exception {
		getInstance().waitForPageToLoad(standardPageLoadWaitTime);
		injectJqueryIfAbsent();
	}

	public static void waitForFrameToLoad(String frameAddress) {
		getInstance().waitForFrameToLoad(frameAddress,
				STANDARD_PAGE_LOAD_WAIT_TIME);
	}

	public static void reloadPage() throws Exception {
		getInstance().refresh();
		injectJqueryIfAbsent();
	}

	public static void typeKeys(String locator, String searchString) {
		getInstance().typeKeys(LocatorStrategyIdentifier.prepareforSelenium(locator), searchString);
	}

}
