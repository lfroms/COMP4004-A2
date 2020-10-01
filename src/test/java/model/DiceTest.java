package model;

import static org.junit.Assert.*;

import java.util.List;

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
	public void testRollUnheldRollsUnheldDiceIgnoringTreasureChest() {
		Dice dice = new Dice();
		dice.rollUnheld();

		dice.getAll().get(0).setHeld(true);
		dice.getAll().get(1).setHeld(true);
		dice.getAll().get(2).setInTreasureChest(true);

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

	@Test
	public void testGetScorableReturnsTreasureChestAndNonSkulls() {
		Dice dice = new Dice();

		dice.getAll().get(0).setFace(DieFace.SKULL);
		dice.getAll().get(1).setFace(DieFace.SKULL);
		dice.getAll().get(2).setFace(DieFace.COIN);
		dice.getAll().get(2).setInTreasureChest(true);
		dice.getAll().get(3).setFace(DieFace.COIN);
		dice.getAll().get(4).setFace(DieFace.DIAMOND);
		dice.getAll().get(5).setFace(DieFace.DIAMOND);
		dice.getAll().get(6).setFace(DieFace.DIAMOND);
		dice.getAll().get(7).setFace(DieFace.DIAMOND);

		List<Die> returnedDice = dice.getScorable();

		assertEquals(DieFace.COIN, returnedDice.get(0).getFace());
		assertEquals(DieFace.COIN, returnedDice.get(1).getFace());
		assertEquals(DieFace.DIAMOND, returnedDice.get(2).getFace());
		assertEquals(DieFace.DIAMOND, returnedDice.get(3).getFace());
		assertEquals(DieFace.DIAMOND, returnedDice.get(4).getFace());
		assertEquals(DieFace.DIAMOND, returnedDice.get(5).getFace());
	}

	@Test
	public void testGetAllFromTreasureChestReturnsTreasureChestDice() {
		Dice dice = new Dice();

		dice.getAll().get(0).setFace(DieFace.SKULL);
		dice.getAll().get(1).setFace(DieFace.SKULL);
		dice.getAll().get(2).setFace(DieFace.COIN);
		dice.getAll().get(2).setInTreasureChest(true);
		dice.getAll().get(3).setFace(DieFace.COIN);
		dice.getAll().get(4).setFace(DieFace.DIAMOND);
		dice.getAll().get(5).setFace(DieFace.DIAMOND);
		dice.getAll().get(6).setFace(DieFace.DIAMOND);
		dice.getAll().get(7).setFace(DieFace.DIAMOND);

		List<Die> returnedDice = dice.getAllFromTreasureChest();

		assertEquals(DieFace.COIN, returnedDice.get(0).getFace());
	}
}
