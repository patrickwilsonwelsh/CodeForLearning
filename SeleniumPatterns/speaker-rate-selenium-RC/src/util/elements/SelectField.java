package util.elements;


public class SelectField extends BaseElement {

	public SelectField(String selector) {
		super(selector);
	}

	public void select(String selection) {
	  browserDriver.select(selector, selection);
	}

}
