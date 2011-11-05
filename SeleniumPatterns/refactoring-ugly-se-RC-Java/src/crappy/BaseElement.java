package crappy;

public class BaseElement {
	public String locator;
	protected BrowserDriver browserDriver;
	
	public BaseElement() {
		browserDriver = BrowserDriver.getInstance();
	}

}
