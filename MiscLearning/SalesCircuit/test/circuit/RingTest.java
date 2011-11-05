package circuit;

import static org.junit.Assert.*;

import org.junit.Test;


public class RingTest {
	
	
	@Test
	public void canAdd_TwoPlaces_ToRing() throws Exception {
		Ring circuit = new Ring();
		Place baltimore = new Place("Baltimore");
		Place pittsburgh = new Place("Pittsburgh");

		circuit.insertAtEnd(baltimore);
		circuit.insertAtEnd(pittsburgh);
		
		assertEquals(pittsburgh, baltimore.nextPlace);
		assertEquals(baltimore, pittsburgh.previousPlace);
	}
}
