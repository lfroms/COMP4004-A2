package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

	public List<Die> getRollable() {
		return dice.stream()
				.filter(die -> !die.getIsHeld() && (die.getFace() != DieFace.SKULL) && !die.getInTreasureChest())
				.collect(Collectors.toList());
	}

	public List<Die> getScorable() {
		return dice.stream().filter(die -> die.getFace() != DieFace.SKULL).collect(Collectors.toList());
	}

	public List<Die> getAllFromTreasureChest() {
		return dice.stream().filter(die -> die.getInTreasureChest()).collect(Collectors.toList());
	}

	public void rollUnheld() {
		dice.forEach((die) -> {
			if (!die.getIsHeld() && (die.getFace() != DieFace.SKULL) && !die.getInTreasureChest()) {
				die.roll();
			}
		});
	}
}
