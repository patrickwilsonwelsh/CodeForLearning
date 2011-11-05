package game;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;



/*
 * Sample 10x10 grid of x,y coords
 * 00, 01, 02, 03, 04, 05, 06, 07, 08, 09
 * 10, 11, 12, 13, 14, 15, 16, 17, 18, 19
 * 20, 21, 22, 23, 24, 25, 26, 27, 28, 29
 * 30, 31, 32, 33, 34, 35, 36, 37, 38, 39
 * 40, 41, 42, 43, 44, 45, 46, 47, 48, 49
 * 50, 51, 52, 53, 54, 55, 56, 57, 58, 59
 * 60, 61, 62, 63, 64, 65, 66, 67, 68, 69
 * 70, 71, 72, 73, 74, 75, 76, 77, 78, 79
 * 80, 81, 82, 83, 84, 85, 86, 87, 88, 89
 * 90, 91, 92, 93, 94, 95, 96, 97, 98, 99
 * 
 */

public class LiveNeighborsTest {
	private Board grid;

	@Before
	public void init() {
		int width = 10;
		int height = 10;
		grid = new Board(width, height);
	}
	
	@Test
	public void shouldReturnZeroInEmptyGrid() {
		assertEquals(0, grid.getNumberOfAliveNeighbors(new CellLocation(5,5)));
	}
	
	@Test
	public void shouldReturnOneLiveNeighborForCellWithOneLiveNeighborWest() {
		grid.setCellStateAt(new CellLocation(5,4), CellState.Alive);
		assertEquals(1, grid.getNumberOfAliveNeighbors(new CellLocation(5,5)));
	}
	
	@Test
	public void shouldReturnTwoLiveNeighborsForCellWithLiveNeighborWest_AndLiveNeighborSouthEast() {
		grid.setCellStateAt(new CellLocation(4,4), CellState.Alive);
		grid.setCellStateAt(new CellLocation(6,6), CellState.Alive);
		
		assertEquals(2, grid.getNumberOfAliveNeighbors(new CellLocation(5,5)));
	}
	
	@Test
	public void shouldReturnEightForAllLiveNeighbors() {
		setAllNeighborsLiveAround55();		
		assertEquals(8, grid.getNumberOfAliveNeighbors(new CellLocation(5,5)));
	}

	@Test
	public void shouldReturnEightForAllLiveNeighbors_ForCellInBottomRightCorner() {
		setAllNeighborsLiveAround99();	
		assertEquals(8, grid.getNumberOfAliveNeighbors(new CellLocation(9,9)));		
	}
	
	@Test
	public void shouldReturnEightForAllLiveNeighbors_ForCellInTopLeftCorner() {
		setAllNeighborsLiveAround00();	
		assertEquals(8, grid.getNumberOfAliveNeighbors(new CellLocation(0,0)));		
	}


	private void setAllNeighborsLiveAround55() {
		grid.setCellStateAt(new CellLocation(4,4), CellState.Alive);
		grid.setCellStateAt(new CellLocation(4,5), CellState.Alive);
		grid.setCellStateAt(new CellLocation(4,6), CellState.Alive);
		grid.setCellStateAt(new CellLocation(5,4), CellState.Alive);
		grid.setCellStateAt(new CellLocation(5,6), CellState.Alive);
		grid.setCellStateAt(new CellLocation(6,4), CellState.Alive);
		grid.setCellStateAt(new CellLocation(6,5), CellState.Alive);
		grid.setCellStateAt(new CellLocation(6,6), CellState.Alive);
	}
	
	private void setAllNeighborsLiveAround99() {
		grid.setCellStateAt(new CellLocation(8,8), CellState.Alive);
		grid.setCellStateAt(new CellLocation(8,9), CellState.Alive);
		grid.setCellStateAt(new CellLocation(8,0), CellState.Alive);
		grid.setCellStateAt(new CellLocation(9,8), CellState.Alive);
		grid.setCellStateAt(new CellLocation(9,0), CellState.Alive);
		grid.setCellStateAt(new CellLocation(0,8), CellState.Alive);
		grid.setCellStateAt(new CellLocation(0,9), CellState.Alive);
		grid.setCellStateAt(new CellLocation(0,0), CellState.Alive);
	}
	
	private void setAllNeighborsLiveAround00() {
		grid.setCellStateAt(new CellLocation(0,9), CellState.Alive);
		grid.setCellStateAt(new CellLocation(0,1), CellState.Alive);
		grid.setCellStateAt(new CellLocation(1,9), CellState.Alive);
		grid.setCellStateAt(new CellLocation(1,0), CellState.Alive);
		grid.setCellStateAt(new CellLocation(1,1), CellState.Alive);
		grid.setCellStateAt(new CellLocation(9,9), CellState.Alive);
		grid.setCellStateAt(new CellLocation(9,0), CellState.Alive);
		grid.setCellStateAt(new CellLocation(9,1), CellState.Alive);
	}
	/*
	 * Sample 10x10 wrqpping grid of coords
	 *  99, 90, 91
	 * 	09, 00, 01, 02, 03, 04, 05, 06, 07, 08, 09
	 * 	19, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19
	 * 		20, 21, 22, 23, 24, 25, 26, 27, 28, 29
	 * 		30, 31, 32, 33, 34, 35, 36, 37, 38, 39
	 * 		40, 41, 42, 43, 44, 45, 46, 47, 48, 49
	 * 		50, 51, 52, 53, 54, 55, 56, 57, 58, 59
	 * 		60, 61, 62, 63, 64, 65, 66, 67, 68, 69
	 * 		70, 71, 72, 73, 74, 75, 76, 77, 78, 79
	 * 		80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 80 
	 * 		90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 90
	 * 										08, 09, 00
	 * 
	 */
	
}
