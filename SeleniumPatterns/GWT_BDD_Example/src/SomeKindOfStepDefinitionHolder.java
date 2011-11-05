
/*
 * 
GIVEN an unathenticated user cannot access restricted content
WHEN unathenticated user authenticates successfully
THEN authenticated user gains access access to restricted content
 */

public class SomeKindOfStepDefinitionHolder {
	
	@Given "an unathenticated user cannot access restricted content"
	public boolean noAccessToRestrictedContent() {
		User unAuthenticatedUser = new User();
		return validator.isGrantedBasicContentAccess(unAuthenticatedUser);
	}
}
	///------------


public interface Validator {
	public boolean isGrantedBasicContentAcccess(User);

}


//-------------


public class LoginPageValidator implements Validator {
	
	public boolean isGrantedBasicContentAccess(User) {
		loginPageService.hasBeenGrantedBasicContentAccess(User);
	}
}


//--------

public class RSAKeyValidator implements Validator {
	
	public boolean isGrantedBasicContentAccess(User) {
		rsaKeyService.hasBeenGrantedBasicContentAccess(User);
	}
	
}
