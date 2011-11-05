package com.pillar.interview;


public class D_WaysToJumpCounter {

    /**
     * A set of stairs has n steps.
     * You can jump either 1 or 2 steps at a time.
     * For example, if the stairs is n=4 steps, you can reach the end in 5 possible ways:
     * 1-1-1-1, or 1-2-1 or 1-1-2 or 2-1-1 or 2-2
     * Please implement this method to
     * return the count of the different ways to reach the end of the stairs with n steps.
	 */
	public long countWaysToJump (int stairs) {
		int n = stairs + 1;
		
		long firstFibPartial = f(n-1);
		long secondFibPartial = f(n-2);
		
		return firstFibPartial + secondFibPartial;
	}

	public long f(int i) {
		// TODO recursive? Dunno!
		return 0;
	}
}
