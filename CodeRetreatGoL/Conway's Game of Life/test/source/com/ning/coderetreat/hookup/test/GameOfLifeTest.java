package com.ning.coderetreat.hookup.test;

import static org.junit.Assert.*;

import org.junit.Test;
/*
 *  Uses Keith Braithwaite's TDD As If You Meant It
Any live cell with ONE OR FEWER live neighbours dies, as if caused by underpopulation.
Any live cell with FOUR OR MORE live neighbours dies, as if by overcrowding.
Any live cell with TWO live neighbours lives on to the next generation.

Any live cell with THREE live neighbours lives on to the next generation.
Any dead cell with exactly THREE live neighbours becomes a live cell.
 */

public class GameOfLifeTest {
	@Test
	public void cellWithFiveNeighborsDoesNotLive() {
		int liveNeighbors = 5;
		assertFalse(lives(liveNeighbors));			
	}
	
	@Test
	public void cellWithFourNeighborsDoesNotLive() {
		int liveNeighbors = 4;
		assertFalse(lives(liveNeighbors));			
	}
	
	@Test
	public void cellWithTwoNeighborsLives() {
		int liveNeighbors = 2;
		assertTrue(lives(liveNeighbors));
	}
	
	@Test
	public void cellWithOneNeighborDoesNotLive() {
		int liveNeighbors = 1;
		assertFalse(lives(liveNeighbors));		
	}
	
	@Test
	public void cellWithZeroNeighborsDoesNotLive() {
		int liveNeighbors = 0;
		assertFalse(lives(liveNeighbors));		
	}
	
	@Test
	public void liveCellWithThreeNeighborsDies() {
		int liveNeighbors = 3;
		assertFalse(lives(liveNeighbors));			
	}
	
	@Test
	public void deadCellwithThreeLiveNeighborsLives() {
		int liveNeighbors = 3;
		alive = false;
		assertTrue(lives(liveNeighbors));			
	}
	
	//------
	
	private boolean alive = true;

	private boolean lives(int liveNeighbors) {
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

}
