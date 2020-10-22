Feature: Level 1b: Treasure Chest

  @treasureChestA
  Scenario:
  	Given game has started with 1 players
  	And player has "TREASURE_CHEST" fortune card
  	And player will receive 0 skulls, 3 parrots, 2 swords, 0 monkeys, 1 coins, 2 diamonds
  	And player will receive 0 skulls, 2 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
  	And player will receive 1 skulls, 1 parrots, 0 swords, 0 monkeys, 1 coins, 0 diamonds
  	When player rolls dice
  	And player places 0 parrots, 0 swords, 0 monkeys, 1 coins, 2 diamonds in treasure chest
  	And player has held 3 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
  	And player rolls dice
  	And player places 5 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds in treasure chest
  	And player removes 0 parrots, 0 swords, 0 monkeys, 1 coins, 2 diamonds from treasure chest
  	When player rolls dice
  	Then player receives 1100 points
  	
  @treasureChestB
  Scenario:
  	Given game has started with 1 players
  	And player has "TREASURE_CHEST" fortune card
  	And player will receive 2 skulls, 3 parrots, 0 swords, 0 monkeys, 3 coins, 0 diamonds
  	And player will receive 0 skulls, 0 parrots, 0 swords, 0 monkeys, 1 coins, 2 diamonds
  	And player will receive 1 skulls, 0 parrots, 0 swords, 0 monkeys, 1 coins, 0 diamonds
  	When player rolls dice
  	And player places 0 parrots, 0 swords, 0 monkeys, 3 coins, 0 diamonds in treasure chest
  	And player rolls dice
  	And player places 0 parrots, 0 swords, 0 monkeys, 1 coins, 0 diamonds in treasure chest
  	When player rolls dice
  	Then player receives 600 points
