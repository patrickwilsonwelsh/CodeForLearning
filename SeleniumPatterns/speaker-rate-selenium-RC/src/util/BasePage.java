package util;


public abstract class BasePage extends BaseContainer {

	public BasePage() {
	  super();
		waitUntilLoaded();
	}
}
