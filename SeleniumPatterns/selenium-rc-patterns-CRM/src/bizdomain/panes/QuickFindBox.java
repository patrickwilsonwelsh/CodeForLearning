package bizdomain.panes;

import util.BasePane;
import util.browserdriver.BrowserDriver;
import util.elements.ClickableElement;
import util.elements.PageLink;
import util.elements.TextField;


public class QuickFindBox<T> extends BasePane {
	private static final String PAGE_IS_LOADED_CSS = "div[id=jumpbox]";
	private static final String INPUT_QUERY_CSS = "input[id=auto_complete_query]";
	
	public TextField searchEntryField;
	public ClickableElement accountsMenuItem;

	public QuickFindBox() throws Exception {
		super();
		searchEntryField = new TextField(INPUT_QUERY_CSS);
		accountsMenuItem = new ClickableElement("a:contains('Accounts')");
	}

	@Override
	public String getPageLoadedCssLocator() {
		return PAGE_IS_LOADED_CSS;
	}
	
	public T searchForAccount(String searchString, Class<T> clazz) throws Exception {
		accountsMenuItem.click();
		BrowserDriver.waitForElementVisible(accountsMenuItem.locator);
		searchEntryField.typeKeys(INPUT_QUERY_CSS, searchString);
		PageLink<T> highlightedAutoCompleteDropDown = new PageLink<T>("div[id=auto_complete_dropdown] li[class=selected] strong[class=highlight]", clazz);
		
		BrowserDriver.waitForElementVisible(highlightedAutoCompleteDropDown.locator);
		highlightedAutoCompleteDropDown.clickToNewPage();
		return  clazz.getConstructor().newInstance();

	}
	

//	public BasePage searchForAccount(String searchString, Class<T> clazz) throws Exception {
//		DhtmlLink<T> searchDropDown;
//		assertTrue(BrowserDriver.isElementVisible(INPUT_QUERY_CSS));
//		BrowserDriver.typeKeys(INPUT_QUERY_CSS, searchString);
//		Thread.sleep(2000);
//		
//		String highlightedAutoCompleteDropDown = "div[id=auto_complete_dropdown] li[class=selected] strong[class=highlight]";
//		BrowserDriver.click(highlightedAutoCompleteDropDown);
//		
//		BrowserDriver.waitForPageToLoad(BrowserDriver.STANDARD_PAGE_LOAD_WAIT_TIME);
//		return  (BasePage) clazz.getConstructor().newInstance();
//
//	}

}
