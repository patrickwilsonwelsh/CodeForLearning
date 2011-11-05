package com.domain.conway;

import org.jbehave.scenario.PropertyBasedConfiguration;
import org.jbehave.scenario.Scenario;
import org.jbehave.scenario.errors.PendingErrorStrategy;

public class GameScenario extends Scenario {
	public GameScenario() {
		super(new PropertyBasedConfiguration() {
			@Override
			public PendingErrorStrategy forPendingSteps() {
				return PendingErrorStrategy.FAILING;
			}
		});
		addSteps(new GameSteps());
	}
}
