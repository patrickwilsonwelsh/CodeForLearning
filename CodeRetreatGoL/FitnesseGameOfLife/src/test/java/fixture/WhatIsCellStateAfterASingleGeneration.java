package fixture;

import rules.Rules;

public class WhatIsCellStateAfterASingleGeneration {
	private boolean startingState;
	private int neighbourCount;
	
	public void setStartingState(String state) {
		this.startingState = state.equalsIgnoreCase("alive");
	}
	
	public void setNeighbourCount(int neighbourCount) {
		this.neighbourCount = neighbourCount;
	}
	
	public String stateAfterNextGeneration() {
		boolean result = Rules.getStateForNextGeneration(startingState, neighbourCount);
		return isAliveBooleanToString(result);
	}

	private String isAliveBooleanToString(boolean aliveValue) {
		return aliveValue == true ? "alive" : "dead";
	}

}
