package a22.sim203.tp3;

import a22.sim203.tp3.controller.MainWindow;
import a22.sim203.tp3.simulation.*;
import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Basic calculator/simulator app
 */
public class SimulationApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("fxml/MainWindow.fxml"));
        Parent root = fxmlLoader.load();
        ((MainWindow)fxmlLoader.getController()).setStage(primaryStage);
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(300);
        primaryStage.setHeight(600);
        primaryStage.setWidth(800);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
			System.out.println("Hello World !");
        launch();
    }


}
