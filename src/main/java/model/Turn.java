package model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class Turn {
	private final FortuneCard fortuneCard;
	private final Dice dice = new Dice();
	private DieFace[][] rollSequence = null;

	private Boolean sorceressFortuneCardExpired = false;
	private Integer rollCount = 0;

	public Turn() {
		fortuneCard = new FortuneCard();
	}

	public Turn(FortuneCard fortuneCard) {
		this.fortuneCard = fortuneCard;
	}

	public Turn(FortuneCard fortuneCard, DieFace[][] rollSequence) {
		this.fortuneCard = fortuneCard;
		this.rollSequence = rollSequence;

		rigDice();
	}

	public Dice getDice() {
		return dice;
	}

	public FortuneCard getFortuneCard() {
		return fortuneCard;
	}

	public void rollDice() throws TurnCompleteException, InsufficientDiceException {
		if (isDisqualified()) {
			throw new TurnCompleteException();
		}

		if (countHeldDice(dice.getAll()) > 6) {
			throw new InsufficientDiceException();
		}

		rollCount++;

		if (shouldRigDice()) {
			rigDice();
		} else {
			dice.rollUnheld();
		}

	}

	public Boolean isDisqualified() {
		Integer numberOfSkulls = dice.getAll().stream().filter(die -> die.getFace() == DieFace.SKULL)
				.collect(Collectors.toList()).size();

		return numberOfSkulls > 2;
	}

	public Boolean canRerollASkull() {
		return !sorceressFortuneCardExpired && fortuneCard.getType() == FortuneCardType.SORCERESS;
	}

	public void rerollSingleSkull() throws FortuneCardInvalidException {
		if (!canRerollASkull()) {
			throw new FortuneCardInvalidException();
		}

		Optional<Die> firstSkull = dice.getAll().stream().filter(die -> die.getFace() == DieFace.SKULL).findFirst();

		if (firstSkull.isEmpty()) {
			return;
		}

		firstSkull.get().roll();
		sorceressFortuneCardExpired = true;
	}

	private void rigDice() {
		for (int i = 0; i < dice.getAll().size(); i++) {
			Die die = dice.getAll().get(i);

			if ((die.getIsHeld() == false && die.getFace() != DieFace.SKULL) || rollCount == 0) {
				die.setFace(rollSequence[rollCount][i]);
			}
		}
	}

	private Boolean shouldRigDice() {
		return rollSequence != null;
	}

	private Integer countHeldDice(List<Die> input) {
		return input.stream().filter(die -> die.getIsHeld()).collect(Collectors.toList()).size();
	}
}
