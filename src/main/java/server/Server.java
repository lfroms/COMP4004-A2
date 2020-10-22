package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

final class Server extends Thread {
	private static final Integer PORT = 8000;

	private final InetAddress localhost;
	private Integer numberOfPlayers = null;

	private ServerSocket serverSocket = null;
	private final List<Player> players = new ArrayList<Player>();

	private Game game;
	private final GameTestMode testMode;

	public static void main(String[] args) throws IOException {
		GameTestMode testModeToRun = null;

		if (args.length > 0) {
			switch (args[0]) {
			case "seq_a":
				testModeToRun = GameTestMode.SEQUENCE_A;
				break;
			case "seq_b":
				testModeToRun = GameTestMode.SEQUENCE_B;
			}
		}

		new Server(PORT, testModeToRun).start();
	}

	public Server(Integer numberOfPlayers) throws IOException {
		this.numberOfPlayers = numberOfPlayers;

		serverSocket = new ServerSocket(PORT);
		localhost = InetAddress.getLocalHost();
		testMode = null;

	}

	public Server(Integer port, GameTestMode testMode) throws IOException {
		serverSocket = new ServerSocket(port);
		localhost = InetAddress.getLocalHost();

		if (testMode != null) {
			this.numberOfPlayers = 3;
		}
		this.testMode = testMode;
	}

	public Game getGame() {
		return game;
	}

	@Override
	public void run() {
		System.out.println("Server started at " + localhost.toString());
		promptForNumberOfPlayers();

		System.out.println("The game will begin automatically after " + numberOfPlayers + " players have joined.");

		while (players.size() < numberOfPlayers) {
			try {
				Socket socket = serverSocket.accept();
				Player player = new Player(socket, players.size() + 1);

				players.add(player);

				// TODO: Run in thread to not block other clients from connecting.
				player.sendPlayerId();
				player.printGreeting(numberOfPlayers);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (testMode != null) {
			TurnFactory factory = new TurnFactory(testMode);
			game = new Game(players, factory);
		} else {
			game = new Game(players);
		}

		game.loop();
	}

	private void promptForNumberOfPlayers() {
		if (numberOfPlayers != null) {
			return;
		}

		System.out.println("How many players should join? Enter a number:");

		Scanner in = new Scanner(System.in);
		numberOfPlayers = in.nextInt();
		in.close();
	}
}
