package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import shared.Opcode;

final class Client {
	private static final Integer PORT = 8000;

	private final InetAddress address;
	private final Socket socket;
	private final BufferedReader systemReader;
	private final BufferedReader inFromServer;
	private final PrintWriter outToServer;

	private Integer playerId = null;

	public static void main(String args[]) throws IOException {
		new Client(PORT).start();
	}

	public Client(Integer port) throws IOException {
		try {
			address = InetAddress.getLocalHost();
			socket = new Socket(address, port);
			systemReader = new BufferedReader(new InputStreamReader(System.in));
			inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			outToServer = new PrintWriter(socket.getOutputStream());

		} catch (IOException e) {
			throw e;
		}
	}

	private void start() {
		printLine("Connected to " + address.toString());

		try {
			while (true) {
				awaitResponse();
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);

		} finally {
			try {
				inFromServer.close();
				outToServer.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}

	private void awaitResponse() throws IOException {
		String response = readLineFromServer();

		if (response == null) {
			return;
		}

		if (response.startsWith(">")) {
			handleOpcode(response);
			return;
		}

		printLine(response);
	}

	private void handleOpcode(String message) {
		Opcode operation = Opcode.valueOf(message.split(" ")[1]);
		String value = message.split(" ")[2];

		switch (operation) {
		case SEND_PLAYER_ID:
			playerId = Integer.valueOf(value);
			printLine("You are player #" + playerId + ".");
			break;

		default:
			break;
		}
	}

	private String readLineFromUser() throws IOException {
		return systemReader.readLine();
	}

	private void printLine(String input) {
		System.out.println(input);
	}

	private String readLineFromServer() throws IOException {
		return inFromServer.readLine();
	}

	private void sendLineToServer(String input) {
		outToServer.println(input);
	}
}
