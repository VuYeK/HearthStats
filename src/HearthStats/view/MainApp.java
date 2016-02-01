package HearthStats.view;/**
 * Created by VuYeK on 03.11.2015.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage) throws IOException {
        showRootLayout(primaryStage);
    }



    public void showRootLayout(Stage primaryStage) throws IOException {
        Parent rootLayout = FXMLLoader.load(getClass().getResource("RootLayout.fxml"));
        primaryStage.getIcons().add(new Image("http://i.imgur.com/2XYhhKJ.png"));
        primaryStage.setTitle("HearthStats");
        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.show();
    }


    public void showStatCharts() throws IOException {
        Parent rootLayout = FXMLLoader.load(getClass().getResource("Statistics.fxml"));
        Stage statStage = new Stage();
        statStage.getIcons().add(new Image("http://i.imgur.com/2XYhhKJ.png"));
        statStage.setTitle("Statystyki");
        //bookStage.initOwner(primaryStage);
        statStage.setScene(new Scene(rootLayout));
        statStage.showAndWait();
    }
}
