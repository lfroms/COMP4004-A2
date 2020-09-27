package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

final class Server {
	private static final Integer PORT = 8000;

	private ServerSocket serverSocket = null;
	private final List<Player> players = new ArrayList<Player>();

	public static void main(String[] args) throws IOException {
		new Server().start();
	}

	private Server() throws IOException {
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			throw e;
		}
	}

	private void start() throws UnknownHostException {
		System.out.println("Server started at " + InetAddress.getLocalHost().toString());
		System.out.println("How many players should join? Enter a number:");

		Scanner in = new Scanner(System.in);
		Integer desiredNumberOfPlayers = in.nextInt();
		in.close();

		System.out
				.println("The game will begin automatically after " + desiredNumberOfPlayers + " players have joined.");

		while (players.size() < desiredNumberOfPlayers) {
			try {
				Socket socket = serverSocket.accept();
				Player player = new Player(socket, players.size() + 1);

				players.add(player);

				// TODO: Run in thread to not block other clients from connecting.
				player.sendPlayerId();
				player.printGreeting();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Game game = new Game(players);
		game.loop();
	}
}
