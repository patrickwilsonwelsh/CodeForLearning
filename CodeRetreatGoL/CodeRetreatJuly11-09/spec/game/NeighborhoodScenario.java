package game;

import org.jbehave.scenario.Scenario;

public class NeighborhoodScenario extends Scenario {

	public  NeighborhoodScenario() {
		super(new NeighborhoodSteps());  //Frickin YUCK!
	}
}
