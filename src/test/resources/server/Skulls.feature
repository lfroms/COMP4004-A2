Feature: Level 1b: Island of Skulls and Skulls Fortune Card

  @dieWith1SkullAnd2SkullsFortuneCard
  Scenario:
  	Given game has started with 1 players
  	And player has "SKULLS" fortune card with 2
  	And player will receive 1 skulls, 7 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	Then the turn ends
  
  @dieWith2SkullsAnd1SkullFortuneCard
  Scenario:
  	Given game has started with 1 players
  	And player has "SKULLS" fortune card with 1
  	And player will receive 2 skulls, 6 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	Then the turn ends
  	
  @roll5SkullsWithCaptainFortuneCard
  Scenario:
  	Given game has started with 2 players
  	And player 1 has gained 1000 points
  	And player has "CAPTAIN" fortune card
  	And player will receive 5 skulls, 3 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	And player ends turn
  	Then player 1 has 0 points
  	
  @roll2SkullsAnd2SkullsFortuneCard
  Scenario:
  	Given game has started with 2 players
  	And player 1 has gained 700 points
  	And player has "SKULLS" fortune card with 2
  	And player will receive 2 skulls, 6 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
  	And player will receive 2 skulls, 4 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
  	And player will receive 1 skulls, 3 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	And player rolls dice
  	And player rolls dice
  	And player ends turn
  	Then player 1 has 0 points
  	
  @roll3SkullsAnd2SkullsFortuneCard
  Scenario:
  	Given game has started with 2 players
  	And player 1 has gained 500 points
  	And player has "SKULLS" fortune card with 2
  	And player will receive 3 skulls, 5 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
  	And player will receive 0 skulls, 8 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	And player rolls dice
  	And player ends turn
  	Then player 1 has 0 points
  	
 	@roll3SkullsAnd1SkullFortuneCard
 	Scenario:
 		Given game has started with 2 players
 		And player 1 has gained 500 points
 		And player has "SKULLS" fortune card with 1
 		And player will receive 3 skulls, 5 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
 		And player will receive 1 skulls, 4 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
 		And player will receive 0 skulls, 4 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
 		When player rolls dice
 		And player rolls dice
 		And player rolls dice
 		And player ends turn
 		Then player 1 has 0 points

 	@islandOfSkullsCannotMakeNegativePoints
 	Scenario:
 		Given game has started with 2 players
 		And player 1 has gained 100 points
 		And player has "SKULLS" fortune card with 3
 		And player will receive 1 skulls, 7 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
 		When player rolls dice
 		And player ends turn
 		Then player 1 has 0 points
