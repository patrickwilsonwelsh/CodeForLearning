package bizdomain.panes;

import util.BasePane;
import util.browserdriver.BrowserDriver;
import util.elements.DhtmlLink;
import util.elements.TextLabel;
import bizdomain.pages.LoginPage;


@SuppressWarnings("unchecked")
public class TopRightLoggedInLinksSet extends BasePane {
	public static final String PANE_IS_LOADED_CSS = "div[id=welcome]";
	public TextLabel welcomeLabel;
	public DhtmlLink<QuickFindBox> quickFind;
	public DhtmlLink<Profile> profile;
	public DhtmlLink<LoginPage> logout;
	
	public TopRightLoggedInLinksSet()  {
		super();
		welcomeLabel = new TextLabel(PANE_IS_LOADED_CSS +" span[id='welcome_username']");
		quickFind = new DhtmlLink<QuickFindBox>(PANE_IS_LOADED_CSS + " a[id=jumper]", QuickFindBox.class);
		profile = new DhtmlLink<Profile>(PANE_IS_LOADED_CSS + " a[href$=profile]", Profile.class);
		logout = new DhtmlLink<LoginPage>(PANE_IS_LOADED_CSS + " a[href$=logout]", LoginPage.class);
	}
	
	public boolean isCompletelyLoaded() {
		if (! BrowserDriver.isElementPresent(PANE_IS_LOADED_CSS)) return false;
		if (! welcomeLabel.isPresent()) return false;
		if (! quickFind.isPresent()) return false;
		if (! profile.isPresent()) return false;
		if (! logout.isPresent()) return false;
		return true;
	}

	@Override
	public String getPageLoadedCssLocator() {
		return PANE_IS_LOADED_CSS;
	}
}
