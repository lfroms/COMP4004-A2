package model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class Turn {
	private FortuneCard fortuneCard;
	private final Dice dice = new Dice();
	private DieFace[][] rollSequence = null;

	private Boolean sorceressFortuneCardExpired = false;
	private Integer rollCount = 0;
	private Boolean isIslandOfSkulls = false;
	private Integer previousNumberOfSkulls = 0;

	public Turn() {
		fortuneCard = new FortuneCard();
	}

	public Turn(FortuneCard fortuneCard) {
		this.fortuneCard = fortuneCard;
	}

	public Turn(FortuneCard fortuneCard, DieFace[][] rollSequence) {
		this.fortuneCard = fortuneCard;
		this.rollSequence = rollSequence;
	}

	public void addRiggedRoll(DieFace[] faces) {
		if (this.rollSequence == null) {
			this.rollSequence = new DieFace[0][8];
		}

		this.rollSequence = Arrays.copyOf(this.rollSequence, this.rollSequence.length + 1);
		this.rollSequence[this.rollSequence.length - 1] = faces;
	}

	public Dice getDice() {
		return dice;
	}

	public FortuneCard getFortuneCard() {
		return fortuneCard;
	}

	public void setFortuneCard(FortuneCard fortuneCard) {
		this.fortuneCard = fortuneCard;
	}

	public Boolean getIsIslandOfSkulls() {
		if (rollCount == 1) {
			isIslandOfSkulls = numberOfSkulls() >= 4 && !getIsInSeaBattle();
		}

		return isIslandOfSkulls;
	}

	public Boolean getIsInSeaBattle() {
		return fortuneCard.getType() == FortuneCardType.SEA_BATTLE;
	}

	public Boolean wonSeaBattle() {
		if (!getIsInSeaBattle() || isDisqualified()) {
			return false;
		}

		return numberOfSwords() >= fortuneCard.getNumericalValue();
	}

	private Boolean getHasStartedTurn() {
		return rollCount > 0;
	}

	public void rollDice() throws TurnCompleteException, InsufficientDiceException {
		if (getHasStartedTurn()) {
			if (isDisqualified()) {
				throw new TurnCompleteException();
			}

			if (countUnrollableDice(dice.getAll()) > 6) {
				throw new InsufficientDiceException();
			}

			previousNumberOfSkulls = numberOfSkulls();
		}

		if (shouldRigDice()) {
			rigDice();
		} else {
			dice.rollUnheld();
		}

		rollCount++;
	}

	public Boolean isDisqualified() {
		if (getIsIslandOfSkulls()) {
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

		if (shouldRigDice()) {
			rigSingleDie(firstSkull.get());
		} else {
			firstSkull.get().roll();
		}

		sorceressFortuneCardExpired = true;
	}

	private void rigDice() {
		// Can rig all dice on first roll
		List<Die> diceToRig = rollCount == 0 ? dice.getAll() : dice.getRollable();

		for (int i = 0; i < diceToRig.size(); i++) {
			Die die = diceToRig.get(i);
			DieFace newFace = rollSequence[rollCount][i];
			die.setFace(newFace);
		}
	}

	private void rigSingleDie(Die die) {
		die.setFace(rollSequence[rollCount][0]);
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

	private Integer numberOfSwords() {
		return dice.getAll().stream().filter(die -> die.getFace() == DieFace.SWORD).collect(Collectors.toList()).size();
	}
}
