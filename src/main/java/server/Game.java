package server;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import model.FortuneCardInvalidException;
import model.FortuneCardType;
import model.InsufficientDiceException;
import model.Turn;
import model.TurnCompleteException;

final class Game {
	private final List<Player> players;
	private final ScoreCard scoreCard = new ScoreCard();
	private final GameTestMode testMode;

	private Integer turnSequence = 0;

	public Game(List<Player> players) {
		this.players = players;
		this.testMode = null;
	}

	public Game(List<Player> players, GameTestMode testMode) {
		this.players = players;
		this.testMode = testMode;
	}

	public void loop() {
		System.out.println("Sending game start message.");
		broadcastMessage("The game has started.");

		while (!scoreCard.hasWinner()) {
			players.forEach(player -> {
				alertOtherPlayersOfTurn(player);
				turnForPlayer(player);
				turnSequence++;
			});
		}

		endGame();
	}

	private void turnForPlayer(Player player) {
		Turn turn = testMode == null ? new Turn() : new TestTurnFactory(testMode, turnSequence).createTurn();

		player.printMessage("");
		player.printMessage("It is now your turn. Your fortune card is: " + turn.getFortuneCard().getType());

		if (turn.getFortuneCard().getType() == FortuneCardType.SKULLS) {
			player.printMessage("Since you have the Skulls fortune card, you have "
					+ turn.getFortuneCard().getNumericalValue() + " skulls by default.");
		}

		Boolean shouldLoop = true;

		while (!turn.isDisqualified() && shouldLoop) {
			player.printMessage("");
			printDice(turn, player);

			if (turn.getIsIslandOfSkulls()) {
				player.printMessage("Since you have more than 3 skulls, you are on the island of skulls.");
				player.printMessage("Try to collect as many skulls as possible.");
				player.printMessage("");
			}

			if (turn.getIsInSeaBattle()) {
				player.printMessage("You are engaged in a sea battle! You must get "
						+ turn.getFortuneCard().getNumericalValue() + " swords.");
				player.printMessage(
						"If you fail, the bonus points you could have won will be deducted from your total.");
			}

			printMenu(turn, player);

			try {
				String input = player.promptForInput();

				switch (Integer.parseInt(input)) {
				case 0:
					turn.rerollSingleSkull();
					break;
				case 1:
					holdOrUnholdDice(true, turn, player);
					break;
				case 2:
					holdOrUnholdDice(false, turn, player);
					break;
				case 3:
					turn.rollDice();
					break;
				case 4:
					continue;
				case 5:
					shouldLoop = false;
					break;
				case 6:
					addOrRemoveFromTreasureChest(true, turn, player);
					break;
				case 7:
					addOrRemoveFromTreasureChest(false, turn, player);
					break;
				}

			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			} catch (TurnCompleteException e) {
				player.printMessage("You can't reroll your dice because your turn is over.");
			} catch (InsufficientDiceException e) {
				player.printMessage("You can't reroll these dice. You must have at least 2 unheld dice.");
			} catch (FortuneCardInvalidException e) {
				player.printMessage("You can't perform this action because you do not have the correct fortune card.");
			}
		}

		printDice(turn, player);
		endTurn(turn, player);
	}

	private void printMenu(Turn turn, Player player) {
		player.printMessage("What would you like to do?");

		if (turn.canRerollASkull()) {
			player.printMessage("0) Reroll a skull.");
		}

		if (turn.getIsIslandOfSkulls()) {
			player.printMessage("3) Reroll all non-skull dice.");

		} else {
			player.printMessage("1) Select dice to hold.");
			player.printMessage("2) Select dice to unhold.");
			player.printMessage("3) Reroll unheld dice.");
		}

		player.printMessage("4) Reprint dice and menu.");
		player.printMessage("5) Complete turn.");

		if (turn.getFortuneCard().getType() == FortuneCardType.TREASURE_CHEST) {
			player.printMessage("6) Select dice to add to treasure chest.");
			player.printMessage("7) Select dice to remove from treasure chest.");
		}
	}

