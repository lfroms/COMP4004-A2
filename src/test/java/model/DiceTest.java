package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class DiceTest {
	@Test
	public void testRollUnheldRollsUnheldDice() {
		Dice dice = new Dice();
		dice.rollUnheld();

		dice.getAll().get(0).setHeld(true);
		dice.getAll().get(1).setHeld(true);
		dice.getAll().get(2).setHeld(true);

		DieFace firstFace = dice.getAll().get(0).getFace();
		DieFace secondFace = dice.getAll().get(1).getFace();
		DieFace thirdFace = dice.getAll().get(2).getFace();

		dice.rollUnheld();

		assertEquals(firstFace, dice.getAll().get(0).getFace());
		assertEquals(secondFace, dice.getAll().get(1).getFace());
		assertEquals(thirdFace, dice.getAll().get(2).getFace());
	}

	@Test
	public void testRollUnheldDoesNotRollSkulls() {
		Dice dice = new Dice();
		dice.rollUnheld();

		dice.getAll().get(0).setFace(DieFace.SKULL);

		dice.rollUnheld();
		assertEquals(dice.getAll().get(0).getFace(), DieFace.SKULL);
	}
}
