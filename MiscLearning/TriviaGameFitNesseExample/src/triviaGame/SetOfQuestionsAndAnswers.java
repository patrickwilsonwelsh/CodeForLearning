package triviaGame;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class SetOfQuestionsAndAnswers extends LinkedHashMap {
	private static final long serialVersionUID = -3382583623331647114L;
	private Iterator iterator;

	public SetOfQuestionsAndAnswers() {
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
