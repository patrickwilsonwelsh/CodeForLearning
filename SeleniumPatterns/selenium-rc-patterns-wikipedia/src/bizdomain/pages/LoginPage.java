package bizdomain.pages;

import util.BasePane;
import util.elements.PageLink;
import util.elements.TextField;

public class LoginPage extends BasePane {
	public static final String PAGE_IS_LOADED_CSS = "input[id=authentication_username]";
	private TextField userNameField;
	private TextField passwordField;
	private PageLink loginButton;

	public LoginPage() {
		super();
		userNameField = new TextField("input[id=authentication_username]");
		passwordField = new TextField("input[id=authentication_password]");
		//loginButton = new PageLink("input[id=authentication_submit]", DashBoard.class);
	}

	@Override
	public String getPageLoadedCssLocator() {
		return PAGE_IS_LOADED_CSS;
	}





}
