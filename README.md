# CS 5010 Semester Project

This repo represents the coursework for CS 5010, the Summer 2023 Edition!

### About/Overview

The program is designed to solve the problem of creating a blueprint for Doctor Lucky's mansion. It reads coordinates and room names from a file, scales up the rectangles representing the rooms, adds borders to the rectangles, and labels each room with its name. The program generates a graphical representation of the mansion blueprint. It follows the MVC architecture design principles. The model, view, and controller components interact through well-defined interfaces. When a player performs an action, such as moving or picking up an item, the Controller interprets the input and communicates with the Model to execute the action. The Model processes the action, updates the game state, and informs the Controller of any changes. The Controller then updates the View to reflect the changes in the game state. For example, if a player moves to a different room, the View updates to display the new room's visuals. The Model handles the game's internal logic, the View presents the game to players, and the Controller manages user interactions and communicates between the Model and View. 



### List of Features

1. Read coordinates and room names from a file.
2. Display the mansion blueprint in a graphical window.
3. Add computer-controlled player to the game.
4. Add human-controlled player to the game.
5. Move the player to a room.
6. Pick an item from the room.
7. Display the information about the room.
8. Display the information about the player.
9. Implements MVC architecture.
10. Give number of turns to the players.
11. Attack the target with an item.
12. Attack the target by poking in the eye without item.
13. Provide hints about the surroundings in the mansion at the start of the game.
14. Move the pet character in the mansion.
15. The pet character adds spice to the game by manipulating the room visibility.


### How to Run

To run the jar File:
1. Open command prompt and change the directory to the destined location and type 'java -jar filename.jar args'.
2. Example: (java -jar Milestone4.jar ./mansion.txt 15) 15 is sent as command line arguments to denote the number of turns.

### How to Use the Program

Driver class has the main method so it acts as a starting point of the game. It will give control to the controller class. The controller class then executes the user input and perform desired operations user input operations. For example, if user wants to play the game, they can select the number of players, choice of actions until the maximum number of turns.



### Example Runs

1. res/ExampleRun1 playerInfo Milestone2.txt - This is the example run to print the player information.
2. res/ExampleRun2 spaceInfo Milestone2.txt - This is the example run to print the room information.
3. res/ExampleRun3 movePlayer Milestone2.txt - This is the example run to demonstrate the move the player from one location to other location within the grid.
4. res/ExampleRun4 pickItem.txt - This is the example run to demonstrate item picked by a player.
5. res/ExampleRun.txt - This is the example run to demonstrate the moves made by a human-controlled player in Milestone 3.
6. res/ExampleRunAdjacentPet.txt -This is the example run to demonstrate the moves made when the pet is in a neighboring space in the mansion.
7. res/ExampleRunComputerControlledPlayer.txt - This is the example run to demonstrate the moves made by a computer-controlled player.
8. res/ExampleRunComputerPlayerWin.txt - This is the example run where the computer-controlled player wins the game.
9. res/ExampleRunHumanPlayerWins.txt - This is the example run where the human-controlled player wins the game.
10. res/ExampleRunNoWinner.txt - This is the example run where no one wins the game and the game exits gracefully.
11. res/ExampleRunWanderingPet.txt - This is the example run where the updated feature, moving the pet can be seen.



### Design/Model Changes

1. Controller class is included in Milestone 2 and now the program is more structured. 
2. The programs are declared in an elaborate fashion. 
3. Players have been added to the grid and necessary operations are also implemented in respective methods.
4.  The GUI of the player has been done.

Changes made in Milestone 3:
1. All the functionalities of Milestone 1 and Milestone 2 are imported.
2. Controller class makes use of Stack data structure for storing all the necessary actions performed by the players.
3. Added ActionInterface consisting of all the user-controlled actions.
4. Attacking player is introduced with a new class AttackPlayer which implements ActionInterface.
5. Added separate interface for Rooms class, Item class, Player class, Random class, Pet class respectively.
6. Random moves are made by the computer-controlled player.
7. Picking an item is introduced with a new class PickItem which implements ActionInterface.
8. Moving a pet is introduced with a new class MovePet which implements ActionInterface.

Changes made in Milestone 4:
1.  The Controller class implements both PlayerCommandInterface and ControllerInterface. These interfaces likely define methods for different aspects of the game control, promoting a more modular and structured design.
2.  The playGame method expects an instance of MansionBoardGameViewInterface as a parameter. This suggests that the Controller is interacting more directly with the view layer, possibly adhering to the principles of MVC (Model-View-Controller) architecture.
3.  The constructor of the Controller class takes in the model, numberOfTurns, and file as parameters, indicating a move towards dependency injection. This approach allows for easier testing and flexibility in choosing dependencies.
4.  The Controller class now interacts more closely with the view layer, indicating a more interactive and dynamic user experience. This aligns with the idea of the Controller serving as an intermediary between the Model and the View.
![RevisedMilestone4PreliminaryDesign drawio](ttps://github.com/CS5010Feinberg/cs5010project-rajasrp/assets/132959895/86e40ff4-93e7-46a8-8cd6-f8a9775f65f0)

### Assumptions

1. The input file (`mansion.txt`) follows a specific format with coordinates and names of rooms provided in each line.
2. The target character is confined to move only inside rooms.
3. Each item has a damage value point which will reduce the health of the target character.
4. The neighboring rooms are visible through the active room.
5. A player can move within the grid only one step at a time.
6. The player starts from a location taken from user input.
7. Target character moves sequentially one turn at a time.
8. The spaces in the mansion and its items are fixed.

Assumptions as of Milestone 3:
1. Adding the players is done at the start of the game by the Controller class.
2. Players are added to a specified location with a specific item list limit.
3. Players cannot attack each other.

Assumptions as of Milestone 4:
1. The computer-controlled player starts the move from the Armory room.
2. Players can pick the start room position in the mansion.
3. Players have the liberty to choose their own item limit.
4. The maximum number of players added to the game is 9.
5. The maximum limit of items for a player to carry is 10.
### Limitations
The operations are limited to look around, pick item, attack target character, move pet character, move player to the neighboring room.


### Citations
[1] ArrayList-https://www.w3schools.com/java/java_arraylist.asp#:~:text=The%20ArrayList%20class%20is%20a,to%20create%20a%20new%20one).
[2] Depth First Search - https://www.simplilearn.com/tutorials/data-structure-tutorial/dfs-algorithm#:~:text=The%20depth%2Dfirst%20search%20or,far%20as%20possible%20before%20backtracking.
[3] BufferedImage - https://www.tutorialspoint.com/java_dip/java_buffered_image.htm




