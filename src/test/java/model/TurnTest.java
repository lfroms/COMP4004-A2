package model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class TurnTest {
	@Test
	public void testIsDisqualifiedIsFalseIfLessThanThreeSkulls() {
		Turn turn = new Turn();

		List<Die> dice = turn.getDice().getAll();
		dice.forEach(die -> {
			die.setFace(DieFace.COIN);
		});

		dice.get(0).setFace(DieFace.SKULL);

		assertFalse(turn.isDisqualified());
	}

	@Test
	public void testIsDisqualifiedIsTrueIfAtLeastThreeSkulls() {
		Turn turn = new Turn();

		List<Die> dice = turn.getDice().getAll();
		dice.forEach(die -> {
			die.setFace(DieFace.SKULL);
		});

		dice.get(0).setFace(DieFace.DIAMOND);

		assertTrue(turn.isDisqualified());
	}

	@Test
	public void testRollDiceThrowsTurnCompleteExceptionIfCannotContinue() {
		Turn turn = new Turn();

		List<Die> dice = turn.getDice().getAll();
		dice.forEach(die -> {
			die.setFace(DieFace.SKULL);
		});

		try {
			turn.rollDice();

			fail("TurnCompleteException was not thrown when the turn was complete");
		} catch (TurnCompleteException exception) {
		} catch (InsufficientDiceException exception) {
		}
	}

	@Test
	public void testRollDiceDoesNotThrowTurnCompleteExceptionIfCanContinue() {
		Turn turn = new Turn();

		List<Die> dice = turn.getDice().getAll();
		dice.forEach(die -> {
			die.setFace(DieFace.DIAMOND);
		});

		try {
			turn.rollDice();
		} catch (TurnCompleteException exception) {
			fail("TurnCompleteException was thrown when the turn was not complete");
		} catch (InsufficientDiceException exception) {
		}
	}

	@Test
	public void testRollDiceThrowsInsufficientDiceExceptionIfInsufficientDice() {
		Turn turn = new Turn();

		List<Die> dice = turn.getDice().getAll();
		dice.forEach(die -> {
			die.setFace(DieFace.DIAMOND);
			die.setHeld(true);
		});

		try {
			turn.rollDice();

			fail("InsufficientDiceException was not thrown when there were insufficient dice");
		} catch (InsufficientDiceException exception) {
		} catch (TurnCompleteException exception) {
		}
	}

	@Test
	public void testRollDiceDoesNotThrowInsufficientDiceExceptionIfSufficientDice() {
		Turn turn = new Turn();

		List<Die> dice = turn.getDice().getAll();
		dice.forEach(die -> {
			die.setFace(DieFace.DIAMOND);
			die.setHeld(false);
		});

		try {
			turn.rollDice();
		} catch (InsufficientDiceException exception) {
			fail("InsufficientDiceException was thrown when there were sufficient dice");
		} catch (TurnCompleteException exception) {
		}
	}

	@Test
	public void testRerollSingleSkullWithSorceressFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.SORCERESS));

		List<Die> dice = turn.getDice().getAll();
		dice.subList(0, 2).forEach(die -> {
			die.setFace(DieFace.SKULL);
		});
		dice.subList(2, 8).forEach(die -> {
			die.setFace(DieFace.COIN);
			die.setHeld(true);
		});

		assertTrue(turn.canRerollASkull());

		try {
			turn.rerollSingleSkull();
		} catch (FortuneCardInvalidException e) {
			fail("FortuneCardInvalidException should not have been thrown because not yet used.");
		}

		assertFalse(turn.canRerollASkull());

		try {
			turn.rerollSingleSkull();

			fail("FortuneCardInvalidException was not thrown when the reroll is reused.");
		} catch (FortuneCardInvalidException exception) {
		}
	}

	@Test
	public void testRerollSingleSkullOnSecondRoundWithSorceressFortuneCard() throws Exception {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.SORCERESS));

		List<Die> dice = turn.getDice().getAll();
		dice.subList(0, 8).forEach(die -> {
			die.setFace(DieFace.COIN);
		});

		turn.rollDice();

		dice.subList(0, 8).forEach(die -> {
			die.setFace(DieFace.COIN);
		});

		dice.get(0).setFace(DieFace.SKULL);

		assertTrue(turn.canRerollASkull());

		try {
			turn.rerollSingleSkull();
		} catch (FortuneCardInvalidException e) {
			fail("FortuneCardInvalidException should not have been thrown because not yet used.");
		}

		assertFalse(turn.canRerollASkull());

		try {
			turn.rerollSingleSkull();

			fail("FortuneCardInvalidException was not thrown when the reroll is reused.");
		} catch (FortuneCardInvalidException exception) {
		}
	}

	@Test
	public void testRerollSingleSkullOnSecondRoundThenContinueWithSorceressFortuneCard() throws Exception {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.SORCERESS));

		List<Die> dice = turn.getDice().getAll();
		dice.subList(0, 8).forEach(die -> {
			die.setFace(DieFace.COIN);
		});

		turn.rollDice();

		dice.subList(0, 8).forEach(die -> {
			die.setFace(DieFace.COIN);
		});

		dice.get(0).setFace(DieFace.SKULL);

		assertTrue(turn.canRerollASkull());

		try {
			turn.rerollSingleSkull();
		} catch (FortuneCardInvalidException e) {
			fail("FortuneCardInvalidException should not have been thrown because not yet used.");
		}

		assertFalse(turn.canRerollASkull());

		try {
			turn.rerollSingleSkull();

			fail("FortuneCardInvalidException was not thrown when the reroll is reused.");
		} catch (FortuneCardInvalidException exception) {
		}

		turn.rollDice();
	}

	@Test
	public void testRerollSingleSkullThrowsCannotRerollSkullExceptionIfNotSorceressFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));

		turn.getDice().getAll().forEach(die -> die.setFace(DieFace.SKULL));

		try {
			turn.rerollSingleSkull();

			fail("FortuneCardInvalidException was not thrown but the Sorceress fortune card is not the active card.");
		} catch (FortuneCardInvalidException exception) {
		}
	}

	@Test
	public void testDisqualifiedIsTrueIfOneSkullAndTwoSkullFortuneCard() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] { { DieFace.SKULL, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
				DieFace.PARROT, DieFace.PARROT, DieFace.COIN, DieFace.COIN } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.SKULLS, 2);
		Turn turn = new Turn(fortuneCard, rollSequence);

		assertTrue(turn.isDisqualified());
	}

	@Test
	public void testDisqualifiedIsTrueIfTwoSkullsAndOneSkullFortuneCard() throws Exception {
		DieFace[][] rollSequence = new DieFace[][] { { DieFace.SKULL, DieFace.SKULL, DieFace.PARROT, DieFace.PARROT,
				DieFace.PARROT, DieFace.PARROT, DieFace.COIN, DieFace.COIN } };

		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.SKULLS, 1);
		Turn turn = new Turn(fortuneCard, rollSequence);

		assertTrue(turn.isDisqualified());
	}
}
