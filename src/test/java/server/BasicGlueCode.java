package server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Die;
import model.DieFace;
import model.FortuneCard;
import model.FortuneCardType;
import model.InsufficientDiceException;
import model.Turn;
import model.TurnCompleteException;

public class BasicGlueCode {
	Game game;

	@Given("game has started with {int} players")
	public void game_has_started_with_players(Integer int1) {
		List<Player> players = new ArrayList<>();

		for (int i = 0; i < int1; i++) {
			players.add(new Player(i));
		}

		game = new Game(players);
		game.setCurrentTurn(new Turn());
	}

	@Then("the turn ends")
	public void the_turn_ends() {
		System.out.println(Printer.toString(game.getCurrentTurn().getDice()));
		assertTrue(game.getCurrentTurn().isDisqualified());
	}

	@Given("player will receive {int} skulls, {int} parrots, {int} swords, {int} monkeys, {int} coins, {int} diamonds")
	public void player_will_receive_skulls_parrots_swords_monkeys_coins_diamonds(Integer int1, Integer int2,
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

	@When("player rolls dice")
	public void player_rolls_dice() throws TurnCompleteException, InsufficientDiceException {
		game.getCurrentTurn().rollDice();
	}

	@When("player has held {int} parrots, {int} swords, {int} monkeys, {int} coins, {int} diamonds")
	public void player_has_held_parrots_swords_monkeys_coins_diamonds(Integer int1, Integer int2, Integer int3,
			Integer int4, Integer int5) {
		List<Die> dice = game.getCurrentTurn().getDice().getRollable();

		holdDiceOfType(dice, DieFace.PARROT, int1);
		holdDiceOfType(dice, DieFace.SWORD, int2);
		holdDiceOfType(dice, DieFace.MONKEY, int3);
		holdDiceOfType(dice, DieFace.COIN, int4);
		holdDiceOfType(dice, DieFace.DIAMOND, int5);
	}

	@Given("player has {string} fortune card")
	public void player_has_fortune_card(String string) {
		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.valueOf(string));
		game.getCurrentTurn().setFortuneCard(fortuneCard);
	}

	@Then("player receives {int} points")
	public void player_receives_points(Integer int1) {
		ScoreEvaluator evaluator = new ScoreEvaluator(game.getCurrentTurn());
		assertEquals(int1, evaluator.evaluate());
	}

	@Then("player cannot reroll unheld dice")
	public void player_cannot_reroll_unheld_dice() throws TurnCompleteException {
		try {
			game.getCurrentTurn().rollDice();
			fail("InsufficientDiceException was not thrown when there were insufficient dice to reroll");
		} catch (InsufficientDiceException e) {
		}
	}

	private void addNumberOfFaces(List<DieFace> input, DieFace face, Integer count) {
		for (int i = 0; i < count; i++) {
			input.add(face);
		}
	}

	private void holdDiceOfType(List<Die> input, DieFace face, Integer count) {
		Integer numberHeld = 0;
		List<Die> diceWithFace = input.stream().filter(die -> die.getFace() == face).collect(Collectors.toList());

		for (int i = 0; i < diceWithFace.size(); i++) {
			Die die = diceWithFace.get(i);

			if (numberHeld < count) {
				die.setHeld(true);
				numberHeld++;
			} else {
				die.setHeld(false);
			}
		}
	}
}
