package crappy;

public class LoginPage {
	private BrowserDriver browserDriver;
	private TextField userNameField;
	private TextField passwordField;
	private SubmitButton submitButton;
	
	public LoginPage() {
		browserDriver = BrowserDriver.getInstance();
		browserDriver.openPage("http://demo.fatfreecrm.com/login");
		userNameField = new TextField("css=input[id=authentication_username]");
		passwordField = new TextField("css=input[id=authentication_password]");
		submitButton = new SubmitButton("css=input[id=authentication_submit]");
	}

	public LandingPage login(String userName, String password) {
		userNameField.enter(userName);
		passwordField.enter(password);
		submitButton.submit();
		return new LandingPage();
	}

}
