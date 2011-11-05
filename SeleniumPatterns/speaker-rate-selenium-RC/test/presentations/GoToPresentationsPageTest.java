package presentations;

import org.junit.Test;

import speakerrate.pages.Presentations;


public class GoToPresentationsPageTest extends BaseWebTest {

  @Test
  public void canClick_ManagePresentationsLink_AndGoTo_PresentationsPage() throws Exception {
    Presentations presentationsPage = homePage.managePresentations.clickToNewPage();
    
  }
}
