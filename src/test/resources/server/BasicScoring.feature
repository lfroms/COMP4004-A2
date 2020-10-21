Feature: Level 1a: Basic Scoring

	@scoreFirstRollCaptainFortuneCard
  Scenario:
    Given game has started with 1 players
    And player has "CAPTAIN" fortune card
  	And player will receive 0 skulls, 2 parrots, 0 swords, 2 monkeys, 2 coins, 2 diamonds
  	When player rolls dice
    Then player receives 800 points
    
  @scoreWithThirdMonkeyOnSecondRoll
  Scenario:
  	Given game has started with 1 players
  	And player has "GOLD" fortune card
  	And player will receive 2 skulls, 2 parrots, 2 swords, 2 monkeys, 0 coins, 0 diamonds
  	And player will receive 0 skulls, 2 parrots, 1 swords, 1 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	And player has held 0 parrots, 0 swords, 2 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	Then player receives 200 points
  	
  @score2SetsOf3OnFirstRoll
  Scenario:
  	Given game has started with 1 players
  	And player has "GOLD" fortune card
  	And player will receive 0 skulls, 3 parrots, 3 swords, 2 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	Then player receives 300 points
  	
  @score2SetsOf3Using2Rolls
  Scenario:
  	Given game has started with 1 players
  	And player has "GOLD" fortune card
  	And player will receive 0 skulls, 3 parrots, 3 swords, 0 monkeys, 1 coins, 2 diamonds
  	And player will receive 0 skulls, 0 parrots, 2 swords, 3 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	And player has held 3 parrots, 0 swords, 2 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	Then player receives 300 points
  	
  @scoreSetOf3Diamonds
  Scenario:
  	Given game has started with 1 players
  	And player has "GOLD" fortune card
  	And player will receive 0 skulls, 2 parrots, 2 swords, 1 monkeys, 0 coins, 3 diamonds
  	When player rolls dice
  	Then player receives 500 points
  	
  @score4CoinsWithDiamondFortuneCard
  Scenario:
  	Given game has started with 1 players
  	And player has "DIAMOND" fortune card
  	And player will receive 0 skulls, 2 parrots, 2 swords, 0 monkeys, 4 coins, 0 diamonds
  	When player rolls dice
  	Then player receives 700 points

	@score3SwordsAnd4ParrotsOnFirstRoll
	Scenario:
		Given game has started with 1 players
		And player has "GOLD" fortune card
		And player will receive 1 skulls, 4 parrots, 3 swords, 0 monkeys, 0 coins, 0 diamonds
		When player rolls dice
		Then player receives 400 points
		
	@score3CoinsAnd4SwordsOverSeveralRolls
	Scenario:
		Given game has started with 1 players
		And player has "GOLD" fortune card
		And player will receive 0 skulls, 4 parrots, 3 swords, 0 monkeys, 1 coins, 0 diamonds
		And player will receive 0 skulls, 4 parrots, 1 swords, 2 monkeys, 1 coins, 0 diamonds
		And player will receive 0 skulls, 1 parrots, 4 swords, 0 monkeys, 3 coins, 0 diamonds
		When player rolls dice
		And player rolls dice
		And player rolls dice
		Then player receives 800 points
		
	@score3CoinsAnd4SwordsOverSeveralRollsWithCaptainFortuneCard
	Scenario:
		Given game has started with 1 players
		And player has "CAPTAIN" fortune card
		And player will receive 0 skulls, 4 parrots, 3 swords, 0 monkeys, 1 coins, 0 diamonds
		And player will receive 0 skulls, 4 parrots, 1 swords, 2 monkeys, 1 coins, 0 diamonds
		And player will receive 0 skulls, 1 parrots, 4 swords, 0 monkeys, 3 coins, 0 diamonds
		When player rolls dice
		And player rolls dice
		And player rolls dice
		Then player receives 1200 points
		
	@score5SwordsOver3Rolls
	Scenario:
		Given game has started with 1 players
		And player has "GOLD" fortune card
		And player will receive 0 skulls, 4 parrots, 3 swords, 0 monkeys, 1 coins, 0 diamonds
		And player will receive 1 skulls, 2 parrots, 3 swords, 0 monkeys, 2 coins, 0 diamonds
		And player will receive 0 skulls, 2 parrots, 5 swords, 0 monkeys, 0 coins, 0 diamonds
		When player rolls dice
		And player rolls dice
		And player rolls dice
		Then player receives 600 points
		
	@score6MonkeysOnFirstRoll
	Scenario:
		Given game has started with 1 players
		And player has "GOLD" fortune card
		And player will receive 0 skulls, 2 parrots, 0 swords, 6 monkeys, 0 coins, 0 diamonds
		When player rolls dice
		Then player receives 1100 points
		
	@score7ParrotsOnFirstRoll
	Scenario:
		Given game has started with 1 players
		And player has "GOLD" fortune card
		And player will receive 0 skulls, 7 parrots, 1 swords, 0 monkeys, 0 coins, 0 diamonds
		When player rolls dice
		Then player receives 2100 points
		
	@score8CoinsOnFirstRoll
	Scenario:
		Given game has started with 1 players
		And player has "GOLD" fortune card
		And player will receive 0 skulls, 0 parrots, 0 swords, 0 monkeys, 8 coins, 0 diamonds
		When player rolls dice
		Then player receives 5400 points
		
	@score8CoinsWithDiamondFortuneCard
	Scenario:
		Given game has started with 1 players
		And player has "DIAMOND" fortune card
		And player will receive 0 skulls, 0 parrots, 0 swords, 0 monkeys, 8 coins, 0 diamonds
		When player rolls dice
		Then player receives 5400 points
		
	@score8SwordsWithCaptainFortuneCard
	Scenario:
		Given game has started with 1 players
		And player has "CAPTAIN" fortune card
		And player will receive 0 skulls, 0 parrots, 8 swords, 0 monkeys, 0 coins, 0 diamonds
		When player rolls dice
		Then player receives 9000 points
		
	@score8MonkeysOverSeveralRolls
	Scenario:
		Given game has started with 1 players
		And player has "GOLD" fortune card
		And player will receive 0 skulls, 2 parrots, 2 swords, 2 monkeys, 2 coins, 0 diamonds
		And player will receive 0 skulls, 4 parrots, 0 swords, 2 monkeys, 2 coins, 0 diamonds
		And player will receive 0 skulls, 0 parrots, 0 swords, 8 monkeys, 0 coins, 0 diamonds
		When player rolls dice
		And player rolls dice
		And player rolls dice
		Then player receives 4600 points
		
	@score2DiamondsOver2RollsWithDiamondFortuneCard
	Scenario:
		Given game has started with 1 players
		And player has "DIAMOND" fortune card
		And player will receive 0 skulls, 2 parrots, 2 swords, 2 monkeys, 1 coins, 1 diamonds
		And player will receive 0 skulls, 2 parrots, 2 swords, 2 monkeys, 0 coins, 1 diamonds
		When player rolls dice
		And player has held 0 parrots, 0 swords, 0 monkeys, 0 coins, 1 diamonds
		When player rolls dice
		Then player receives 400 points
		
	@score3DiamondsOver2Rolls
	Scenario:
		Given game has started with 1 players
		And player has "GOLD" fortune card
		And player will receive 0 skulls, 2 parrots, 2 swords, 2 monkeys, 0 coins, 2 diamonds
		And player will receive 0 skulls, 2 parrots, 2 swords, 1 monkeys, 0 coins, 1 diamonds
		When player rolls dice
		And player has held 0 parrots, 0 swords, 0 monkeys, 0 coins, 2 diamonds
		When player rolls dice
		Then player receives 500 points
  	
  @score3CoinsOver2Rolls
  Scenario:
  	Given game has started with 1 players
  	And player has "GOLD" fortune card
  	And player will receive 2 skulls, 2 parrots, 2 swords, 1 monkeys, 1 coins, 0 diamonds
  	And player will receive 0 skulls, 2 parrots, 0 swords, 1 monkeys, 2 coins, 0 diamonds
  	When player rolls dice
  	And player has held 0 parrots, 0 swords, 0 monkeys, 1 coins, 0 diamonds
  	When player rolls dice
  	Then player receives 600 points
  	
  @score3CoinsOver2RollsWithDiamondFortuneCard
  Scenario:
  	Given game has started with 1 players
  	And player has "DIAMOND" fortune card
  	And player will receive 2 skulls, 2 parrots, 2 swords, 1 monkeys, 1 coins, 0 diamonds
  	And player will receive 0 skulls, 2 parrots, 0 swords, 1 monkeys, 2 coins, 0 diamonds
  	When player rolls dice
  	And player has held 0 parrots, 0 swords, 0 monkeys, 1 coins, 0 diamonds
  	When player rolls dice
  	Then player receives 500 points
  	
  @score4Monkeys3Coins
  Scenario:
  	Given game has started with 1 players
  	And player has "GOLD" fortune card
  	And player will receive 0 skulls, 0 parrots, 2 swords, 4 monkeys, 3 coins, 0 diamonds
  	When player rolls dice
  	Then player receives 600 points
  	
  @scoreCannotRollSingleDie
  Scenario:
  	Given game has started with 1 players
  	And player has "GOLD" fortune card
  	And player will receive 0 skulls, 0 parrots, 7 swords, 1 monkeys, 0 coins, 0 diamonds
  	When player rolls dice
  	And player has held 0 parrots, 7 swords, 0 monkeys, 0 coins, 0 diamonds
  	Then player cannot reroll unheld dice
