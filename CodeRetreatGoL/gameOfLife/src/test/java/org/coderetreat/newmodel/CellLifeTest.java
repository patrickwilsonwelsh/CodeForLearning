package org.coderetreat.newmodel;

import junit.framework.TestCase;

public class CellLifeTest extends TestCase {
	
	//TODO use board
//	public void testLonelyCellDiesNextTime() throws Exception {
//		NewCell cell = new NewCell();
//		
//		assertFalse(cell.aliveNextTime());
//	}
	
	public void testAliveNextTime_IfThreeLiveNeighbors() throws Exception {
		Cells cells = createTwoByTwoBoardAllAlive();
		
		assertTrue(cells.aliveNextTime(0,0));
	}
	
	public void testDeadNextTime_IfOneLiveNeighbor() throws Exception {
		Cells cells = createTwoByTwoBoard(new int[]{1,0,0,1});
		
		assertFalse(cells.aliveNextTime(0,0));
	}
	
	public void testAliveDeadIsInitiatlized() throws Exception {
		Cells cells = createTwoByTwoBoard(new int[]{0,1,1,0});
		
		assertFalse(cells.getCell(0,0).isAlive());
		assertTrue(cells.getCell(0,1).isAlive());
		assertTrue(cells.getCell(1,0).isAlive());
		assertFalse(cells.getCell(1,1).isAlive());
	}

	private Cells createTwoByTwoBoard(int[] board) {
		return new Cells(board);
	}

	private Cells createTwoByTwoBoardAllAlive() {
		return createTwoByTwoBoard(new int[]{1,1,1,1});
	}

}
