package fixture;

import game.Cell;
import game.Board;
import game.Life;
import loader.TableRepresentationConverter;

public class SixGenerationFixture {
	private Board grid; 
	private Life life;
	public SixGenerationFixture() {}

	public void setStart(String startingBoard) {
		Cell[][] state = TableRepresentationConverter.convertWonkTo2DArray(startingBoard);
		grid = new Board(state.length, state[0].length);
		grid.setGridState(state);
		life = new Life();
	}
	
	public String cycle1() {
		grid = life.nextState(grid);
		return TableRepresentationConverter.convert2DArrayToWonk(grid.getGridState());
	}
	
	public String cycle2() {
		grid = life.nextState(grid);
		return TableRepresentationConverter.convert2DArrayToWonk(grid.getGridState());
	}
	
	public String cycle3() {
		grid = life.nextState(grid);
		return TableRepresentationConverter.convert2DArrayToWonk(grid.getGridState());
	}
	
	public String cycle4() {
		grid = life.nextState(grid);
		return TableRepresentationConverter.convert2DArrayToWonk(grid.getGridState());
	}
	
	public String cycle5() {
		grid = life.nextState(grid);
		return TableRepresentationConverter.convert2DArrayToWonk(grid.getGridState());
	}
	
	public String cycle6() {
		grid = life.nextState(grid);
		return TableRepresentationConverter.convert2DArrayToWonk(grid.getGridState());
	}
	
	
}
