package util;

import game.CellLocation;

public class GridWrapper {
	private int height;
	private int width;
	
	public GridWrapper(int width, int height) {
		this.width = width;
		this.height = height;
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
	 */

	public CellLocation wrapGrid(CellLocation location, CellLocation neighborLoc) {
		if (offBoardToSouth(neighborLoc)) getCellPastSBoundary(neighborLoc);
		if (offBoardToEast(neighborLoc)) getCellPastEBoundary(neighborLoc);
		
		if (offBoardToNorth(neighborLoc)) getCellPastNBoundary(neighborLoc);		
		if (offBoardToWest(neighborLoc)) getCellPastWBoundary(neighborLoc);

		return neighborLoc;
	}
	
	public void getCellPastWBoundary(CellLocation neighborLoc) {
		//-1, 0 => 0,9
		//-1, 1 => 9,1
		//-1, -1 => 99
		neighborLoc.x = neighborLoc.x + 1;
		neighborLoc.y = neighborLoc.y + (height-1);
	}

	public void getCellPastNBoundary(CellLocation neighborLoc) {
		//0,1 => 9,0
		
		neighborLoc.x = neighborLoc.x + (width-1);
		neighborLoc.y = neighborLoc.y + 1;
	}
	
	public void getCellPastSBoundary(CellLocation neighborLoc) {
		neighborLoc.y = neighborLoc.y - height;
	}

	public void getCellPastEBoundary(CellLocation neighborLoc) {
		neighborLoc.x = neighborLoc.x - width;
	}
	
	private boolean offBoardToNorth(CellLocation neighborLoc) {
		return neighborLoc.y < 0;
	}
	
	private boolean offBoardToWest(CellLocation neighborLoc) {
		return neighborLoc.x < 0;
	}
	
	private boolean offBoardToSouth(CellLocation neighborLoc) {
		return neighborLoc.y > (height-1);
	}
	
	private boolean offBoardToEast(CellLocation neighborLoc) {
		return neighborLoc.x > (width-1);
	}
	
}
