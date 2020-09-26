package model;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class Turn {
	private final FortuneCard fortuneCard;
	private final Dice dice = new Dice();

	private Integer accumulatedPoints = 0;

	private Integer numberOfParrots = 0;
	private Integer numberOfSwords = 0;
	private Integer numberOfCoins = 0;
	private Integer numberOfMonkeys = 0;
	private Integer numberOfDiamonds = 0;

	public Turn() {
		fortuneCard = new FortuneCard();
	}

	public Turn(FortuneCard fortuneCard) {
		this.fortuneCard = fortuneCard;
	}

	public List<Die> getDice() {
		return dice.getDice();
	}

	public FortuneCardType getFortuneCardType() {
		return fortuneCard.getType();
	}

	public void rollDice() throws TurnCompleteException, InsufficientDiceException {
		if (!turnCanContinue()) {
			throw new TurnCompleteException();
		}

		if (countHeldDice(dice.getDice()) > 6) {
			throw new InsufficientDiceException();
		}

		dice.rollUnheld();
	}

	public Boolean turnCanContinue() {
		Integer numberOfSkulls = countDice(dice.getDice(), DieFace.SKULL);

		return numberOfSkulls < 3;
	}

	// **** Scoring **** //

	public Integer evaluatePoints() {
		countDiceTypes();

		accumulatedPoints += evaluateOfAKindPoints(numberOfParrots);
		accumulatedPoints += evaluateOfAKindPoints(numberOfSwords);
		accumulatedPoints += evaluateOfAKindPoints(numberOfCoins);
		accumulatedPoints += evaluateOfAKindPoints(numberOfMonkeys);
		accumulatedPoints += evaluateOfAKindPoints(numberOfDiamonds);

		accumulatedPoints += numberOfCoins * 100;
		accumulatedPoints += numberOfDiamonds * 100;

		if (isFullChest()) {
			accumulatedPoints += 500;
		}

		triggerFortuneCardScoring();

		return accumulatedPoints;
	}

	private void triggerFortuneCardScoring() {
		switch (fortuneCard.getType()) {
		case CAPTAIN:
			scoreCaptainFortuneCard();
			break;

		default:
			break;
		}
	}

	private void scoreCaptainFortuneCard() {
		accumulatedPoints *= 2;
	}

	// **** Miscellaneous Helpers **** //

	private void countDiceTypes() {
		List<Die> validDice = getNonSkullDice();

		numberOfParrots = countDice(validDice, DieFace.PARROT);
		numberOfSwords = countDice(validDice, DieFace.SWORD);
		numberOfCoins = countDice(validDice, DieFace.COIN);
		numberOfMonkeys = countDice(validDice, DieFace.MONKEY);
		numberOfDiamonds = countDice(validDice, DieFace.DIAMOND);

		if (fortuneCard.getType() == FortuneCardType.GOLD && numberOfCoins != 8) {
			numberOfCoins++;
		}

		if (fortuneCard.getType() == FortuneCardType.DIAMOND && numberOfDiamonds != 8) {
			numberOfDiamonds++;
		}
	}

	private Integer evaluateOfAKindPoints(Integer count) {
		if (count == 8) {
			return 4000;
		} else if (count == 7) {
			return 2000;
		} else if (count == 6) {
			return 1000;
		} else if (count == 5) {
			return 500;
		} else if (count == 4) {
			return 200;
		} else if (count == 3) {
			return 100;
		}

		return 0;
	}

	private Boolean isFullChest() {
		// TODO: Add full implementation for Full Chest.

		return dice.getDice().stream().allMatch(die -> die.getFace() != DieFace.SKULL);
	}

	private Integer countDice(List<Die> input, DieFace face) {
		return filterByPredicate(input, die -> die.getFace() == face).size();
	}

	private Integer countHeldDice(List<Die> input) {
		return filterByPredicate(input, die -> die.getIsHeld() == true).size();
	}

	private List<Die> getNonSkullDice() {
		return filterByPredicate(dice.getDice(), die -> die.getFace() != DieFace.SKULL);
	}

	private <T, X> List<X> filterByPredicate(List<X> input, Predicate<? super X> predicate) {
		return input.stream().filter(predicate).collect(Collectors.toList());
	}
}
