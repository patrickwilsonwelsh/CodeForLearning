package presentations;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import speakerrate.pages.CreateNewPresentation;
import speakerrate.pages.Presentation;
import speakerrate.pages.PresentationRating;
import speakerrate.pages.Presentations;

public class CreateNewPresentationTest extends BaseWebTest {
  private CreateNewPresentation newPresentationPage;

  @Before
  public void init() {
    Presentations presentationsPage = homePage.managePresentations.clickToNewPage();
    newPresentationPage = presentationsPage.createNewPresentation.clickToNewPage();
    assertTrue(newPresentationPage.titleField.isVisible());
    assertTrue(newPresentationPage.dateField.isVisible());    
  }

  @Test
  public void canCreateNewPresentation() throws Exception {
    newPresentationPage.titleField.enter("Why Statics In Java Are Really, Really Evil" + Math.random());
    newPresentationPage.dateField.enter("01/30/2011");
    Presentation newPresentationSummaryPage = newPresentationPage.submit.clickToNewPage();
    assertTrue(newPresentationSummaryPage.containsText("Rate this presentation"));
    
    PresentationRating ratingPage = newPresentationSummaryPage.rateThisPresentation.clickToNewPage();
    
  }
  
}