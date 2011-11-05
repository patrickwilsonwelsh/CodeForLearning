package crappy;

public class TextField extends BaseElement {
	
	public TextField(String cssLocator) {
		this.locator = cssLocator;
	}

	public void enter(String entry) {
		browserDriver.enterTextIn(locator, entry);
	}

}
