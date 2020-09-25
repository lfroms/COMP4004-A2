package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class DieTest {
	@Test
	public void testGetFaceReturnsRolledSide() {
		Die die = new Die();

		assertEquals(die.roll(), die.getFace());
		assertEquals(die.roll(), die.getFace());
		assertEquals(die.roll(), die.getFace());
	}

	@Test
	public void testSetFaceSetsFace() {
		Die die = new Die();

		die.setFace(DieFace.COIN);
		assertEquals(die.getFace(), DieFace.COIN);
	}

	@Test
	public void testSetHeldSetsIsHeldToTrue() {
		Die die = new Die();

		die.setHeld(true);
		assertTrue(die.getIsHeld());
	}

	@Test
	public void testSetHeldSetsIsHeldToFalse() {
		Die die = new Die();

		die.setHeld(false);
		assertFalse(die.getIsHeld());
	}
}
