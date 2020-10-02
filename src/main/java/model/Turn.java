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
	private Boolean isIslandOfSkulls = false;
	private Integer previousNumberOfSkulls = 0;

	public Turn() {
		fortuneCard = new FortuneCard();
		this.isIslandOfSkulls = numberOfSkulls() >= 4;
	}

	public Turn(FortuneCard fortuneCard) {
		this.fortuneCard = fortuneCard;
		this.isIslandOfSkulls = numberOfSkulls() >= 4;

	}

	public Turn(FortuneCard fortuneCard, DieFace[][] rollSequence) {
		this.fortuneCard = fortuneCard;
		this.rollSequence = rollSequence;

		rigDice();

		this.isIslandOfSkulls = numberOfSkulls() >= 4;
	}

	public Dice getDice() {
		return dice;
	}

	public FortuneCard getFortuneCard() {
		return fortuneCard;
	}

	public Boolean getIsIslandOfSkulls() {
		return isIslandOfSkulls;
	}

	public void rollDice() throws TurnCompleteException, InsufficientDiceException {
		if (isDisqualified()) {
			throw new TurnCompleteException();
		}

		if (countUnrollableDice(dice.getAll()) > 6) {
			throw new InsufficientDiceException();
		}

		rollCount++;
		previousNumberOfSkulls = numberOfSkulls();

		if (shouldRigDice()) {
			rigDice();
		} else {
			dice.rollUnheld();
		}

	}

	public Boolean isDisqualified() {
		if (isIslandOfSkulls) {
			return numberOfSkulls() <= previousNumberOfSkulls;
		}

		return numberOfSkulls() > 2;
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

	private Integer countUnrollableDice(List<Die> input) {
		return input.stream()
				.filter(die -> die.getIsHeld() || die.getInTreasureChest() || die.getFace() == DieFace.SKULL)
				.collect(Collectors.toList()).size();
	}

	private Integer numberOfSkulls() {
		Integer numberOfSkulls = dice.getAll().stream().filter(die -> die.getFace() == DieFace.SKULL)
				.collect(Collectors.toList()).size();

		if (fortuneCard.getType() == FortuneCardType.SKULLS) {
			numberOfSkulls += fortuneCard.getNumericalValue();
		}

		return numberOfSkulls;

	}
}
