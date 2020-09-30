package model;

import java.util.ArrayList;
import java.util.List;

public final class Dice {
	private List<Die> dice = new ArrayList<Die>();

	public Dice() {
		for (int i = 0; i < 8; i++) {
			dice.add(new Die());
		}
	}

	public List<Die> getAll() {
		return dice;
	}

	public void rollUnheld() {
		dice.forEach((die) -> {
			if (!die.getIsHeld() && (die.getFace() != DieFace.SKULL)) {
				die.roll();
			}
		});
	}
}
