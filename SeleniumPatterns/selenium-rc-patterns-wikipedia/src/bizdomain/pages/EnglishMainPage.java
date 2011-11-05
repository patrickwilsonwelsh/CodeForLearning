package bizdomain.pages;

import util.BasePage;
import util.elements.TextLabel;


public class EnglishMainPage extends BasePage {
	public static final String PAGE_IS_LOADED_CSS = "a[href='/wiki/Portal:Arts']";
	public static final String WIKIPEDIA_DOMAIN = "http://en.wikipedia.org";
	
	public TextLabel todaysFeaturedArticle;
	
	public EnglishMainPage() {
		super();
		todaysFeaturedArticle = new TextLabel("span[class=mw-headline]");
	}
	
	@Override
	public String getPageLoadedCssLocator() {
		return PAGE_IS_LOADED_CSS;
	}

}
