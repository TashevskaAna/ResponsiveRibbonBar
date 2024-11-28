package com.example.ribbonbar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("com.example.ribbonbar.resource");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"), resourceBundle);
        Parent root = loader.load();

        Scene scene = new Scene(root, 900, 700);

        String css = getClass().getResource("style.css").toExternalForm();
        scene.getStylesheets().add(css);

        primaryStage.setScene(scene);
        primaryStage.setTitle(resourceBundle.getString("app.title"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
