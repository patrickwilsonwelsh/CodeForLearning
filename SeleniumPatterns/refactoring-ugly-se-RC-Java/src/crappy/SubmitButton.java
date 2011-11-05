package crappy;

public class SubmitButton extends BaseElement {
	
	public SubmitButton(String cssLocator) {
		this.locator = cssLocator;
	}

	public void submit() {
		browserDriver.clickToNewPage(locator);
	}
}
