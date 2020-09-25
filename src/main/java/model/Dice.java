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

	public void rollAll() {
		dice.forEach((die) -> {
			if (die.getFace().isEmpty()) {
				die.roll();
			}

			if (die.getFace().get() != DieFace.SKULL) {
				die.roll();
			}
		});
	}

	public List<Die> getDice() {
		return dice;
	}

	public void rollUnheld() {
		dice.forEach((die) -> {
			if (die.getFace().isEmpty()) {
				die.roll();
			}

			if (!die.getIsHeld() && (die.getFace().get() != DieFace.SKULL)) {
				die.roll();
			}
		});
	}
}
