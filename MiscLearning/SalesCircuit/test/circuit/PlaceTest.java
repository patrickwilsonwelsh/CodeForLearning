package circuit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class PlaceTest {
	private Place pittsburgh;
	private Place washingtonDC;
	private Place columbusOH;

	@Before
	public void setup() {
		pittsburgh = new Place("Pittsburgh");
		washingtonDC = new Place("Washington D.C.");
		columbusOH = new Place("Columbus OH");
	}
	
	@Test
	public void canCreatePlace_WithCityName() throws Exception {
		assertEquals("Columbus OH", columbusOH.placeName);
		assertEquals("Pittsburgh", pittsburgh.placeName);
	}
	
	@Test
	public void canAddPreviousPlace_AndNextPlace_ToPlace() throws Exception {
		pittsburgh.setPreviousPlace(washingtonDC);
		pittsburgh.setNextPlafce(columbusOH);
		
		assertEquals(washingtonDC, pittsburgh.previousPlace);
		assertEquals(columbusOH, pittsburgh.nextPlace);
	}

}
