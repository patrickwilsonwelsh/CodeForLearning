package bizdomain.common;

import bizdomain.pages.DashBoard;
import bizdomain.pages.LoginPage;

public class AutoLogin {
	
	
	public static DashBoard login() throws Exception {
		LoginPage loginPage = new LoginPage();
		return loginPage.login(LoginPage.USERNAME, LoginPage.PASSWORD);
	}

}
