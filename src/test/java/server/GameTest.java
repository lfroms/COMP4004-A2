package server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import model.FortuneCardInvalidException;
import model.FortuneCardType;
import model.InsufficientDiceException;
import model.Turn;
import model.TurnCompleteException;

public class GameTest {
	List<Player> players = new ArrayList<>();
	Game game;

	@Given("game has started with {int} players")
	public void game_has_started_with_players(Integer int1) {
		players.clear();

		for (int i = 0; i < int1; i++) {
			players.add(new Player(i));
		}

		game = new Game(players);
		game.setCurrentTurn(new Turn());
	}

	@Then("the turn ends")
	public void the_turn_ends() {
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
		List<Die> dice = game.getCurrentTurn().getDice().getAll();

		holdDiceOfType(dice, DieFace.PARROT, int1);
		holdDiceOfType(dice, DieFace.SWORD, int2);
		holdDiceOfType(dice, DieFace.MONKEY, int3);
		holdDiceOfType(dice, DieFace.COIN, int4);
		holdDiceOfType(dice, DieFace.DIAMOND, int5);
	}

	@Given("player has {string} fortune card with {int}")
	public void player_has_fortune_card_with(String string, Integer integer) {
		FortuneCard fortuneCard = new FortuneCard(FortuneCardType.valueOf(string), integer);
		game.getCurrentTurn().setFortuneCard(fortuneCard);
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

	@When("player rerolls {int} skull")
	public void player_rerolls_skull(Integer int1) throws FortuneCardInvalidException {
		game.getCurrentTurn().rerollSingleSkull();
	}

	@Then("turn can continue")
	public void turn_can_continue() {
		assertFalse(game.getCurrentTurn().isDisqualified());
	}

	@When("player places {int} parrots, {int} swords, {int} monkeys, {int} coins, {int} diamonds in treasure chest")
	public void player_places_parrots_swords_monkeys_coins_diamonds_in_treasure_chest(Integer int1, Integer int2,
			Integer int3, Integer int4, Integer int5) {
		List<Die> dice = game.getCurrentTurn().getDice().getAll();

		setTreasureChestDiceOfType(dice, DieFace.PARROT, int1, true);
		setTreasureChestDiceOfType(dice, DieFace.SWORD, int2, true);
		setTreasureChestDiceOfType(dice, DieFace.MONKEY, int3, true);
		setTreasureChestDiceOfType(dice, DieFace.COIN, int4, true);
		setTreasureChestDiceOfType(dice, DieFace.DIAMOND, int5, true);
	}

	@When("player removes {int} parrots, {int} swords, {int} monkeys, {int} coins, {int} diamonds from treasure chest")
	public void player_removes_parrots_swords_monkeys_coins_diamonds_from_treasure_chest(Integer int1, Integer int2,
			Integer int3, Integer int4, Integer int5) {
		List<Die> dice = game.getCurrentTurn().getDice().getAll();

		setTreasureChestDiceOfType(dice, DieFace.PARROT, int1, false);
		setTreasureChestDiceOfType(dice, DieFace.SWORD, int2, false);
		setTreasureChestDiceOfType(dice, DieFace.MONKEY, int3, false);
		setTreasureChestDiceOfType(dice, DieFace.COIN, int4, false);
		setTreasureChestDiceOfType(dice, DieFace.DIAMOND, int5, false);
	}

	@Given("player {int} has gained {int} points")
	public void player_has_gained(Integer int1, Integer int2) {
		game.getScoreCard().addNewScore(new Score(int1, int2));
	}

	@When("player ends turn")
	public void player_ends_turn() {
		game.endTurn(game.getCurrentTurn(), players.get(0));
	}

	@Then("player {int} has {int} points")
	public void player_has_points(Integer int1, Integer int2) {
		assertEquals(int2, game.getScoreCard().getCurrentScore(int1));
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

	private void setTreasureChestDiceOfType(List<Die> input, DieFace face, Integer count, Boolean add) {
		Integer numberAdded = 0;
		List<Die> diceWithFace = input.stream().filter(die -> die.getFace() == face).collect(Collectors.toList());

		for (int i = 0; i < diceWithFace.size(); i++) {
			Die die = diceWithFace.get(i);

			if (die.getInTreasureChest() == add) {
				continue;
			}

			if (numberAdded < count) {
				die.setInTreasureChest(add);
				numberAdded++;
			}
		}
	}
}
