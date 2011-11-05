package kata;

import static org.junit.Assert.*;

import org.junit.Test;

public class GreatestContiguousSumTest {
	@Test
	public void canFindContiguousPositiveSum_AmongNegatives() throws Exception {
		GreatestContiguousSum sum = new GreatestContiguousSum();
		assertEquals(3, sum.findGreatestContiguousSum(new int[] {-99, -5, 2, 1, -1, -48, -6}));
	}
	
	
}
