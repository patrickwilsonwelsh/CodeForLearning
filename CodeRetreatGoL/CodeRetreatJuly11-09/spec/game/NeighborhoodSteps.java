package game;

import org.jbehave.scenario.annotations.Given;
import org.jbehave.scenario.annotations.When;
import org.jbehave.scenario.annotations.Then;
import org.jbehave.scenario.steps.Steps;
import static org.jbehave.Ensure.*;

public class NeighborhoodSteps extends Steps {
	
	private Grid gameGrid;
	private Grid neighborhood;

	@Given("a new 9 by 9 grid")
	public void createGrid() {
		gameGrid = new Grid(9,9);
	}
	
	@When("grid is asked for Neighborhood around center cell position")
	public void askGridForNeighborhood() {
		neighborhood = gameGrid.getNeighborhoodAt(27,27);
	}
	
	@Then("neighborhood should contain $center cell and at least three other cells")
	public void finalThing() {
		  ensureThat(neighborhood.contains(27,27));
	}
	
	
}
