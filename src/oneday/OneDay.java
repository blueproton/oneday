/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package oneday;

import java.net.MalformedURLException;
import java.net.URL;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author tim
 */
public class OneDay extends Application {
    
        
    private final static String PATH_TO_SPLASH_SCREEN="file:///C:\\Users\\tim\\Desktop\\images\\header.jpg";
    private final static String PATH_TO_SOUND="file:///C:\\Users\\tim\\Desktop\\images\\Pigs.mp3";
    private final static int HSIZE = 500;
    private final static int VSIZE = 400;
    
    private SoundManager soundManager;
    
    @Override
    public void start(Stage primaryStage) {
          this.soundManager = new SoundManager();
        
        try {
            soundManager.loadSound(new URL(PATH_TO_SOUND));
        } catch (MalformedURLException ex) {
            System.out.println("Unknown URL for sound "+ex.getMessage());
        }
        
        this.soundManager.playSound();
        
        BorderPane root = new BorderPane();
        HBox hbox = createTitleBox();
        root.setTop(hbox);
        root.setBottom(createButtonBar());
        root.setCenter(createSplashScreenImage());

        
        Scene scene = new Scene(root, HSIZE, VSIZE);
        
        primaryStage.setTitle("Title Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
     private ImageView createSplashScreenImage() {
        Image image = new Image(PATH_TO_SPLASH_SCREEN);
        ImageView iv = new ImageView();
        iv.setImage(image);
        
        return iv;
    }
    
    private void shutdown() {
        
        this.soundManager.shutdown();
    }
    
    private HBox createButtonBar() {
        HBox hbox = new HBox();
        
        Button startButton = new Button();
        startButton.setText("Start");
        
        Button helpButton = new Button();
        helpButton.setText("Help");
        
        Button exitButton = new Button();
        exitButton.setText("Exit");
        
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Shutting Down");
                shutdown();
                System.exit(0);
            }
        });
        
        
        hbox.getChildren().addAll(startButton, helpButton, exitButton);
        
        return hbox;
    }
    
    private HBox createTitleBox() {
        HBox hbox = new HBox();
        
        Text t = new Text(10,VSIZE, "WELCOME");
        t.setFont(new Font(20));
        t.textAlignmentProperty().set(TextAlignment.CENTER);
        hbox.setAlignment(Pos.CENTER);
        
        hbox.getChildren().add(t);
        return hbox;

    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
