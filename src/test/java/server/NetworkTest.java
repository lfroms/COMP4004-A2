package server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import client.Client;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.DieFace;
import model.FortuneCard;
import model.FortuneCardType;

public class NetworkTest {
	Server server = null;
	Game game = null;
	ArrayList<Client> clients = new ArrayList<>();

	@Given("network game has started with {int} players")
	public void network_game_has_started_with_players(Integer int1) throws IOException, InterruptedException {
		final Integer port = 8000;

		server = new Server(int1, true);
		server.start();

		for (int i = 0; i < int1; i++) {
			Client client = new Client(port);
			clients.add(client);
			client.start();
		}

		// Wait for all connections to complete
		Thread.sleep(500);

		game = server.getGame();
	}

	@When("player {int} responds with option {int}")
	public void player_responds_with_option(Integer int1, Integer int2) throws InterruptedException {
		clients.get(int1).setNextInput(int2);
		server.getPlayers().get(int1).setHoldForUserInput(false);

		// Wait for server to process
		Thread.sleep(200);
	}

	@When("turn has started")
	public void turn_has_started() throws InterruptedException {
		game.setFinishedConfiguring();

		// Wait for all clients to receive new instructions
		Thread.sleep(clients.size() * 100);
	}

	@When("player will receive {string} fortune card")
	public void player_will_receive_fortune_card(String string) {
		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.valueOf(string));
		game.getCurrentTurn().setFortuneCard(fortuneCard);
	}

	@When("current player will receive {int} skulls, {int} parrots, {int} swords, {int} monkeys, {int} coins, {int} diamonds")
	public void current_player_will_receive_skulls_parrots_swords_monkeys_coins_diamonds(Integer int1, Integer int2,
			Integer int3, Integer int4, Integer int5, Integer int6) {
		List<DieFace> faces = new ArrayList<>();

		addNumberOfFaces(faces, DieFace.SKULL, int1);
		addNumberOfFaces(faces, DieFace.PARROT, int2);
		addNumberOfFaces(faces, DieFace.SWORD, int3);
		addNumberOfFaces(faces, DieFace.MONKEY, int4);
		addNumberOfFaces(faces, DieFace.COIN, int5);
		addNumberOfFaces(faces, DieFace.DIAMOND, int6);

		DieFace[] array = new DieFace[faces.size()];
		game.getCurrentTurn().addRiggedRoll(faces.toArray(array));
	}

	@Then("player {int} has score of {int}")
	public void player_has_score_of(Integer int1, Integer int2) {
		assertEquals(int2, game.getScoreCard().getCurrentScore(int1 + 1));
	}

	@Then("player {int} has won the game")
	public void player_has_won_the_game(Integer int1) {
		// Adding 1 since ID's are offset by 1
		assertTrue(game.getScoreCard().getWinnerId().isPresent());
		assertEquals(Integer.valueOf(int1 + 1), game.getScoreCard().getWinnerId().get());
	}

	@Then("the game is finished")
	public void the_game_is_finished() {
		assertTrue(game.getIsComplete());
	}

	@Then("all connections are closed")
	public void all_connections_are_closed() throws InterruptedException, IOException {
		server.getSocket().close();
	}

	private void addNumberOfFaces(List<DieFace> input, DieFace face, Integer count) {
		for (int i = 0; i < count; i++) {
			input.add(face);
		}
	}
}
