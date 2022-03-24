import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/*
This is the GUI version of domino. Here methods from
different class are called in order to create playable domino.
 */
public class dominoesGUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    /*
    Hboxes are created for screen display.
     */
    private static final HBox humanHBox = new HBox(4);
    private static final HBox boardHBox1 = new HBox();
    private static dominoes pieces;
    private static int index;
    public static boolean humanWinner;
    private static final HBox boardHBox2 = new HBox();
    private static final VBox mainDisplay = new VBox();
    public static boolean firstClick;

    Label gameLabel = new Label();
    HBox hBox1 = new HBox();
    Label boneyard = new Label();
    HBox hBox2 = new HBox();
    Label computerDeck = new Label();
    Button draw = new Button();
    HBox hBox3 = new HBox();
    Label winner =new Label();
    HBox hBox4 =new HBox();
    Button exit = new Button("EXIT");
    HBox hBox5 = new HBox();
    HBox drawFromBoneyard = new HBox();
    /*
    This method is called to display the winner if boneyard is empty.
     */
    public void displayWinner(){
        if(humanWinner){
            winner.setText("Hurray!! You are the winner!");
        }
        else {
            winner.setText("Better luck next time! Computer rocks!");
        }
        winner.setFont(new Font(24));
        hBox4.getChildren().addAll(winner);
        hBox4.setAlignment(Pos.CENTER);
        draw.setDisable(true);
    }
    /*
    Here computer plays using computer game logic. If there is no playable domino,
    piece is drawn from boneyard.
     */
    public void computerPlay(){
        computerGame.arrangeDeck(board.computerDeck);
        boolean gameOn=true;
        while (gameOn){
            if(computerGame.computerPlay(board.gameBoard,
                    board.computerDeck)){
                pieces=board.gameBoard.getFirst();
                disPlayBoard();
                if (board.winner()){
                    displayWinner();
                }
                gameOn=false;
            }
            else if(board.boneyard.size()==0){
                gameOn=false;
            }
            else {
                board.drawFromBoneyard(board.computerDeck);
                pieces=board.gameBoard.getFirst();
                disPlayBoard();
                if (board.winner()){
                    displayWinner();
                }
                gameOn=true;
            }
        }
        if((board.checkMatch(board.gameBoard,board.humanDeck))){
            draw.setDisable(true);
        }else {
            draw.setDisable(false);
        }
    }
    /*
    This method displays the main game board. Here board is displayed in two
    different rows determining each move. Also the game label i.e.
    number of dominoes that boneyard and computer has is updated.
     */
    public void disPlayBoard(){
        boardHBox1.getChildren().clear();
        boardHBox2.getChildren().clear();
        Rectangle blank = new Rectangle(0,0,100,50);
        blank.setVisible(false);
        boardHBox2.getChildren().add(blank);

        hBox2.getChildren().clear();
        boneyard.setText("Boneyard has "+ board.boneyard.size() +" dominoes!");
        hBox2.getChildren().addAll(boneyard);

        hBox3.getChildren().clear();
        computerDeck.setText("Computer has "+ board.computerDeck.size()
                +" dominoes!");
        hBox3.getChildren().addAll(computerDeck);

        for (int i=0; i<board.gameBoard.size();i++){
            Image boardImage = new Image("file:resources/"+
                    board.gameBoard.get(i).getL()+"-"+board.gameBoard.get(i).getR()+".png");
            ImageView boardImageView = new ImageView(boardImage);
            boardImageView.setFitWidth(100);
            boardImageView.setFitHeight(100);
            boardImageView.setPreserveRatio(true);
            boardImageView.setVisible(true);
            VBox tempVbox = new VBox();
            tempVbox.setId(Integer.toString(i));
            /*
            This handles the mouse click at the end of the board.
            If the second click is detected at the each end of the board
            it performs the human side of operation i.e. looks for the
            matching piece and plays if found one.
             */
            boardImageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int pos=Integer.parseInt(tempVbox.getId());
                    /*
                    Handles left mouse click on left end of the board.
                     */
                    if(event.getButton() == MouseButton.PRIMARY &&
                            (pos==0) && firstClick){
                       board.gameBoard.addFirst(pieces);
                            if(board.checkPieceLeft(board.gameBoard)){
                                board.humanDeck.remove(index);
                                boardHBox1.getChildren().clear();
                                boardHBox2.getChildren().clear();
                                humanHBox.getChildren().clear();
                                disPlayBoard();
                                disPlayHuman();
                                if(board.boneyard.size()!=0) computerPlay();
                                if (board.winner()){
                                    displayWinner();
                                }
                            }else {
                                board.gameBoard.removeFirst();
                            }
                        firstClick=false;
                    }
                    /*
                    Handles left mouse click at the right end of the board
                     */
                    else if((event.getButton()==MouseButton.PRIMARY) &&
                            (pos==board.gameBoard.size()-1) && firstClick){
                        board.gameBoard.addLast(pieces);
                        if(board.checkPieceRight(board.gameBoard)){
                            board.humanDeck.remove(index);
                            boardHBox1.getChildren().clear();
                            boardHBox2.getChildren().clear();
                            humanHBox.getChildren().clear();
                            disPlayBoard();
                            disPlayHuman();
                            if(board.boneyard.size()!=0) computerPlay();
                            if (board.winner()){
                                displayWinner();
                            }
                        }else {
                            board.gameBoard.removeLast();
                        }
                        firstClick=false;
                    }
                }
            });
            tempVbox.setId(Integer.toString(i));
            tempVbox.getChildren().addAll(boardImageView);
            if(i%2==0) {
                boardHBox1.getChildren().addAll(tempVbox);
            }else {
                boardHBox2.getChildren().addAll(tempVbox);
            }
        }
    }
    /*
    This handles the display of human deck. It removes the pieces
    from human deck if needed. Also here pieced is rotated.
     */
    public void disPlayHuman(){
        humanHBox.getChildren().removeAll();
        for(int i=0;i<board.humanDeck.size();i++){
            Image tempDominoImage = new Image("file:resources/"+
                    board.humanDeck.get(i).getL()+"-"+board.humanDeck.get(i).getR()+".png");
            ImageView tempDominoImageView = new ImageView(tempDominoImage);
            tempDominoImageView.setFitWidth(100);
            tempDominoImageView.setFitHeight(70);
            tempDominoImageView.setPreserveRatio(true);
            tempDominoImageView.setVisible(true);
            VBox tempVbox = new VBox();
            if((board.gameBoard.size()!=0) && (board.checkMatch
                    (board.gameBoard,board.humanDeck))){
                draw.setDisable(true);
            }else {
                draw.setDisable(false);
            }
            tempVbox.setId(Integer.toString(i));
            /*
            Handles mouse click at the human deck.
             */
            tempDominoImageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int pos=Integer.parseInt(tempVbox.getId());
                    /*
                    Waits for second click and execute the operations
                     */
                    if(event.getButton() == MouseButton.PRIMARY){
                        if(board.gameBoard.size()==0){
                            board.gameBoard.addFirst(board.humanDeck.get(pos));
                            board.humanDeck.remove(pos);
                            humanHBox.getChildren().clear();
                            index=pos;
                            pieces=board.gameBoard.getFirst();
                            disPlayHuman();
                            disPlayBoard();
                            if(board.boneyard.size()!=0) computerPlay();
                        }
                        else{
                            firstClick=true;
                            index=pos;
                            pieces=board.humanDeck.get(pos);
                            disPlayBoard();
                        }
                    }
                    /*
                    If right click is detected, it rotates the piece in human deck
                    and updates the deck.
                     */
                    else if(event.getButton()==MouseButton.SECONDARY){
                        firstClick=false;
                        dominoes temp = board.humanDeck.get
                                (Integer.parseInt(tempVbox.getId()));
                        board.humanDeck.set(pos,dominoes.rotate(temp));
                        humanHBox.getChildren().clear();
                        disPlayHuman();
                    }
                }
            });
            tempVbox.getChildren().addAll(tempDominoImageView);
            humanHBox.getChildren().addAll(tempVbox);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        Game is initialized here
         */
        board.initializeBoard();
        /*
        Required panels and Hboxes are initiated
        Customized accordingly
         */
        gameLabel.setText("Dominoes");
        gameLabel.setFont(new Font(30));
        hBox1.getChildren().addAll(gameLabel);
        hBox1.setAlignment(Pos.CENTER);

        boneyard.setText("Boneyard has "+
                board.boneyard.size() +" dominoes!");
        boneyard.setFont(new Font(20));
        hBox2.getChildren().addAll(boneyard);
        hBox2.setAlignment(Pos.CENTER);

        computerDeck.setText("Computer has "+
                board.computerDeck.size() +" dominoes!");
        computerDeck.setFont(new Font(20));
        hBox3.getChildren().addAll(computerDeck);
        hBox3.setAlignment(Pos.CENTER);

        boardHBox1.setAlignment(Pos.CENTER);
        boardHBox2.setAlignment(Pos.CENTER);
        mainDisplay.setAlignment(Pos.CENTER);
        Rectangle blank = new Rectangle(0,0,100,50);
        blank.setVisible(false);
        boardHBox1.getChildren().add(blank);
        boardHBox2.getChildren().add(blank);
        mainDisplay.getChildren().addAll(boardHBox1,boardHBox2);

        disPlayHuman();
        humanHBox.setAlignment(Pos.CENTER);
        draw.setText("Draw from Boneyard");
        drawFromBoneyard.setAlignment(Pos.CENTER);
        drawFromBoneyard.getChildren().addAll(draw);
        draw.setDisable(true);
        /*
        Draw button is enable if needed
        i.e no piece to move else disabled
         */
        draw.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(board.boneyard.size()==0){
                    draw.setDisable(true);
                }
                else {
                    board.drawFromBoneyard(board.humanDeck);
                    humanHBox.getChildren().clear();
                    disPlayHuman();
                    disPlayBoard();
                    if (board.winner()){
                        displayWinner();
                    }
                }
            }
        });
        /*
        Exits the game
         */
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        hBox5.getChildren().addAll(exit);
        hBox5.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(40);
        /*
        Adds everything in the display.
         */
        vBox.getChildren().addAll(hBox1,hBox2,hBox3,
                mainDisplay,humanHBox,drawFromBoneyard,hBox5,hBox4);
        BorderPane screen = new BorderPane(vBox);
        Scene scene = new Scene(screen,1500,700,Color.SKYBLUE);
        primaryStage.setTitle("Dominoes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
