package triviaGame;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class CircularQuestionAnswerMap extends LinkedHashMap {
	private static final long serialVersionUID = -5805554265629320724L;
	private Iterator iterator;

	public CircularQuestionAnswerMap() {
		super();
		iterator = this.entrySet().iterator();
	}

	public String getQuestionAnswerPair() {
		if (!iterator.hasNext())
			iterator = getNewIterator();
		return iterator.next().toString();
	}

	private Iterator getNewIterator() {
		return this.entrySet().iterator();
	}
}
