package client;

import java.io.IOException;

import shared.Opcode;

public final class Client extends Thread {
	private static final Integer PORT = 8000;
	private final Connection connection;

	private Integer playerId = null;
	private String nextInput = null;

	public static void main(String args[]) throws IOException {
		new Client(PORT).start();
	}

	public Client(Integer port) throws IOException {
		connection = new Connection(port);
	}

	public Connection getConnection() {
		return connection;
	}

	@Override
	public void run() {
		connection.printLine("Connected to " + connection.getAddress());

		try {
			Opcode code = null;

			while (code != Opcode.TERMINATE) {
				code = pollServer();

				// Poll every 100ms
				Thread.sleep(100);
			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			System.exit(-1);

		} finally {
			try {
				connection.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}

	public void setNextInput(Integer input) {
		this.nextInput = String.valueOf(input);
	}

	private Opcode pollServer() throws IOException {
		String response = null;

		while ((response = connection.readLineFromServer()) != null) {
			if (response.startsWith(">")) {
				return handleOpcode(response);
			}

			connection.printLine(response);

		}

		return null;
	}

	private Opcode handleOpcode(String message) {
		String[] splitMessage = message.split(" ");

		Opcode operation = Opcode.valueOf(splitMessage[1]);
		String value = null;

		if (splitMessage.length == 3) {
			value = splitMessage[2];
		}

		switch (operation) {
		case SEND_PLAYER_ID:
			handleSendPlayerId(value);
			break;

		case REQUEST_INPUT:
			handleRequestInput();
			break;

		default:
			break;
		}

		return operation;
	}

	private void handleSendPlayerId(String value) {
		playerId = Integer.valueOf(value);
		connection.printLine("You are player #" + playerId + ".");
	}

	private void handleRequestInput() {
		try {
			String response;

			if (nextInput == null) {
				response = connection.readLineFromUser();
			} else {
				response = nextInput;
			}

			connection.sendLineToServer(response);
			nextInput = null;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
