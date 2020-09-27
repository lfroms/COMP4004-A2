package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import shared.Opcode;

final class Player {
	private InputStream inputStream = null;
	private BufferedReader in = null;
	private DataOutputStream out = null;

	public Player(Socket socket) throws IOException {
		inputStream = socket.getInputStream();
		in = new BufferedReader(new InputStreamReader(inputStream));
		out = new DataOutputStream(socket.getOutputStream());
	}

	public void printGreeting() {
		safePrint("Welcome to the game. The game will automatically begin once two players join.");
	}

	public void printMessage(String message) {
		safePrint(message);
	}

	public String promptForSelection() throws IOException {
		printOpcode(Opcode.REQUEST_INTEGER);

		return in.readLine();
	}

	public void sendPlayerId(Integer id) {
		printOpcode(Opcode.SEND_PLAYER_ID, String.valueOf(id));
	}

	private void printOpcode(Opcode code, String value) {
		safePrint("> " + code.toString() + " " + value + "\r");
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
