package speakerrate.pages;

import util.BasePage;
import util.elements.PageLink;


public class Presentations extends BasePage {
  private static final String PAGE_IS_LOADED_CSS = "a[href='presentations/new']";
  
  public PageLink<CreateNewPresentation> createNewPresentation;
  
  public Presentations() {
    super();
    createNewPresentation = PageLink.create(PAGE_IS_LOADED_CSS, CreateNewPresentation.class);
  }

  @Override
  public String getPageLoadedCssSelector() {
    return PAGE_IS_LOADED_CSS;
  }

}
