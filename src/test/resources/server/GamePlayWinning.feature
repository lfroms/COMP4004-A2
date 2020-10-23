Feature: Level 2: Networked Game Winning

  @winnerIsDeclared
  Scenario: Game can be won in networked gameplay
  	Given network game has started with 3 players
  	
  	# Round 1
  	
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
  	
  	# Round 2
  	
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
  	
  	Given current player will receive 3 skulls, 5 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
  	And player will receive "GOLD" fortune card
  	When turn has started
  	Then player 2 has score of 1100
  	
  	# Round 3
  	
  	Given current player will receive 2 skulls, 0 parrots, 0 swords, 6 monkeys, 0 coins, 0 diamonds
  	And current player will receive 0 skulls, 0 parrots, 0 swords, 1 monkeys, 0 coins, 0 diamonds
  	And player will receive "SORCERESS" fortune card
  	When turn has started
  	When player 0 responds with option 0
  	And player 0 responds with option 5
  	Then player 0 has score of 5600
  	
  	Given current player will receive 4 skulls, 0 parrots, 4 swords, 0 monkeys, 0 coins, 0 diamonds
  	Given current player will receive 2 skulls, 0 parrots, 2 swords, 0 monkeys, 0 coins, 0 diamonds
  	Given current player will receive 1 skulls, 1 parrots, 0 swords, 0 monkeys, 0 coins, 0 diamonds
  	And player will receive "GOLD" fortune card
  	When turn has started
  	When player 1 responds with option 3
  	And player 1 responds with option 3
  	And player 1 responds with option 3
  	And player 1 responds with option 5
  	Then player 0 has score of 4900
  	And player 1 has score of 2300
  	And player 2 has score of 400
  	
  	Given current player will receive 0 skulls, 0 parrots, 0 swords, 0 monkeys, 0 coins, 8 diamonds
  	And player will receive "CAPTAIN" fortune card
  	When turn has started
  	And player 2 responds with option 5
  	Then player 2 has score of 11000
  	
  	Then player 2 has won the game
  	And the game is finished

  	Then all connections are closed
