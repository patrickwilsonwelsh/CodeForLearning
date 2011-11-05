package gale.gameOfLife.loader;

import gale.gameOfLife.CellState;
import gale.gameOfLife.LifeBoard;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

public class LifeLoaderTest {

	
	@Test
	public void loadBoardReturnsAppropriateBoard() throws IOException {
		LifeBoard lifeBoard = LifeLoader.loadBoard("testInitialState.txt");

		
		Assert.assertEquals(CellState.Dead, lifeBoard.getState(0, 0));
		Assert.assertEquals(CellState.Dead, lifeBoard.getState(1, 1));
		Assert.assertEquals(CellState.Alive, lifeBoard.getState(1, 0));
		Assert.assertEquals(CellState.Alive, lifeBoard.getState(0, 1));
	
		
	}
	
	@Test
	public void readFileReturnsStringFromFileInClasspath() throws IOException {
		
		String str = LifeLoader.readFileFromClasspath("testInitialState.txt");
		
		String expectedStr = ".X\nX.\n";
		
		Assert.assertEquals(expectedStr, str);
	}
	
	
	@Test
	public void readBoardFromStreamReturnsNewBoard() {
		
		String boardStr = ".X\nX."; 
		
		LifeBoard lifeBoard = LifeLoader.readBoard(boardStr);
		
		Assert.assertEquals(CellState.Dead, lifeBoard.getState(0, 0));
		Assert.assertEquals(CellState.Dead, lifeBoard.getState(1, 1));
		Assert.assertEquals(CellState.Alive, lifeBoard.getState(1, 0));
		Assert.assertEquals(CellState.Alive, lifeBoard.getState(0, 1));
		
		
	}
	
}
