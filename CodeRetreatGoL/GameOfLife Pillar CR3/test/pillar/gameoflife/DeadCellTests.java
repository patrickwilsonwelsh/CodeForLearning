package pillar.gameoflife;

import junit.framework.TestCase;

public class DeadCellTests extends TestCase {
	private Cell cell;
	
	protected void setUp() throws Exception {
		cell = new Cell();
	}
	
	public void testNewCellisDead() throws Exception {
		assertTrue(cell.isDead());
	}
	
	public void testCanResurrectDeadCell() throws Exception {
		cell.resurrect();
		assertTrue(cell.isAlive());
	}
	
	public void testDeadCellWithThreeLiveNeighborsComesAlive() throws Exception {
		cell.setLiveNeighbors(3);
		assertTrue(cell.willBeAliveNextGeneration());
	}
	
	 public void testDeadCellWithTwoLiveNeighborsStaysDead() throws Exception {
	    cell.setLiveNeighbors(2);
	    assertFalse(cell.willBeAliveNextGeneration());
	  }
	
	
}
