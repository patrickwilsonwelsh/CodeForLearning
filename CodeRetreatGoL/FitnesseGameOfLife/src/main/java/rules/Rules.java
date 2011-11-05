package rules;

public class Rules {
	public static boolean getStateForNextGeneration(boolean isAlive, int neighbourCount) {
		return neighbourCount == 3 || (isAlive && neighbourCount == 2);
	}
}
