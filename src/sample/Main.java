//Nick House Whack-A-Mole

package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;


public class Main extends Application {

    int HITS = 0;
    double rate;
    Controller controller = new Controller();

    public Main() throws IOException { }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Window Title of Game
        primaryStage.setTitle("Whac-A-Mole");

        //Big Title
        HBox Title = new HBox();
        Title.setPadding(new Insets(10));
        Title.setStyle("-fx-background-color: #9EC677; "+
                "-fx-border-color: rgba(0, 0, 0, 1); -fx-border-width: 1px;");

        //Game Title With Drop Shadow Effect
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

        Text title = new Text();
        title.setEffect(ds);
        title.setCache(true);
        title.setFill(Color.MAROON);
        title.setText("Whack - A - Mole");
        title.setFont(Font.font(null, FontWeight.BOLD, 70));

        //Position Game Title and Add
        Title.getChildren().add(title);
        Title.setAlignment(Pos.CENTER);

        //Create Top display Box
        //Contain Hits, Misses and Current Speed Level
        HBox TopDisplay = new HBox(30);
        TopDisplay.setPadding(new Insets(30));
        TopDisplay.setStyle("-fx-background-color: #101511; " +
                "-fx-border-color: rgba(0, 0, 0, 1); -fx-border-width: 1px;");

        //Create text boxes for Top Box
        Text Hits = new Text("Hits: ");
        Hits.setEffect(ds);
        Hits.setFill(Color.GREEN);
        Hits.setFont(Font.font(null, FontPosture.ITALIC, 40));

        //Create Label for Hits
        Label NumHits = new Label();
        NumHits.setText("0");
        NumHits.setPrefWidth(80);
        NumHits.setTextFill(Color.SLATEGREY);
        NumHits.setFont(Font.font(null, FontPosture.ITALIC, 40));

        //Create Text For Misses
        Text Misses = new Text("Misses: ");
        Misses.setEffect(ds);
        Misses.setFill(Color.RED);
        Misses.setFont(Font.font(null, FontPosture.ITALIC, 40));

        //Create Label to Display Misses
        Label NumMisses = new Label();
        NumMisses.setText("0");
        NumMisses.setPrefWidth(80);
        NumMisses.setTextFill(Color.SLATEGREY);
        NumMisses.setFont(Font.font(null, FontPosture.ITALIC, 40));

        //Create Speed Text
        Text Speed = new Text("Speed: ");
        Speed.setEffect(ds);
        Speed.setFill(Color.ORANGE);
        Speed.setFont(Font.font(null, FontPosture.ITALIC, 40));

        //Create Label for Speed
        Label SpeedLabel = new Label();
        SpeedLabel.setText(""+controller.getSpeed());
        SpeedLabel.setPrefWidth(80);
        SpeedLabel.setTextFill(Color.SLATEGREY);
        SpeedLabel.setFont(Font.font(null, FontPosture.ITALIC, 40));

        //Add Text To Top Display
        TopDisplay.getChildren().addAll(Hits, NumHits, Misses, NumMisses, Speed, SpeedLabel);
        TopDisplay.setAlignment(Pos.CENTER_LEFT);

        //Create Bottom Display Box
        //Contain Current Score Reset Button and High Score
        HBox BottomDisplay = new HBox(40);
        BottomDisplay.setPadding(new Insets(20));
        BottomDisplay.setStyle("-fx-background-color: #101511;; " +
                "-fx-border-color: rgba(0, 0, 0, 1); -fx-border-width: 1px;");

        //Create Reset Button
        Button reset = new Button("Reset");
        reset.setPrefWidth(90);
        reset.setPrefHeight(30);
        reset.setFont(Font.font(null, FontWeight.BOLD, 20));

        //Create Current Score
        Text score = new Text("Score: ");
        score.setEffect(ds);
        score.setFill(Color.RED);
        score.setFont(Font.font(null, FontPosture.ITALIC, 30));

        //Create Label for Score
        Label CScore = new Label();
        CScore.setText("0");
        CScore.setPrefWidth(120);
        CScore.setTextFill(Color.SLATEGREY);
        CScore.setFont(Font.font(null, FontPosture.ITALIC, 30));

