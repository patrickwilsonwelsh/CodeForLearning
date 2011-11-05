package util.elements;

public class DhtmlLink<T> extends ClickableElement {
	private Class<T> clazz;

	public DhtmlLink(String locator, Class<T> clazz) {
		super(locator);
		this.clazz = clazz;
	}

	public T clickToNewContainer() {
		try {
			click();
			return clazz.getConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
