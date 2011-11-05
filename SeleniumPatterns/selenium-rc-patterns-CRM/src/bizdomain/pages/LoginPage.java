package bizdomain.pages;

import util.BasePane;
import util.elements.PageLink;
import util.elements.TextField;

public class LoginPage extends BasePane {
	public static final String PAGE_IS_LOADED_CSS = "input[id=authentication_username]";
	
	public static final String PASSWORD = "seleniumpatterns";
	public static final String USERNAME = PASSWORD;
	
	private TextField userNameField;
	private TextField passwordField;
	private PageLink<DashBoard> loginButton;

	public LoginPage() {
		super();
		userNameField = new TextField("input[id=authentication_username]");
		passwordField = new TextField("input[id=authentication_password]");
		loginButton = new PageLink<DashBoard>("input[id=authentication_submit]", DashBoard.class);
	}

	@Override
	public String getPageLoadedCssLocator() {
		return PAGE_IS_LOADED_CSS;
	}

	public DashBoard login(String userName, String password) {
		userNameField.enter(userName);
		passwordField.enter(password);
		return loginButton.clickToNewPage();
		
	}



}
