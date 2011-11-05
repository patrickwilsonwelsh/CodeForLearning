package hand;

import hand.Pocket.PocketType;

import java.util.HashMap;
import java.util.Map;

public class PocketClassifier {
	public enum PocketGroup {
		One, Two, Three, Four, Five, Six, Seven, None
	};

	private Map<PocketType, PocketGroup> pocketGroups = new HashMap<PocketType, PocketGroup>();

	public PocketClassifier() {
		initPocketGroups();
	}

	public Hand classify(Hand unclassifiedTwoCardHand) throws Exception {
		PocketType pocketType = unclassifiedTwoCardHand.getPocketName();
		PocketGroup group = pocketGroups.get(pocketType);

		if (group == null) {
			group = PocketGroup.None;
			return new GroupNonePocket(unclassifiedTwoCardHand);
		}
		if (group == PocketGroup.One)
			return new GroupOnePocket(unclassifiedTwoCardHand);
		if (group == PocketGroup.Two)
			return new GroupTwoPocket(unclassifiedTwoCardHand);
		if (group == PocketGroup.Three)
			return new GroupThreePocket(unclassifiedTwoCardHand);
		if (group == PocketGroup.Four)
			return new GroupFourPocket(unclassifiedTwoCardHand);
		if (group == PocketGroup.Five)
			return new GroupFivePocket(unclassifiedTwoCardHand);
		if (group == PocketGroup.Six)
			return new GroupSixPocket(unclassifiedTwoCardHand);
		if (group == PocketGroup.Seven)
			return new GroupSevenPocket(unclassifiedTwoCardHand);

		return unclassifiedTwoCardHand;
	}

