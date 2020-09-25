package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum DieFace {
	SKULL, PARROT, SWORD, MONKEY, COIN, DIAMOND;

	private static final List<DieFace> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static DieFace random() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
