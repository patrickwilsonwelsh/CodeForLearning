package gale.gameOfLife.view;

public interface IGameOfLifeView {
	void restartGame();

	void drawMark(int row, int column, boolean filled);
}
