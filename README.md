This is project-2 domino game, can be play in two versions.

Console verison:
//dominoes class creates dominoes object with left an right value accessible from required methods.

//board class initialize the boneyard class and distributes the dominoes pieces for human and computer
Here draw from boneyard metho is implemented, also winner method determines the winner
Checkmatch and two other methods to check the playable domino scanning through whole deck and
for each piece respectively
Here Linked list is used to store the dominoes--> created by calling the dominoes class
I used linked list as the data manipulation is easier in the linked list.
Also they are static so that it is can be accessed from required class.

//gamePlay class is where console version of game is played
It runs game in while loop till the boneyard is empty and winner is detected.
It calls humangame and computergame class simultaneously.
Board is also printed in this class by printBoard.

//humanGame class implements game logic for human
Takes the input from the user using scanner
With the correct set of instructions, i.e answer to the questions asked,
we can make a human move, if the move is valid.
Valid move is checked by comparing the each ends of the board and the piece want to play.
Certain set of message is displayed if the arguments are wrong.
It also drawfrom boneyard if we have no playable piece and as per instruction from player
It user wants to quit, they can quit by pressing q.

//computerGame class implements computer logic.
Here computer deck is sorted at first in the order of decreasing order sum of left and right values of each sides of domino
so that if it has multiple matching domino, best domino is played. We need to get rid of large domino,
so by sort at the begining it helps.
Computer compares each end of the perfect domino with the board and throws if matches.

GUI Version:
Game play:
//Right mouse click in the particular domino to rotate
//Left click on the domino we want to play and left click on the matching end of the board to place the domino
//Draw from boneyard, if no piece to play
//Exit button to quit the game

//dominoesGUI class implements all the dominoes gui version
Here display is built on one Vbox and multiple hbox
It implements the same logic of console version.
Board and deck are diplayed simulateously.
Mouse click is used to play game responding to the action event.