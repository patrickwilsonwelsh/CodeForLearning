package fatFreeCRM;

public class NewCampaignTest extends KanaBaseFromIDETest {
  public void testNewCampaign() throws Exception {
    waitForElementPresent("tabs");
    selenium.open("/campaigns");
    
    selenium.click("link=? Create Campaign");
    waitForElementPresent("campaign_name");

    String expectedText = "BrandNewCampaign";
    selenium.type("campaign_name", expectedText);
    selenium.click("campaign_submit");
    waitForTextPresent(expectedText);

    verifyTrue(selenium.isTextPresent(expectedText));
    selenium.click("link=Delete!");
  }

}
