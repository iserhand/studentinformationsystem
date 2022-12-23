package com.oop.informationsystem.GUI;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    Scene scene;
    FXMLLoader fxmlLoader;

    @Override
    public void start(Stage stage) throws IOException {
        stage.getIcons().add(new Image("https://img.icons8.com/ios-glyphs/30/null/student-center.png"));
        fxmlLoader = new FXMLLoader(getClass().getResource("/login-view.fxml"));
        scene = new Scene(fxmlLoader.load());
        stage.setTitle("Log-in!");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}