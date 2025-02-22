package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import shared.Opcode;

final class Player {
	private final InputStream inputStream;
	private final BufferedReader in;
	private final DataOutputStream out;
	private final Integer playerId;

	private Boolean holdForUserInput = false;

	public Player(Integer id) {
		inputStream = System.in;
		in = new BufferedReader(new InputStreamReader(inputStream));
		out = new DataOutputStream(System.out);
		playerId = id;
	}

	public Player(Socket socket, Integer id) throws IOException {
		inputStream = socket.getInputStream();
		in = new BufferedReader(new InputStreamReader(inputStream));
		out = new DataOutputStream(socket.getOutputStream());
		playerId = id;
	}

	public Integer getId() {
		return playerId;
	}

	public void printGreeting(Integer numberOfPlayers) {
		safePrint("Welcome to the game. The game will automatically begin once " + numberOfPlayers + " players join.");
	}

	public void setHoldForUserInput(Boolean holdForUserInput) {
		this.holdForUserInput = holdForUserInput;
	}

	public void printMessage(String message) {
		safePrint(message);
	}

	public String promptForInput() throws IOException, InterruptedException {
		while (holdForUserInput) {
			// This is used for testing. We can delay printing the opcode until we have
			// provided the client with a predetermined response.
			Thread.sleep(100);
		}

		printOpcode(Opcode.REQUEST_INPUT);

		return in.readLine();
	}

	public void sendPlayerId() {
		printOpcode(Opcode.SEND_PLAYER_ID, String.valueOf(playerId));
	}

	public void endGame() {
		printOpcode(Opcode.TERMINATE);
	}

	private void printOpcode(Opcode code, String value) {
		safePrint("> " + code.toString() + " " + value);
	}

	private void printOpcode(Opcode code) {
		safePrint("> " + code.toString());
	}

	private void safePrint(String input) {
		try {
			out.writeBytes(input + "\r");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
