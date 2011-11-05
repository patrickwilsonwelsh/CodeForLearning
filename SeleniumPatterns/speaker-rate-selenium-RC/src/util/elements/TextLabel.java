package util.elements;

import static org.junit.Assert.assertEquals;

public class TextLabel extends BaseElement {
	public TextLabel(String selector) {
		super(selector);
	}

	public boolean reads(String expected) {
		assertEquals(getText(), expected);

		return (expected.equals(getText()));
	}

	public final String getValue() {
		return browserDriver.getValue(selector);
	}

	public String getText() {
		return browserDriver.getText(selector);
	}

}