        //Create High Score
        Text Hscore = new Text("High Score: ");
        Hscore.setEffect(ds);
        Hscore.setFill(Color.GREEN);
        Hscore.setFont(Font.font(null, FontPosture.ITALIC, 30));

        //Create Label for Score
        Label HScore = new Label();
        HScore.setText(""+controller.getHighScore());
        HScore.setPrefWidth(120);
        HScore.setTextFill(Color.SLATEGREY);
        HScore.setFont(Font.font(null, FontPosture.ITALIC, 30));

        //Add Text to Bottom Display
        BottomDisplay.getChildren().addAll(score, CScore, Hscore, HScore, reset);
        BottomDisplay.setAlignment(Pos.CENTER_LEFT);


        //Set Spacing and layout of Whack A Mole Game Interface
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(7);
        grid.setVgap(7);
        grid.setStyle("-fx-background-color: #D6E4E2; " +
                "-fx-border-color: rgba(0, 0, 0, 1); -fx-border-width: 1px;");

        //Create Displayed layout of game
        BorderPane border = new BorderPane();
        border.setTop(TopDisplay);
        border.setCenter(grid);
        border.setBottom(BottomDisplay);

        //Outer Boarder for Title
        BorderPane Outer = new BorderPane();
        Outer.setCenter(border);
        Outer.setTop(Title);

        //Set Size stage
        primaryStage.setScene((new Scene(Outer)));
        //Disallow resizing of window
        primaryStage.setResizable(false);

        //Ground Hog Image
        ImageView GroundHog = new ImageView(new Image( new FileInputStream("src/Groundhog.jpg"))) ;
        //Format Image Size
        GroundHog.setFitHeight(50);
        GroundHog.setFitWidth(70);

        //Empty Hole Image
        ImageView grass = new ImageView(new Image( new FileInputStream("src/grass.jpg"))) ;
        //Format Image Size
        grass.setFitHeight(50);
        grass.setFitWidth(70);

        //Create Buttons for UI
        Button[][] button = new Button[10][10];

