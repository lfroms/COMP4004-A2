Feature: Level 1b: Sorceress Fortune Card

	@sorceressRerollSingleSkull
  Scenario:
  	Given game has started with 1 players
  	And player has "SORCERESS" fortune card
  	And player will receive 2 skulls, 2 parrots, 2 swords, 2 monkeys, 0 coins, 0 diamonds
  	And player will receive 0 skulls, 1 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	And player rerolls 1 skull
  	Then turn can continue
  	
  @sorceressRerollSkullOnSecondRoll
  Scenario:
  	Given game has started with 1 players
  	And player has "SORCERESS" fortune card
  	And player will receive 0 skulls, 2 parrots, 2 swords, 2 monkeys, 2 coins, 0 diamonds
  	And player will receive 1 skulls, 2 parrots, 1 swords, 2 monkeys, 2 coins, 0 diamonds
  	And player will receive 0 skulls, 1 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	And player rolls dice
  	When player rerolls 1 skull
  	Then player receives 300 points
  	
  @sorceressRerollSkullOnSecondRollAndContinue
  Scenario:
  	Given game has started with 1 players
  	And player has "SORCERESS" fortune card
  	And player will receive 0 skulls, 2 parrots, 2 swords, 2 monkeys, 2 coins, 0 diamonds
  	And player will receive 1 skulls, 2 parrots, 1 swords, 2 monkeys, 2 coins, 0 diamonds
  	And player will receive 0 skulls, 1 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	And player rolls dice
  	When player rerolls 1 skull
  	Then turn can continue
