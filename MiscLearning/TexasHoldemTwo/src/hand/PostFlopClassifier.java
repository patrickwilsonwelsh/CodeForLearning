package hand;

import hand.PocketClassifier.PocketGroup;

public class PostFlopClassifier {
	// The low limit hold'em hand categories are: Big Pairs, Draws, and
	// Milking Hands.
	public enum PostFlopHandGroup {
		BigPair, Draw, MilkingHand, None
	};

	// private Map<String, PostFlopHandGroup> postFlopHandGroup = new
	// HashMap<String, PostFlopHandGroup>();

	public Hand classify(Hand unclassifiedPostFlopHand) throws Exception {
		// First, check for SlowPlayHand (two pair or better)
		// else, check for FastPlayHand,
		// else, check for DrawingHand

		if (isMilkingHand(unclassifiedPostFlopHand)) {
			return new MilkingHand(unclassifiedPostFlopHand);
		}

		// Here is the BigPair code
		PocketClassifier pocketClassifier = new PocketClassifier();
		if (pocketClassifier.classify(unclassifiedPostFlopHand)
				.getPocketGroup() == PocketGroup.One) {
			// We have a BigPair. How to represent? As GroupOnePockets, or
			// GroupTwoPockets?
			// Do we revise the object model for hand groups
		}

		return null;

	}

	private boolean isMilkingHand(Hand unclassifiedPostFlopHand) {
		return false;
	}
}
