package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

final class Server {
	private static final Integer PORT = 8000;

	private ServerSocket serverSocket = null;
	private final List<Player> players = new ArrayList<Player>();

	public static void main(String[] args) throws IOException {
		new Server().initializeGame();
	}

	private Server() throws IOException {
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			throw e;
		}
	}

	private void initializeGame() {
		while (players.size() < 2) {
			try {
				Socket socket = serverSocket.accept();
				Player player = new Player(socket);

				players.add(player);

				// TODO: Run in thread to not block other clients from connecting.
				player.sendPlayerId(players.size());
				player.printGreeting();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		gameLoop();
	}

	private void gameLoop() {
		broadcastMessage("The game has started.");
		// TODO: Implement game loop.
	}

	private void broadcastMessage(String message) {
		players.forEach(player -> player.printMessage(message));
	}
}
