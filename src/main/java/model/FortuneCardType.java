package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum FortuneCardType {
	TREASURE_CHEST, CAPTAIN, SORCERESS, SEA_BATTLE, GOLD, DIAMOND, MONKEY_BUSINESS, SKULLS;

	private static final List<FortuneCardType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static FortuneCardType random() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
