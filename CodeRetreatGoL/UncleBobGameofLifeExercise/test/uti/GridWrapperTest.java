package uti;

import org.junit.Before;

import util.GridWrapper;


public class GridWrapperTest {
	private GridWrapper gridWrapper;

	@Before
	public void init() {
		gridWrapper = new GridWrapper(10,10);
	}
}
