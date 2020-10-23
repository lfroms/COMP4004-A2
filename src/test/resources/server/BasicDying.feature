Feature: Level 1a: Basic Dying

	@dieWith3SkullsFirstRoll
  Scenario: Die with three skulls on first roll
    Given game has started with 1 players
    And player has "GOLD" fortune card
  	And player will receive 3 skulls, 2 parrots, 2 swords, 1 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
    Then the turn ends

	@dieWithSecondAndThirdSkullOnSecondRoll
  Scenario: Die with second and third skull on second roll
  	Given game has started with 1 players
  	And player has "GOLD" fortune card
  	And player will receive 1 skulls, 4 parrots, 3 swords, 0 monkeys, 0 coins, 0 diamonds
  	And player will receive 2 skulls, 0 parrots, 1 swords, 0 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	And player has held 4 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	Then the turn ends
  	
  @dieWithThirdSkullOnFinalRoll
  Scenario: Die with third skull on final roll
  	Given game has started with 1 players
  	And player has "GOLD" fortune card
  	And player will receive 2 skulls, 4 parrots, 2 swords, 0 monkeys, 0 coins, 0 diamonds
  	And player will receive 1 skulls, 0 parrots, 1 swords, 0 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	And player has held 4 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	Then the turn ends
  	
  @dieWithSkullsOverThreeRolls
  Scenario: Die with three skulls over three rolls
  	Given game has started with 1 players
  	And player has "GOLD" fortune card
  	And player will receive 1 skulls, 4 parrots, 3 swords, 0 monkeys, 0 coins, 0 diamonds
  	And player will receive 1 skulls, 0 parrots, 0 swords, 2 monkeys, 0 coins, 0 diamonds
  	And player will receive 1 skulls, 0 parrots, 0 swords, 1 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	And player has held 4 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	And player has held 4 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	Then the turn ends