	private void printDice(Turn turn, Player player) {
		player.printMessage("Here are your dice:");
		player.printMessage(Printer.toString(turn.getDice()));
	}

	private void handleEndOfSeaBattle(Turn turn, Player player, ScoreEvaluator evaluator) {
		if (!turn.getIsInSeaBattle()) {
			return;
		}

		if (turn.wonSeaBattle()) {
			player.printMessage("You have won the sea battle! You will gain an additional "
					+ evaluator.seaBattleBonusPoints() + " points.");
		} else {
			player.printMessage("You have lost the sea battle. You will lose " + (-1 * evaluator.seaBattleBonusPoints())
					+ " points.");
		}

		scoreCard.addNewScore(new Score(player.getId(), evaluator.seaBattleBonusPoints()));
	}

	private void summarizeEndOfTurn(Turn turn, Player player) {
		player.printMessage("Your total number of points is " + scoreCard.getCurrentScore(player.getId()) + ".");
	}

	private void endTurn(Turn turn, Player player) {
		ScoreEvaluator evaluator = new ScoreEvaluator(turn);

		if (turn.isDisqualified() && !(turn.getFortuneCard().getType() == FortuneCardType.TREASURE_CHEST)
				&& !turn.getIsIslandOfSkulls()) {

			handleEndOfSeaBattle(turn, player, evaluator);

			if (!turn.getIsInSeaBattle()) {
				player.printMessage("That's the end of your turn! You got no points because you rolled 3 skulls.");
			}

			summarizeEndOfTurn(turn, player);
			return;
		}

		Integer points = evaluator.evaluate();

		if (turn.getIsIslandOfSkulls()) {
			player.printMessage("That's the end of your turn! Each player has lost " + points + " points.");
			summarizeEndOfTurn(turn, player);

			allPlayersExcept(player).forEach(otherPlayer -> {
				scoreCard.addNewScore(new Score(otherPlayer.getId(), points * -1));
			});

			return;
		}

		handleEndOfSeaBattle(turn, player, evaluator);
		scoreCard.addNewScore(new Score(player.getId(), points));

		player.printMessage("That's the end of your turn! You got " + points + " points.");
		summarizeEndOfTurn(turn, player);
	}

	private void holdOrUnholdDice(Boolean holdUnhold, Turn turn, Player player) {
		player.printMessage("Enter a comma-separated list (no spaces) of dice to mark as held:");

		try {
			String input = player.promptForInput();
			String[] dice = input.split(",");

			for (int i = 0; i < dice.length; i++) {
				turn.getDice().getAll().get(Integer.parseInt(dice[i])).setHeld(holdUnhold);
			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void addOrRemoveFromTreasureChest(Boolean addRemove, Turn turn, Player player) {
		player.printMessage(
				"Enter a comma-separated list (no spaces) of dice to add or remove from the treasure chest:");

		try {
			String input = player.promptForInput();
			String[] dice = input.split(",");

			for (int i = 0; i < dice.length; i++) {
				turn.getDice().getAll().get(Integer.parseInt(dice[i])).setInTreasureChest(addRemove);
			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void alertOtherPlayersOfTurn(Player currentPlayer) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getId() == currentPlayer.getId()) {
				continue;
			}

			players.get(i).printMessage("It is now player #" + currentPlayer.getId() + "'s turn.");
		}
	}

	private List<Player> allPlayersExcept(Player player) {
		return players.stream().filter(otherPlayer -> otherPlayer.getId() != player.getId())
				.collect(Collectors.toList());
	}

	private void endGame() {
		broadcastMessage("The game is over. Player #" + scoreCard.getWinnerId().get() + " has won the game!");
		players.forEach(player -> player.endGame());
	}

	private void broadcastMessage(String message) {
		players.forEach(player -> player.printMessage(message));
	}
}
