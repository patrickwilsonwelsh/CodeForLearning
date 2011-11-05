package speakerrate.pages;

import util.BasePage;
import util.elements.PageLink;
import static org.junit.Assert.*;

public class Presentation extends BasePage {
  private static final String PAGE_IS_LOADED_CSS = "div[id=wrap]";
  public PageLink<PresentationRating> rateThisPresentation;
  
  public Presentation() {
    super();
    rateThisPresentation = PageLink.create("a[href$=ratings/new]", PresentationRating.class);
    assertTrue(containsText("Summary"));
    assertTrue(containsText("Ratings"));
    System.out.println(browserDriver.getPageUrl());
  }
   
  @Override
  public String getPageLoadedCssSelector() {
    return PAGE_IS_LOADED_CSS;
  }

}
