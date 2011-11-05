package pillar.gameoflife;

public class Cell {
	private int numberOfLiveNeighbors;
	private boolean isAlive;

	public boolean isDead() {
		return (!isAlive());
	}

	public void resurrect() {
		isAlive = true;
	}

	public void kill() {
		isAlive = false;
	}

	public void setLiveNeighbors(int liveNeighbors) {
		numberOfLiveNeighbors = liveNeighbors;
		
	}

	public boolean willBeAliveNextGeneration() {
		if(numberOfLiveNeighbors == 3) return true;
		if(isAlive() && (numberOfLiveNeighbors == 2)) return true;
		return false;
	}

	public boolean isAlive() {
		return isAlive;
	}

}
