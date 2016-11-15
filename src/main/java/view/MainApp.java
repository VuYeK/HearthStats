package view;/**
 * Created by VuYeK on 03.11.2015.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainApp extends Application {

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage) throws IOException {
        showRootLayout(primaryStage);
    }


    class WindowButtons extends HBox {

        public WindowButtons() {
            Button closeBtn = new Button("X");

            closeBtn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent actionEvent) {
                    Platform.exit();
                }
            });

            this.getChildren().add(closeBtn);
        }
    }


    public void showRootLayout(Stage primaryStage) throws IOException {
        Parent rootLayout = FXMLLoader.load(getClass().getResource("/RootLayout.fxml"));

        primaryStage.initStyle(StageStyle.TRANSPARENT);

        primaryStage.getIcons().add(new Image("http://i.imgur.com/2XYhhKJ.png"));
        primaryStage.setTitle("HearthStats");
        Scene scene = new Scene(rootLayout);
        scene.setFill(Color.TRANSPARENT);


        /**
         * Możliwość przenoszenia okna
         */

        final double[] xOffset = new double[1];
        final double[] yOffset = new double[1];
         /*
        The two following lambda expressions makes it possible to move the application without the standard StageStyle
         */
        //Lambda mouse event handler
        scene.setOnMousePressed(event -> {
            xOffset[0] = primaryStage.getX() - event.getScreenX();
            yOffset[0] = primaryStage.getY() - event.getScreenY();
        });
        //Lambda mouse event handler
        scene.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() + xOffset[0]);
            primaryStage.setY(event.getScreenY() + yOffset[0]);
        });





        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void showStatCharts() throws IOException {
        Parent rootLayout = FXMLLoader.load(getClass().getResource("/Statistics.fxml"));
        Stage statStage = new Stage();
        statStage.getIcons().add(new Image("http://i.imgur.com/2XYhhKJ.png"));
        statStage.setTitle("Statystyki");
        //bookStage.initOwner(primaryStage);
        statStage.setScene(new Scene(rootLayout));
        statStage.showAndWait();
    }
}
