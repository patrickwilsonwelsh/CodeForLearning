package gale.gameOfLife;

import gale.gameOfLife.CellState;
import gale.gameOfLife.LifeBoard;
import gale.gameOfLife.loader.TableRepresentationConverter;

public class CLGame {

	public static void main(String args[]) {
		
		TableRepresentationConverter.setDelimiter("\n");
		
		LifeBoard lifeBoard = new LifeBoard(5, 5);
		
		lifeBoard.setState(1, 1, CellState.Alive);
		lifeBoard.setState(1, 0, CellState.Alive);
		lifeBoard.setState(0, 1, CellState.Alive);		

		System.out.println(TableRepresentationConverter.convert2DArrayToWonk(lifeBoard.getCellState()));
		
		System.out.println("----------");
		
		lifeBoard.tick();
		
		System.out.println(TableRepresentationConverter.convert2DArrayToWonk(lifeBoard.getCellState()));
		
	}
	
}
