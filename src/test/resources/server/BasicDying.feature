Feature: Level 1a: Basic Dying

	@dieWith3SkullsFirstRoll
  Scenario:
    Given game has started with 1 players
  	And player will receive 3 skulls, 2 parrots, 2 swords, 1 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
    Then the turn ends

	@dieWithSecondAndThirdSkullOnSecondRoll
  Scenario:
  	Given game has started with 1 players
  	And player will receive 1 skulls, 4 parrots, 3 swords, 0 monkeys, 0 coins, 0 diamonds
  	And player will receive 2 skulls, 0 parrots, 1 swords, 0 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	And player has held 4 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	Then the turn ends
  	
  @dieWithThirdSkullOnFinalRoll
  Scenario:
  	Given game has started with 1 players
  	And player will receive 2 skulls, 4 parrots, 2 swords, 0 monkeys, 0 coins, 0 diamonds
  	And player will receive 1 skulls, 0 parrots, 1 swords, 0 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	And player has held 4 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	Then the turn ends
  	
  @dieWithSkullsOverThreeRolls
  Scenario:
  	Given game has started with 1 players
  	And player will receive 1 skulls, 4 parrots, 3 swords, 0 monkeys, 0 coins, 0 diamonds
  	And player will receive 1 skulls, 0 parrots, 0 swords, 2 monkeys, 0 coins, 0 diamonds
  	And player will receive 1 skulls, 0 parrots, 0 swords, 1 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	And player has held 4 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	And player has held 4 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	Then the turn ends
