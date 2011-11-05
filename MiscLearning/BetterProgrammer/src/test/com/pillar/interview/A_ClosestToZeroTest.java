package com.pillar.interview;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.pillar.interview.A_ClosestToZero;

public class A_ClosestToZeroTest {
	private A_ClosestToZero closest;
	
	@Before
	public void setUp() {
		closest = new A_ClosestToZero();
	}
	
	@Test
	public void canFindZeroInArrayContainingZero()  {
		int result = closest.getClosestToZero(new int[] {1, -1, 0});
		assertEquals(0, result);
	}
	
	@Test
	public void canFindClosestNumberInArrayContaining_OnlyPositives() {
		int result = closest.getClosestToZero(new int[] {14, 2, 1});
		assertEquals(1, result);
	}

}
