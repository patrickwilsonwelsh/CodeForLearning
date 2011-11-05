package game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class RulesTest {
	private Rules rules;
	
	@Before
	public void init() {
		rules = new Rules();
	}
	
	@Test
	public void cellWithFiveNeighborsDoesNotLive() {
		int liveNeighbors = 5;
		assertFalse(rules.determineIfCellLivesFromNumberOf(liveNeighbors));			
	}
	
	@Test
	public void cellWithFourNeighborsDoesNotLive() {
		int liveNeighbors = 4;
		assertFalse(rules.determineIfCellLivesFromNumberOf(liveNeighbors));			
	}
	
	@Test
	public void cellWithTwoNeighborsLives() {
		int liveNeighbors = 2;
		assertTrue(rules.determineIfCellLivesFromNumberOf(liveNeighbors));
	}
	
	@Test
	public void cellWithOneNeighborDoesNotLive() {
		int liveNeighbors = 1;
		assertFalse(rules.determineIfCellLivesFromNumberOf(liveNeighbors));		
	}
	
	@Test
	public void cellWithZeroNeighborsDoesNotLive() {
		int liveNeighbors = 0;
		assertFalse(rules.determineIfCellLivesFromNumberOf(liveNeighbors));		
	}
	
	@Test
	public void liveCellWithThreeNeighborsDies() {
		int liveNeighbors = 3;
		assertFalse(rules.determineIfCellLivesFromNumberOf(liveNeighbors));			
	}
	
	@Test
	public void deadCellwithThreeNeighborsLives() {
		int liveNeighbors = 3;
		rules.setAlive(false);
		assertTrue(rules.determineIfCellLivesFromNumberOf(liveNeighbors));			
	}
	
	
}

