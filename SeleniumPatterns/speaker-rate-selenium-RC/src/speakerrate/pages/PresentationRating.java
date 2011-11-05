package speakerrate.pages;

import util.BasePage;
import static org.junit.Assert.*;

public class PresentationRating extends BasePage {
  private static final String PAGE_IS_LOADED_CSS = "div[id=contentScoreScale]";
  
  public PresentationRating() {
    super();
    assertTrue(containsText("E-mail:"));
    assertTrue(containsText("Content Score:"));
    assertTrue(containsText("Delivery Score:"));
    assertTrue(containsText("Comments:"));
  }

  @Override
  public String getPageLoadedCssSelector() {
    return PAGE_IS_LOADED_CSS;
  }

}
