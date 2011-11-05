package speakerrate.pages;


import util.BasePage;
import util.elements.PageLink;
import util.elements.TextField;

public class CreateNewPresentation extends BasePage {
  private static final String PAGE_IS_LOADED_CSS = "input[type=text][name=title]";
  
  public TextField titleField;
  public TextField dateField;

  public PageLink<Presentation> submit;
  
  public CreateNewPresentation() {
    super();
    titleField = new TextField(PAGE_IS_LOADED_CSS);
    dateField = new TextField("input[type=text][id=presentedAt]");
    submit = PageLink.create("button[type=submit]", Presentation.class);
  }

  @Override
  public String getPageLoadedCssSelector() {
    return PAGE_IS_LOADED_CSS;
  }

}
