package model;

import java.util.Random;

public final class FortuneCard {
	private FortuneCardType type = null;
	private Integer numericalValue = 0;

	Random random = new Random();

	public FortuneCard() {
		type = FortuneCardType.random();
		numericalValue = getRandomNumericalValue();
	}

	public FortuneCard(FortuneCardType type) {
		this.type = type;
		numericalValue = getRandomNumericalValue();
	}

	public FortuneCard(FortuneCardType type, Integer numericalValue) {
		this.type = type;
		this.numericalValue = numericalValue;
	}

	public FortuneCardType getType() {
		return type;
	}

	public Integer getNumericalValue() {
		return numericalValue;
	}

	private Integer getRandomNumericalValue() {
		if (type == FortuneCardType.SKULLS) {
			return random.nextInt() * 2 + 1;
		}

		if (type == FortuneCardType.SEA_BATTLE) {
			return random.nextInt() * 4 + 1;
		}

		return 0;
	}
}
