package util.elements;


public class RadioButton extends ClickableElement {

	public RadioButton(String selector) {
		super(selector);
	}

	public void click() {
	  browserDriver.click(selector);
	}

	public boolean isChecked() {
		return browserDriver.isChecked(selector);
	}

}
