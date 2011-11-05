package util.elements;


public class DhtmlLink extends ClickableElement {
	private Class<?> clazz;

	public DhtmlLink(String locator, Class<?> clazz) {
		super(locator);
		this.clazz = clazz;
	}

	public Object clickToNewContainer() {
		try {
			click();
			return clazz.getConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