	private void initPocketGroups() {
		pocketGroups.put(PocketType.AceAce, PocketGroup.One);
		pocketGroups.put(PocketType.KingKing, PocketGroup.One);
		pocketGroups.put(PocketType.QueenQueen, PocketGroup.One);
		pocketGroups.put(PocketType.JackJack, PocketGroup.One);
		pocketGroups.put(PocketType.AceKingSame, PocketGroup.One);

		pocketGroups.put(PocketType.TenTen, PocketGroup.Two);
		pocketGroups.put(PocketType.AceQueenSame, PocketGroup.Two);
		pocketGroups.put(PocketType.AceJackSame, PocketGroup.Two);
		pocketGroups.put(PocketType.KingQueenSame, PocketGroup.Two);
		pocketGroups.put(PocketType.AceKing, PocketGroup.Two);

		pocketGroups.put(PocketType.NineNine, PocketGroup.Three);
		pocketGroups.put(PocketType.KingTenSame, PocketGroup.Three);
		pocketGroups.put(PocketType.QueenJackSame, PocketGroup.Three);
		pocketGroups.put(PocketType.KingJackSame, PocketGroup.Three);
		pocketGroups.put(PocketType.AceTenSame, PocketGroup.Three);
		pocketGroups.put(PocketType.AceQueen, PocketGroup.Three);

		pocketGroups.put(PocketType.AceEightSame, PocketGroup.Four);
		pocketGroups.put(PocketType.KingQueen, PocketGroup.Four);
		pocketGroups.put(PocketType.EightEight, PocketGroup.Four);
		pocketGroups.put(PocketType.QueenTenSame, PocketGroup.Four);
		pocketGroups.put(PocketType.AceNineSame, PocketGroup.Four);
		pocketGroups.put(PocketType.AceTen, PocketGroup.Four);
		pocketGroups.put(PocketType.AceJack, PocketGroup.Four);
		pocketGroups.put(PocketType.JackTenSame, PocketGroup.Four);

		pocketGroups.put(PocketType.SevenSeven, PocketGroup.Five);
		pocketGroups.put(PocketType.QueenNineSame, PocketGroup.Five);
		pocketGroups.put(PocketType.KingJack, PocketGroup.Five);
		pocketGroups.put(PocketType.QueenJack, PocketGroup.Five);
		pocketGroups.put(PocketType.JackTen, PocketGroup.Five);
		pocketGroups.put(PocketType.AceSevenSame, PocketGroup.Five);
		pocketGroups.put(PocketType.AceSixSame, PocketGroup.Five);
		pocketGroups.put(PocketType.AceFiveSame, PocketGroup.Five);
		pocketGroups.put(PocketType.AceFourSame, PocketGroup.Five);
		pocketGroups.put(PocketType.AceThreeSame, PocketGroup.Five);
		pocketGroups.put(PocketType.AceDeuceSame, PocketGroup.Five);
		pocketGroups.put(PocketType.JackNineSame, PocketGroup.Five);
		pocketGroups.put(PocketType.TenNineSame, PocketGroup.Five);
		pocketGroups.put(PocketType.KingNineSame, PocketGroup.Five);
		pocketGroups.put(PocketType.KingTen, PocketGroup.Five);
		pocketGroups.put(PocketType.QueenTen, PocketGroup.Five);

		pocketGroups.put(PocketType.SixSix, PocketGroup.Six);
		pocketGroups.put(PocketType.JackEightSame, PocketGroup.Six);
		pocketGroups.put(PocketType.NineEightSame, PocketGroup.Six);
		pocketGroups.put(PocketType.TenEightSame, PocketGroup.Six);
		pocketGroups.put(PocketType.FiveFive, PocketGroup.Six);
		pocketGroups.put(PocketType.JackNine, PocketGroup.Six);
		pocketGroups.put(PocketType.FourThreeSame, PocketGroup.Six);
		pocketGroups.put(PocketType.SevenFiveSame, PocketGroup.Six);
		pocketGroups.put(PocketType.TenNine, PocketGroup.Six);
		pocketGroups.put(PocketType.ThreeThree, PocketGroup.Six);
		pocketGroups.put(PocketType.NineEight, PocketGroup.Six);
		pocketGroups.put(PocketType.SixFourSame, PocketGroup.Six);
		pocketGroups.put(PocketType.DeuceDeuce, PocketGroup.Six);
		pocketGroups.put(PocketType.KingEightSame, PocketGroup.Six);
		pocketGroups.put(PocketType.KingSevenSame, PocketGroup.Six);
		pocketGroups.put(PocketType.KingSixSame, PocketGroup.Six);
		pocketGroups.put(PocketType.KingFiveSame, PocketGroup.Six);
		pocketGroups.put(PocketType.KingFourSame, PocketGroup.Six);
		pocketGroups.put(PocketType.KingThreeSame, PocketGroup.Six);
		pocketGroups.put(PocketType.KingDeuceSame, PocketGroup.Six);
		pocketGroups.put(PocketType.QueenEightSame, PocketGroup.Six);
		pocketGroups.put(PocketType.FourFour, PocketGroup.Six);
		pocketGroups.put(PocketType.EightSevenSame, PocketGroup.Six);
		pocketGroups.put(PocketType.NineSevenSame, PocketGroup.Six);

		pocketGroups.put(PocketType.EightSeven, PocketGroup.Seven);
		pocketGroups.put(PocketType.FiveThreeSame, PocketGroup.Seven);
		pocketGroups.put(PocketType.AceNine, PocketGroup.Seven);
		pocketGroups.put(PocketType.QueenNine, PocketGroup.Seven);
		pocketGroups.put(PocketType.SevenSixSame, PocketGroup.Seven);
		pocketGroups.put(PocketType.FourDeuceSame, PocketGroup.Seven);
		pocketGroups.put(PocketType.ThreeDeuceSame, PocketGroup.Seven);
		pocketGroups.put(PocketType.NineSixSame, PocketGroup.Seven);
		pocketGroups.put(PocketType.EightFiveSame, PocketGroup.Seven);
		pocketGroups.put(PocketType.JackEight, PocketGroup.Seven);
		pocketGroups.put(PocketType.JackSevenSame, PocketGroup.Seven);
		pocketGroups.put(PocketType.SixFive, PocketGroup.Seven);
		pocketGroups.put(PocketType.FiveFour, PocketGroup.Seven);
		pocketGroups.put(PocketType.SevenFourSame, PocketGroup.Seven);
		pocketGroups.put(PocketType.KingNine, PocketGroup.Seven);
		pocketGroups.put(PocketType.TenEight, PocketGroup.Seven);
		pocketGroups.put(PocketType.SevenSix, PocketGroup.Seven);
		pocketGroups.put(PocketType.SixFiveSame, PocketGroup.Seven);
		pocketGroups.put(PocketType.FiveFourSame, PocketGroup.Seven);
		pocketGroups.put(PocketType.EightSixSame, PocketGroup.Seven);
	}
}
