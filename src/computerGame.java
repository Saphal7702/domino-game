import java.util.LinkedList;
/*
This class implements computer logic. Here the computer plays the game.
If computer makes a move, it returns false otherwise returns true.
Main logic here is at the beginning the computer deck is sorted
in decreasing order of the sum of two sides of dominoes so that
when multiple matching piece is found, the biggest piece is played by computer.
 */
public class computerGame {
    //Here deck is arranged in the decreasing order of the sum of two sides of
    //dominoes of computer deck.
    public static void arrangeDeck(LinkedList<dominoes> list){
        for (int i = 0; i < list.size()-1; i++)
            for (int j = 0; j < list.size()-i-1; j++)
                if ((list.get(j).getL()+list.get(j).getR()) <
                        (list.get(j+1).getL()+list.get(j+1).getR()))
                {
                    dominoes temp = list.get(j);
                    list.set(j,list.get(j+1));
                    list.set(j+1,temp);
                }
    }
    //This checks for the playable domino and plays if found
    public static boolean computerPlay(LinkedList<dominoes> board,
                                       LinkedList<dominoes> deck){
        boolean flag = false;
        /*
        Check left and right of the domino piece with left and right of the board
        and when match found, it plays and the board is updated.
         */
        for (int i = 0; i< deck.size(); i++){
            if((deck.get(i).getL()==board.getFirst().getL()) ||
                    (deck.get(i).getL()==0) ||(board.getFirst().getL()==0)){
                board.addFirst(dominoes.rotate(deck.get(i)));
                System.out.println("Computer plays "+"["+board.getFirst().getL()
                        +" "+board.getFirst().getR()+"] "+"at left");
                deck.remove(i);
                flag=true;
                break;
            }
            else if((deck.get(i).getL()==board.getLast().getR()) ||
                    (deck.get(i).getL()==0) ||(board.getLast().getR()==0)){
                board.addLast(deck.get(i));
                System.out.println("Computer plays "+"["+board.getLast().getL()
                        +" "+board.getLast().getR()+"] "+"at right");
                deck.remove(i);
                flag=true;
                break;
            }
            else if((deck.get(i).getR()==board.getFirst().getL()) ||
                    (deck.get(i).getR()==0)){
                board.addFirst(deck.get(i));
                System.out.println("Computer plays "+"["+board.getFirst().getL()
                        +" "+board.getFirst().getR()+"] "+"at left");
                deck.remove(i);
                flag=true;
                break;
            }
            else if((deck.get(i).getR()==board.getLast().getR()) ||
                    (deck.get(i).getR()==0)){
                board.addLast(dominoes.rotate(deck.get(i)));
                System.out.println("Computer plays "+"["+board.getLast().getL()+
                        " "+board.getLast().getR()+"] "+"at right");
                deck.remove(i);
                flag=true;
                break;
            }else flag = false;
        }
        return flag;
    }
}
