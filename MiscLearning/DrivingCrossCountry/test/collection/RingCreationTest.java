package collection;

import static org.junit.Assert.*;

import org.junit.Test;

//Test List
//TODO Can create new Ring
//TODO Can add Place to Ring
//TODO Can add second Place to Ring
//TODO Can retrieve Place from ring by cityname
//TODO Can retrieve total number of Place in Ring
//TODO Can traverse Ring fully, asserting Place name as you go
//TODO Can remove Place from Ring 


public class RingCreationTest {
	
	
	@Test
	public void canCreateEmptyRing() throws Exception {
		assertTrue(new Ring().isEmpty());
	}
	
	@Test
	public void canAddPlaceToRing() throws Exception {
		Ring ring = new Ring();
		ring.appendPlace(new Place("Baltimore"));
		assertEquals(1, ring.numberOfPlaces());
	}
	
	public void canAddTwoPlacesToRing() throws Exception {
		Ring ring = new Ring();
		ring.appendPlace(new Place("Baltimmore"));
		ring.appendPlace(new Place("Knoxville"));
		
		assertEquals(2, ring.numberOfPlaces());
	}

}
