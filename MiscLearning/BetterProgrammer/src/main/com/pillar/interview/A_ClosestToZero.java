package com.pillar.interview;

public class A_ClosestToZero {

	/**
	 * Please implement this method to return the number in the array that is
	 * closest to zero. 
	 * 
	 * 
	 * If there are two equally close to zero elements like 2
	 * and -2 - consider the positive element to be "closer" to zero.
	 * 
	 * 
	 * If the input is null or empty, throw an IllegalArgumentException.
	 * 
	 */
	public int getClosestToZero(int[] candidates) { 
		// if an candidate is less than all other candidate return that candidate 
		int bestCandidate = Integer.MIN_VALUE;
		
		for (int candidate : candidates) {
			if (candidate == 0) return candidate;
		}
		
		return bestCandidate;
	}
	
	
}
		