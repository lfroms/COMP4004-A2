package model;

public final class FortuneCard {
	private FortuneCardType type = null;

	public FortuneCard() {
		type = FortuneCardType.random();
	}

	public FortuneCard(FortuneCardType type) {
		this.type = type;
	}

	public FortuneCardType getType() {
		return type;
	}
}
