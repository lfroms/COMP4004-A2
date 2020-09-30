package model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class TurnTest {
	@Test
	public void testTurnCanContinueIsTrueIfLessThanThreeSkulls() {
		Turn turn = new Turn();

		List<Die> dice = turn.getDice().getAll();
		dice.forEach(die -> {
			die.setFace(DieFace.COIN);
		});

		dice.get(0).setFace(DieFace.SKULL);

		assertTrue(turn.turnCanContinue());
	}

	@Test
	public void testTurnCanContinueIsFalseIfAtLeastThreeSkulls() {
		Turn turn = new Turn();

		List<Die> dice = turn.getDice().getAll();
		dice.forEach(die -> {
			die.setFace(DieFace.SKULL);
		});

		dice.get(0).setFace(DieFace.DIAMOND);

		assertFalse(turn.turnCanContinue());
	}

	@Test
	public void testRollDiceThrowsTurnCompleteExceptionIfCannotContinue() throws InsufficientDiceException {
		Turn turn = new Turn();

		List<Die> dice = turn.getDice().getAll();
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

		List<Die> dice = turn.getDice().getAll();
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

		List<Die> dice = turn.getDice().getAll();
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

		List<Die> dice = turn.getDice().getAll();
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
		} catch (CannotRerollSkullException e) {
			fail("CannotRerollSkullException should not have been thrown because not yet used.");
		}

		assertFalse(turn.canRerollASkull());

		try {
			turn.rerollSingleSkull();

			fail("CannotRerollSkullException was not thrown when the reroll is reused.");
		} catch (CannotRerollSkullException exception) {
		}
	}

	@Test
	public void testRerollSingleSkullThrowsCannotRerollSkullExceptionIfNotSorceressFortuneCard() {
		Turn turn = new Turn(new FortuneCard(FortuneCardType.GOLD));

		turn.getDice().getAll().forEach(die -> die.setFace(DieFace.SKULL));

		try {
			turn.rerollSingleSkull();

			fail("CannotRerollSkullException was not thrown but the Sorceress fortune card is not the active card.");
		} catch (CannotRerollSkullException exception) {
		}
	}
}
