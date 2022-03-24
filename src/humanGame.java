import java.util.Scanner;
/*
This class implements human logic. Here input from the console is
taken and commands are executed accordingly. It is later called
in console to play the game.
 */
public class humanGame {
    public static boolean humanPlay(){
        boolean flag = false;
        Scanner options = new Scanner(System.in);
        String command = options.next();
        /*If it is a play, the game moves in this direction
        It asks for number of domino, left or right and rotate or not
        After all the answer, piece from human deck is placed in a board.
        */
        if ("p".equals(command) && (board.humanDeck.size()!=0)) {
            System.out.println("Which domino?");
            int domino = options.nextInt();
            if((domino<0) || (domino>board.humanDeck.size()-1)) {
                System.out.println("Please enter the number within the rage" +
                        " starting from Zero!");
                return true;
            }
            System.out.println("Left or Right? (l/r)");
            String leftOrRight = options.next();
            System.out.println("Rotate first? (y/n)");
            String rotation = options.next();
            if (rotation.equals("y")) {
                if (leftOrRight.equals("l")) {
                    if (board.gameBoard.size() == 0) {
                        board.gameBoard.addFirst(dominoes.rotate
                                (board.humanDeck.get(domino)));
                        System.out.println("Playing " + "[" +
                                board.gameBoard.getFirst().getL() + " " +
                                board.gameBoard.getFirst().getR() +
                                "]" + " at left");
                        board.humanDeck.remove(domino);
                        // flag = false;
                    } else {
                       // dominoes temp = dominoes.rotate(board.humanDeck.get(domino));
                        board.gameBoard.addFirst(dominoes.rotate(board.humanDeck.get(domino)));
                        if (board.checkPieceLeft(board.gameBoard)) {
                            System.out.println("Playing " + "[" +
                                    board.gameBoard.getFirst().getL() + " " +
                                    board.gameBoard.getFirst().getR() + "]" + " at left");
                            board.humanDeck.remove(domino);
                        } else {
                            System.out.println("Can't play " + "[" +
                                    board.gameBoard.getFirst().getL() + " " +
                                    board.gameBoard.getFirst().getR() + "]" + " at left!");
                            board.gameBoard.removeFirst();
                            flag = true;
                        }
                    }
                } else if(leftOrRight.equals("r")) {
                    if (board.gameBoard.size() == 0) {
                        board.gameBoard.addLast(dominoes.rotate
                                (board.humanDeck.get(domino)));
                        System.out.println("Playing " + "[" +
                                board.gameBoard.getLast().getL() + " " +
                                board.gameBoard.getLast().getR() + "]" + " at right");
                        board.humanDeck.remove(domino);
                        // flag = false;
                    } else {
                        board.gameBoard.addLast(dominoes.rotate
                                (board.humanDeck.get(domino)));
                        if (board.checkPieceRight(board.gameBoard)) {
                            System.out.println("Playing " + "[" +
                                    board.gameBoard.getLast().getL() + " " +
                                    board.gameBoard.getLast().getR() + "]" + " at right");
                            board.humanDeck.remove(domino);
                        } else {
                            System.out.println("Can't play " + "[" +
                                    board.gameBoard.getLast().getL() + " " +
                                    board.gameBoard.getLast().getR() + "]" + " at right!");
                            board.gameBoard.removeLast();
                            flag = true;
                        }
                    }
                }
                else{
                    System.out.println("Invalid direction!");
                    return true;
                }
            } else if(rotation.equals("n")) {
                if (leftOrRight.equals("l")) {
                    if (board.gameBoard.size() == 0) {
                        board.gameBoard.addFirst(board.humanDeck.get(domino));
                        System.out.println("Playing " + "[" +
                                board.gameBoard.getFirst().getL() + " " +
                                board.gameBoard.getFirst().getR() + "]" + " at left");
                        board.humanDeck.remove(domino);
                        // flag=false;
                    } else {
                        board.gameBoard.addFirst(board.humanDeck.get(domino));
                        if (board.checkPieceLeft(board.gameBoard)) {
                            System.out.println("Playing " + "[" +
                                    board.gameBoard.getFirst().getL() + " " +
                                    board.gameBoard.getFirst().getR() + "]" + " at left");
                            board.humanDeck.remove(domino);
                        } else {
                            System.out.println("Can't play " + "[" +
                                    board.gameBoard.getFirst().getL() + " " +
                                    board.gameBoard.getFirst().getR() + "]" + " at left!");
                            board.gameBoard.removeFirst();
                            flag = true;
                        }
                    }
                } else if(leftOrRight.equals("r")) {
                    if (board.gameBoard.size() == 0) {
                        board.gameBoard.addLast(board.humanDeck.get(domino));
                        System.out.println("Playing " + "[" +
                                board.gameBoard.getLast().getL() + " " +
                                board.gameBoard.getLast().getR() + "]" + " at right");
                        board.humanDeck.remove(domino);
                        // flag=false;
                    } else {
                        board.gameBoard.addLast(board.humanDeck.get(domino));
                        if (board.checkPieceRight(board.gameBoard)) {
                            System.out.println("Playing " + "[" +
                                    board.gameBoard.getLast().getL() + " " +
                                    board.gameBoard.getLast().getR() + "]" + " at right");
                            board.humanDeck.remove(domino);
                        } else {
                            System.out.println("Can't play " + "[" +
                                    board.gameBoard.getLast().getL() + " " +
                                    board.gameBoard.getLast().getR() + "]" + " at right!");
                            board.gameBoard.removeLast();
                            flag = true;
                        }
                    }
                }
                else {
                    System.out.println("Invalid direction!");
                    return true;
                }
            }else {
                System.out.println("Invalid rotation parameter!");
                return true;
            }
        } else if ("d".equals(command)) {
            /*
            If draw is the called, piece from is boneyard is drawn and put in human deck
            If human already matching piece, it won't let us draw.
             */
            if (board.gameBoard.size() == 0) {
                System.out.println("Can't draw from " +
                        "boneyard at the start of the game!");
            }
                else if(board.checkMatch(board.gameBoard,
                    board.humanDeck)){
                    System.out.println("Can't draw from " +
                            "boneyard when you have a playable domino!");
                }
            else {
                board.drawFromBoneyard(board.humanDeck);
                System.out.print("Tray: [");
                gamePlay.printBoard(board.humanDeck);
                System.out.println("]");
            }
            flag = true;
        } else if ("q".equals(command)) {
            System.out.println("Sorry to see you go. Maybe next time!");
            System.exit(0);
        } else {
            System.out.println("Invalid Input!");
            flag = true;
        }
        return flag;
    }
}
