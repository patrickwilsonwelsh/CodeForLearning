package gale.fixture;

import gale.gameOfLife.CellState;

public class SixGenerationFixture {
	private CellState[][] startingBoard;
	private CellState[][] emptyBoard = new CellState[0][0];
	
	
	public SixGenerationFixture() {}

	public void setStart(String startingBoard) {
		this.startingBoard = TableRepresentationConverter.convertWonkTo2DArray(startingBoard);
	}
	
	public String cycle1() {
		return TableRepresentationConverter.convert2DArrayToWonk(emptyBoard);
	}
	
	public String cycle2() {
		return TableRepresentationConverter.convert2DArrayToWonk(emptyBoard);
	}
	
	public String cycle3() {
		return TableRepresentationConverter.convert2DArrayToWonk(emptyBoard);
	}
	
	public String cycle4() {
		return TableRepresentationConverter.convert2DArrayToWonk(emptyBoard);
	}
	
	public String cycle5() {
		return TableRepresentationConverter.convert2DArrayToWonk(emptyBoard);
	}
	
	public String cycle6() {
		return TableRepresentationConverter.convert2DArrayToWonk(emptyBoard);
	}
	
	
}
