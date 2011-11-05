package gale.gameOfLife.view;

import static org.junit.Assert.assertEquals;
import gale.gameOfLife.CellState;
import gale.gameOfLife.LifeBoard;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class LifeBoardViewTest {
	LifeBoard board;
	
	@Before
	public void init() {
		board = new LifeBoard(10, 10);
		board.setState(1, 1, CellState.Alive);
		board.setState(1, 0, CellState.Alive);
		board.setState(0, 1, CellState.Alive);

		board.setState(4, 4, CellState.Dead);
		board.setState(6, 2, CellState.Alive);
		board.setState(6, 3, CellState.Alive);
	}
	
	@Test
	public void initView() throws Exception {
		
		LifeBoardView lbv = new LifeBoardView(board);
		lbv.setGUI(getStubConcreteView());
		
		lbv = new LifeBoardView();
		lbv.setGUI(getStubConcreteView());
		
	}
	
	@Test
	public void startView() throws Exception {
		LifeBoardView lbv = new LifeBoardView(board);
		lbv.setGUI(getStubConcreteView());
		lbv.start();
	}
	
	@Test
	public void stopView() throws Exception {
		LifeBoardView lbv = new LifeBoardView(board);
		lbv.setGUI(getStubConcreteView());
		lbv.start();
		lbv.stop();
	}
	
	@Test
	public void startStartedView() throws Exception {
		LifeBoardView lbv = new LifeBoardView(board);
		lbv.setGUI(getStubConcreteView());
		lbv.start();
		lbv.stop();
		lbv.start();
	}
	
	@Test
	public void toggleMark() throws Exception {
		LifeBoardView lbv = new LifeBoardView(board);
		lbv.setGUI(getStubConcreteView());
		lbv.toggleMark(1, 1);
		lbv.toggleMark(4, 4);
		
		assertEquals(CellState.Dead, lbv.getLifeBoard().getState(1, 1));
		assertEquals(CellState.Alive, lbv.getLifeBoard().getState(4, 4));
	}
	
	@Test
	public void drawBoard() throws Exception {
		
		LifeBoardView lbv = new LifeBoardView(board);
		TrackingConcreteView trackingConcreteView = new TrackingConcreteView();
		lbv.setGUI(trackingConcreteView);
		
		lbv.redrawBoard();
		
		assertEquals(0, trackingConcreteView.getRestarts());
		
		assertEquals(100, trackingConcreteView.getMarksDrawn().size());
		
	}
	
	protected IGameOfLifeView getStubConcreteView() {
		return new IGameOfLifeView() {

			public void drawMark(int row, int column, boolean filled) {
				
			}

			public void restartGame() {
				
			}
		};
	}
	
	protected class TrackingConcreteView implements IGameOfLifeView {
		private int restarts = 0;
		private List<RowColumnMark> marksDrawn = new ArrayList<RowColumnMark>();
		
		public void drawMark(int row, int column, boolean filled) {
			marksDrawn.add(new RowColumnMark(row, column, filled));
		}

		public void restartGame() {
			restarts++;
		}

		public int getRestarts() {
			return restarts;
		}

		public List<RowColumnMark> getMarksDrawn() {
			return marksDrawn;
		}
	
	}
	
	protected class RowColumnMark {
		
		private int row;
		private int column;
		private boolean filled;
		
		public RowColumnMark(int row, int column, boolean mark) {
			super();
			this.row = row;
			this.column = column;
			this.filled = mark;
		}
		public int getRow() {
			return row;
		}
		public void setRow(int row) {
			this.row = row;
		}
		public int getColumn() {
			return column;
		}
		public void setColumn(int column) {
			this.column = column;
		}
		public boolean getFilled() {
			return filled;
		}
		public void setFilled(boolean filled) {
			this.filled = filled;
		}
	}
}