        //Set Default Background Images
        Image image = new Image(new FileInputStream("src/Groundhog.jpg"));
        BackgroundImage bImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(70, 50, true, true, true, false));
        Background backGround = new Background(bImage);

        //Create Timer for Mole and Score Difficulty
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(2), e -> {
                    grid.getChildren().remove(GroundHog);
                    controller.generateRandom();
                    try { controller.CheckHS(); } catch (IOException ex)        //Autosave HighScore
                    { ex.printStackTrace(); }
                    grid.add(GroundHog, controller.getX(), controller.getY());
                    controller.setMisses(1);
                    controller.ZeroScore();
                    CScore.setText(""+controller.getScore());
                    HScore.setText(""+controller.getHighScore());
                    NumMisses.setText(""+controller.getMisses());
                })
        );

        final double[] duration = {timeline.getCycleDuration().toSeconds()};

        //Create Handler for Reset Button
        EventHandler<MouseEvent> resetHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //Short Pause before Next GH
                try { Thread.sleep(300); }
                catch (InterruptedException e) { e.printStackTrace(); }
                try { controller.CheckHS(); } catch (IOException ex)        //Autosave HighScore
                { ex.printStackTrace(); }
                //Reset All Stats and Display to UI
                controller.Reset();
                grid.getChildren().remove(GroundHog);
                grid.add(GroundHog, controller.getX(), controller.getY());
                NumHits.setText(""+controller.getHits());
                NumMisses.setText(""+controller.getMisses());
                SpeedLabel.setText(""+controller.getSpeed());
                CScore.setText(""+controller.getScore());
                HScore.setText(""+controller.getHighScore());
                //Reset Timer Speed and start timer over
                timeline.setRate(1);
                timeline.playFromStart();
                rate = 0;
            }
        };

        reset.setOnMouseClicked(resetHandler);


        //Creating the mouse event handler For Hitting/Missing Mole
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                //Check for Mole Hit or Not
                if(mouseEvent.getTarget()== GroundHog){
                    //Get current milliseconds to convert to Score
                    controller.setScore((int) timeline.getCurrentTime().toMillis());
                    HITS = controller.setHits(1);                                       //increment Hit
                    NumHits.setText(""+HITS);                                           //Display Hit number
                    grid.getChildren().remove(GroundHog);                               //remove replace GH
                    controller.generateRandom();
                    grid.add(GroundHog, controller.getX(), controller.getY());
                    SpeedLabel.setText(""+controller.getSpeed());                       //Display Speed
                    try { controller.CheckHS(); }                                       //Autosave HighScore
                    catch (IOException e) { e.printStackTrace(); }
                    CScore.setText(""+controller.getScore());                           //Display Current Score
                    controller.setSpeedCounter();                                       //Increment Speed Counter
                    //Gradually Increase speed with each hit
                    rate += .1;
                    timeline.setRate(rate);
                    timeline.playFromStart();                                           //Reset Timer
                }
            }
        };

        //Create event Handler call for Mole Button
        GroundHog.setOnMouseClicked(eventHandler);

        //Create 10x10 Grid of buttons and give backgrounds
        for(int i = 0; i < 10 ; ++i){
            for(int j = 0; j < 10; ++j){

                //Create Instance of Button and Format
                button[i][j] = new Button();
                button[i][j].setPrefHeight(50);
                button[i][j].setPrefWidth(70);

                //Set Default Background Images
                Image image2 = new Image(new FileInputStream("src/grass.jpg"));
                BackgroundImage bImage2 = new BackgroundImage(image2, BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                        new BackgroundSize(70, 50, true, true, true, false));

                Background backGround2 = new Background(bImage2);
                button[i][j].setBackground(backGround2);

                //Add new Button to Grid
                grid.add(button[i][j], j, i);


            }
        }

        //Show First Mole
        grid.add(GroundHog, controller.getX(), controller.getY());

        primaryStage.show();
        timeline.playFromStart();
        timeline.setOnFinished(e-> {timeline.playFromStart();});

    }

    public static void main(String[] args) { launch(args); }

    public class Controller {

        //Variables for Game
        int x, y, speedCounter;
        int hits, misses, speed;
        int score, highScore;
        double rate;
        String filename = "WAM_HighScore";
        File file = new File(filename);

        //Default Constructor
        Controller() throws IOException {
            //Initialize Game Condition
            Reset();
            InitializeHSFile();
        }

        //Reset Game Stats
        void Reset(){
            //Save high score
            generateRandom();
            hits = 0;
            misses = 0;
            score = 0;
            speed = 1;
            speedCounter = 0;
        }

        //Open High Score File or Create New File if Needed
        void InitializeHSFile() throws IOException {
            if(file.createNewFile()){ //Check for HS File, create one if not
                try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename))){
                    dos.writeInt(0);
                }
            }
            else //If HS File exists Update HighScore
                { try(DataInputStream dis = new DataInputStream(new FileInputStream(filename))){
                int ch = dis.readInt();
                highScore = ch;
            }
        }
      }

        //getter for HighScore
        int getHighScore(){
            return highScore;
        }

        //Check Score against HS
        void CheckHS() throws IOException {
            InitializeHSFile();
            if(score > highScore){ saveHighScore(); } }

        //Save HighScore Value to File
        void saveHighScore() {
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename))) {
                dos.writeInt(score);
            } catch (IOException e) { e.printStackTrace(); }
        }

        //Get X Value
        int getX(){ return x; }

        //Get Y Value
        int getY(){ return y; }

        //Get Misses
        int getMisses(){ return misses; }
        //Set Misses
        int setMisses(int misses) { this.misses += misses; return this.misses; }

        //Get Hits
        int getHits(){ return hits; }
        //Set Hits
        int setHits(int hits){ this.hits += hits; return this.hits; }

        //Get Score
        int getScore(){ return score; }
        //Set Score
        void setScore(int score){ this.score += score; }
        //Set Score to Zero
        void ZeroScore(){ score = 0;  }

        //Speed Adjustments
        int getSpeed(){ return speed; }
        void pushSpeed(){ this.speed += 1; this.rate += .35; speedCounter = 0; }
        double getRate(){ return rate; }
        void setSpeedCounter(){ speedCounter += 1;
        if(speedCounter == 3){ pushSpeed(); }
        }
        int getSpeedCounter(){ return speedCounter; }
        void resetSpeed(){ speed = 1; }

        //generate Random Coordinates
        void generateRandom(){
            x = (int)(Math.random()*10);
            y = (int)(Math.random()*10);
        }
    }
}



