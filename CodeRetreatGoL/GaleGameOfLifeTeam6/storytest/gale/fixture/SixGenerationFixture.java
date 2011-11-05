package gale.fixture;

import gale.gameOfLife.CellState;
import gale.gameOfLife.LifeBoard;
import gale.gameOfLife.loader.TableRepresentationConverter;

public class SixGenerationFixture {
	private LifeBoard lifeboard; 
	
	public SixGenerationFixture() {}

	public void setStart(String startingBoard) {
		CellState[][] initialState = TableRepresentationConverter.convertWonkTo2DArray(startingBoard);
		lifeboard = new LifeBoard(initialState.length, initialState[0].length);
		lifeboard.setCellState(initialState);
	}
	
	public String cycle1() {
		lifeboard.tick();
		return TableRepresentationConverter.convert2DArrayToWonk(lifeboard.getCellState());
	}
	
	public String cycle2() {
		lifeboard.tick();
		return TableRepresentationConverter.convert2DArrayToWonk(lifeboard.getCellState());
	}
	
	public String cycle3() {
		lifeboard.tick();
		return TableRepresentationConverter.convert2DArrayToWonk(lifeboard.getCellState());
	}
	
	public String cycle4() {
		lifeboard.tick();
		return TableRepresentationConverter.convert2DArrayToWonk(lifeboard.getCellState());
	}
	
	public String cycle5() {
		lifeboard.tick();
		return TableRepresentationConverter.convert2DArrayToWonk(lifeboard.getCellState());
	}
	
	public String cycle6() {
		lifeboard.tick();
		return TableRepresentationConverter.convert2DArrayToWonk(lifeboard.getCellState());
	}
	
	
}
