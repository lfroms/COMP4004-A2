# COMP4004-A2
Assignment #2 for COMP4004

Created by: Lukas Romsicki

Student Number: 101059080

## Launch instructions
1. Clone this repository.
1. ```cd COMP4004-A2``` - `cd` into the source.
1. ```mvn package``` - Compile the source into JARs.
1. ```cd target``` - `cd` into the location of the JARs.

### To launch the game in normal mode
1. ```java -cp COMP4004-A2-0.0.1.jar server.Server``` - Start the server.
1. In the same shell, enter an integer representing the number of players and press enter.
1. ```java -cp COMP4004-A2-0.0.1.jar client.Client``` - Run as many times as needed in separate shells to start each client.
