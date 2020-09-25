package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class DiceTest {
	@Test
	public void testRollUnheldRollsUnheldDice() {
		Dice dice = new Dice();
		dice.rollUnheld();

		dice.getDice().get(0).setHeld(true);
		dice.getDice().get(1).setHeld(true);
		dice.getDice().get(2).setHeld(true);

		DieFace firstFace = dice.getDice().get(0).getFace();
		DieFace secondFace = dice.getDice().get(1).getFace();
		DieFace thirdFace = dice.getDice().get(2).getFace();

		dice.rollUnheld();

		assertEquals(firstFace, dice.getDice().get(0).getFace());
		assertEquals(secondFace, dice.getDice().get(1).getFace());
		assertEquals(thirdFace, dice.getDice().get(2).getFace());
	}

	@Test
	public void testRollUnheldDoesNotRollSkulls() {
		Dice dice = new Dice();
		dice.rollUnheld();

		dice.getDice().get(0).setFace(DieFace.SKULL);

		dice.rollUnheld();
		assertEquals(dice.getDice().get(0).getFace(), DieFace.SKULL);
	}
}
