Feature: Level 1b: Sea Battle Fortune Card

	@seaBattle2SwordsDieFirstRoll
	Scenario: Die on first roll (2 swords)
		Given game has started with 1 players
		And player 0 has gained 500 points
		And player has "SEA_BATTLE" fortune card with 2
		And player will receive 4 skulls, 2 parrots, 2 swords, 0 monkeys, 0 coins, 0 diamonds
		When player rolls dice
		Then the turn ends
		And player receives -300 points
		When player ends turn
		Then player 0 has 200 points

	@seaBattle3SwordsDieFirstRoll
	Scenario: Die on first roll (3 swords)
		Given game has started with 1 players
		And player 0 has gained 600 points
		And player has "SEA_BATTLE" fortune card with 3
		And player will receive 4 skulls, 2 parrots, 2 swords, 0 monkeys, 0 coins, 0 diamonds
		When player rolls dice
		Then the turn ends
		And player receives -500 points
		When player ends turn
		Then player 0 has 100 points

	@seaBattle4SwordsDieFirstRoll
	Scenario: Die on first roll (4 swords)
		Given game has started with 1 players
		And player 0 has gained 1100 points
		And player has "SEA_BATTLE" fortune card with 4
		And player will receive 4 skulls, 2 parrots, 2 swords, 0 monkeys, 0 coins, 0 diamonds
		When player rolls dice
		Then the turn ends
		And player receives -1000 points
		When player ends turn
		Then player 0 has 100 points
		
	@seaBattleNoNegativePoints
	Scenario: Points cannot become negative
		Given game has started with 1 players
		And player 0 has gained 200 points
		And player has "SEA_BATTLE" fortune card with 4
		And player will receive 4 skulls, 2 parrots, 2 swords, 0 monkeys, 0 coins, 0 diamonds
		When player rolls dice
		Then the turn ends
		And player receives -1000 points
		When player ends turn
		Then player 0 has 0 points
		
	@seaBattleScenarioA
	Scenario: Scenario A
		Given game has started with 1 players
		And player has "SEA_BATTLE" fortune card with 2
		And player will receive 0 skulls, 2 parrots, 2 swords, 3 monkeys, 1 coins, 0 diamonds
		When player rolls dice
		Then player receives 500 points
		
	@seaBattleScenarioB
	Scenario: Scenario B
		Given game has started with 1 players
		And player has "SEA_BATTLE" fortune card with 2
		And player will receive 1 skulls, 2 parrots, 1 swords, 4 monkeys, 0 coins, 0 diamonds
		And player will receive 1 skulls, 0 parrots, 1 swords, 0 monkeys, 0 coins, 0 diamonds
		When player rolls dice
		And player has held 0 parrots, 1 swords, 4 monkeys, 0 coins, 0 diamonds
		When player rolls dice
		Then player receives 500 points
		
	@seaBattleScenarioC
	Scenario: Scenario C
		Given game has started with 1 players
		And player has "SEA_BATTLE" fortune card with 3
		And player will receive 1 skulls, 0 parrots, 4 swords, 3 monkeys, 0 coins, 0 diamonds
		When player rolls dice
		Then player receives 800 points
		
	@seaBattleScenarioD
	Scenario: Scenario D
		Given game has started with 1 players
		And player has "SEA_BATTLE" fortune card with 3
		And player will receive 2 skulls, 0 parrots, 2 swords, 4 monkeys, 0 coins, 0 diamonds
		And player will receive 2 skulls, 0 parrots, 2 swords, 0 monkeys, 0 coins, 0 diamonds
		When player rolls dice
		And player has held 0 parrots, 2 swords, 0 monkeys, 0 coins, 0 diamonds
		When player rolls dice
		Then the turn ends
		
	@seaBattleScenarioE
	Scenario: Scenario E
		Given game has started with 1 players
		And player has "SEA_BATTLE" fortune card with 4
		And player will receive 1 skulls, 0 parrots, 4 swords, 3 monkeys, 0 coins, 0 diamonds
		When player rolls dice
		Then player receives 1300 points
		
	@seaBattleScenarioF
	Scenario: Scenario F
		Given game has started with 1 players
		And player has "SEA_BATTLE" fortune card with 4
		And player will receive 1 skulls, 2 parrots, 1 swords, 3 monkeys, 0 coins, 1 diamonds
		And player will receive 0 skulls, 0 parrots, 2 swords, 0 monkeys, 0 coins, 0 diamonds
		And player will receive 0 skulls, 2 parrots, 1 swords, 0 monkeys, 0 coins, 0 diamonds
		When player rolls dice
		And player has held 0 parrots, 1 swords, 3 monkeys, 0 coins, 1 diamonds
		And player rolls dice
		And player has held 0 parrots, 3 swords, 0 monkeys, 0 coins, 1 diamonds
		And player rolls dice
		Then player receives 1300 points
