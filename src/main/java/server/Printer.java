package server;

import model.Dice;
import model.Die;
import model.DieFace;

final class Printer {
	public static String toString(Dice dice) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < dice.getAll().size(); i++) {
			Die die = dice.getAll().get(i);

			String heldText = die.getIsHeld() ? " (HELD)" : "";
			String cannotRollText = die.getFace() == DieFace.SKULL ? " (cannot re-roll)" : "";
			String treasureChestText = die.getInTreasureChest() ? " (TREASURE CHEST)" : "";

			sb.append(i + ". " + die.getFace() + heldText + cannotRollText + treasureChestText + "\n");
		}

		return sb.toString();
	}
}
