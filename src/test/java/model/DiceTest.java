package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class DiceTest {
	@Test
	public void testRollUnheldRollsAllDiceIfFirstTime() {
		Dice dice = new Dice();

		dice.rollUnheld();

		dice.getDice().forEach((die) -> {
			assertFalse(die.getFace().isEmpty());
		});
	}

	@Test
	public void testRollUnheldRollsUnheldDice() {
		Dice dice = new Dice();
		dice.rollUnheld();

		dice.getDice().get(0).setHeld(true);
		dice.getDice().get(1).setHeld(true);
		dice.getDice().get(2).setHeld(true);

		DieFace firstFace = dice.getDice().get(0).getFace().get();
		DieFace secondFace = dice.getDice().get(1).getFace().get();
		DieFace thirdFace = dice.getDice().get(2).getFace().get();

		dice.rollUnheld();

		assertEquals(firstFace, dice.getDice().get(0).getFace().get());
		assertEquals(secondFace, dice.getDice().get(1).getFace().get());
		assertEquals(thirdFace, dice.getDice().get(2).getFace().get());
	}

	@Test
	public void testRollUnheldDoesNotRollSkulls() {
		Dice dice = new Dice();
		dice.rollUnheld();

		dice.getDice().get(0).setFace(DieFace.SKULL);

		dice.rollUnheld();
		assertEquals(dice.getDice().get(0).getFace().get(), DieFace.SKULL);
	}
}
