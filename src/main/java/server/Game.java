package server;

import java.io.IOException;
import java.util.List;

import model.FortuneCardInvalidException;
import model.FortuneCardType;
import model.InsufficientDiceException;
import model.Turn;
import model.TurnCompleteException;

final class Game {
	private final List<Player> players;
	private final ScoreCard scoreCard = new ScoreCard();

	public Game(List<Player> players) {
		this.players = players;
	}

	public void loop() {
		System.out.println("Sending game start message.");
		broadcastMessage("The game has started.");

		while (!scoreCard.hasWinner()) {
			players.forEach(player -> {
				alertOtherPlayersOfTurn(player);
				turnForPlayer(player);
			});
		}

		endGame();
	}

	private void turnForPlayer(Player player) {
		Turn turn = new Turn();

		player.printMessage("It is now your turn. Your fortune card is: " + turn.getFortuneCard().getType());

		Boolean shouldLoop = true;

		while (shouldLoop) {
			printDice(turn, player);

			if (turn.isDisqualified()) {
				shouldLoop = false;
				endTurn(turn, player);
				continue;
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
					endTurn(turn, player);
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
	}

	private void printMenu(Turn turn, Player player) {
		player.printMessage("What would you like to do?");

		if (turn.canRerollASkull()) {
			player.printMessage("0) Reroll a skull.");
		}

		player.printMessage("1) Select dice to hold.");
		player.printMessage("2) Select dice to unhold.");
		player.printMessage("3) Reroll unheld dice.");
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

	private void endTurn(Turn turn, Player player) {
		if (turn.isDisqualified() && !(turn.getFortuneCard().getType() == FortuneCardType.TREASURE_CHEST)) {
			player.printMessage("That's the end of your turn! You got no points because you rolled 3 skulls.");
			return;
		}

		Integer points = new ScoreEvaluator(turn.getDice(), turn.getFortuneCard(), turn.isDisqualified()).evaluate();

		scoreCard.addNewScore(new Score(player.getId(), points));

		player.printMessage("That's the end of your turn! You got " + points + " points.");
		player.printMessage("Your total number of points is " + scoreCard.getCurrentScore(player.getId()) + ".");
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

	private void endGame() {
		players.forEach(player -> player.endGame());
	}

	private void broadcastMessage(String message) {
		players.forEach(player -> player.printMessage(message));
	}
}
