package server;

import java.util.List;

final class Game {
	private final List<Player> players;
	private final ScoreCard scoreCard = new ScoreCard();

	public Game(List<Player> players) {
		this.players = players;
	}

	public void loop() {
		System.out.println("Sending game start message.");
		broadcastMessage("The game has started.");

		endGame();
	}

	private void endGame() {
		players.forEach(player -> player.endGame());
	}

	private void broadcastMessage(String message) {
		players.forEach(player -> player.printMessage(message));
	}
}
