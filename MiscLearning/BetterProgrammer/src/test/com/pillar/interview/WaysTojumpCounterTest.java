package com.pillar.interview;


/**
 * A set of stairs has n steps.
 * You can jump either 1 or 2 steps at a time.
 * For example, if the stairs is n=4 steps, you can reach the end in 5 possible ways:
 * 1-1-1-1, or 1-2-1 or 1-1-2 or 2-1-1 or 2-2
 * Please implement this method to
 * return the count of the different ways to reach the end of the stairs with n steps.
 * 
 * This looks like simple Fn where n = stairs + 1 and F = Fibonacci calculation 
 */



//TODO: GIVEN 1 stair, return 1 ways to jump
//TODO: GIVEN 2 stairs, return 2 ways to jump
//TODO: GIVEN 3 stairs, return 3 ways to jump
//TODO: GIVEN 4 stairs, return 5 ways to jump
//TODO: GIVEN 5 stairs, (1-1-1-1-1, 2-1-1-1, 1-2-1-1, 1-1-1-2, 1-1-2-1, 1-2-2, 2-2-1, 2-1-2) = 8

/*
 * GIVEN 6 stairs:  return 13
 * 1-1-1-1-1-1
 * 1-1-1-1-2
 * 1-1-1-2-1
 * 1-1-2-1-1
 * 1-2-1-1-1
 * 2-1-1-1-1
 * 2-2-1-1
 * 2-1-2-1
 * 2-1-1-2
 * 1-1-2-2
 * 1-2-1-2
 * 1-1-2-2
 * 2-2-2
 * 
 * 
 */
                          

/*
 * 0 => 0
 * 1 => 1
 * 2 => 2
 * 3 => 3
 * 4 => 5
 * 5 => 8
 * 
 * 
 */


public class WaysTojumpCounterTest {

}
