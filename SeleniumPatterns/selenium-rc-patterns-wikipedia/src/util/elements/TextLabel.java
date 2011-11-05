package util.elements;

import static org.junit.Assert.assertEquals;

public class TextLabel extends BaseElement {
	public TextLabel(String locator) {
		super(locator);
	}

	public boolean reads(String expected)  {
		assertEquals(getText(), expected);
		
		return (expected.equals(getText()));
	}

}
