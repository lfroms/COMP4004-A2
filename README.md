# COMP4004-A1
Assignment #1 for COMP4004

Created by: Lukas Romsicki

Student Number: 101059080

## Launch instructions
1. Clone this repository.
1. ```cd COMP4004-A1``` - `cd` into the source.
1. ```mvn package``` - Compile the source into JARs.
1. ```cd target``` - `cd` into the location of the JARs.

### To run the manual test cases
1. ```java -cp COMP4004-A1-0.0.1.jar server.Server <sequence>``` - Start the server, where `<sequence>` is either `seq_a` or `seq_b`.
1. ```java -cp COMP4004-A1-0.0.1.jar client.Client``` - Run this 3 times in separate shells to start each client.

### To launch the game in normal mode
1. ```java -cp COMP4004-A1-0.0.1.jar server.Server``` - Start the server.
1. In the same shell, enter an integer representing the number of players and press enter.
1. ```java -cp COMP4004-A1-0.0.1.jar client.Client``` - Run as many times as needed in separate shells to start each client.
