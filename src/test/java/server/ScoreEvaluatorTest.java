package server;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Dice;
import model.DieFace;
import model.FortuneCard;
import model.FortuneCardType;

public class ScoreEvaluatorTest {
	@Test
	public void testSetOfThreeWithGoldFortuneCard() {
		Dice dice = new Dice();
		ScoreEvaluator evaluator = new ScoreEvaluator(dice, new FortuneCard(FortuneCardType.GOLD));

		dice.getAll().subList(0, 3).forEach(die -> {
			die.setFace(DieFace.MONKEY);
		});

		dice.getAll().get(3).setFace(DieFace.PARROT);
		dice.getAll().get(4).setFace(DieFace.SKULL);
		dice.getAll().get(5).setFace(DieFace.SKULL);
		dice.getAll().get(6).setFace(DieFace.PARROT);
		dice.getAll().get(7).setFace(DieFace.SWORD);

		assertEquals(Integer.valueOf(200), evaluator.evaluate());
	}

	@Test
	public void testTwoSetsOfThreeWithGoldFortuneCard() {
		Dice dice = new Dice();
		ScoreEvaluator evaluator = new ScoreEvaluator(dice, new FortuneCard(FortuneCardType.GOLD));

		dice.getAll().subList(0, 3).forEach(die -> {
			die.setFace(DieFace.MONKEY);
		});
		dice.getAll().subList(3, 6).forEach(die -> {
			die.setFace(DieFace.SWORD);
		});

		dice.getAll().get(6).setFace(DieFace.PARROT);
		dice.getAll().get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(300), evaluator.evaluate());
	}

	@Test
	public void testThreeDiamondsWithGoldFortuneCard() {
		Dice dice = new Dice();
		ScoreEvaluator evaluator = new ScoreEvaluator(dice, new FortuneCard(FortuneCardType.GOLD));

		dice.getAll().subList(0, 3).forEach(die -> {
			die.setFace(DieFace.DIAMOND);
		});

		dice.getAll().get(3).setFace(DieFace.PARROT);
		dice.getAll().get(4).setFace(DieFace.SKULL);
		dice.getAll().get(5).setFace(DieFace.PARROT);
		dice.getAll().get(6).setFace(DieFace.SWORD);
		dice.getAll().get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(500), evaluator.evaluate());
	}

	@Test
	public void testFourCoinsWithDiamondFortuneCard() {
		Dice dice = new Dice();
		ScoreEvaluator evaluator = new ScoreEvaluator(dice, new FortuneCard(FortuneCardType.DIAMOND));

		dice.getAll().subList(0, 4).forEach(die -> {
			die.setFace(DieFace.COIN);
		});

		dice.getAll().get(4).setFace(DieFace.SKULL);
		dice.getAll().get(5).setFace(DieFace.PARROT);
		dice.getAll().get(6).setFace(DieFace.SWORD);
		dice.getAll().get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(700), evaluator.evaluate());
	}

	@Test
	public void testThreeSwordsAndFourParrotsWithGoldFortuneCard() {
		Dice dice = new Dice();
		ScoreEvaluator evaluator = new ScoreEvaluator(dice, new FortuneCard(FortuneCardType.GOLD));

		dice.getAll().subList(0, 3).forEach(die -> {
			die.setFace(DieFace.SWORD);
		});
		dice.getAll().subList(3, 7).forEach(die -> {
			die.setFace(DieFace.PARROT);
		});

		dice.getAll().get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(400), evaluator.evaluate());
	}

	@Test
	public void testThreeCoinsAndFourSwordsWithGoldFortuneCard() {
		Dice dice = new Dice();
		ScoreEvaluator evaluator = new ScoreEvaluator(dice, new FortuneCard(FortuneCardType.GOLD));

		dice.getAll().subList(0, 3).forEach(die -> {
			die.setFace(DieFace.COIN);
		});
		dice.getAll().subList(3, 7).forEach(die -> {
			die.setFace(DieFace.SWORD);
		});

		dice.getAll().get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(800), evaluator.evaluate());
	}

	@Test
	public void testThreeCoinsAndFourSwordsWithCaptainFortuneCard() {
		Dice dice = new Dice();
		ScoreEvaluator evaluator = new ScoreEvaluator(dice, new FortuneCard(FortuneCardType.CAPTAIN));

		dice.getAll().subList(0, 3).forEach(die -> {
			die.setFace(DieFace.COIN);
		});
		dice.getAll().subList(3, 7).forEach(die -> {
			die.setFace(DieFace.SWORD);
		});

		dice.getAll().get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(1200), evaluator.evaluate());
	}

	@Test
	public void testFiveSwordsWithGoldFortuneCard() {
		Dice dice = new Dice();
		ScoreEvaluator evaluator = new ScoreEvaluator(dice, new FortuneCard(FortuneCardType.GOLD));

		dice.getAll().subList(0, 5).forEach(die -> {
			die.setFace(DieFace.SWORD);
		});

		dice.getAll().get(5).setFace(DieFace.PARROT);
		dice.getAll().get(6).setFace(DieFace.SKULL);
		dice.getAll().get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(600), evaluator.evaluate());
	}

	@Test
	public void testSixMonkeysWithGoldFortuneCard() {
		Dice dice = new Dice();
		ScoreEvaluator evaluator = new ScoreEvaluator(dice, new FortuneCard(FortuneCardType.GOLD));

		dice.getAll().subList(0, 6).forEach(die -> {
			die.setFace(DieFace.MONKEY);
		});

		dice.getAll().get(6).setFace(DieFace.SKULL);
		dice.getAll().get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(1100), evaluator.evaluate());
	}

	@Test
	public void testSevenParrotsWithGoldFortuneCard() {
		Dice dice = new Dice();
		ScoreEvaluator evaluator = new ScoreEvaluator(dice, new FortuneCard(FortuneCardType.GOLD));

		dice.getAll().subList(0, 7).forEach(die -> {
			die.setFace(DieFace.PARROT);
		});

		dice.getAll().get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(2100), evaluator.evaluate());
	}

	@Test
	public void testEightCoinsWithGoldFortuneCard() {
		Dice dice = new Dice();
		ScoreEvaluator evaluator = new ScoreEvaluator(dice, new FortuneCard(FortuneCardType.GOLD));

		dice.getAll().subList(0, 8).forEach(die -> {
			die.setFace(DieFace.COIN);
		});

		assertEquals(Integer.valueOf(5300), evaluator.evaluate());
	}

	@Test
	public void testEightCoinsWithDiamondFortuneCard() {
		Dice dice = new Dice();
		ScoreEvaluator evaluator = new ScoreEvaluator(dice, new FortuneCard(FortuneCardType.DIAMOND));

		dice.getAll().subList(0, 8).forEach(die -> {
			die.setFace(DieFace.COIN);
		});

		assertEquals(Integer.valueOf(5400), evaluator.evaluate());
	}

	@Test
	public void testEightSwordsWithCaptainFortuneCard() {
		Dice dice = new Dice();
		ScoreEvaluator evaluator = new ScoreEvaluator(dice, new FortuneCard(FortuneCardType.CAPTAIN));

		dice.getAll().subList(0, 8).forEach(die -> {
			die.setFace(DieFace.SWORD);
		});

		assertEquals(Integer.valueOf(9000), evaluator.evaluate());
	}

	@Test
	public void testEightMonkeysWithGoldFortuneCard() {
		Dice dice = new Dice();
		ScoreEvaluator evaluator = new ScoreEvaluator(dice, new FortuneCard(FortuneCardType.GOLD));

		dice.getAll().subList(0, 8).forEach(die -> {
			die.setFace(DieFace.MONKEY);
		});

		assertEquals(Integer.valueOf(4600), evaluator.evaluate());
	}

	@Test
	public void testTwoDiamondsWithDiamondFortuneCard() {
		Dice dice = new Dice();
		ScoreEvaluator evaluator = new ScoreEvaluator(dice, new FortuneCard(FortuneCardType.DIAMOND));

		dice.getAll().subList(0, 2).forEach(die -> {
			die.setFace(DieFace.DIAMOND);
		});

		dice.getAll().get(2).setFace(DieFace.MONKEY);
		dice.getAll().get(3).setFace(DieFace.SKULL);
		dice.getAll().get(4).setFace(DieFace.MONKEY);
		dice.getAll().get(5).setFace(DieFace.PARROT);
		dice.getAll().get(6).setFace(DieFace.SWORD);
		dice.getAll().get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(400), evaluator.evaluate());
	}

	@Test
	public void testThreeCoinsWithGoldFortuneCard() {
		Dice dice = new Dice();
		ScoreEvaluator evaluator = new ScoreEvaluator(dice, new FortuneCard(FortuneCardType.GOLD));

		dice.getAll().subList(0, 3).forEach(die -> {
			die.setFace(DieFace.COIN);
		});

		dice.getAll().get(3).setFace(DieFace.SKULL);
		dice.getAll().get(4).setFace(DieFace.MONKEY);
		dice.getAll().get(5).setFace(DieFace.PARROT);
		dice.getAll().get(6).setFace(DieFace.SWORD);
		dice.getAll().get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(600), evaluator.evaluate());
	}

	@Test
	public void testFourMonkeysAndFourCoinsWithGoldFortuneCard() {
		Dice dice = new Dice();
		ScoreEvaluator evaluator = new ScoreEvaluator(dice, new FortuneCard(FortuneCardType.GOLD));

		dice.getAll().subList(0, 4).forEach(die -> {
			die.setFace(DieFace.MONKEY);
		});
		dice.getAll().subList(4, 6).forEach(die -> {
			die.setFace(DieFace.COIN);
		});

		dice.getAll().get(6).setFace(DieFace.SKULL);
		dice.getAll().get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(600), evaluator.evaluate());
	}

	@Test
	public void testCombinationAWithMonkeyBusinessFortuneCard() {
		Dice dice = new Dice();
		ScoreEvaluator evaluator = new ScoreEvaluator(dice, new FortuneCard(FortuneCardType.MONKEY_BUSINESS));

		dice.getAll().get(0).setFace(DieFace.MONKEY);
		dice.getAll().get(1).setFace(DieFace.MONKEY);
		dice.getAll().get(2).setFace(DieFace.MONKEY);
		dice.getAll().get(3).setFace(DieFace.PARROT);
		dice.getAll().get(4).setFace(DieFace.PARROT);
		dice.getAll().get(5).setFace(DieFace.PARROT);
		dice.getAll().get(6).setFace(DieFace.SKULL);
		dice.getAll().get(7).setFace(DieFace.COIN);

		assertEquals(Integer.valueOf(1100), evaluator.evaluate());
	}

	@Test
	public void testCombinationBWithMonkeyBusinessFortuneCard() {
		Dice dice = new Dice();
		ScoreEvaluator evaluator = new ScoreEvaluator(dice, new FortuneCard(FortuneCardType.MONKEY_BUSINESS));

		dice.getAll().get(0).setFace(DieFace.MONKEY);
		dice.getAll().get(1).setFace(DieFace.MONKEY);
		dice.getAll().get(2).setFace(DieFace.PARROT);
		dice.getAll().get(3).setFace(DieFace.COIN);
		dice.getAll().get(4).setFace(DieFace.COIN);
		dice.getAll().get(5).setFace(DieFace.DIAMOND);
		dice.getAll().get(6).setFace(DieFace.SWORD);
		dice.getAll().get(7).setFace(DieFace.SWORD);

		assertEquals(Integer.valueOf(400), evaluator.evaluate());
	}

	@Test
	public void testCombinationCWithMonkeyBusinessFortuneCard() {
		Dice dice = new Dice();
		ScoreEvaluator evaluator = new ScoreEvaluator(dice, new FortuneCard(FortuneCardType.MONKEY_BUSINESS));

		dice.getAll().get(0).setFace(DieFace.MONKEY);
		dice.getAll().get(1).setFace(DieFace.MONKEY);
		dice.getAll().get(2).setFace(DieFace.MONKEY);
		dice.getAll().get(3).setFace(DieFace.PARROT);
		dice.getAll().get(4).setFace(DieFace.PARROT);
		dice.getAll().get(5).setFace(DieFace.PARROT);
		dice.getAll().get(6).setFace(DieFace.PARROT);
		dice.getAll().get(7).setFace(DieFace.SWORD);

		assertEquals(Integer.valueOf(2000), evaluator.evaluate());
	}

	@Test
	public void testFullChestWith3Monkeys3Swords1Diamond1ParrotWithGoldFortuneCard() {
		Dice dice = new Dice();
		ScoreEvaluator evaluator = new ScoreEvaluator(dice, new FortuneCard(FortuneCardType.GOLD));

		dice.getAll().get(0).setFace(DieFace.MONKEY);
		dice.getAll().get(1).setFace(DieFace.MONKEY);
		dice.getAll().get(2).setFace(DieFace.MONKEY);
		dice.getAll().get(3).setFace(DieFace.SWORD);
		dice.getAll().get(4).setFace(DieFace.SWORD);
		dice.getAll().get(5).setFace(DieFace.SWORD);
		dice.getAll().get(6).setFace(DieFace.DIAMOND);
		dice.getAll().get(7).setFace(DieFace.PARROT);

		assertEquals(Integer.valueOf(400), evaluator.evaluate());
	}

	@Test
	public void testFullChestWith3Monkeys3Swords2CoinsWithCaptainFortuneCard() {
		Dice dice = new Dice();
		ScoreEvaluator evaluator = new ScoreEvaluator(dice, new FortuneCard(FortuneCardType.CAPTAIN));

		dice.getAll().get(0).setFace(DieFace.MONKEY);
		dice.getAll().get(1).setFace(DieFace.MONKEY);
		dice.getAll().get(2).setFace(DieFace.MONKEY);
		dice.getAll().get(3).setFace(DieFace.SWORD);
		dice.getAll().get(4).setFace(DieFace.SWORD);
		dice.getAll().get(5).setFace(DieFace.SWORD);
		dice.getAll().get(6).setFace(DieFace.COIN);
		dice.getAll().get(7).setFace(DieFace.COIN);

		assertEquals(Integer.valueOf(1800), evaluator.evaluate());
	}

	@Test
	public void testFullChestWith3Monkeys4Swords1DiamondWithGoldFortuneCard() {
		Dice dice = new Dice();
		ScoreEvaluator evaluator = new ScoreEvaluator(dice, new FortuneCard(FortuneCardType.GOLD));

		dice.getAll().get(0).setFace(DieFace.MONKEY);
		dice.getAll().get(1).setFace(DieFace.MONKEY);
		dice.getAll().get(2).setFace(DieFace.MONKEY);
		dice.getAll().get(3).setFace(DieFace.SWORD);
		dice.getAll().get(4).setFace(DieFace.SWORD);
		dice.getAll().get(5).setFace(DieFace.SWORD);
		dice.getAll().get(6).setFace(DieFace.SWORD);
		dice.getAll().get(7).setFace(DieFace.DIAMOND);

		assertEquals(Integer.valueOf(1000), evaluator.evaluate());
	}

	@Test
	public void testFullChestWithMonkeyBusinessFortuneCard() {
		Dice dice = new Dice();
		ScoreEvaluator evaluator = new ScoreEvaluator(dice, new FortuneCard(FortuneCardType.MONKEY_BUSINESS));

		dice.getAll().get(0).setFace(DieFace.MONKEY);
		dice.getAll().get(1).setFace(DieFace.MONKEY);
		dice.getAll().get(2).setFace(DieFace.PARROT);
		dice.getAll().get(3).setFace(DieFace.COIN);
		dice.getAll().get(4).setFace(DieFace.COIN);
		dice.getAll().get(5).setFace(DieFace.DIAMOND);
		dice.getAll().get(6).setFace(DieFace.DIAMOND);
		dice.getAll().get(7).setFace(DieFace.DIAMOND);

		assertEquals(Integer.valueOf(1200), evaluator.evaluate());
	}
}
