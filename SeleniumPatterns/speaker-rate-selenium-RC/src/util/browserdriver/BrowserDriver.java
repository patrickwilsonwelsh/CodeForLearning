package util.browserdriver;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import speakerrate.pages.Home;

import com.thoughtworks.selenium.DefaultSelenium;

public class BrowserDriver {

  private static final String TRUE = "true";
  private static final String FASTEST_EXECUTION_SPEED = "0";
  public static final String FIREFOX = "*firefox";
  public static final String IEXPLORE = "*iexplore";
  public static final String DEFAULT_USERNAME = "seleniumrctester";
  public static final String DEFAULT_PASSWORD = "seleniumrctester";

  public static final String STANDARD_PAGE_LOAD_WAIT_TIME = "60000";
  public static final String STANDARD_DHTML_LOAD_WAIT_TIME = "60000";

  protected static String url;

  private static DefaultSelenium seSingleton;
  private static JqueryCodeFactory jsFactory;

  private static Object Latch = new Object();
  private static final int MS_PER_SECOND = 1000;
  public static final String CSS_PREFIX = "css=";

  public BrowserDriver() {
    jsFactory = new JqueryCodeFactory();
  }

  private DefaultSelenium getSe() {
    synchronized (Latch) {
      if (seSingleton == null)
        seSingleton = launchStaticSeleniumBrowser();

      return seSingleton;
    }
  }

