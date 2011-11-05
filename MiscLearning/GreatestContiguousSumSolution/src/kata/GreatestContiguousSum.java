package kata;

import static kata.Range.*;

public class GreatestContiguousSum {
	private int max = Integer.MIN_VALUE;
	private final int[] input;
	
	public GreatestContiguousSum(int[] input) {
		this.input = input;
	}

	public int findGreatestContiguousSum() {
		for (int start : range (0, input.length)) {
			findGreatestSumFromStart(start);
		}
		return max;
	}

	private void findGreatestSumFromStart(int start) {
		int sum = 0;
		int tailLength = input.length-start;
		
		for (int window : inclusiveRange(1, tailLength)) {
			int thisElement = start + window - 1;
			sum += input[thisElement];
			if (sum > max) {max = sum;}
		}
	}
}
