package server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.DieFace;
import model.FortuneCard;
import model.FortuneCardType;
import model.Turn;

public class MultipleRollScoreTest {
	@Test
	public void testTurnCanContinueIsFalseAfterSkullsMultipleRollsA() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.SKULL, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT, DieFace.SWORD,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.SKULL, DieFace.COIN, DieFace.COIN, DieFace.COIN, DieFace.PARROT, DieFace.SKULL, DieFace.SKULL,
						DieFace.SWORD } };

		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD), rollSequence);

		assertTrue(turn.turnCanContinue());

		turn.getDice().getAll().stream().filter(die -> die.getFace() == DieFace.PARROT)
				.forEach(die -> die.setHeld(true));

		turn.rollDice();
		assertFalse(turn.turnCanContinue());
	}

	@Test
	public void testTurnCanContinueIsFalseAfterSkullsMultipleRollsB() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.SKULL, DieFace.SKULL, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.SKULL, DieFace.SKULL, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
						DieFace.SKULL, DieFace.SWORD } };

		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD), rollSequence);

		assertTrue(turn.turnCanContinue());

		turn.getDice().getAll().stream().filter(die -> die.getFace() == DieFace.PARROT)
				.forEach(die -> die.setHeld(true));

		turn.rollDice();
		assertFalse(turn.turnCanContinue());
	}

	@Test
	public void testTurnCanContinueIsFalseAfterSkullsMultipleRollsC() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.SKULL, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT, DieFace.SWORD,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.SKULL, DieFace.COIN, DieFace.COIN, DieFace.COIN, DieFace.PARROT, DieFace.SKULL,
						DieFace.MONKEY, DieFace.MONKEY },
				{ DieFace.SKULL, DieFace.COIN, DieFace.COIN, DieFace.COIN, DieFace.PARROT, DieFace.SKULL, DieFace.SKULL,
						DieFace.MONKEY } };

		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD), rollSequence);

		assertTrue(turn.turnCanContinue());

		turn.getDice().getAll().stream().filter(die -> die.getFace() == DieFace.PARROT)
				.forEach(die -> die.setHeld(true));

		turn.rollDice();
		assertTrue(turn.turnCanContinue());

		turn.rollDice();

		assertFalse(turn.turnCanContinue());
	}

	@Test
	public void testThirdMonkeyOnSecondRoll() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.SKULL, DieFace.SKULL, DieFace.PARROT, DieFace.PARROT, DieFace.MONKEY, DieFace.MONKEY,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.SKULL, DieFace.SKULL, DieFace.PARROT, DieFace.PARROT, DieFace.MONKEY, DieFace.MONKEY,
						DieFace.MONKEY, DieFace.SWORD } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.GOLD);
		Turn turn = new Turn(fortuneCard, rollSequence);

		turn.rollDice();

		ScoreEvaluator evaluator = new ScoreEvaluator(turn.getDice(), fortuneCard);
		assertEquals(Integer.valueOf(200), evaluator.evaluate());
	}

	@Test
	public void tesetTwoSetsOfThreeUsingTwoRolls() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.MONKEY, DieFace.MONKEY, DieFace.MONKEY, DieFace.PARROT, DieFace.SKULL, DieFace.SKULL,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.MONKEY, DieFace.MONKEY, DieFace.MONKEY, DieFace.PARROT, DieFace.SKULL, DieFace.SKULL,
						DieFace.PARROT, DieFace.PARROT } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.GOLD);
		Turn turn = new Turn(fortuneCard, rollSequence);

		turn.getDice().getAll().get(0).setHeld(true);
		turn.getDice().getAll().get(1).setHeld(true);
		turn.getDice().getAll().get(2).setHeld(true);

		// First roll is done automatically
		turn.rollDice();

		ScoreEvaluator evaluator = new ScoreEvaluator(turn.getDice(), fortuneCard);
		assertEquals(Integer.valueOf(300), evaluator.evaluate());
	}

	@Test
	public void testThreeCoinsFourSwordsOverSeveralRollsGoldFortuneCard() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.COIN, DieFace.COIN, DieFace.COIN, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.COIN, DieFace.COIN, DieFace.COIN, DieFace.SWORD, DieFace.SWORD, DieFace.PARROT, DieFace.SWORD,
						DieFace.SWORD },
				{ DieFace.COIN, DieFace.COIN, DieFace.COIN, DieFace.SWORD, DieFace.SWORD, DieFace.SWORD, DieFace.SWORD,
						DieFace.PARROT } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.GOLD);
		Turn turn = new Turn(fortuneCard, rollSequence);

		turn.getDice().getAll().get(0).setHeld(true);
		turn.getDice().getAll().get(1).setHeld(true);
		turn.getDice().getAll().get(2).setHeld(true);

		// First roll is done automatically
		turn.rollDice();
		turn.rollDice();

		ScoreEvaluator evaluator = new ScoreEvaluator(turn.getDice(), fortuneCard);
		assertEquals(Integer.valueOf(800), evaluator.evaluate());
	}

	@Test
	public void testThreeCoinsFourSwordsOverSeveralRollsCaptainFortuneCard() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.COIN, DieFace.COIN, DieFace.COIN, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.COIN, DieFace.COIN, DieFace.COIN, DieFace.SWORD, DieFace.SWORD, DieFace.PARROT, DieFace.SWORD,
						DieFace.SWORD },
				{ DieFace.COIN, DieFace.COIN, DieFace.COIN, DieFace.SWORD, DieFace.SWORD, DieFace.SWORD, DieFace.SWORD,
						DieFace.PARROT } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.CAPTAIN);
		Turn turn = new Turn(fortuneCard, rollSequence);

		turn.getDice().getAll().get(0).setHeld(true);
		turn.getDice().getAll().get(1).setHeld(true);
		turn.getDice().getAll().get(2).setHeld(true);

		// First roll is done automatically
		turn.rollDice();
		turn.rollDice();

		ScoreEvaluator evaluator = new ScoreEvaluator(turn.getDice(), fortuneCard);
		assertEquals(Integer.valueOf(1200), evaluator.evaluate());
	}

	@Test
	public void testSetOfFiveSwordsOverThreeRolls() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.COIN, DieFace.COIN, DieFace.COIN, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.COIN, DieFace.COIN, DieFace.COIN, DieFace.COIN, DieFace.COIN, DieFace.SWORD, DieFace.SWORD,
						DieFace.SWORD },
				{ DieFace.SKULL, DieFace.SKULL, DieFace.PARROT, DieFace.SWORD, DieFace.SWORD, DieFace.SWORD,
						DieFace.SWORD, DieFace.SWORD } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.GOLD);
		Turn turn = new Turn(fortuneCard, rollSequence);

		turn.getDice().getAll().get(6).setHeld(true);
		turn.getDice().getAll().get(7).setHeld(true);

		// First roll is done automatically
		turn.rollDice();

		turn.getDice().getAll().get(5).setHeld(true);

		turn.rollDice();

		ScoreEvaluator evaluator = new ScoreEvaluator(turn.getDice(), fortuneCard);
		assertEquals(Integer.valueOf(600), evaluator.evaluate());
	}

	@Test
	public void testEightMonkeysOverSeveralRolls() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.MONKEY, DieFace.COIN, DieFace.COIN, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.MONKEY, DieFace.MONKEY, DieFace.COIN, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.MONKEY, DieFace.MONKEY, DieFace.MONKEY, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.MONKEY, DieFace.MONKEY, DieFace.MONKEY, DieFace.MONKEY, DieFace.MONKEY, DieFace.MONKEY,
						DieFace.MONKEY, DieFace.MONKEY } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.GOLD);
		Turn turn = new Turn(fortuneCard, rollSequence);

		turn.getDice().getAll().get(0).setHeld(true);

		// First roll is done automatically
		turn.rollDice();

		turn.getDice().getAll().get(1).setHeld(true);
		turn.rollDice();

		turn.getDice().getAll().get(2).setHeld(true);
		turn.rollDice();

		ScoreEvaluator evaluator = new ScoreEvaluator(turn.getDice(), fortuneCard);
		assertEquals(Integer.valueOf(4600), evaluator.evaluate());
	}

	@Test
	public void testTwoDiamondsOverTwoRolls() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.DIAMOND, DieFace.COIN, DieFace.COIN, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.DIAMOND, DieFace.DIAMOND, DieFace.SKULL, DieFace.PARROT, DieFace.SKULL, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.DIAMOND);
		Turn turn = new Turn(fortuneCard, rollSequence);

		turn.getDice().getAll().get(0).setHeld(true);

		// First roll is done automatically
		turn.rollDice();

		ScoreEvaluator evaluator = new ScoreEvaluator(turn.getDice(), fortuneCard);
		assertEquals(Integer.valueOf(400), evaluator.evaluate());
	}

	@Test
	public void testThreeDiamondsOverTwoRolls() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.DIAMOND, DieFace.COIN, DieFace.COIN, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.DIAMOND, DieFace.DIAMOND, DieFace.DIAMOND, DieFace.PARROT, DieFace.SKULL, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.GOLD);
		Turn turn = new Turn(fortuneCard, rollSequence);

		turn.getDice().getAll().get(0).setHeld(true);

		// First roll is done automatically
		turn.rollDice();

		ScoreEvaluator evaluator = new ScoreEvaluator(turn.getDice(), fortuneCard);
		assertEquals(Integer.valueOf(500), evaluator.evaluate());
	}

	@Test
	public void testThreeCoinsOverTwoRollsGoldFortuneCard() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.COIN, DieFace.COIN, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.COIN, DieFace.COIN, DieFace.COIN, DieFace.PARROT, DieFace.SKULL, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.GOLD);
		Turn turn = new Turn(fortuneCard, rollSequence);

		turn.getDice().getAll().get(0).setHeld(true);
		turn.getDice().getAll().get(1).setHeld(true);

		// First roll is done automatically
		turn.rollDice();

		ScoreEvaluator evaluator = new ScoreEvaluator(turn.getDice(), fortuneCard);
		assertEquals(Integer.valueOf(600), evaluator.evaluate());
	}

	@Test
	public void testThreeCoinsOverTwoRollsDiamondFortuneCard() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.COIN, DieFace.COIN, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.COIN, DieFace.COIN, DieFace.COIN, DieFace.PARROT, DieFace.SKULL, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.DIAMOND);
		Turn turn = new Turn(fortuneCard, rollSequence);

		turn.getDice().getAll().get(0).setHeld(true);
		turn.getDice().getAll().get(1).setHeld(true);

		// First roll is done automatically
		turn.rollDice();

		ScoreEvaluator evaluator = new ScoreEvaluator(turn.getDice(), fortuneCard);
		assertEquals(Integer.valueOf(500), evaluator.evaluate());
	}
}
