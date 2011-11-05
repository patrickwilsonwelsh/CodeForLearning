package collection;

import static org.junit.Assert.*;

import org.junit.Test;


public class PlaceTest {
	
	@Test
	public void canSetPlaceName() throws Exception {
		Place baltimore = new Place("Baltimore");
		
		assertEquals("Baltimore", baltimore.getName());
	}
	
	@Test
	public void canSetPreviousPlace() throws Exception {
		
	}
	
	
	@Test
	public void canSetNextPlace() throws Exception {
		
	}

}
