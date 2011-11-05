package game;

public class Rules {
	public boolean alive = true;

	public boolean determineIfCellLivesFromNumberOf(int liveNeighbors) {
		if (shouldFlip(liveNeighbors)) return flip();
		return  (shouldntFlip(liveNeighbors));
	}

	private boolean shouldFlip(int liveNeighbors) {
		return liveNeighbors == 3;
	}

	private boolean shouldntFlip(int liveNeighbors) {
		return liveNeighbors == 2;
	}
	
	private boolean flip() {
		alive = !alive;
		return alive;
	}

	public void setAlive(boolean state) {
		alive = state;
	}

}
