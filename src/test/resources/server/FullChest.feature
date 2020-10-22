Feature: Level 1b: Full Chest

	@fullChestGoldFortuneCardNoBonus
	Scenario:
		Given game has started with 1 players
		And player has "GOLD" fortune card
		And player will receive 0 skulls, 1 parrots, 3 swords, 3 monkeys, 0 coins, 1 diamonds
		When player rolls dice
		Then player receives 400 points
		
	@fullChestCaptainFortuneCard
	Scenario:
		Given game has started with 1 players
		And player has "CAPTAIN" fortune card
		And player will receive 0 skulls, 0 parrots, 3 swords, 3 monkeys, 2 coins, 0 diamonds
		When player rolls dice
		Then player receives 1800 points
		
	@fullChestGoldFortuneCard
	Scenario:
		Given game has started with 1 players
		And player has "GOLD" fortune card
		And player will receive 0 skulls, 0 parrots, 4 swords, 3 monkeys, 0 coins, 1 diamonds
		When player rolls dice
		Then player receives 1000 points
		
	@fullChest2SwordSeaBattle
	Scenario:
		Given game has started with 1 players
		And player has "SEA_BATTLE" fortune card with 2
		And player will receive 0 skulls, 2 parrots, 1 swords, 4 monkeys, 1 coins, 0 diamonds
		And player will receive 0 skulls, 0 parrots, 1 swords, 0 monkeys, 1 coins, 0 diamonds
		When player rolls dice
		And player has held 0 parrots, 1 swords, 4 monkeys, 1 coins, 0 diamonds
		And player rolls dice
		Then player receives 1200 points
		
	@fullChestMonkeyBusiness
	Scenario:
		Given game has started with 1 players
		And player has "MONKEY_BUSINESS" fortune card
		And player will receive 0 skulls, 1 parrots, 0 swords, 2 monkeys, 2 coins, 3 diamonds
		When player rolls dice
		Then player receives 1200 points
