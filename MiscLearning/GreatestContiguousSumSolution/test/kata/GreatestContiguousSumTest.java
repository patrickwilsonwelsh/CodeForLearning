package kata;

import static org.junit.Assert.*;

import org.junit.Test;

public class GreatestContiguousSumTest {
	@Test
	public void notOffByOne() throws Exception {
		GreatestContiguousSum sum = new GreatestContiguousSum(new int[] {1});
		assertEquals(1, sum.findGreatestContiguousSum());
	}
	
	@Test
	public void canFindSingleHighestNegative_AmongAllNegatives() throws Exception {
		GreatestContiguousSum sum = new GreatestContiguousSum(new int[] {-99, -5, -1, -48, -6});
		assertEquals(-1, sum.findGreatestContiguousSum());
	}
	
	@Test
	public void canFindContiguousPositiveSum_AmongNegatives() throws Exception {
		GreatestContiguousSum sum = new GreatestContiguousSum(new int[] {-99, -5, 2, 1, -1, -48, -6});
		assertEquals(3, sum.findGreatestContiguousSum());
	}
	
	@Test
	public void canFindBracketedGroup_ThatIncludesNegative() throws Exception {
		GreatestContiguousSum sum = new GreatestContiguousSum(new int[] {-2, 1, -99, 500, -1, 900, -12, 8, 4});
		assertEquals(1399, sum.findGreatestContiguousSum());
	}
	
}
