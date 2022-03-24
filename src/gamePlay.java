/*Saphal Karki
  CS351 - Project 2
  2/28/2021
 */
import java.util.LinkedList;
/*
This class is console version of the game. Here human and computer plays
simultaneously and the board is printed in the console.
 */
public class gamePlay {
    /*It prints the Console deck in the console.
    */
    public static void printBoard(LinkedList<dominoes> deck){
        for (int i = 0; i< deck.size();i++){
            System.out.print("["+deck.get(i).getL()+" "+deck.get(i).getR()+"]");
        }
    }
    public static void main(String[] args) {
        //Flag to indicate game is not over
        boolean notGameOver = true;
        boolean humanPlay;
        boolean computerPlay;
        System.out.println("Dominoes!");
        board.initializeBoard();
        // while game is not over both human and computer play
        while (notGameOver){
            System.out.println("Computer has "+ board.computerDeck.size()+" dominoes");
            System.out.println("Boneyard contains "+ board.boneyard.size()+" dominoes");
            System.out.print("Tray: [");
            printBoard(board.humanDeck);
            System.out.println("]");
            humanPlay=true;
            computerPlay=true;
            //Human's turn
            while (humanPlay) {
                //If winner, game is stopped and message is displayed.
                //Otherwise game is played
                if(board.winner()){
                    computerPlay=false;
                    notGameOver=false;
                }else {
                    System.out.println("Human's turn");
                    System.out.println("[p] Play Domino");
                    System.out.println("[d] Draw from boneyard");
                    System.out.println("[q] Quit");
                    if (board.humanDeck.size() == 0) {
                        System.out.println("No pieces to play! Draw from boneyard");
                        humanPlay = true;
                    }
                    if (humanGame.humanPlay()) {
                        humanPlay = true;
                    } else {
                        humanPlay = false;
                    }
                }
            }
            System.out.print("Board :");
            printBoard(board.gameBoard);
            System.out.println("");
            //Here computer plays
            while (computerPlay) {
                //If computer is winner, it is declared here
                if (board.winner()) {
                    computerPlay = false;
                    notGameOver = false;
                } else {
                    computerGame.arrangeDeck(board.computerDeck);
                    System.out.println("Computer's turn");
                    //If computer can make a move it plays else boneyard is called
                    if (computerGame.computerPlay(board.gameBoard, board.computerDeck)) {
                        computerPlay = false;
                    } else {
                        //Here computer calls boneyard if the condition is not matched.
                        System.out.println("Computer drawing from Boneyard");
                        board.drawFromBoneyard(board.computerDeck);
                        computerPlay = true;
                    }
                }
            }
            System.out.print("Board :");
            printBoard(board.gameBoard);
            System.out.println("");
        }
    }
}