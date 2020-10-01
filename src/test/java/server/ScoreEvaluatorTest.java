package server;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.DieFace;
import model.FortuneCard;
import model.FortuneCardType;
import model.Turn;

public class ScoreEvaluatorTest {
	@Test
	public void testSetOfThreeWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		turn.getDice().getAll().subList(0, 3).forEach(die -> {
			die.setFace(DieFace.MONKEY);
		});

		turn.getDice().getAll().get(3).setFace(DieFace.PARROT);
		turn.getDice().getAll().get(4).setFace(DieFace.SKULL);
		turn.getDice().getAll().get(5).setFace(DieFace.SKULL);
		turn.getDice().getAll().get(6).setFace(DieFace.PARROT);
		turn.getDice().getAll().get(7).setFace(DieFace.SWORD);

		assertEquals(Integer.valueOf(200), evaluator.evaluate());
	}

	@Test
	public void testTwoSetsOfThreeWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		turn.getDice().getAll().subList(0, 3).forEach(die -> {
			die.setFace(DieFace.MONKEY);
		});
		turn.getDice().getAll().subList(3, 6).forEach(die -> {
			die.setFace(DieFace.SWORD);
		});

		turn.getDice().getAll().get(6).setFace(DieFace.PARROT);
		turn.getDice().getAll().get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(300), evaluator.evaluate());
	}

	@Test
	public void testThreeDiamondsWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		turn.getDice().getAll().subList(0, 3).forEach(die -> {
			die.setFace(DieFace.DIAMOND);
		});

		turn.getDice().getAll().get(3).setFace(DieFace.PARROT);
		turn.getDice().getAll().get(4).setFace(DieFace.SKULL);
		turn.getDice().getAll().get(5).setFace(DieFace.PARROT);
		turn.getDice().getAll().get(6).setFace(DieFace.SWORD);
		turn.getDice().getAll().get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(500), evaluator.evaluate());
	}

	@Test
	public void testFourCoinsWithDiamondFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.DIAMOND));
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		turn.getDice().getAll().subList(0, 4).forEach(die -> {
			die.setFace(DieFace.COIN);
		});

		turn.getDice().getAll().get(4).setFace(DieFace.SKULL);
		turn.getDice().getAll().get(5).setFace(DieFace.PARROT);
		turn.getDice().getAll().get(6).setFace(DieFace.SWORD);
		turn.getDice().getAll().get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(700), evaluator.evaluate());
	}

	@Test
	public void testThreeSwordsAndFourParrotsWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		turn.getDice().getAll().subList(0, 3).forEach(die -> {
			die.setFace(DieFace.SWORD);
		});
		turn.getDice().getAll().subList(3, 7).forEach(die -> {
			die.setFace(DieFace.PARROT);
		});

		turn.getDice().getAll().get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(400), evaluator.evaluate());
	}

	@Test
	public void testThreeCoinsAndFourSwordsWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		turn.getDice().getAll().subList(0, 3).forEach(die -> {
			die.setFace(DieFace.COIN);
		});
		turn.getDice().getAll().subList(3, 7).forEach(die -> {
			die.setFace(DieFace.SWORD);
		});

		turn.getDice().getAll().get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(800), evaluator.evaluate());
	}

	@Test
	public void testThreeCoinsAndFourSwordsWithCaptainFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.CAPTAIN));
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		turn.getDice().getAll().subList(0, 3).forEach(die -> {
			die.setFace(DieFace.COIN);
		});
		turn.getDice().getAll().subList(3, 7).forEach(die -> {
			die.setFace(DieFace.SWORD);
		});

		turn.getDice().getAll().get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(1200), evaluator.evaluate());
	}

	@Test
	public void testTwoDiamondsTwoCoinsWithCaptainFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.CAPTAIN));
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		turn.getDice().getAll().subList(0, 3).forEach(die -> {
			die.setFace(DieFace.DIAMOND);
		});
		turn.getDice().getAll().subList(3, 5).forEach(die -> {
			die.setFace(DieFace.COIN);
		});

		turn.getDice().getAll().get(3).setFace(DieFace.PARROT);
		turn.getDice().getAll().get(4).setFace(DieFace.SKULL);
		turn.getDice().getAll().get(5).setFace(DieFace.SKULL);
		turn.getDice().getAll().get(6).setFace(DieFace.PARROT);
		turn.getDice().getAll().get(7).setFace(DieFace.SWORD);

		assertEquals(Integer.valueOf(800), evaluator.evaluate());
	}

	@Test
	public void testFiveSwordsWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		turn.getDice().getAll().subList(0, 5).forEach(die -> {
			die.setFace(DieFace.SWORD);
		});

		turn.getDice().getAll().get(5).setFace(DieFace.PARROT);
		turn.getDice().getAll().get(6).setFace(DieFace.SKULL);
		turn.getDice().getAll().get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(600), evaluator.evaluate());
	}

	@Test
	public void testSixMonkeysWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		turn.getDice().getAll().subList(0, 6).forEach(die -> {
			die.setFace(DieFace.MONKEY);
		});

		turn.getDice().getAll().get(6).setFace(DieFace.SKULL);
		turn.getDice().getAll().get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(1100), evaluator.evaluate());
	}

	@Test
	public void testSevenParrotsWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		turn.getDice().getAll().subList(0, 7).forEach(die -> {
			die.setFace(DieFace.PARROT);
		});

		turn.getDice().getAll().get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(2100), evaluator.evaluate());
	}

	@Test
	public void testEightCoinsWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		turn.getDice().getAll().subList(0, 8).forEach(die -> {
			die.setFace(DieFace.COIN);
		});

		assertEquals(Integer.valueOf(5300), evaluator.evaluate());
	}

	@Test
	public void testEightCoinsWithDiamondFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.DIAMOND));
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		turn.getDice().getAll().subList(0, 8).forEach(die -> {
			die.setFace(DieFace.COIN);
		});

		assertEquals(Integer.valueOf(5400), evaluator.evaluate());
	}

	@Test
	public void testEightSwordsWithCaptainFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.CAPTAIN));
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		turn.getDice().getAll().subList(0, 8).forEach(die -> {
			die.setFace(DieFace.SWORD);
		});

		assertEquals(Integer.valueOf(9000), evaluator.evaluate());
	}

	@Test
	public void testEightMonkeysWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		turn.getDice().getAll().subList(0, 8).forEach(die -> {
			die.setFace(DieFace.MONKEY);
		});

		assertEquals(Integer.valueOf(4600), evaluator.evaluate());
	}

	@Test
	public void testTwoDiamondsWithDiamondFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.DIAMOND));
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		turn.getDice().getAll().subList(0, 2).forEach(die -> {
			die.setFace(DieFace.DIAMOND);
		});

		turn.getDice().getAll().get(2).setFace(DieFace.MONKEY);
		turn.getDice().getAll().get(3).setFace(DieFace.SKULL);
		turn.getDice().getAll().get(4).setFace(DieFace.MONKEY);
		turn.getDice().getAll().get(5).setFace(DieFace.PARROT);
		turn.getDice().getAll().get(6).setFace(DieFace.SWORD);
		turn.getDice().getAll().get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(400), evaluator.evaluate());
	}

	@Test
	public void testThreeCoinsWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		turn.getDice().getAll().subList(0, 3).forEach(die -> {
			die.setFace(DieFace.COIN);
		});

		turn.getDice().getAll().get(3).setFace(DieFace.SKULL);
		turn.getDice().getAll().get(4).setFace(DieFace.MONKEY);
		turn.getDice().getAll().get(5).setFace(DieFace.PARROT);
		turn.getDice().getAll().get(6).setFace(DieFace.SWORD);
		turn.getDice().getAll().get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(600), evaluator.evaluate());
	}

	@Test
	public void testFourMonkeysAndFourCoinsWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		turn.getDice().getAll().subList(0, 4).forEach(die -> {
			die.setFace(DieFace.MONKEY);
		});
		turn.getDice().getAll().subList(4, 6).forEach(die -> {
			die.setFace(DieFace.COIN);
		});

		turn.getDice().getAll().get(6).setFace(DieFace.SKULL);
		turn.getDice().getAll().get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(600), evaluator.evaluate());
	}

	@Test
	public void testCombinationAWithMonkeyBusinessFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.MONKEY_BUSINESS));
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		turn.getDice().getAll().get(0).setFace(DieFace.MONKEY);
		turn.getDice().getAll().get(1).setFace(DieFace.MONKEY);
		turn.getDice().getAll().get(2).setFace(DieFace.MONKEY);
		turn.getDice().getAll().get(3).setFace(DieFace.PARROT);
		turn.getDice().getAll().get(4).setFace(DieFace.PARROT);
		turn.getDice().getAll().get(5).setFace(DieFace.PARROT);
		turn.getDice().getAll().get(6).setFace(DieFace.SKULL);
		turn.getDice().getAll().get(7).setFace(DieFace.COIN);

		assertEquals(Integer.valueOf(1100), evaluator.evaluate());
	}

	@Test
	public void testCombinationBWithMonkeyBusinessFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.MONKEY_BUSINESS));
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		turn.getDice().getAll().get(0).setFace(DieFace.MONKEY);
		turn.getDice().getAll().get(1).setFace(DieFace.MONKEY);
		turn.getDice().getAll().get(2).setFace(DieFace.PARROT);
		turn.getDice().getAll().get(3).setFace(DieFace.COIN);
		turn.getDice().getAll().get(4).setFace(DieFace.COIN);
		turn.getDice().getAll().get(5).setFace(DieFace.DIAMOND);
		turn.getDice().getAll().get(6).setFace(DieFace.SWORD);
		turn.getDice().getAll().get(7).setFace(DieFace.SWORD);

		assertEquals(Integer.valueOf(400), evaluator.evaluate());
	}

	@Test
	public void testCombinationCWithMonkeyBusinessFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.MONKEY_BUSINESS));
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		turn.getDice().getAll().get(0).setFace(DieFace.MONKEY);
		turn.getDice().getAll().get(1).setFace(DieFace.MONKEY);
		turn.getDice().getAll().get(2).setFace(DieFace.MONKEY);
		turn.getDice().getAll().get(3).setFace(DieFace.PARROT);
		turn.getDice().getAll().get(4).setFace(DieFace.PARROT);
		turn.getDice().getAll().get(5).setFace(DieFace.PARROT);
		turn.getDice().getAll().get(6).setFace(DieFace.PARROT);
		turn.getDice().getAll().get(7).setFace(DieFace.SWORD);

		assertEquals(Integer.valueOf(2000), evaluator.evaluate());
	}

	@Test
	public void testFullChestWith3Monkeys3Swords1Diamond1ParrotWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		turn.getDice().getAll().get(0).setFace(DieFace.MONKEY);
		turn.getDice().getAll().get(1).setFace(DieFace.MONKEY);
		turn.getDice().getAll().get(2).setFace(DieFace.MONKEY);
		turn.getDice().getAll().get(3).setFace(DieFace.SWORD);
		turn.getDice().getAll().get(4).setFace(DieFace.SWORD);
		turn.getDice().getAll().get(5).setFace(DieFace.SWORD);
		turn.getDice().getAll().get(6).setFace(DieFace.DIAMOND);
		turn.getDice().getAll().get(7).setFace(DieFace.PARROT);

		assertEquals(Integer.valueOf(400), evaluator.evaluate());
	}

	@Test
	public void testFullChestWith3Monkeys3Swords2CoinsWithCaptainFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.CAPTAIN));
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		turn.getDice().getAll().get(0).setFace(DieFace.MONKEY);
		turn.getDice().getAll().get(1).setFace(DieFace.MONKEY);
		turn.getDice().getAll().get(2).setFace(DieFace.MONKEY);
		turn.getDice().getAll().get(3).setFace(DieFace.SWORD);
		turn.getDice().getAll().get(4).setFace(DieFace.SWORD);
		turn.getDice().getAll().get(5).setFace(DieFace.SWORD);
		turn.getDice().getAll().get(6).setFace(DieFace.COIN);
		turn.getDice().getAll().get(7).setFace(DieFace.COIN);

		assertEquals(Integer.valueOf(1800), evaluator.evaluate());
	}

	@Test
	public void testFullChestWith3Monkeys4Swords1DiamondWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		turn.getDice().getAll().get(0).setFace(DieFace.MONKEY);
		turn.getDice().getAll().get(1).setFace(DieFace.MONKEY);
		turn.getDice().getAll().get(2).setFace(DieFace.MONKEY);
		turn.getDice().getAll().get(3).setFace(DieFace.SWORD);
		turn.getDice().getAll().get(4).setFace(DieFace.SWORD);
		turn.getDice().getAll().get(5).setFace(DieFace.SWORD);
		turn.getDice().getAll().get(6).setFace(DieFace.SWORD);
		turn.getDice().getAll().get(7).setFace(DieFace.DIAMOND);

		assertEquals(Integer.valueOf(1000), evaluator.evaluate());
	}

	@Test
	public void testFullChestWithMonkeyBusinessFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.MONKEY_BUSINESS));
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		turn.getDice().getAll().get(0).setFace(DieFace.MONKEY);
		turn.getDice().getAll().get(1).setFace(DieFace.MONKEY);
		turn.getDice().getAll().get(2).setFace(DieFace.PARROT);
		turn.getDice().getAll().get(3).setFace(DieFace.COIN);
		turn.getDice().getAll().get(4).setFace(DieFace.COIN);
		turn.getDice().getAll().get(5).setFace(DieFace.DIAMOND);
		turn.getDice().getAll().get(6).setFace(DieFace.DIAMOND);
		turn.getDice().getAll().get(7).setFace(DieFace.DIAMOND);

		assertEquals(Integer.valueOf(1200), evaluator.evaluate());
	}
}
