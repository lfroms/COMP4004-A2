package model;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
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

	private Boolean sorceressFortuneCardExpired = false;

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

	public Boolean canRerollASkull() {
		return !sorceressFortuneCardExpired;
	}

	public void rerollSingleSkull() throws SorceressExpiredException {
		if (!canRerollASkull()) {
			throw new SorceressExpiredException();
		}

		Optional<Die> firstSkull = dice.getDice().stream().filter(die -> die.getFace() == DieFace.SKULL).findFirst();

		if (firstSkull.isEmpty()) {
			return;
		}

		firstSkull.get().roll();
		sorceressFortuneCardExpired = true;
	}

	// **** Scoring **** //

	public Integer evaluatePoints() {
		countDiceTypes();

		if (fortuneCard.getType() == FortuneCardType.MONKEY_BUSINESS) {
			accumulatedPoints += evaluateOfAKindPoints(numberOfParrots + numberOfMonkeys);
		} else {
			accumulatedPoints += evaluateOfAKindPoints(numberOfParrots);
			accumulatedPoints += evaluateOfAKindPoints(numberOfMonkeys);
		}

		accumulatedPoints += evaluateOfAKindPoints(numberOfSwords);
		accumulatedPoints += evaluateOfAKindPoints(numberOfCoins);
		accumulatedPoints += evaluateOfAKindPoints(numberOfDiamonds);

		accumulatedPoints += numberOfCoins * 100;
		accumulatedPoints += numberOfDiamonds * 100;

		if (isFullChest()) {
			accumulatedPoints += 500;
		}

		if (fortuneCard.getType() == FortuneCardType.CAPTAIN) {
			accumulatedPoints *= 2;
		}

		return accumulatedPoints;
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
		Boolean hasNoSkulls = dice.getDice().stream().allMatch(die -> die.getFace() != DieFace.SKULL);

		if (!hasNoSkulls) {
			return false;
		}

		return dice.getDice().stream().filter(die -> !dieHasFaceValue(die)).allMatch(die -> dieIsPartOfSet(die));
	}

	private boolean dieHasFaceValue(Die die) {
		return die.getFace() == DieFace.COIN || die.getFace() == DieFace.DIAMOND;
	}

	private Boolean dieIsPartOfSet(Die die) {
		return dice.getDice().stream().filter(otherDie -> diceHaveMatchingScorableFaces(die, otherDie)).count() > 2;
	}

	private Boolean diceHaveMatchingScorableFaces(Die first, Die second) {
		if (fortuneCard.getType() != FortuneCardType.MONKEY_BUSINESS) {
			return first.getFace() == second.getFace();
		}

		return dieIsMonkeyOrParrot(first) ? dieIsMonkeyOrParrot(second) : false;
	}

	private Boolean dieIsMonkeyOrParrot(Die die) {
		return die.getFace() == DieFace.MONKEY || die.getFace() == DieFace.PARROT;
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

	public <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Map<Object, Boolean> seen = new ConcurrentHashMap<>();
		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
}
