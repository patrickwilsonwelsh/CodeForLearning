package kata;

import java.util.Iterator;

public class IntegerRange implements Iterable<Integer> {
	int start, end;

	public IntegerRange(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public Iterator<Integer> iterator() {
		if (start <= end) {
			return new ForwardIntegerRangeIterator(start, end);
		} else {
			return new ReverseIntegerRangeIterator(start, end);
		}
	}

	public int size() {
		return Math.abs(end - start);
	}

	private static abstract class IntegerRangeIterator implements
			Iterator<Integer> {
		protected int current;
		protected int end;

		protected IntegerRangeIterator(int current, int end) {
			this.current = current;
			this.end = end;
		}

		public void remove() {
			throw new UnsupportedOperationException(
					"IntegerRange doesn't support removing values");
		}
	}

	public static final class ForwardIntegerRangeIterator extends
			IntegerRangeIterator {
		public ForwardIntegerRangeIterator(int current, int end) {
			super(current, end);
		}

		public boolean hasNext() {
			return current < end;
		}

		public Integer next() {
			return current++;
		}
	}

	public static final class ReverseIntegerRangeIterator extends
			IntegerRangeIterator {
		public ReverseIntegerRangeIterator(int current, int end) {
			super(current, end);
		}

		public boolean hasNext() {
			return current > end;
		}

		public Integer next() {
			return current--;
		}
	}
}