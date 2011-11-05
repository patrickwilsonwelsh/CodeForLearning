package gale.gameOfLife.view;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import gale.gameOfLife.CellState;
import gale.gameOfLife.LifeBoard;
import gale.gameOfLife.loader.LifeLoader;

public class LifeBoardView {
	LifeBoard lifeBoard;
	IGameOfLifeView gui;
	Timer nextStateTimer;

	public LifeBoardView() {
		try{
			lifeBoard = LifeLoader.loadBoard("initialState.txt");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	protected LifeBoard getLifeBoard() {
		return lifeBoard;
	}

	public void start() {
		try {
			if (nextStateTimer != null) 
				nextStateTimer.cancel();
		} catch (Exception e) {
		}
		nextStateTimer = new Timer();
		nextStateTimer.schedule(createTask(), 500, 500);
	}
	
	public void stop() {
		if (nextStateTimer != null)
			nextStateTimer.cancel();
	}

	public LifeBoardView(LifeBoard lifeBoard) {
		this.lifeBoard = lifeBoard;
	}
	
	public void setGUI(IGameOfLifeView gui) {
		this.gui = gui;
	}
	
	public void redrawBoard() {
		CellState[][] grid = lifeBoard.getCellState();
		
		for (int row = 0; row < grid.length; row++) {
			for (int column = 0; column < grid[0].length; column++) {
				gui.drawMark(row, column, grid[row][column] == CellState.Alive ? true: false);
			}
		}
	}
	
	public void clearBoard() {
		CellState[][] grid = lifeBoard.getCellState();
		
		for (int row = 0; row < grid.length; row++) {
			for (int column = 0; column < grid[0].length; column++) {
				gui.drawMark(row, column, false);
				lifeBoard.setState(row, column, CellState.Dead);
			}
		}		
	}

	protected TimerTask createTask() {
		TimerTask nextStateTask = new TimerTask() {
			@Override
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {

					public void run() {
						lifeBoard.tick();
						redrawBoard();
					}
					
				});
			}
		};
		return nextStateTask;
	}

	public void toggleMark(int boardX, int boardY) {
		CellState current = lifeBoard.getState(boardY, boardX);
		lifeBoard.setState(boardY, boardX, current == CellState.Alive ? CellState.Dead: CellState.Alive);
		redrawBoard();
		
	}

	public int getWidthCellCount() {
		return lifeBoard.getCellState()[0].length;
	}

	public int getHeightCellCount() {
		return lifeBoard.getCellState().length;
	}
}
