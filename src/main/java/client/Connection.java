package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public final class Connection {
	private final InetAddress address;
	private final Socket socket;
	private final BufferedReader inFromServer;
	private final PrintWriter outToServer;

	private final Scanner scanner = new Scanner(System.in);

	public Connection(Integer port) throws IOException {
		try {
			address = InetAddress.getLocalHost();
			socket = new Socket(address, port);
			inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			outToServer = new PrintWriter(socket.getOutputStream());

		} catch (IOException e) {
			throw e;
		}
	}

	public void close() throws IOException {
		inFromServer.close();
		outToServer.close();
		socket.close();
		scanner.close();
	}

	public String getAddress() {
		return address.toString();
	}

	public String readLineFromUser() throws IOException {
		return scanner.nextLine();
	}

	public void printLine(String input) {
		System.out.println(input);
	}

	public String readLineFromServer() throws IOException {
		return inFromServer.readLine();
	}

	public void sendLineToServer(String input) {
		outToServer.println(input);
		outToServer.flush();
	}
}
