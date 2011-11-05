package speakerrate.pages;

import util.BasePage;
import util.elements.PageLink;

public class Home extends BasePage {
  private static final String PAGE_IS_LOADED_CSS = "a[class=presentations-index-link][href=presentations]";
  public static final String SPEAKER_RATE_DOMAIN = "http://localhost:8080/speakerrate";
  
  public PageLink<Presentations> managePresentations;
  
  public Home() {
    super();
    managePresentations = PageLink.create(PAGE_IS_LOADED_CSS, Presentations.class);
  }
  
  
  @Override
  public String getPageLoadedCssSelector() {
    return PAGE_IS_LOADED_CSS;
  }

}
