package com.pillar.codemash.kata;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class VennIntersectionTests {

	private List<Integer> firstList;
	private List<Integer> secondList;
	private VennIntersectionFinder vennIntersectionFinder;
	private List<Integer> intersectingSet;
	
	@Before
	public void setUp() throws Exception {
		firstList = new ArrayList<Integer>();
		secondList = new ArrayList<Integer>();
		
		vennIntersectionFinder = new VennIntersectionFinder();
	}

	@Test
	public void two_empty_lists_will_return_an_empty_intersection() {
		intersectingSet = vennIntersectionFinder.findAllNonDuplicateCommonIntegers(firstList, secondList);
		assertTrue(intersectingSet.isEmpty());
	}
	
	
	@Test
	public void second_null_lists_will_return_an_empty_intersection() {
		intersectingSet = vennIntersectionFinder.findAllNonDuplicateCommonIntegers(firstList, null);
		assertTrue(intersectingSet.isEmpty());
	}

	@Test
	public void first_null_lists_will_return_an_empty_intersection() {
		intersectingSet = vennIntersectionFinder.findAllNonDuplicateCommonIntegers(null, secondList);
		assertTrue(intersectingSet.isEmpty());
	}

	@Test
	public void two_lists_with_null_elements_will_return_an_empty_list() {
		firstList.add(null);
		secondList.add(null);
		intersectingSet = vennIntersectionFinder.findAllNonDuplicateCommonIntegers(firstList, secondList);

		assertEquals(0, intersectingSet.size());
	}

	@Test
	public void one_integer_and_second_list_empty_returns_an_empty_intersection() {
		firstList.add(1);
		intersectingSet = vennIntersectionFinder.findAllNonDuplicateCommonIntegers(firstList, secondList);
		
		assertEquals(1, intersectingSet.size());
		assertEquals(1, intersectingSet.get(0));
	}

	@Test
	public void one_integer_and_first_list_empty_returns_an_empty_intersection() {
		secondList.add(1);
		intersectingSet = vennIntersectionFinder.findAllNonDuplicateCommonIntegers(firstList, secondList);
		
		assertEquals(1, intersectingSet.size());
		assertEquals(1, intersectingSet.get(0));
	}

	@Test
	public void the_same_integer_in_two_lists_returns_a_list_with_the_integer() {
		firstList.add(1);
		secondList.add(1);
		intersectingSet = vennIntersectionFinder.findAllNonDuplicateCommonIntegers(firstList, secondList);
		
		assertEquals(1, intersectingSet.size());
		assertEquals(1, intersectingSet.get(0));
	}

	@Test
	public void non_intesecting_integers_should_not_be_returned_1() {
		firstList.add(1);
		firstList.add(2);
		secondList.add(1);
		intersectingSet = vennIntersectionFinder.findAllNonDuplicateCommonIntegers(firstList, secondList);
		
		assertEquals(1, intersectingSet.size());
		assertEquals(1, intersectingSet.get(0));
	}


	@Test
	public void non_intesecting_integers_should_not_be_returned_2() {
		firstList.add(7);
		firstList.add(6);
		firstList.add(5);
		firstList.add(1);
		firstList.add(2);
		firstList.add(3);
		firstList.add(4);
		secondList.add(2);
		secondList.add(8);
		secondList.add(6);
		secondList.add(4);
		
		intersectingSet = vennIntersectionFinder.findAllNonDuplicateCommonIntegers(firstList, secondList);
		
		assertEquals(3, intersectingSet.size());
		assertTrue(intersectingSet.contains(2));
		assertTrue(intersectingSet.contains(6));
		assertTrue(intersectingSet.contains(4));
	}

	
	@Test
	public void the_resulting_intersection_should_have_no_duplicates() {
		firstList.add(1);
		firstList.add(1);
		secondList.add(1);
		intersectingSet = vennIntersectionFinder.findAllNonDuplicateCommonIntegers(firstList, secondList);
		
		assertEquals(1, intersectingSet.size());
		assertEquals(1, intersectingSet.get(0));
	}


}
