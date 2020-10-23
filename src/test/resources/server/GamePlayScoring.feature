Feature: Level 2: Networked Game Scoring

  @scoreUpdatesDuringGameplay
  Scenario:
  	Given network game has started with 3 players
  	Given current player will receive 0 skulls, 0 parrots, 0 swords, 3 monkeys, 2 coins, 3 diamonds
  	And player will receive "GOLD" fortune card
  	When turn has started
  	When player 0 responds with option 5
  	Then player 0 has score of 1400
  	
  	Given current player will receive 2 skulls, 6 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
  	And player will receive "CAPTAIN" fortune card
  	When turn has started
  	When player 1 responds with option 5
  	Then player 1 has score of 2000
  	
  	Given current player will receive 0 skulls, 0 parrots, 3 swords, 3 monkeys, 2 coins, 0 diamonds
  	And player will receive "GOLD" fortune card
  	When turn has started
  	When player 2 responds with option 5
  	Then player 2 has score of 1100
  	
  	Given current player will receive 0 skulls, 0 parrots, 3 swords, 5 monkeys, 0 coins, 0 diamonds
  	And player will receive "CAPTAIN" fortune card
  	When turn has started
  	When player 0 responds with option 5
  	Then player 0 has score of 3600
  	
  	Given current player will receive 2 skulls, 2 parrots, 2 swords, 0 monkeys, 1 coins, 1 diamonds
  	And player will receive "GOLD" fortune card
  	When turn has started
  	When player 1 responds with option 5
  	Then player 1 has score of 2300

  	Then all connections are closed
