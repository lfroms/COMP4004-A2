package model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class TurnTest {
	@Test
	public void testTurnCanContinueIsTrueIfLessThanThreeSkulls() {
		Turn turn = new Turn();

		List<Die> dice = turn.getDice();
		dice.forEach(die -> {
			die.setFace(DieFace.COIN);
		});

		dice.get(0).setFace(DieFace.SKULL);

		assertTrue(turn.turnCanContinue());
	}

	@Test
	public void testTurnCanContinueIsFalseIfAtLeastThreeSkulls() {
		Turn turn = new Turn();

		List<Die> dice = turn.getDice();
		dice.forEach(die -> {
			die.setFace(DieFace.SKULL);
		});

		dice.get(0).setFace(DieFace.DIAMOND);

		assertFalse(turn.turnCanContinue());
	}

	@Test
	public void testRollDiceThrowsTurnCompleteExceptionIfCannotContinue() throws InsufficientDiceException {
		Turn turn = new Turn();

		List<Die> dice = turn.getDice();
		dice.forEach(die -> {
			die.setFace(DieFace.SKULL);
		});

		try {
			turn.rollDice();

			fail("TurnCompleteException was not thrown when the turn was complete");
		} catch (TurnCompleteException exception) {
		}
	}

	@Test
	public void testRollDiceDoesNotThrowTurnCompleteExceptionIfCanContinue() throws InsufficientDiceException {
		Turn turn = new Turn();

		List<Die> dice = turn.getDice();
		dice.forEach(die -> {
			die.setFace(DieFace.DIAMOND);
		});

		try {
			turn.rollDice();
		} catch (TurnCompleteException exception) {
			fail("TurnCompleteException was thrown when the turn was not complete");
		}
	}

	@Test
	public void testRollDiceThrowsInsufficientDiceExceptionIfInsufficientDice() throws TurnCompleteException {
		Turn turn = new Turn();

		List<Die> dice = turn.getDice();
		dice.forEach(die -> {
			die.setFace(DieFace.DIAMOND);
			die.setHeld(true);
		});

		try {
			turn.rollDice();

			fail("InsufficientDiceException was not thrown when there were insufficient dice");
		} catch (InsufficientDiceException exception) {
		}
	}

	@Test
	public void testRollDiceDoesNotThrowInsufficientDiceExceptionIfSufficientDice() throws TurnCompleteException {
		Turn turn = new Turn();

		List<Die> dice = turn.getDice();
		dice.forEach(die -> {
			die.setFace(DieFace.DIAMOND);
			die.setHeld(false);
		});

		try {
			turn.rollDice();
		} catch (InsufficientDiceException exception) {
			fail("InsufficientDiceException was thrown when there were sufficient dice");
		}
	}

	@Test
	public void testSetOfThreeWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));

		List<Die> dice = turn.getDice();
		dice.subList(0, 3).forEach(die -> {
			die.setFace(DieFace.MONKEY);
		});

		dice.get(3).setFace(DieFace.PARROT);
		dice.get(4).setFace(DieFace.SKULL);
		dice.get(5).setFace(DieFace.SKULL);
		dice.get(6).setFace(DieFace.PARROT);
		dice.get(7).setFace(DieFace.SWORD);

		assertEquals(Integer.valueOf(200), turn.evaluatePoints());
	}

	@Test
	public void testTwoSetsOfThreeWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));

		List<Die> dice = turn.getDice();
		dice.subList(0, 3).forEach(die -> {
			die.setFace(DieFace.MONKEY);
		});
		dice.subList(3, 6).forEach(die -> {
			die.setFace(DieFace.SWORD);
		});

		dice.get(6).setFace(DieFace.PARROT);
		dice.get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(300), turn.evaluatePoints());
	}

	@Test
	public void testThreeDiamondsWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));

		List<Die> dice = turn.getDice();
		dice.subList(0, 3).forEach(die -> {
			die.setFace(DieFace.DIAMOND);
		});

		dice.get(3).setFace(DieFace.PARROT);
		dice.get(4).setFace(DieFace.SKULL);
		dice.get(5).setFace(DieFace.PARROT);
		dice.get(6).setFace(DieFace.SWORD);
		dice.get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(500), turn.evaluatePoints());
	}

	@Test
	public void testFourCoinsWithDiamondFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.DIAMOND));

		List<Die> dice = turn.getDice();
		dice.subList(0, 4).forEach(die -> {
			die.setFace(DieFace.COIN);
		});

		dice.get(4).setFace(DieFace.SKULL);
		dice.get(5).setFace(DieFace.PARROT);
		dice.get(6).setFace(DieFace.SWORD);
		dice.get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(700), turn.evaluatePoints());
	}

	@Test
	public void testThreeSwordsAndFourParrotsWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));

		List<Die> dice = turn.getDice();
		dice.subList(0, 3).forEach(die -> {
			die.setFace(DieFace.SWORD);
		});
		dice.subList(3, 7).forEach(die -> {
			die.setFace(DieFace.PARROT);
		});

		dice.get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(400), turn.evaluatePoints());
	}

	@Test
	public void testThreeCoinsAndFourSwordsWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));

		List<Die> dice = turn.getDice();
		dice.subList(0, 3).forEach(die -> {
			die.setFace(DieFace.COIN);
		});
		dice.subList(3, 7).forEach(die -> {
			die.setFace(DieFace.SWORD);
		});

		dice.get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(800), turn.evaluatePoints());
	}

	@Test
	public void testThreeCoinsAndFourSwordsWithCaptainFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.CAPTAIN));

		List<Die> dice = turn.getDice();
		dice.subList(0, 3).forEach(die -> {
			die.setFace(DieFace.COIN);
		});
		dice.subList(3, 7).forEach(die -> {
			die.setFace(DieFace.SWORD);
		});

		dice.get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(1200), turn.evaluatePoints());
	}

	@Test
	public void testFiveSwordsWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));

		List<Die> dice = turn.getDice();
		dice.subList(0, 5).forEach(die -> {
			die.setFace(DieFace.SWORD);
		});

		dice.get(5).setFace(DieFace.PARROT);
		dice.get(6).setFace(DieFace.SKULL);
		dice.get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(600), turn.evaluatePoints());
	}

	@Test
	public void testSixMonkeysWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));

		List<Die> dice = turn.getDice();
		dice.subList(0, 6).forEach(die -> {
			die.setFace(DieFace.MONKEY);
		});

		dice.get(6).setFace(DieFace.SKULL);
		dice.get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(1100), turn.evaluatePoints());
	}

	@Test
	public void testSevenParrotsWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));

		List<Die> dice = turn.getDice();
		dice.subList(0, 7).forEach(die -> {
			die.setFace(DieFace.PARROT);
		});

		dice.get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(2100), turn.evaluatePoints());
	}

	@Test
	public void testEightCoinsWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));

		List<Die> dice = turn.getDice();
		dice.subList(0, 8).forEach(die -> {
			die.setFace(DieFace.COIN);
		});

		assertEquals(Integer.valueOf(5300), turn.evaluatePoints());
	}

	@Test
	public void testEightCoinsWithDiamondFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.DIAMOND));

		List<Die> dice = turn.getDice();
		dice.subList(0, 8).forEach(die -> {
			die.setFace(DieFace.COIN);
		});

		assertEquals(Integer.valueOf(5400), turn.evaluatePoints());
	}

	@Test
	public void testEightSwordsWithCaptainFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.CAPTAIN));

		List<Die> dice = turn.getDice();
		dice.subList(0, 8).forEach(die -> {
			die.setFace(DieFace.SWORD);
		});

		assertEquals(Integer.valueOf(9000), turn.evaluatePoints());
	}

	@Test
	public void testEightMonkeysWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));

		List<Die> dice = turn.getDice();
		dice.subList(0, 8).forEach(die -> {
			die.setFace(DieFace.MONKEY);
		});

		assertEquals(Integer.valueOf(4600), turn.evaluatePoints());
	}

	@Test
	public void testTwoDiamondsWithDiamondFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.DIAMOND));

		List<Die> dice = turn.getDice();
		dice.subList(0, 2).forEach(die -> {
			die.setFace(DieFace.DIAMOND);
		});

		dice.get(2).setFace(DieFace.MONKEY);
		dice.get(3).setFace(DieFace.SKULL);
		dice.get(4).setFace(DieFace.MONKEY);
		dice.get(5).setFace(DieFace.PARROT);
		dice.get(6).setFace(DieFace.SWORD);
		dice.get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(400), turn.evaluatePoints());
	}

	@Test
	public void testThreeCoinsWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));

		List<Die> dice = turn.getDice();
		dice.subList(0, 3).forEach(die -> {
			die.setFace(DieFace.COIN);
		});

		dice.get(3).setFace(DieFace.SKULL);
		dice.get(4).setFace(DieFace.MONKEY);
		dice.get(5).setFace(DieFace.PARROT);
		dice.get(6).setFace(DieFace.SWORD);
		dice.get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(600), turn.evaluatePoints());
	}

	@Test
	public void testFourMonkeysAndFourCoinsWithGoldFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));

		List<Die> dice = turn.getDice();
		dice.subList(0, 4).forEach(die -> {
			die.setFace(DieFace.MONKEY);
		});
		dice.subList(4, 6).forEach(die -> {
			die.setFace(DieFace.COIN);
		});

		dice.get(6).setFace(DieFace.SKULL);
		dice.get(7).setFace(DieFace.SKULL);

		assertEquals(Integer.valueOf(600), turn.evaluatePoints());
	}
}
