Feature: Level 1b: Monkey Business
  
  @monkeyBusinessFirstRoll
  Scenario:
  	Given game has started with 1 players
  	And player has "MONKEY_BUSINESS" fortune card
  	And player will receive 1 skulls, 3 parrots, 0 swords, 3 monkeys, 1 coins, 0 diamonds
  	When player rolls dice
  	Then player receives 1100 points
  	
  @monkeyBusinessSeveralRollsA
  Scenario:
  	Given game has started with 1 players
  	And player has "MONKEY_BUSINESS" fortune card
  	And player will receive 0 skulls, 2 parrots, 2 swords, 3 monkeys, 0 coins, 1 diamonds
  	And player will receive 0 skulls, 2 parrots, 3 swords, 1 monkeys, 0 coins, 2 diamonds
  	And player will receive 0 skulls, 1 parrots, 2 swords, 2 monkeys, 2 coins, 1 diamonds
  	When player rolls dice
  	And player rolls dice
  	And player rolls dice
  	Then player receives 400 points
  	
  @monkeyBusinessSeveralRollsB
  Scenario:
  	Given game has started with 1 players
  	And player has "MONKEY_BUSINESS" fortune card
  	And player will receive 0 skulls, 2 parrots, 2 swords, 3 monkeys, 0 coins, 1 diamonds
  	And player will receive 0 skulls, 2 parrots, 3 swords, 1 monkeys, 0 coins, 2 diamonds
  	And player will receive 0 skulls, 4 parrots, 1 swords, 3 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	And player rolls dice
  	And player rolls dice
  	Then player receives 2000 points
