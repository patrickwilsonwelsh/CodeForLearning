package gale.gameOfLife.view;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameOfLifeGUI extends Applet implements IGameOfLifeView {
	private static final int HEIGHT_FUDGE_FACTOR = 35;
	private static final int WIDTH_FUDGE_FACTOR = 32;
	private static final int CELL_WIDTH = 30;
	private static final int CELL_HEIGHT = 40;

	private static final long serialVersionUID = 4873261510528018302L;

	private LifeBoardView boardView;
	private GameImages gameImages;

	// We maintain an offscreen image buffer, so as to not have to maintain or
	// query total game state on every repaint
	// (instead we just react to requests to repaint things).
	private Image imageBuffer;
	private Graphics imageGraphics;

	public void setBoardView(LifeBoardView boardView) {
		this.boardView = boardView;
	}

	public void init() {
		this.boardView = new LifeBoardView();
		
		int windowWidth = boardView.getWidthCellCount() * WIDTH_FUDGE_FACTOR;
		int windowHeight = boardView.getHeightCellCount() * HEIGHT_FUDGE_FACTOR;
		setSize(windowWidth, windowHeight);
		imageBuffer = createImage(windowWidth, windowHeight);
		imageGraphics = imageBuffer.getGraphics();

		gameImages = new GameImages(this);
		gameImages.awaitImageLoad();

		boardView.setGUI(this);

		this.restartGame();
		
		addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {
				handleMouseEvent(e.getX(), e.getY());
			}
		});

		repaint();
		boardView.start();
	}

	public void update(Graphics g) {
		// Overridden so it doesn't clear the canvas on every redraw
		paint(g);
	}

	public void paint(Graphics g) {
		// Blit the offscreen image buffer to the screen
		g.drawImage(imageBuffer, 0, 0, this);
	}

	public void handleMouseEvent(int mouseX, int mouseY) {
		if (clickedStartButton(mouseX, mouseY)) {
			boardView.start();
		}
		else if (clickedStopButton(mouseX, mouseY)) {
			boardView.stop();
		}
		else if (clickedClearButton(mouseX, mouseY)) {
			boardView.clearBoard();
		}
		else {
			int boardX = mouseX / CELL_WIDTH;
			int boardY = (mouseY - CELL_HEIGHT) / CELL_WIDTH;
			
			boardView.toggleMark(boardX, boardY);
			
		}
	}
	
	
	private boolean clickedStartButton(int mouseX, int mouseY) {
		return (mouseY < 22) && (mouseX < 22);
	}

	private boolean clickedStopButton(int mouseX, int mouseY) {
		return (mouseY < 22) && (mouseX > 22 && mouseX < 45);
	}

	private boolean clickedClearButton(int mouseX, int mouseY) {
		return (mouseY < 22) && (mouseX > 46 && mouseX < 68);
	}

	private void resetToolbar() {
		imageGraphics.drawImage(gameImages.getStartImage(), 0, 0, this);
		imageGraphics.drawImage(gameImages.getStopImage(), 23, 0, this);
		imageGraphics.drawImage(gameImages.getClearImage(), 46, 0, this);
		
	}

	public void restartGame() {
		boardView.redrawBoard();
		resetToolbar();
	}

	public void drawMark(final int row, final int column, boolean filled) {
		int x = column * CELL_WIDTH;
		int y = row * CELL_WIDTH + CELL_HEIGHT;

		imageGraphics.drawImage(gameImages.getImageForPlayerMark(filled),
				x, y, this);

		repaint();
	}
}
