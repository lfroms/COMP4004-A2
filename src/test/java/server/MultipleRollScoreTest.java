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
		DieFace[][] rollSequence = new DieFace[][] { { DieFace.SKULL, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
				DieFace.PARROT, DieFace.SWORD, DieFace.SWORD, DieFace.SWORD },
				{ DieFace.SKULL, DieFace.SKULL, DieFace.SKULL } };

		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD), rollSequence);
		turn.rollDice();

		assertFalse(turn.isDisqualified());

		turn.getDice().getAll().stream().filter(die -> die.getFace() == DieFace.PARROT)
				.forEach(die -> die.setHeld(true));

		turn.rollDice();
		assertTrue(turn.isDisqualified());
	}

	@Test
	public void testTurnCanContinueIsFalseAfterSkullsMultipleRollsB() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] { { DieFace.SKULL, DieFace.SKULL, DieFace.PARROT, DieFace.PARROT,
				DieFace.PARROT, DieFace.PARROT, DieFace.SWORD, DieFace.SWORD }, { DieFace.SKULL, DieFace.SWORD } };

		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD), rollSequence);
		turn.rollDice();

		assertFalse(turn.isDisqualified());

		turn.getDice().getAll().stream().filter(die -> die.getFace() == DieFace.PARROT)
				.forEach(die -> die.setHeld(true));

		turn.rollDice();
		assertTrue(turn.isDisqualified());
	}

	@Test
	public void testTurnCanContinueIsFalseAfterSkullsMultipleRollsC() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.SKULL, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT, DieFace.SWORD,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.SKULL, DieFace.MONKEY, DieFace.MONKEY }, { DieFace.SKULL, DieFace.MONKEY } };

		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD), rollSequence);
		turn.rollDice();

		assertFalse(turn.isDisqualified());

		turn.getDice().getAll().stream().filter(die -> die.getFace() == DieFace.PARROT)
				.forEach(die -> die.setHeld(true));

		turn.rollDice();
		assertFalse(turn.isDisqualified());

		turn.rollDice();

		assertTrue(turn.isDisqualified());
	}

	@Test
	public void testThirdMonkeyOnSecondRoll() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.SKULL, DieFace.SKULL, DieFace.PARROT, DieFace.PARROT, DieFace.MONKEY, DieFace.MONKEY,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.PARROT, DieFace.PARROT, DieFace.MONKEY, DieFace.MONKEY, DieFace.MONKEY, DieFace.SWORD } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.GOLD);
		Turn turn = new Turn(fortuneCard, rollSequence);

		turn.rollDice();
		turn.rollDice();

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(200), evaluator.evaluate());
	}

	@Test
	public void testTwoSetsOfThreeUsingTwoRolls() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] { { DieFace.MONKEY, DieFace.MONKEY, DieFace.MONKEY, DieFace.PARROT,
				DieFace.SKULL, DieFace.SKULL, DieFace.SWORD, DieFace.SWORD },
				{ DieFace.PARROT, DieFace.PARROT, DieFace.PARROT } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.GOLD);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		turn.getDice().getAll().get(0).setHeld(true);
		turn.getDice().getAll().get(1).setHeld(true);
		turn.getDice().getAll().get(2).setHeld(true);

		turn.rollDice();

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(300), evaluator.evaluate());
	}

	@Test
	public void testThreeCoinsFourSwordsOverSeveralRollsGoldFortuneCard() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.COIN, DieFace.COIN, DieFace.COIN, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.SWORD, DieFace.SWORD, DieFace.PARROT, DieFace.SWORD, DieFace.SWORD },
				{ DieFace.SWORD, DieFace.SWORD, DieFace.SWORD, DieFace.SWORD, DieFace.PARROT } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.GOLD);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		turn.getDice().getAll().get(0).setHeld(true);
		turn.getDice().getAll().get(1).setHeld(true);
		turn.getDice().getAll().get(2).setHeld(true);

		turn.rollDice();
		turn.rollDice();

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(800), evaluator.evaluate());
	}

	@Test
	public void testThreeCoinsFourSwordsOverSeveralRollsCaptainFortuneCard() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.COIN, DieFace.COIN, DieFace.COIN, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.SWORD, DieFace.SWORD, DieFace.PARROT, DieFace.SWORD, DieFace.SWORD },
				{ DieFace.SWORD, DieFace.SWORD, DieFace.SWORD, DieFace.SWORD, DieFace.PARROT } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.CAPTAIN);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		turn.getDice().getAll().get(0).setHeld(true);
		turn.getDice().getAll().get(1).setHeld(true);
		turn.getDice().getAll().get(2).setHeld(true);

		turn.rollDice();
		turn.rollDice();

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(1200), evaluator.evaluate());
	}

	@Test
	public void testSetOfFiveSwordsOverThreeRolls() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.COIN, DieFace.COIN, DieFace.COIN, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.COIN, DieFace.COIN, DieFace.COIN, DieFace.COIN, DieFace.COIN, DieFace.SWORD },
				{ DieFace.SKULL, DieFace.SKULL, DieFace.PARROT, DieFace.SWORD, DieFace.SWORD } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.GOLD);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		turn.getDice().getAll().get(6).setHeld(true);
		turn.getDice().getAll().get(7).setHeld(true);

		turn.rollDice();

		turn.getDice().getAll().get(5).setHeld(true);

		turn.rollDice();

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(600), evaluator.evaluate());
	}

	@Test
	public void testEightMonkeysOverSeveralRolls() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.MONKEY, DieFace.COIN, DieFace.COIN, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.MONKEY, DieFace.COIN, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT, DieFace.SWORD,
						DieFace.SWORD },
				{ DieFace.MONKEY, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT, DieFace.SWORD, DieFace.SWORD },
				{ DieFace.MONKEY, DieFace.MONKEY, DieFace.MONKEY, DieFace.MONKEY, DieFace.MONKEY } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.GOLD);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		turn.getDice().getAll().get(0).setHeld(true);

		turn.rollDice();

		turn.getDice().getAll().get(1).setHeld(true);
		turn.rollDice();

		turn.getDice().getAll().get(2).setHeld(true);
		turn.rollDice();

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(4600), evaluator.evaluate());
	}

	@Test
	public void testTwoDiamondsOverTwoRolls() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.DIAMOND, DieFace.COIN, DieFace.COIN, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.DIAMOND, DieFace.SKULL, DieFace.PARROT, DieFace.SKULL, DieFace.PARROT, DieFace.SWORD,
						DieFace.SWORD } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.DIAMOND);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		turn.getDice().getAll().get(0).setHeld(true);

		turn.rollDice();

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(400), evaluator.evaluate());
	}

	@Test
	public void testThreeDiamondsOverTwoRolls() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.DIAMOND, DieFace.COIN, DieFace.COIN, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.DIAMOND, DieFace.DIAMOND, DieFace.PARROT, DieFace.SKULL, DieFace.PARROT, DieFace.SWORD,
						DieFace.SWORD } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.GOLD);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		turn.getDice().getAll().get(0).setHeld(true);

		turn.rollDice();

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(500), evaluator.evaluate());
	}

	@Test
	public void testThreeCoinsOverTwoRollsGoldFortuneCard() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.COIN, DieFace.COIN, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.COIN, DieFace.PARROT, DieFace.SKULL, DieFace.PARROT, DieFace.SWORD, DieFace.SWORD } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.GOLD);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		turn.getDice().getAll().get(0).setHeld(true);
		turn.getDice().getAll().get(1).setHeld(true);

		turn.rollDice();

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(600), evaluator.evaluate());
	}

	@Test
	public void testThreeCoinsOverTwoRollsDiamondFortuneCard() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.COIN, DieFace.COIN, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.COIN, DieFace.PARROT, DieFace.SKULL, DieFace.PARROT, DieFace.SWORD, DieFace.SWORD } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.DIAMOND);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		turn.getDice().getAll().get(0).setHeld(true);
		turn.getDice().getAll().get(1).setHeld(true);

		turn.rollDice();

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(500), evaluator.evaluate());
	}

	@Test
	public void testRerollSkullSorceressFortuneCard() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.COIN, DieFace.COIN, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.COIN, DieFace.PARROT, DieFace.SKULL, DieFace.PARROT, DieFace.SWORD, DieFace.SWORD } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.DIAMOND);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		turn.getDice().getAll().get(0).setHeld(true);
		turn.getDice().getAll().get(1).setHeld(true);

		turn.rollDice();

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(500), evaluator.evaluate());
	}

	@Test
	public void testTwoMonkeysOneParrotTwoCoinsOneDiamondTwoSwordsSeveralRollsMonkeyBusinessFortuneCard()
			throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.MONKEY, DieFace.MONKEY, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.PARROT, DieFace.COIN, DieFace.PARROT, DieFace.PARROT, DieFace.MONKEY, DieFace.SWORD },
				{ DieFace.COIN, DieFace.DIAMOND, DieFace.SWORD } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.MONKEY_BUSINESS);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		turn.getDice().getAll().get(0).setHeld(true);
		turn.getDice().getAll().get(1).setHeld(true);

		turn.rollDice();

		turn.getDice().getAll().get(2).setHeld(true);
		turn.getDice().getAll().get(3).setHeld(true);
		turn.getDice().getAll().get(7).setHeld(true);

		turn.rollDice();

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(400), evaluator.evaluate());
	}

	@Test
	public void testThreeMonkeysFourParrotsOneSwordSeveralRollsMonkeyBusinessFortuneCard() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.MONKEY, DieFace.MONKEY, DieFace.PARROT, DieFace.PARROT, DieFace.COIN, DieFace.PARROT,
						DieFace.SWORD, DieFace.SWORD },
				{ DieFace.PARROT, DieFace.SWORD, DieFace.PARROT, DieFace.PARROT, DieFace.MONKEY, DieFace.SWORD },
				{ DieFace.MONKEY, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT, DieFace.SWORD } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.MONKEY_BUSINESS);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		turn.getDice().getAll().get(0).setHeld(true);
		turn.getDice().getAll().get(1).setHeld(true);

		turn.rollDice();

		turn.getDice().getAll().get(4).setHeld(true);

		turn.rollDice();

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(2000), evaluator.evaluate());
	}

	@Test
	public void testTreasureChestScenarioA() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.PARROT, DieFace.PARROT, DieFace.PARROT, DieFace.SWORD, DieFace.SWORD, DieFace.DIAMOND,
						DieFace.DIAMOND, DieFace.COIN },
				{ DieFace.PARROT, DieFace.PARROT }, { DieFace.SKULL, DieFace.COIN, DieFace.PARROT } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.TREASURE_CHEST);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		turn.getDice().getAll().get(0).setHeld(true);
		turn.getDice().getAll().get(1).setHeld(true);
		turn.getDice().getAll().get(2).setHeld(true);

		turn.getDice().getAll().get(5).setInTreasureChest(true);
		turn.getDice().getAll().get(6).setInTreasureChest(true);
		turn.getDice().getAll().get(7).setInTreasureChest(true);

		turn.rollDice();

		turn.getDice().getAll().get(5).setInTreasureChest(false);
		turn.getDice().getAll().get(6).setInTreasureChest(false);
		turn.getDice().getAll().get(7).setInTreasureChest(false);

		turn.getDice().getAll().get(0).setInTreasureChest(true);
		turn.getDice().getAll().get(1).setInTreasureChest(true);
		turn.getDice().getAll().get(2).setInTreasureChest(true);
		turn.getDice().getAll().get(3).setInTreasureChest(true);
		turn.getDice().getAll().get(4).setInTreasureChest(true);

		turn.rollDice();

		turn.getDice().getAll().get(6).setInTreasureChest(true);
		turn.getDice().getAll().get(7).setInTreasureChest(true);

		assertFalse(turn.isDisqualified());

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(1100), evaluator.evaluate());
	}

	@Test
	public void testTreasureChestScenarioB() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.SKULL, DieFace.SKULL, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT, DieFace.COIN,
						DieFace.COIN, DieFace.COIN },
				{ DieFace.DIAMOND, DieFace.DIAMOND, DieFace.COIN }, { DieFace.SKULL, DieFace.COIN } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.TREASURE_CHEST);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		turn.getDice().getAll().get(5).setInTreasureChest(true);
		turn.getDice().getAll().get(6).setInTreasureChest(true);
		turn.getDice().getAll().get(7).setInTreasureChest(true);

		turn.rollDice();

		turn.getDice().getAll().get(4).setInTreasureChest(true);

		turn.rollDice();

		assertTrue(turn.isDisqualified());

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(600), evaluator.evaluate());
	}

	@Test
	public void testRollTwoSkullsWithTwoSkullFortuneCard() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.SKULL, DieFace.SKULL, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT, DieFace.COIN,
						DieFace.COIN, DieFace.COIN },
				{ DieFace.SKULL, DieFace.SKULL, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT, DieFace.COIN },
				{ DieFace.SKULL, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.SKULLS, 2);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		assertTrue(turn.getIsIslandOfSkulls());
		assertFalse(turn.isDisqualified());

		turn.rollDice();

		assertTrue(turn.getIsIslandOfSkulls());
		assertFalse(turn.isDisqualified());

		turn.rollDice();

		assertTrue(turn.getIsIslandOfSkulls());
		assertFalse(turn.isDisqualified());

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(700), evaluator.evaluate());
	}

	@Test
	public void testRollThreeSkullsWithTwoSkullFortuneCardAndNoSkullsOnSecondRoll() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.SKULL, DieFace.SKULL, DieFace.SKULL, DieFace.PARROT, DieFace.PARROT, DieFace.COIN,
						DieFace.COIN, DieFace.COIN },
				{ DieFace.PARROT, DieFace.PARROT, DieFace.COIN, DieFace.COIN, DieFace.COIN } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.SKULLS, 2);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		assertTrue(turn.getIsIslandOfSkulls());
		assertFalse(turn.isDisqualified());

		turn.rollDice();

		assertTrue(turn.getIsIslandOfSkulls());
		assertTrue(turn.isDisqualified());

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(500), evaluator.evaluate());
	}

	@Test
	public void testRollThreeSkullsWithOneSkullFortuneCardAndNoSkullsOnSecondRoll() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.SKULL, DieFace.SKULL, DieFace.SKULL, DieFace.PARROT, DieFace.PARROT, DieFace.COIN,
						DieFace.COIN, DieFace.COIN },
				{ DieFace.SKULL, DieFace.PARROT, DieFace.COIN, DieFace.COIN, DieFace.COIN },
				{ DieFace.PARROT, DieFace.COIN, DieFace.COIN, DieFace.COIN } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.SKULLS, 1);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		assertTrue(turn.getIsIslandOfSkulls());
		assertFalse(turn.isDisqualified());

		turn.rollDice();

		assertTrue(turn.getIsIslandOfSkulls());
		assertFalse(turn.isDisqualified());

		turn.rollDice();

		assertTrue(turn.getIsIslandOfSkulls());
		assertTrue(turn.isDisqualified());

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(500), evaluator.evaluate());
	}

	@Test
	public void testFailOnFirstRollWithSeaBattleFortuneCardTwoSwords() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] { { DieFace.SKULL, DieFace.SKULL, DieFace.SKULL, DieFace.PARROT,
				DieFace.PARROT, DieFace.MONKEY, DieFace.SKULL, DieFace.MONKEY } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.SEA_BATTLE, 2);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		assertTrue(turn.isDisqualified());

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(-300), evaluator.seaBattleBonusPoints());
		assertEquals(Integer.valueOf(0), evaluator.evaluate());
	}

	@Test
	public void testFailOnFirstRollWithSeaBattleFortuneCardThreeSwords() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] { { DieFace.SKULL, DieFace.SKULL, DieFace.SKULL, DieFace.PARROT,
				DieFace.PARROT, DieFace.MONKEY, DieFace.SKULL, DieFace.MONKEY } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.SEA_BATTLE, 3);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		assertTrue(turn.isDisqualified());

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(-500), evaluator.seaBattleBonusPoints());
		assertEquals(Integer.valueOf(0), evaluator.evaluate());
	}

	@Test
	public void testFailOnFirstRollWithSeaBattleFortuneCardFourSwords() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] { { DieFace.SKULL, DieFace.SKULL, DieFace.SKULL, DieFace.PARROT,
				DieFace.PARROT, DieFace.MONKEY, DieFace.SKULL, DieFace.MONKEY } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.SEA_BATTLE, 4);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		assertTrue(turn.isDisqualified());

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(-1000), evaluator.seaBattleBonusPoints());
		assertEquals(Integer.valueOf(0), evaluator.evaluate());
	}

	@Test
	public void testSeaBattleCaseA() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] { { DieFace.MONKEY, DieFace.MONKEY, DieFace.MONKEY, DieFace.SWORD,
				DieFace.SWORD, DieFace.COIN, DieFace.PARROT, DieFace.PARROT } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.SEA_BATTLE, 2);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		assertFalse(turn.isDisqualified());

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(300), evaluator.seaBattleBonusPoints());
		assertEquals(Integer.valueOf(500), Integer.valueOf(evaluator.evaluate() + evaluator.seaBattleBonusPoints()));
	}

	@Test
	public void testSeaBattleCaseB() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] { { DieFace.MONKEY, DieFace.MONKEY, DieFace.MONKEY, DieFace.MONKEY,
				DieFace.SWORD, DieFace.SKULL, DieFace.PARROT, DieFace.PARROT },
				{ DieFace.SKULL, DieFace.SWORD, DieFace.SKULL } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.SEA_BATTLE, 2);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		assertFalse(turn.isDisqualified());

		turn.getDice().getAll().get(0).setHeld(true);
		turn.getDice().getAll().get(1).setHeld(true);
		turn.getDice().getAll().get(2).setHeld(true);
		turn.getDice().getAll().get(3).setHeld(true);
		turn.getDice().getAll().get(4).setHeld(true);

		turn.rollDice();

		assertFalse(turn.isDisqualified());

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(300), evaluator.seaBattleBonusPoints());
		assertEquals(Integer.valueOf(500), Integer.valueOf(evaluator.evaluate() + evaluator.seaBattleBonusPoints()));
	}

	@Test
	public void testSeaBattleCaseC() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] { { DieFace.MONKEY, DieFace.MONKEY, DieFace.MONKEY, DieFace.SWORD,
				DieFace.SWORD, DieFace.SWORD, DieFace.SWORD, DieFace.SKULL } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.SEA_BATTLE, 3);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		assertFalse(turn.isDisqualified());

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(500), evaluator.seaBattleBonusPoints());
		assertEquals(Integer.valueOf(800), Integer.valueOf(evaluator.evaluate() + evaluator.seaBattleBonusPoints()));
	}

	@Test
	public void testSeaBattleCaseD() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.MONKEY, DieFace.MONKEY, DieFace.MONKEY, DieFace.MONKEY, DieFace.SWORD, DieFace.SWORD,
						DieFace.SKULL, DieFace.SKULL },
				{ DieFace.SKULL, DieFace.SKULL, DieFace.SWORD, DieFace.SWORD, DieFace.SKULL, DieFace.SKULL } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.SEA_BATTLE, 3);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		assertFalse(turn.isDisqualified());

		turn.getDice().getAll().get(4).setHeld(true);
		turn.getDice().getAll().get(5).setHeld(true);

		turn.rollDice();

		assertTrue(turn.isDisqualified());
	}

	@Test
	public void testSeaBattleCaseE() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] { { DieFace.MONKEY, DieFace.MONKEY, DieFace.MONKEY, DieFace.SWORD,
				DieFace.SWORD, DieFace.SWORD, DieFace.SWORD, DieFace.SKULL } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.SEA_BATTLE, 4);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		assertFalse(turn.isDisqualified());

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(1000), evaluator.seaBattleBonusPoints());
		assertEquals(Integer.valueOf(1300), Integer.valueOf(evaluator.evaluate() + evaluator.seaBattleBonusPoints()));
	}

	@Test
	public void testSeaBattleCaseF() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] {
				{ DieFace.MONKEY, DieFace.MONKEY, DieFace.MONKEY, DieFace.SWORD, DieFace.SKULL, DieFace.DIAMOND,
						DieFace.PARROT, DieFace.PARROT },
				{ DieFace.SWORD, DieFace.SWORD }, { DieFace.SWORD, DieFace.PARROT, DieFace.PARROT } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.SEA_BATTLE, 4);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		assertFalse(turn.isDisqualified());

		turn.getDice().getAll().get(0).setHeld(true);
		turn.getDice().getAll().get(1).setHeld(true);
		turn.getDice().getAll().get(2).setHeld(true);
		turn.getDice().getAll().get(3).setHeld(true);
		turn.getDice().getAll().get(5).setHeld(true);

		turn.rollDice();
		assertFalse(turn.isDisqualified());

		turn.getDice().getAll().get(0).setHeld(false);
		turn.getDice().getAll().get(1).setHeld(false);
		turn.getDice().getAll().get(2).setHeld(false);
		turn.getDice().getAll().get(6).setHeld(true);
		turn.getDice().getAll().get(7).setHeld(true);

		turn.rollDice();
		assertFalse(turn.isDisqualified());

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(1000), evaluator.seaBattleBonusPoints());
		assertEquals(Integer.valueOf(1300), Integer.valueOf(evaluator.evaluate() + evaluator.seaBattleBonusPoints()));
	}

	@Test
	public void testFullChestSeaBattle() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] { { DieFace.MONKEY, DieFace.MONKEY, DieFace.MONKEY, DieFace.MONKEY,
				DieFace.SWORD, DieFace.PARROT, DieFace.PARROT, DieFace.COIN }, { DieFace.COIN, DieFace.SWORD } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.SEA_BATTLE, 2);
		Turn turn = new Turn(fortuneCard, rollSequence);
		turn.rollDice();

		assertFalse(turn.isDisqualified());

		turn.getDice().getAll().get(0).setHeld(true);
		turn.getDice().getAll().get(1).setHeld(true);
		turn.getDice().getAll().get(2).setHeld(true);
		turn.getDice().getAll().get(3).setHeld(true);
		turn.getDice().getAll().get(4).setHeld(true);
		turn.getDice().getAll().get(7).setHeld(true);

		turn.rollDice();
		assertFalse(turn.isDisqualified());

		ScoreEvaluator evaluator = new ScoreEvaluator(turn);
		assertEquals(Integer.valueOf(300), evaluator.seaBattleBonusPoints());
		assertEquals(Integer.valueOf(1200), Integer.valueOf(evaluator.evaluate() + evaluator.seaBattleBonusPoints()));
	}
}
