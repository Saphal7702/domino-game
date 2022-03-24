import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
/*
This class has most of the elements used in a board.
It initializes the board and dominoes are distributed for human and computer.
Also piece from boneyard is drawn and winner is determined.
 */
public class board {
    public static LinkedList<dominoes> boneyard = new LinkedList<>();
    public static LinkedList<dominoes> humanDeck = new LinkedList<dominoes>();
    public static LinkedList<dominoes> computerDeck = new LinkedList<>();
    public static LinkedList<dominoes> gameBoard = new LinkedList<>();
    /*
    It initializes the board and deck for human and computer
     */
    public static void initializeBoard()
    {
        Random rand = new Random();
        for (int i=0;i<=6;i++){
            for(int j=i;j<=6;j++){
                boneyard.add(new dominoes(i,j));
            }
        }
        Collections.shuffle(boneyard);
        for (int h=0;h<7;h++){
            int r = rand.nextInt(27-h)+0;
            humanDeck.add(h, boneyard.get(r));
            boneyard.remove(r);
        }
        for (int h=0;h<7;h++) {
            int r = rand.nextInt(21 - h) + 0;
            computerDeck.add(h, boneyard.get(r));
            boneyard.remove(r);
        }
    }
    /*
    It draws from boneyard and and put the piece in particular deck.
     */
    public static void drawFromBoneyard(LinkedList<dominoes> list){
        dominoes piece= board.boneyard.getFirst();
        list.addLast(piece);
        board.boneyard.remove(piece);
    }
    /*
    Here winner is detected and certain message is displayed.
     */
    public static boolean winner(){
        int humanScore=0;
        int computerScore=0;
        if(board.boneyard.size()==0){
            for (int i=0;i<board.humanDeck.size();i++){
                humanScore+=(board.humanDeck.get(i).getL()+
                        board.humanDeck.get(i).getR());
            }
            for (int j=0;j<board.computerDeck.size();j++){
                computerScore+=(board.computerDeck.get(j).getL()+
                        board.computerDeck.get(j).getR());
            }
            if(humanScore<computerScore){
                dominoesGUI.humanWinner=true;
                System.out.println("Hurray! You are the winner.");
            }
            else if(computerScore<humanScore){
                System.out.println("Better luck next time! " +
                        "Programmer wrote strong algorithm.");
            }
            else {
                System.out.println("Better luck next time!" +
                        " Programmer wrote strong algorithm.");
            }
            return true;
        }
        else return false;
    }
    /*
    It is called to check if the board and the deck has common domino piece
    i.e. playable domino. If yes, we can't draw from boneyard.
     */
    public static boolean checkMatch(LinkedList<dominoes> board,
                                     LinkedList<dominoes> deck){
        boolean flag = false;
        for (int i = 0; i< deck.size(); i++){
            if((deck.get(i).getL()==board.getFirst().getL()) ||
                    (deck.get(i).getL()==0) ||(board.getFirst().getL()==0)){
                flag=true;
                break;
            }
            else if((deck.get(i).getL()==board.getLast().getR()) ||
                    (deck.get(i).getR()==0) ||(board.getLast().getR()==0)){
                flag=true;
                break;
            }
            else if(deck.get(i).getR()==board.getFirst().getL()){
                flag=true;
                break;
            }
            else if(deck.get(i).getR()==board.getLast().getR()){
                flag=true;
                break;
            }else flag = false;
        }
        return flag;
    }
    /*
    The following two method checks if there is a playable domino in each end.
    i.e. left and right end of the board
    If yes, returns true.
     */
    public static boolean checkPieceLeft(LinkedList<dominoes> gameBoard){
        boolean flag = false;
            if((gameBoard.getFirst().getR()==gameBoard.get(1).getL()) ||
                    (gameBoard.getFirst().getR()==0) || (gameBoard.get(1).getL()==0)){
                flag=true;
            }
        return flag;
    }

    public static boolean checkPieceRight(LinkedList<dominoes> gameBoard){
        boolean flag = false;
        if((gameBoard.getLast().getL()==gameBoard.get(
                (gameBoard.size()-2)).getR()) || (gameBoard.getLast().getL()==0) ||
                (gameBoard.get((gameBoard.size()-2)).getR()==0)){
            flag=true;
        }
        return flag;
    }
}
