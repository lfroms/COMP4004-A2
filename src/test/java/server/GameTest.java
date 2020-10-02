package server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Test;

import client.Client;

public class GameTest {

	@Test
	public void testShortGameA() throws IOException, InterruptedException {
		final Integer port = 8000;

		Server server = new Server(port, GameTestMode.SEQUENCE_A);
		server.start();

		System.setIn(new ByteArrayInputStream("5\n".getBytes()));
		Client first = new Client(port);
		first.start();

		System.setIn(new ByteArrayInputStream("5\n".getBytes()));
		Client second = new Client(port);
		second.start();

		System.setIn(new ByteArrayInputStream("5\n".getBytes()));
		Client third = new Client(port);
		third.start();

		first.join();
		second.join();
		third.join();

		ScoreCard scoreCard = server.getGame().getScoreCard();

		assertTrue(scoreCard.hasWinner());
		assertEquals(Integer.valueOf(3), scoreCard.getWinnerId().get());
		assertEquals(Integer.valueOf(9000), scoreCard.getCurrentScore(3));

		server.interrupt();
		first.interrupt();
		second.interrupt();
		third.interrupt();
	}

	@Test
	public void testShortGameB() throws IOException, InterruptedException {
		final Integer port = 8001;

		Server server = new Server(port, GameTestMode.SEQUENCE_B);
		server.start();

		System.setIn(new ByteArrayInputStream("5\n5\n".getBytes()));
		Client first = new Client(port);
		first.start();

		System.setIn(new ByteArrayInputStream("5\n5\n".getBytes()));
		Client second = new Client(port);
		second.start();

		System.setIn(new ByteArrayInputStream("5\n3\n5\n".getBytes()));
		Client third = new Client(port);
		third.start();

		first.join();
		second.join();
		third.join();

		ScoreCard scoreCard = server.getGame().getScoreCard();

		assertTrue(scoreCard.hasWinner());
		assertEquals(Integer.valueOf(3), scoreCard.getWinnerId().get());
		assertEquals(Integer.valueOf(9500), scoreCard.getCurrentScore(3));

		server.interrupt();
		first.interrupt();
		second.interrupt();
		third.interrupt();
	}
}
