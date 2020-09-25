package model;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

public class DieTest {
	@Test
	public void testGetFaceIsInitiallyEmpty() {
		Die die = new Die();

		assertEquals(Optional.empty(), die.getFace());
	}

	@Test
	public void testGetFaceReturnsRolledSide() {
		Die die = new Die();

		assertEquals(die.roll(), die.getFace().get());
		assertEquals(die.roll(), die.getFace().get());
		assertEquals(die.roll(), die.getFace().get());
	}

	@Test
	public void testSetFaceSetsFace() {
		Die die = new Die();

		die.setFace(DieFace.COIN);
		assertEquals(die.getFace().get(), DieFace.COIN);
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