  private DefaultSelenium launchStaticSeleniumBrowser() {
    try {

      SeleniumProxySingleton.makeSureWeHaveAJettyProxyRunning();
      seSingleton = new DefaultSelenium(
          SeleniumProxySingleton.SELENIUM_SERVER_HOST,
          SeleniumProxySingleton.SELENIUM_SERVER_PORT, getBrowserForTesting(),
          getDomainForTesting());
      setupSelenium();

      return seSingleton;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // TODO Parameterize via properties for particular domain.
  private String getDomainForTesting() {
    return Home.SPEAKER_RATE_DOMAIN;
  }

  private static void setupSelenium() {
    seSingleton.start();
    seSingleton.setSpeed(FASTEST_EXECUTION_SPEED);
    seSingleton.setTimeout(STANDARD_PAGE_LOAD_WAIT_TIME);
    seSingleton.windowMaximize();
    //TODO Find a way to inject jQuery on testrunner page in such a way that we can call seSingleton.getEval() on it 
    // without re-injecting jQuery on each page under test. :(
    
    // seSingleton.addScript(jsFactory.getJqueryLibraryString(), JqueryCodeFactory.WINDOW_JQUERY);
    // seSingleton.addLocationStrategy(SelectorStrategyIdentifier.JQUERY_CSS_SELECTOR_STRATEGY_NAME,
    // SelectorStrategyIdentifier.JQUERY_SELECTOR_STRATEGY_CONTENT);
  }

  // TODO Parameterize via properties for VM-specific browser
  private static String getBrowserForTesting() {
    return FIREFOX;
  }

  public void stopEverything() {
    killSelenium();
    SeleniumProxySingleton.stopJettyProxy();
  }

  public void killSelenium() {
    synchronized (Latch) {
      if (seSingleton != null) {
        try {
          seSingleton.close();
          seSingleton.stop();
        } catch (RuntimeException e) {
          throw (e);
        }
      }
      seSingleton = null;
    }
  }

  public void restartSelenium() {
    synchronized (Latch) {
      killSelenium();
      seSingleton = getSe();
    }
  }

  public boolean isElementPresent(String cssSelector) {
    return getSe().isElementPresent(SelectorStrategyIdentifier.prepareforSelenium(cssSelector));
  }

  public void assertNoAlertPresent() {
    assertTrue(!getSe().isAlertPresent());
  }

  public void open(String url) throws Exception {
    getSe().open(url);
  }

  public void check(String cssSelector) {
    getSe().check(SelectorStrategyIdentifier.prepareforSelenium(cssSelector));
  }

  public String getValue(String cssSelector) {
    return getSe().getValue(
        SelectorStrategyIdentifier.prepareforSelenium(cssSelector));
  }

  public void chooseCancelOnNextConfirmation() {
    getSe().chooseCancelOnNextConfirmation();
  }

  public void chooseOkOnNextConfirmation() {
    getSe().chooseOkOnNextConfirmation();
  }

  public void click(String cssSelector) {
    getSe().click(SelectorStrategyIdentifier.prepareforSelenium(cssSelector));
  }

  public String getAlert() {
    return getSe().getAlert();
  }

  public String getConfirmation() {
    return getSe().getConfirmation();
  }

  public String getText(String cssSelector) {
    return getSe().getText(
        SelectorStrategyIdentifier.prepareforSelenium(cssSelector));
  }

  public boolean isChecked(String cssSelector) {
    return getSe().isChecked(
        SelectorStrategyIdentifier.prepareforSelenium(cssSelector));
  }

  public void select(String cssSelector, String selection) {
    getSe().select(SelectorStrategyIdentifier.prepareforSelenium(cssSelector),
        selection);

  }

  public void type(String cssSelector, String entry) {
    getSe().type(SelectorStrategyIdentifier.prepareforSelenium(cssSelector),
        entry);

  }

  public void clickOkOnAlert() {
    getAlert();
  }

  public void selectFrame(String cssSelector) {
    getSe().selectFrame(
        SelectorStrategyIdentifier.prepareforSelenium(cssSelector));
  }

  public String executeJavascript(String javascript) {
    return getSe().getEval(javascript);
  }

  public void sleepForASecond() {
    try {
      Thread.sleep(MS_PER_SECOND);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void sleep(int milliseconds) {
    try {
      Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public void waitForElement_NOT_Visible(String cssSelector) throws Exception {
    for (int second = 0;; second++) {
      if (second >= Integer
          .valueOf(BrowserDriver.STANDARD_DHTML_LOAD_WAIT_TIME)
          / MS_PER_SECOND)
        fail("Timeout waiting for element " + cssSelector
            + " to become not visible.");

      try {
        boolean elementPresent = elementIsVisible(cssSelector);

        if (!elementPresent)
          break;
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
      sleepForASecond();
    }
  }

  public void waitForPageToLoad(String standardPageLoadWaitTime)
      throws Exception {
    getSe().waitForPageToLoad(standardPageLoadWaitTime);
  }

  public void waitForFrameToLoad(String frameAddress) {
    getSe().waitForFrameToLoad(frameAddress, STANDARD_PAGE_LOAD_WAIT_TIME);
  }

  public void reloadPage() throws Exception {
    getSe().refresh();
  }

  public void typeKeys(String cssSelector, String searchString) {
    getSe().typeKeys(
        SelectorStrategyIdentifier.prepareforSelenium(cssSelector),
        searchString);
  }

  public void injectJqueryIfAbsent() {
    if (jqueryDoesNotExistOnPage())
      
      executeJavascript(jsFactory.getJqueryLibraryString());
  }

  private boolean jqueryDoesNotExistOnPage() {
    return getSe().getEval(jsFactory.getJqueryUndefinedString()).equals(TRUE);
  }

  public void waitForElementVisible(String cssLocator) {
    for (int second = 0;; second++) {
      if (second >= Integer.valueOf(BrowserDriver.STANDARD_DHTML_LOAD_WAIT_TIME)  / MS_PER_SECOND)
        fail("Timeout waiting for element to become visible.");

      if (elementIsVisible(cssLocator)) break;
      sleepForASecond();
    }
  }

  public boolean elementIsVisible(String cssLocator) {
    injectJqueryIfAbsent() ;
    return getSe().getEval(jsFactory.getVisibilityCode(cssLocator)).equals(TRUE);
  }
  
  public boolean textIsPresent(String expectedText) {
    return seSingleton.isTextPresent(expectedText);
  }

  public String getPageUrl() {
    return seSingleton.getLocation();
  }

}
