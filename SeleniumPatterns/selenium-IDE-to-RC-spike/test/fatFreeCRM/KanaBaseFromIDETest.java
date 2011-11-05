package fatFreeCRM;

import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import com.thoughtworks.selenium.SeleneseTestCase;

public class KanaBaseFromIDETest extends SeleneseTestCase {

  public void setUp() throws Exception {
    RemoteControlConfiguration config = new RemoteControlConfiguration();
    config.setPort(4444);
    config.setEnsureCleanSession(true);
    SeleniumServer jettyProxyInstance = new SeleniumServer(config);
    jettyProxyInstance.start();
    
    setUp("http://demo.fatfreecrm.com", "*chrome");
    selenium.windowMaximize();
    loginToFFCRM();
  }
  
  public void tearDown() throws Exception {
    selenium.click("link=Logout");
    selenium.waitForPageToLoad("30000");
  }

  protected void waitForTextPresent(String expectedText) throws InterruptedException {
    for (int second = 0;; second++) {
      if (second >= 60)
        fail("timeout");
      try {
        if (selenium.isTextPresent(expectedText))
          break;
      } catch (Exception e) {
      }
      Thread.sleep(1000);
    }
  }

  protected void waitForElementPresent(String idAttributeValue)
      throws InterruptedException {
        for (int second = 0;; second++) {
          if (second >= 60)
            fail("timeout");
          try {
            if (selenium.isElementPresent(idAttributeValue))
              break;
          } catch (Exception e) {
          }
      
          Thread.sleep(1000);
        }
      }

  private void loginToFFCRM() {
    selenium.open("/login");
    selenium.type("authentication_username", "seleniumrctester");
    selenium.type("authentication_password", "seleniumrctester");
    selenium.click("authentication_submit");
  }

}
