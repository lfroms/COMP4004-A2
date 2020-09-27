package server;

import java.io.IOException;
import java.util.List;

import model.Turn;

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

		player.printMessage("It is now your turn. Your fortune card is: " + turn.getFortuneCardType());

		try {
			String input = player.promptForInput();
			System.out.println(input);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
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
