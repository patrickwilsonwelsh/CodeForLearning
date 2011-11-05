package util.elements;


public class TextField extends ClickableElement {

	public TextField(String selector) {
		super(selector);
	}

	public final void enter(String entry) {
	  browserDriver.type(selector, entry);
	}

	public final String getText() {
		return browserDriver.getText(selector);
	}

}
