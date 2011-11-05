package pillar.gameoflife;

import junit.framework.TestCase;

public class LiveCellTests extends TestCase {
	private Cell cell;

	protected void setUp() throws Exception {
		cell = new Cell();
		cell.resurrect();
    assertTrue(cell.isAlive());
	}
	
	public void testCanKill() throws Exception {
		cell.kill();
		assertTrue(cell.isDead());
	}
	
	public void testWithNoNeighborsDies() throws Exception { //Rule 1
		cell.setLiveNeighbors(0);
		assertFalse(cell.willBeAliveNextGeneration());
	}
	
	public void testWithOneNeighborDies() throws Exception { //Rule 1
		cell.setLiveNeighbors(1);
		assertFalse(cell.willBeAliveNextGeneration());
	}
	
	public void testWithTwoNeighborsStaysAlive() throws Exception { // Rule 3
		cell.setLiveNeighbors(2);
		assertTrue(cell.willBeAliveNextGeneration());
	}
	
	public void testWithThreeNeighborsStaysAlive() throws Exception { //Rule 3
		cell.setLiveNeighbors(3);
		assertTrue(cell.willBeAliveNextGeneration());
	}
	
	public void testWithFourNeighborsDiesNextGeneration() throws Exception { //Rule 2
		cell.setLiveNeighbors(4);
		assertFalse(cell.willBeAliveNextGeneration());
	}
	
	public void testWithFiveNeighborsDiesNextGeneration() throws Exception { //Rule 2
		cell.setLiveNeighbors(5);
		assertFalse(cell.willBeAliveNextGeneration());
	}
	
}
