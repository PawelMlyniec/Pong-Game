package ViewController;

import Database.DatabaseConnect;
import Model.Window;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Controller {
   private Game gameView ;

    private static Logging loggingScene;
    private Window window;
    private static Stage stage;
    private Records records;

    private String playerOneName;
    private String playerTwoName;
    private DatabaseConnect databaseConnect;

    /**
     * @author Paweł Młyniec
     * @version 1.0
     * Controller contains view i model
     */
    
    public Controller()throws Exception{
        gameView = new Game();


        window = new Window();
        gameView.isPaused = false;
        gameView.init(window);
    }

     public void logging(Stage newStage)throws Exception{
        loggingScene = new Logging();
        stage = newStage;
        stage.setTitle("Pong");
        stage.setScene(loggingScene.getScene());
        stage.show();
    }



    private void endGame(){
        if(!gameView.isPaused){
            gameView.timeline.stop();
            gameView.isPaused = true;
        }
        else
            stage.setScene(loggingScene.getScene());
        setScore();
    }

    private void startGame(){
        if(!gameView.settings.isStart()){
            gameView.settings.setStart(true);
            gameView.startTime = System.nanoTime();

        }

        if(gameView.isPaused){
            gameView.timeline.play();


            setScore();
        }

        gameView.isPaused = false;

    }

    private void setScore(){
         if(gameView.settings.getScore1()>=gameView.settings.getEndingScore()||gameView.settings.getScore2()>=gameView.settings.getEndingScore()){
            String tempName = playerTwoName;

            try {
                if(!gameView.settings.isTwoPlayers())
                    tempName = "Computer";
                databaseConnect = new DatabaseConnect(playerOneName,tempName,gameView.gamingTime,gameView.settings.getScore1(),gameView.settings.getScore2());
                databaseConnect.run();
                databaseConnect.getNRecords(50);

            } catch (SQLException ex) {
                System.out.println("Error JavaAppJDBC: " + ex.getMessage());
            }
            gameView.settings.setScore1(0);
            gameView.settings.setScore2(0);
            gameView.gamingTime = 0;
        }




    }

    @FXML
    private TextField userOneTextField;

    @FXML
    private TextField userTwoTextField;

    @FXML
    private Text alert;

     @FXML
     private void initialize(){}
     @FXML
     private void handleHighScore() throws Exception{
         try {
             databaseConnect = new DatabaseConnect();
             records = new Records(stage.getWidth(), stage.getHeight(), databaseConnect.getNRecords(50));
             records.init();
             stage.setScene(records.getScene());
             records.button.setOnAction(ActionEvent -> {stage.setScene(loggingScene.getScene());});
             stage.show();
         }catch (Exception e){ System.out.println("NO connection");}



     }

    @FXML
    protected void handleStartButtom(){

         playerOneName = userOneTextField.getText();
        playerTwoName = userTwoTextField.getText();

        if(playerOneName.isEmpty()){
            alert.setVisible(true);
            return;
        }
        else
            alert.setVisible(false);

        userOneTextField.setText(playerOneName);
        userTwoTextField.setText(playerTwoName);
        stage.setScene(gameView.gameScene);
        gameView.gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                switch (event.getCode()) {
                    case UP:    gameView.playerOnePaddle.goUp = true; break;//TODO gety i sety
                    case DOWN:  gameView.playerOnePaddle.goDown = true; break;
                    case W:    gameView.playerTwoPaddle.goUp = true; break;//TODO gety i sety
                    case S:  gameView.playerTwoPaddle.goDown = true; break;
                    case SPACE:  startGame();break;
                    case ESCAPE: endGame(); break;
                    default:break;
                }
            }
        });

        gameView.gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    gameView.playerOnePaddle.goUp = false; break;
                    case DOWN:  gameView.playerOnePaddle.goDown = false; break;
                    case W:    gameView.playerTwoPaddle.goUp = false; break;//TODO gety i sety
                    case S:  gameView.playerTwoPaddle.goDown = false; break;
                }
            }
        });

        gameView.timeline.play();

     }

    @FXML
    public void handleMenuSizeSmall( ){
        gameView.playerOnePaddle.setPaddleHight(30);
        gameView.playerTwoPaddle.setPaddleHight(30);
    }

    @FXML
    void handleMenuSizeMedium( ){
         System.out.println(5);
        gameView.playerOnePaddle.setPaddleHight(60);
        gameView.playerTwoPaddle.setPaddleHight(60);
    }
    @FXML
    void handleMenuSizeBig( ){
        System.out.println(5);

        gameView.playerOnePaddle.setPaddleHight(100);
        gameView.playerTwoPaddle.setPaddleHight(100);
    }
    @FXML
    void handleMenuOnePlayer(){
        System.out.println(5);
        userTwoTextField.setDisable(true);
        gameView.settings.setTwoPlayers(false);
    }
    @FXML
    void handleMenuTwoPlayer(){
        gameView.settings.setTwoPlayers(true);
        userTwoTextField.setDisable(false);
    }

}
