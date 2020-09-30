package model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class Turn {
	private final FortuneCard fortuneCard;
	private final Dice dice = new Dice();

	private Boolean sorceressFortuneCardExpired = false;

	public Turn() {
		fortuneCard = new FortuneCard();
	}

	public Turn(FortuneCard fortuneCard) {
		this.fortuneCard = fortuneCard;
	}

	public Dice getDice() {
		return dice;
	}

	public FortuneCard getFortuneCard() {
		return fortuneCard;
	}

	public void rollDice() throws TurnCompleteException, InsufficientDiceException {
		if (!turnCanContinue()) {
			throw new TurnCompleteException();
		}

		if (countHeldDice(dice.getAll()) > 6) {
			throw new InsufficientDiceException();
		}

		dice.rollUnheld();
	}

	public Boolean turnCanContinue() {
		Integer numberOfSkulls = dice.getAll().stream().filter(die -> die.getFace() == DieFace.SKULL)
				.collect(Collectors.toList()).size();

		return numberOfSkulls < 3;
	}

	public Boolean canRerollASkull() {
		return !sorceressFortuneCardExpired;
	}

	public void rerollSingleSkull() throws SorceressExpiredException {
		if (!canRerollASkull()) {
			throw new SorceressExpiredException();
		}

		Optional<Die> firstSkull = dice.getAll().stream().filter(die -> die.getFace() == DieFace.SKULL).findFirst();

		if (firstSkull.isEmpty()) {
			return;
		}

		firstSkull.get().roll();
		sorceressFortuneCardExpired = true;
	}

	private Integer countHeldDice(List<Die> input) {
		return input.stream().filter(die -> die.getIsHeld()).collect(Collectors.toList()).size();
	}
}
