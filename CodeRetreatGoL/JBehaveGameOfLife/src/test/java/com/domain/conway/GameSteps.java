package com.domain.conway;

import org.jbehave.scenario.annotations.Given;
import org.jbehave.scenario.annotations.Then;
import org.jbehave.scenario.annotations.When;
import org.jbehave.scenario.steps.Steps;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class GameSteps extends Steps {
	private boolean[][] grid;

	@Given("a $x x $y grid")
	public void givenAGrid(int x, int y) {
		grid = new boolean[x][y];
	}

	@When("cell at $x, $y is alive")
	public void setAlive(int x, int y) {
		grid[x][y] = true;
	}

	@Then("the cells around $x, $y should have $num live neighbors")
	public void shouldHaveXLiveNeighbors(int x, int y, int expectedNeighbors) {
		int neighbors = 0;
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (inBounds(i, j) && grid[i][j]) {
					neighbors++;
				}
			}
		}
		if (grid[x][y]) {
			neighbors--;
		}
		assertThat(neighbors, is(expectedNeighbors));
	}

	private boolean inBounds(int i, int j) {
		return i >= 0 && j >= 0 && i < grid.length && j < grid[0].length;
	}

	@Then("cell at $x, $y should be $state")
	public void setAlive(int x, int y, String stateStr) {
		boolean state = "alive".equals(stateStr);
		assertThat(grid[x][y], is(state));
	}
}
